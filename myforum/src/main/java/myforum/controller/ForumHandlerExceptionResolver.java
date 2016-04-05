package myforum.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理
 */
public class ForumHandlerExceptionResolver extends SimpleMappingExceptionResolver
{
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
    {
        httpServletRequest.setAttribute("ex", e);
        e.printStackTrace();
        return super.doResolveException(httpServletRequest, httpServletResponse, o, e);
    }
}
