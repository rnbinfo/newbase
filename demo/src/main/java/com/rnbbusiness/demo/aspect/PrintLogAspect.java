package com.rnbbusiness.demo.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class PrintLogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.rnbbusiness.newbase.aspect.annotation.PrintLog)")
    public void webLog() {
    }

//    @Around("webLog()")
//    public void handle(ProceedingJoinPoint joinPoint) throws Throwable {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String reqParam = preHandle(joinPoint,request);
//        // 记录下请求内容
//        System.out.println("URL : " + request.getRequestURL().toString());
//        System.out.println("HTTP_METHOD : " + request.getMethod());
//        System.out.println("IP : " + request.getRemoteAddr());
//        System.out.println("REQUEST：" + JSONObject.toJSONString(joinPoint.getArgs()));
//        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//        System.out.println("reqParam : " + reqParam);
//
//        Object result= joinPoint.proceed();
//        String respParam = postHandle(result);
//        System.out.println("respParam : " + respParam);
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) {
//        // 处理完请求，返回内容
//        System.out.println("方法的返回值 : " + ret);
//    }
//
//    private String preHandle(ProceedingJoinPoint joinPoint, HttpServletRequest request) {
//        String reqParam = "";
//        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method targetMethod = methodSignature.getMethod();
//        Annotation[] annotations = targetMethod.getAnnotations();
//        for (Annotation annotation : annotations) {
//            //此处可以改成自定义的注解
//            if (annotation.annotationType().equals(RequestMapping.class)
//                    || annotation.annotationType().equals(PostMapping.class)
//                    || annotation.annotationType().equals(GetMapping.class)
//                    || annotation.annotationType().equals(DeleteMapping.class)
//                    || annotation.annotationType().equals(PutMapping.class)
//                    || annotation.annotationType().equals(PatchMapping.class)) {
//                reqParam = JSON.toJSONString(request.getParameterMap());
//                break;
//            }
//        }
//        return reqParam;
//    }
//
//    /**
//     * 返回数据
//     * @param retVal
//     * @return
//     */
//    private String postHandle(Object retVal) {
//        if(null == retVal){
//            return "";
//        }
//        return JSON.toJSONString(retVal);
//    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 只记录post方法
        if ("POST".equals(request.getMethod())) {
            // 记录下请求内容
            logger.info("请求URL : " + request.getRequestURL());
            logger.info("请求IP : " + request.getRemoteAddr());
            logger.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

            // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
            if (joinPoint.getArgs().length > 0) {
                for (Object o : joinPoint.getArgs()) {
                    if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                        continue;
                    }
                    logger.info("请求参数 : " + JSON.toJSONString(o));
                }
            }
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        logger.info("返回 : " + JSON.toJSONString(ret));
    }
}
