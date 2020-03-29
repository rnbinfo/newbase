package com.rnb.newbase.security.resolver;

import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.security.config.constant.LoginConstant;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.service.SystemUserService;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;

@Component
public class GetUserResolver implements HandlerMethodArgumentResolver {
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        //如果该参数注解有@GetUser且参数类型是SystemUser
        return methodParameter.getParameterAnnotation(GetUser.class) != null &&
                methodParameter.getParameterType() == SystemUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 获取用户数据
        HttpServletRequest httpServletRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String sessionId = httpServletRequest.getSession().getId();
        String loginToken = stringRedisTemplate.opsForValue().get(LoginConstant.REDIS_SESSION_PREFIX + sessionId);
        if (StringUtil.isNotBlank(loginToken)) {
            String userId = stringRedisTemplate.opsForValue().get(LoginConstant.REDIS_LOGIN_TOKEN_PREFIX + loginToken);
            if (StringUtil.isNotBlank(userId)) {
                SystemUser systemUser = systemUserService.queryById(new BigInteger(userId));
                if (systemUser != null) {
                    return systemUser;
                }
            }
        }
        throw new RnbRuntimeException("999403", "user.not.existed");
    }
}
