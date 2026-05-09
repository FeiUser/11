package com.lyf.es.pojo.result;


import org.apache.commons.lang3.StringUtils;

/**
 * 返回值状态
 */
public enum HttpCode {

    /*********************    全局状态码     *******************/
    SUCCESS( 200 ,"Success","请求成功！"),
    ERROR( 500 ,"ERROR","请求失败！");

    private final Integer Code;
    private final String reasonPhrase;
    private final String Annotation;
    private HttpCode(Integer Code, String ReasonPhrase , String Annotation) {
        this.Code = Code;
        this.reasonPhrase = ReasonPhrase;
        this.Annotation = Annotation;
    }
    public Integer code() {
        return this.Code;
    }
    public String reasonPhrase() {
        return this.reasonPhrase;
    }
    public String annotation() {
        return this.Annotation;
    }
    public String annotation(String text) {
        return StringUtils.isNotEmpty(text) ?this.Annotation:this.Annotation+" "+text;
    }
    public String annotation(String text,String texts) {
        return StringUtils.isNotEmpty(text)?this.Annotation:this.Annotation+" "+text;
    }

}
