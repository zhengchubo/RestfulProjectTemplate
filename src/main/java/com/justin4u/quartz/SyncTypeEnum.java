package com.justin4u.quartz;


import com.justin4u.util.JacksonUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步类型枚举
 *
 */
public enum SyncTypeEnum {

    FULL_SYNCHRONIZATION("全量同步", 1),
    ADD_SYNCHRONIZATION("增量同步", 2),
    CHANGE_SYNCHRONIZATION("变化同步", 3);

    private String content;

    private Integer code;

    public String getContent() {
        return content;
    }

    public Integer getCode() {
        return code;
    }

    private SyncTypeEnum(String content) {
        this.content = content;
    }

    private SyncTypeEnum(String content, Integer code) {
        this.content = content;
        this.code = code;
    }

    public static SyncTypeEnum getEnum(Integer inputNumber) {
        SyncTypeEnum result = null;
        for (SyncTypeEnum gradeEnum : SyncTypeEnum.values()) {
            if (inputNumber == gradeEnum.getCode()) {
                result = gradeEnum;
                break;
            }
        }
        return result;
    }


    /**
     *将该枚举全部转化成List
     * @return
     */
    public static List<JSONObject> toJsonArray() {
        List<JSONObject> jsonList = new ArrayList<>();
        for (SyncTypeEnum e : SyncTypeEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("name", e.getContent());
            object.put("value", e.getCode().toString());
            jsonList.add(object);
        }
        return jsonList;
    }

    public static void main(String[] args)
    {
        List<JSONObject> aa  =  toJsonArray();
        System.out.println(JacksonUtils.toJSONString(aa));
    }

}
