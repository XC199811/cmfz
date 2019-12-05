package com.xc.aspect;

import com.xc.annotation.LogAnnotation;
import com.xc.dao.LogDao;
import com.xc.entity.Admin;
import com.xc.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lenovo on 2019/11/29.
 */
@Aspect
@Configuration
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogDao logDao;
    @Around("@annotation(com.xc.annotation.LogAnnotation)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){
        /*
        * 谁 时间 事件 成功与否
        * */
        Log log=new Log();
        //谁在
        HttpSession session=request.getSession();
        Admin admin= (Admin) session.getAttribute("admin");
        log.setUser(admin.getUsername());
        //时间
        Date date=new Date();
        log.setTime(date);
        //获取方法名
        String doo=proceedingJoinPoint.getSignature().getName();
        log.setDoo(doo);
        //获取注解信息
        MethodSignature signature= (MethodSignature) proceedingJoinPoint.getSignature();
        LogAnnotation annotation = signature.getMethod().getAnnotation(LogAnnotation.class);
        String value=annotation.value();
        log.setDoo(value);
        log.setId(UUID.randomUUID().toString());
        try {
            Object o=proceedingJoinPoint.proceed();
            log.setSuccess("success");
            logDao.insert(log);
            return o;
        } catch (Throwable throwable) {
            log.setSuccess("error");
            logDao.insert(log);
            throwable.printStackTrace();
            return null;
        }
    }
}
