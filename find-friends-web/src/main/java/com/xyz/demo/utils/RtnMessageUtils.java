package com.xyz.demo.utils;


import com.xyz.demo.enums.ErrorType;
import com.xyz.demo.rtn.RtnMessage;

/**
 * 构造RtnMessage
 */
public class RtnMessageUtils {
    /**
     * 构造成功的结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RtnMessage<T> buildSuccess(T data) {
        RtnMessage<T> t = new RtnMessage<T>(ErrorType.SUCCESS, data);
        return t;
    }

    /**
     * 构造失败的结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RtnMessage<T> buildFailed(T data) {
        RtnMessage<T> t = new RtnMessage<T>(ErrorType.FAILED, data);
        return t;
    }

    public static <T> RtnMessage<T> buildFailed(String msg) {
        RtnMessage<T> t = new RtnMessage<T>(ErrorType.FAILED, null);
        t.setMessage(msg);
        return t;
    }

    /**
     * 构造失败的结果，包含信息的错误提示
     *
     * @param type
     * @param <T>
     * @return
     */
    public static <T> RtnMessage<T> buildError(ErrorType type) {
        RtnMessage<T> t = new RtnMessage<T>(type, null);
        return t;
    }

    public static <T> RtnMessage<T> buildResut(String code, String message, T data) {
        RtnMessage<T> rtnMessage = new RtnMessage();
        rtnMessage.setCode(code);
        rtnMessage.setMessage(message);
        rtnMessage.setData(data);
        return rtnMessage;
    }

    public static <T> RtnMessage<T> paramValidateFailed(String defaultMessage) {
        RtnMessage rtnMessage = new RtnMessage();
        rtnMessage.setCode(ErrorType.PARAMERROR.getErrorCode());
        rtnMessage.setMessage(defaultMessage);
        return rtnMessage;
    }
}
