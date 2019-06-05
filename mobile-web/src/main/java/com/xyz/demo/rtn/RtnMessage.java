package com.xyz.demo.rtn;

import com.xyz.demo.enums.ErrorType;
import org.apache.commons.lang3.StringUtils;

/**
 * dubbo接口返回通用类型
 *
 * @param <T> t 为自定义返回内容根据业务需要自己定义
 * @author yuerfeng
 * @create 2018-6-22
 */
public class RtnMessage<T> {

    /**
     * 业务码  详见ErrorType
     */
    private String code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public RtnMessage() {
    }

    /**
     * 推荐使用RtnMessageUtils来构造，而不要自己去new RtnMessage
     *
     * @param errorType
     * @param data
     */
    public RtnMessage(ErrorType errorType, T data) {
        this.code = errorType.getErrorCode();
        this.message = errorType.getErrorMsg();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return StringUtils.equals(this.code, ErrorType.SUCCESS.getErrorCode());
    }
}
