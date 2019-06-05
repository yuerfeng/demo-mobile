package com.xyz.demo.enums;

/**
 * 统一错误码定义
 */
public enum ErrorType {
    /**
     * 缺省的成功
     */
    SUCCESS("0000", "成功"),
    /**
     * 失败
     */
    FAILED("9999", "失败"),
    /**
     * 错误:用户未登录
     */
    NEEDLOGIN("10001", "用户未登录"),
    /**
     * 错误:拒绝访问
     */
    UNAUTHORIZED("10002", "权限不足，拒绝访问"),
    /**
     * 错误:获取用户信息失败
     */
    GETUSERFAILED("10003", "获取用户信息失败"),
    /**
     * 错误:获取工号信息失败
     */
    USERNAMENOTFOUND("10004", "获取工号失败"),
    /**
     * 错误:获取工号信息失败
     */
    LOGINFAILED("10004", "登录失败"),
    /**
     * 错误，请求参数缺失
     */
    PARAMERROR("10005", "请求参数错误"),
    /**
     * 错误，没有收到任何文件
     */
    NOFILE("10006", "没有收到任何文件"),

    PARA_ERROR("10007","请求参数不正确"),
    /**
     * 导出数据太多
     */
    EXPORT_TOO_LARGE("10008","导出数据过多"),

    NOT_FOUND_CONTRACT("10009","加盟商特许经营合同没有录入"),

    NOT_AUDIT_CONTRACT("10009","加盟商特许经营合同没有审批完!"),;


    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    ErrorType(String c, String m) {
        this.errorCode = c;
        this.errorMsg = m;
    }
}
