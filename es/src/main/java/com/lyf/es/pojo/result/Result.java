package com.lyf.es.pojo.result;


import com.lyf.es.utils.DateUtils;

import java.io.Serializable;

/**
 * 返回值实体类
 */
public class Result implements Serializable {
    /**
     * 状态码
     */
    private Integer status;
    /**
     * 状态信息
     */
    private String message;
    /**
     * 业务数据
     */
    private Object data;
    /**
     * 时间戳
     */
    private String timestamp;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp= DateUtils.getNowDateTime();
    }

}
