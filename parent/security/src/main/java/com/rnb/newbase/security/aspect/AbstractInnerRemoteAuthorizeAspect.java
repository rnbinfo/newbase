package com.rnb.newbase.security.aspect;

import com.rnb.newbase.controller.api.HttpInnerRequest;
import com.rnb.newbase.controller.api.HttpInnerRequestHeader;
import com.rnb.newbase.exception.NewbaseExceptionConstants;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.security.aspect.config.RemoteClientConfig;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class AbstractInnerRemoteAuthorizeAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("app.remotes.client")
    private Map<String, RemoteClientConfig> remoteClientConfigs;

    public abstract void innerAuthorize();

    @Before("innerAuthorize()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestIp = attributes.getRequest().getRemoteAddr();
        // 从接口中获取用户loginToken
        if ("POST".equals(request.getMethod())) {
            // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
            if (joinPoint.getArgs().length > 0) {
                for (Object argObject : joinPoint.getArgs()) {
                    if (argObject instanceof HttpInnerRequest) {
                        HttpInnerRequestHeader requestHeader = ((HttpInnerRequest) argObject).getHeader();
                        RemoteClientConfig remoteClientConfig = remoteClientConfigs.get(requestHeader.getRequestSys());
                        if (remoteClientConfig == null) {
                            throw new RnbRuntimeException(NewbaseExceptionConstants.HTTP_INNER_REQUEST_NOT_EXISTED);
                        }
                        if (StringUtil.isBlank(requestIp) || !requestIp.equals(remoteClientConfig.getSourceIp())) {
                            throw new RnbRuntimeException(NewbaseExceptionConstants.HTTP_INNER_REQUEST_SOURCE_NOT_ALLOWED);
                        }
                    }
                }
            } else {
                throw new RnbRuntimeException("999500", "error.request");
            }
        }
    }
}
