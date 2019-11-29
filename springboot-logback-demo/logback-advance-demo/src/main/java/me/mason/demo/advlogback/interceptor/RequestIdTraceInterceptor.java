package me.mason.demo.advlogback.interceptor;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 拦截器，添加request-id
 * @Author Mason
 * @Since 2019/11/28
 **/
@Slf4j
@Component
public class RequestIdTraceInterceptor implements HandlerInterceptor {

    public static final String REQUEST_ID_KEY = "request-id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.put(REQUEST_ID_KEY, getRequestId(request));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //把requestId添加到响应头，以便其它应用使用
        response.addHeader(REQUEST_ID_KEY, MDC.get(REQUEST_ID_KEY));
        //请求完成，从MDC中移除requestId
        MDC.remove(REQUEST_ID_KEY);
    }

    /**
     * 根据请求参数或请求头判断是否有“request-id”，有则使用，无则创建
     *
     * @param request
     * @return 返回x-request-id的唯一标识
     */
    public static String getRequestId(HttpServletRequest request) {
        String requestId;
        String parameterRequestId = request.getParameter(REQUEST_ID_KEY);
        String headerRequestId = request.getHeader(REQUEST_ID_KEY);

        if (parameterRequestId == null && headerRequestId == null) {
            log.debug("no request-id in request parameter or header");
            requestId = IdUtil.simpleUUID();
        } else {
            requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
        }

        return requestId;
    }
}
