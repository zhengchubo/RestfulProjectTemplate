package com.justin4u.helper;

import com.justin4u.util.JacksonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * com.justin4u.helper
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-06-12</pre>
 */
public class MultipleThreadExample {

    private static final Logger LOG = LoggerFactory.getLogger(MultipleThreadExample.class);

    // 线程数
    private static final int threadNum = 10;

    private List runParallel(List<String> inputList) {
        List result = new ArrayList();
        // 线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(threadNum);
        // 计数器
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Future<List<ModelMap>>[] futures = new Future[threadNum];

        for (int i = 0; i <= threadNum - 1; i++) {
            int finalI = i;
            futures[i] = threadPool.submit(() -> {
                List<ModelMap> voList = null;
                try {
                    /*long offset = (count / threadNum) * finalI;
                    // 最后一个线程取剩余的量，否则取整，即(count / threadNum)
                    long subCount = (finalI == threadNum - 1) ? (count - offset) : (count / threadNum);
                    voList = doGetSqlList(userId, subCount, offset, enums, vo, selectColumns, params, insertColumns, targetTableName);*/
                } catch (Exception e) {
//                    LOG.error("生成SQL文件-多线程方法异常" + JSONObject.toJSONString(e));
                } finally {
                    countDownLatch.countDown();
                }
                return voList;
            });
        }

        //等待所有线程全部处理完毕
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LOG.error("生成SQL文件-线程await报错" + JacksonUtils.toJSONString(e));
        } finally {
            threadPool.shutdown();
        }

        for (int j = 0; j <= threadNum - 1; j++) {
            try {
                List<ModelMap> voList = futures[j].get();
                if (!CollectionUtils.isEmpty(voList)) {
                    result.addAll(voList);
                    LOG.info("多线程生成SQL文件, voList-" + voList.size());
                }
            } catch (Exception e) {
                LOG.error("生成SQL文件-处理线程组装数据报错" + JacksonUtils.toJSONString(e));
            }
        }
        LOG.info("多线程生成SQL文件，总数为：" + result.size());
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.addAll(null);
        System.out.println(list);
    }
}
