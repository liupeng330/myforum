package myforum.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import myforum.domain.User;
import org.apache.commons.lang3.StringUtils;
import static myforum.cons.CommonConstant.*;

public class ForumFilter implements Filter
{
    private static final String FILTERED_REQUEST = "@@session_context_filtered_request";

    // ① 不需要登录即可访问的URI资源
    private static final String[] INHERENT_ESCAPE_URIS = { "/index.jsp",
            "/index.html", "/login.jsp", "/login/doLogin.html",
            "/register.jsp", "/register.html", "/board/listBoardTopics-",
            "/board/listTopicPosts-" };

    // 执行过滤
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        // 1 保证该过滤器在一次请求中只被调用一次
        if (request != null && request.getAttribute(FILTERED_REQUEST) != null)
        {
            // 放行此请求, 不做拦截
            chain.doFilter(request, response);
        }
        else
        {
            // 2 设置过滤标识，防止一次请求多次过滤
            request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            User userContext = getSessionUser(httpRequest);

            // 3 用户未登录, 且当前URI资源需要登录才能访问
            if (userContext == null && isURINeedToLogin(httpRequest.getRequestURI(), httpRequest))
            {
                String toUrl = httpRequest.getRequestURL().toString();
                if (!StringUtils.isEmpty(httpRequest.getQueryString()))
                {
                    toUrl += "?" + httpRequest.getQueryString();
                }

                // 4 将用户的请求URL保存在session中，用于登录成功之后，跳到目标URL
                httpRequest.getSession().setAttribute(LOGIN_TO_URL, toUrl);

                // 5 拦截请求, 转发到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }

            // 放行此请求, 不做拦截
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    /**
     * 当前URI资源是否需要登录才能访问
     * @param requestURI
     * @param request
     * @return
     */
    private boolean isURINeedToLogin(String requestURI, HttpServletRequest request)
    {
        //判断当浏览器请求的是根目录"/"的时候, 不需要跳到登陆页面
        if (request.getContextPath().equalsIgnoreCase(requestURI)
                || (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
            return false;

        for (String uri : INHERENT_ESCAPE_URIS)
        {
            if (requestURI != null && requestURI.indexOf(uri) >= 0)
            {
                return false;
            }
        }
        return true;
    }

    protected User getSessionUser(HttpServletRequest request)
    {
        return (User) request.getSession().getAttribute(USER_CONTEXT);
    }

    @Override
    public void destroy()
    {
    }
}

