package com.xyz.demo.exceptions;

/**
 * 业务异常
 */
public class ServiceException extends Exception{
    public ServiceException(String message) {
        super(message);
    }
}
