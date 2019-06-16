package com.xyz.demo.aop;

import com.alibaba.fastjson.JSON;
import com.xyz.demo.exceptions.BadReturnTypeException;
import com.xyz.demo.exceptions.ServiceException;
import com.xyz.demo.rtn.RtnMessage;
import com.xyz.demo.utils.RtnMessageUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author yuerfeng 14090408 on 2018/10/16 17:53.
 * 对抛出异常的接口打印出堆栈信息
 * @copyright :@2018
 */
@Aspect
@Component("aopProcess")
public class AopProcess {
    private static Logger logger = LoggerFactory.getLogger(AopProcess.class);

    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
//    @AfterThrowing(value = "execution(* com.xyz.demo.controller..*.*(..))", throwing = "e")
//    public void afterThrowingMethod(JoinPoint jp, Throwable e) {
//        try {
//            String methodName = jp.getSignature().getName();
//            logger.error(methodName + " Exception", e);
//        } catch (Throwable ee) {
//            logger.error("AopProcess afterThrowingMethod Exception", ee);
//        }
//    }
//
//    @Pointcut("execution(* com.xyz.demo.controller..*.*(..))")
//    public void aspectRest() {
//    }
//
//    @Around("aspectRest()")
//    public RtnMessage around(ProceedingJoinPoint pjp) throws Throwable {
//        try {
//            //TODO：进行防止篡改校验
//            Object object = pjp.proceed(pjp.getArgs());
//            if (!(object instanceof RtnMessage)) {
//                printExceptionParam(pjp);
//                throw new BadReturnTypeException();
//            }
//            return (RtnMessage) object;
//        } catch (BadReturnTypeException e) {
//            logger.error("接口返回值不是RtnMessage类型", e);
//            printExceptionParam(pjp);
//            return RtnMessageUtils.buildFailed("接口返回类型不合法");
//        } catch (ConstraintViolationException e) {
//            logger.error("接口参数校验失败", e);
//            StringBuilder buffer = new StringBuilder();
//            for (ConstraintViolation violation : e.getConstraintViolations()) {
//                if (buffer.length() > 0) {
//                    buffer.append(",");
//                }
//                buffer.append(violation.getMessage());
//            }
//            printExceptionParam(pjp);
//            return RtnMessageUtils.paramValidateFailed(buffer.toString());
//        } catch (ServiceException e) {
//            logger.error("ServiceException", e);
//            RtnMessage rtn = RtnMessageUtils.buildFailed(null);
//            rtn.setMessage(e.getMessage());
//            printExceptionParam(pjp);
//            return rtn;
//        } catch (Exception e) {
//            logger.error("Exception", e);
//            printExceptionParam(pjp);
//            return RtnMessageUtils.buildFailed("系统内部错误");
//        }
//    }

    private void printExceptionParam(ProceedingJoinPoint pjp) {
        logger.error("===================Exception Msg Begin=============");
        logger.error("method name:{}", pjp.getSignature().toShortString());
        Object body = findRequestBody(pjp);
        if(body != null){
            logger.error("method requestBody:{}", JSON.toJSONString(body));
        }
        logger.error("===================Exception Msg End=============");
    }

    private Object findRequestBody(ProceedingJoinPoint pjp){
        Class<?> classTarget = pjp.getTarget().getClass();
        Object[] args = pjp.getArgs();
        try{
            Method objMethod = classTarget.getMethod(pjp.getSignature().getName(), ((MethodSignature) pjp.getSignature()).getParameterTypes());
            Annotation[][] paraAnnotataion = objMethod.getParameterAnnotations();
            int i = 0;
            for (Annotation[] oneParamAnnos : paraAnnotataion) {
                for (Annotation anno : oneParamAnnos) {
                    String a = anno.annotationType().getName();
                    String b = RequestBody.class.getName();
                    if (a.equals(b) && args.length>= i+1) {
                        return args[i];
                    }
                }
                i++;
            }
        }catch (Exception e){
            logger.error("printExceptionParam Exception",e);
        }
        return null;
    }
}
