package com.justin4u.quartz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.justin4u.exception.ClientReportException;
import com.justin4u.util.BlockingQueueUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Lazy(false)
@Component
@EnableScheduling
public class SpringDynamicCronTask implements SchedulingConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(SpringDynamicCronTask.class);

    private static final String FIELD_SCHEDULED_FUTURES = "scheduledFutures";

    private static final String CRON_EXPRESSION = "CRON_EXPRESSION";

    private static final String UPDATE_CRON = "UPDATE_CRON";

    public static final String CRON_DEFAULT = "0 */10 * * * ?";

    public static final String SYS_RESOURCE_CLASS_NAME = "com.justin4u.data.transfer.service.impl.SynResourceServiceImpl";

    public static final String SYS_RESOURCE_METHOD_NAME = "getSynResource";


    private ScheduledTaskRegistrar taskRegistrar;

    private Set<ScheduledFuture<?>> scheduledFutures = null;

    private Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

//    @Resource(name = "synDataService")
//    private SynDataService synDataService;

//    @Resource(name = "synResourceService")
//    private SynResourceService synResourceService;

    private class ClientSynResource {
        /**
         * 基础表名
         */
        private String BASE_TABLE_NAME = "client_syn_resource";

        /**
         * 基础表名-中文
         */
        private static final String BASE_TABLE_NAME_CHI = "同步资源表";

        @JsonIgnore
        public String getTableName() {
            return BASE_TABLE_NAME.toLowerCase();
        }

        @JsonIgnore
        public String getTableNameChi() {
            return BASE_TABLE_NAME_CHI;
        }

        private static final long serialVersionUID = 1L;

        private Long id;

        private String subjectName;

        private String description;

        private Integer valid;

        private Integer priority;

        private Integer syncFrequency;

        private Integer syncType;

        private String cronExpression;

        private Integer updatePermission;

        private String updateCron;

        private Integer sqlType;

        private transient boolean persisted;

        public ClientSynResource() {

        }

        public Long getId() {
            return this.id;
        }

        public boolean isNew() {
            return this.id == null;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getSubjectName() {
            return this.subjectName;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return this.description;
        }

        public void setValid(Integer valid) {
            this.valid = valid;
        }

        public Integer getValid() {
            return this.valid;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public Integer getSyncFrequency() {
            return syncFrequency;
        }

        public void setSyncFrequency(Integer syncFrequency) {
            this.syncFrequency = syncFrequency;
        }

        public Integer getSyncType() {
            return syncType;
        }

        public void setSyncType(Integer syncType) {
            this.syncType = syncType;
        }

        public String getCronExpression() {
            return cronExpression;
        }

        public void setCronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
        }

        public Integer getUpdatePermission() {
            return updatePermission;
        }

        public void setUpdatePermission(Integer updatePermission) {
            this.updatePermission = updatePermission;
        }

        public String getUpdateCron() {
            return updateCron;
        }

        public void setUpdateCron(String updateCron) {
            this.updateCron = updateCron;
        }

        public void setPersisted(Boolean persisted) {
            this.persisted = persisted;
        }

        public Boolean getPersisted() {
            return this.persisted;
        }

        public Integer getSqlType() {
            return sqlType;
        }

        public void setSqlType(Integer sqlType) {
            this.sqlType = sqlType;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

    }

    /**
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.taskRegistrar = taskRegistrar;
        this.taskRegistrar.setTaskScheduler(taskScheduler());

        Runnable runnable = () -> {
            //List<ClientSynResource> synResourceList = synResourceService.getSynResourceList(null);
            List<ClientSynResource> synResourceList = new ArrayList<>();
            if (CollectionUtils.isEmpty(synResourceList)) {
                LOG.info("同步资源未配置，请检查表：client_syn_resource 是否存在有效记录");
                return;
            }
            LOG.info(String.format("Fetch client_syn_resource, size is %d.", synResourceList.size()));

            synResourceList.stream().forEach(synResource -> {
                String tableName = synResource.getSubjectName();
                if (StringUtils.isEmpty(tableName)) {
                    LOG.info("表名为空");
                    return;
                }
                // 增量cron表达式
                String cronExp = synResource.getCronExpression();
                if (!StringUtils.isEmpty(cronExp)) {
                    String taskId = tableName + "-cron_expression";
                    LOG.info(String.format("%s cronExp: %s", taskId, cronExp));
                    addTriggerTaskBySysResource(tableName, taskId, CRON_EXPRESSION);
                }

                // 变化cron表达式
                String updateCron = synResource.getUpdateCron();
                if (!StringUtils.isEmpty(updateCron)) {
                    // 变化的，需要覆盖原记录中的syncType值
                    synResource.setSyncType(SyncTypeEnum.CHANGE_SYNCHRONIZATION.getCode());
                    String taskId = tableName + "-update_cron";
                    LOG.info(String.format("%s updateCron: %s", taskId, updateCron));
                    addTriggerTaskBySysResource(tableName, taskId, UPDATE_CRON);
                }
            });
        };

        taskRegistrar.addCronTask(runnable, CRON_DEFAULT);

        runnable.run();
    }

    @Bean()
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        return scheduler;
    }

    private void addTriggerTaskBySysResource(String tableName, String taskId, String cronFieldName) {
        // 已存在的定时任务，不做处理，确保每个定时任务只加入一次。
        // 如果cron配置有变化，任务在执行前与计算下一次运行时间会从DB中取最新的记录。
        if (taskFutures.containsKey(taskId)) {
            LOG.debug(String.format("Task %s 已存在，跳过。", taskId));
            return;
        }

        TriggerTask triggerTask = new TriggerTask(() -> {
            LOG.info(String.format("DynamicCronTask %s is running...", taskId));

            ClientSynResource synResource = getSynResource(tableName);
            if (null == synResource) {
                LOG.info(String.format("DynamicCronTask %s can not be found in synResource", taskId));
                return;
            }
            try {
                //synDataService.getSynData(synResource, null);
            } catch (Exception e) {
                String cronExp = getCronFromSynResource(synResource, cronFieldName);
                String errorMsg = String.format("cron数据同步异常：Task-%s；cron-%s", taskId, cronExp);
                LOG.info(errorMsg);
                BlockingQueueUtil.produce(new ClientReportException(errorMsg, e));
            }
        }, triggerContext -> {
            ClientSynResource synResource = getSynResource(tableName);
            String cronExp = getCronFromSynResource(synResource, cronFieldName);
            if (StringUtils.isEmpty(cronExp)) {
                // 未找到资源，30秒后重试
                LocalDateTime nextTime = LocalDateTime.now().plusSeconds(30);
                return localDateTime2Date(nextTime).toInstant();
            }
            // 任务触发，可修改任务的执行周期
            CronTrigger trigger = new CronTrigger(cronExp);
            return trigger.nextExecutionTime(triggerContext).toInstant();
        });
        addTriggerTask(taskId, triggerTask);
    }

    public void addTriggerTask(String taskId, TriggerTask triggerTask) {
        if (taskFutures.containsKey(taskId)) {
            LOG.info(String.format("Task %s is already exist, about to replace it.", taskId));
            cancelTriggerTask(taskId);
        }
        TaskScheduler scheduler = taskRegistrar.getScheduler();
        ScheduledFuture<?> future = scheduler.schedule(triggerTask.getRunnable(), triggerTask.getTrigger());
        getScheduledFutures().add(future);
        taskFutures.put(taskId, future);
    }

    public void cancelTriggerTask(String taskId) {
        ScheduledFuture<?> future = taskFutures.get(taskId);
        if (null != future) {
            future.cancel(false);
        }
        taskFutures.remove(taskId);
        getScheduledFutures().remove(future);
    }

    private Set<ScheduledFuture<?>> getScheduledFutures() {
        if (scheduledFutures == null) {
            scheduledFutures = (Set<ScheduledFuture<?>>) getProperty(taskRegistrar, FIELD_SCHEDULED_FUTURES);
        }
        return scheduledFutures;
    }

    private static Object getProperty(Object obj, String propertyName) {
        Object value = null;
        Class clazz = obj.getClass();
        Field field = findField(clazz, propertyName);
        if (null != field) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            field.setAccessible(accessible);
        }
        return value;
    }

    private static Field findField(Class<?> clazz, String name) {
        try {
            return clazz.getField(name);
        } catch (NoSuchFieldException e) {
            return findDeclaredField(clazz, name);
        }
    }

    public static Field findDeclaredField(Class<?> clazz, String name) {
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException ex) {
            if (clazz.getSuperclass() != null) {
                return findDeclaredField(clazz.getSuperclass(), name);
            }
            return null;
        }
    }

    private static String getCronFromSynResource(ClientSynResource synResource, String cronFieldName) {
        String result = null;
        if (null == synResource) {
            return result;
        }
        if (CRON_EXPRESSION.equals(cronFieldName)) {
            result = synResource.getCronExpression();
        } else if (UPDATE_CRON.equals(cronFieldName)) {
            result = synResource.getUpdateCron();
        }

        return normalizeCronValue(result);
    }

    private static String normalizeCronValue(String localCron) {
        String result = localCron;
        if (StringUtils.isEmpty(localCron) || !isValidExpression(localCron) || Objects.equals(localCron, "99 * * * * ?")) {
            result = CRON_DEFAULT;
            LOG.info("cron_default:" + CRON_DEFAULT);
        }
        return result;
    }

    private static Date localDateTime2Date(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        return java.sql.Timestamp.valueOf(localDateTime);
    }


    public static boolean isValidExpression(String cronExpression){
        try {
            CronExpression exp = new CronExpression(cronExpression);
            Date date = new Date();
            // 循环得到接下来n此的触发时间点，供验证
            if(exp.getNextValidTimeAfter(date) != null){
                return true;
            }
        } catch (Exception e) {
            LOG.info("[CronUtil.isValidExpression]:failed. throw e");
            String errorMsg = "Cron表达式无效：" + cronExpression;
            BlockingQueueUtil.produce(new ClientReportException(errorMsg, e));
        }
        return false;
    }

    public ClientSynResource getSynResource(String subjectName) {
        Object returnResult = null;
        ClientSynResource synResource = null;
        try {
            // 获取Class对象
            Class sysResourceClass = Class.forName(SYS_RESOURCE_CLASS_NAME);
            // 传入类名和参数
            Method m = sysResourceClass.getDeclaredMethod(SYS_RESOURCE_METHOD_NAME, ClientSynResource.class);
            // 通过构造器实例化
            Object obj = sysResourceClass.getConstructor().newInstance();
            // 对obj对象，调用这个方法
            synResource = new ClientSynResource();
            synResource.setSubjectName(subjectName);
            returnResult = m.invoke(obj, synResource);
        } catch (Exception e) {
            LOG.info("[CronUtil.getSynResource]:failed. throw e:");
            String errorMsg = "CronUtil.getSynResource：" + subjectName;
            BlockingQueueUtil.produce(new ClientReportException(errorMsg, e));
        }
        if (null != returnResult) {
            ClientSynResource synResourceNew = (ClientSynResource) returnResult;
            return synResourceNew;
        }
        return null;
    }

}