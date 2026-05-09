package com.lyf.es.pojo.result;


/**
 * 返回值封装实体
 */
public class HttpResult {

    public synchronized static Result success(Object data) {
        Result result = new Result();
        result.setStatus(HttpCode.SUCCESS.code());
        result.setMessage(HttpCode.SUCCESS.annotation());
        result.setData(data);
        return result;
    }

    public synchronized static Result success(String msg, Object data) {
        Result result = new Result();
        result.setStatus(HttpCode.SUCCESS.code());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public synchronized static Result success() {
        return success(null);
    }

    public synchronized static Result error(String msg) {
        Result result = new Result();
        result.setStatus(HttpCode.ERROR.code());
        result.setMessage(msg);
        result.setData(null);
        return result;
    }

    public synchronized static Result error(String msg, Object data) {
        Result result = new Result();
        result.setStatus(HttpCode.ERROR.code());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
}
