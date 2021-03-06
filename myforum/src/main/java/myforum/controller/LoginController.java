package myforum.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import myforum.cons.CommonConstant;
import myforum.domain.User;
import myforum.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * <br>
 * <b>类描述:</b>
 *
 * <pre>
 *   论坛管理，这部分功能由论坛管理员操作，包括：创建论坛版块、指定论坛版块管理员、
 * 用户锁定/解锁。
 * </pre>
 *
 * @see
 * @since
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController
{
    /**
     * 自动注入
     */
    @Autowired
    private UserService userService;

    /**
     * 用户登陆
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView login(HttpServletRequest request, User user)
    {
        User dbUser = userService.getUserByUserName(user.getUserName());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/login.jsp");

        if (dbUser == null)
        {
            mav.addObject("errorMsg", "用户名不存在");
        }
        else if (!dbUser.getPassword().equals(user.getPassword()))
        {
            mav.addObject("errorMsg", "用户密码不正确");
        }
        else if (dbUser.getLocked() == User.USER_LOCK)
        {
            mav.addObject("errorMsg", "用户已经被锁定，不能登录。");
        }
        else
        {
            dbUser.setLastIp(request.getRemoteAddr());
            dbUser.setLastVisit(new Timestamp(new Date().getTime()));
            userService.loginSuccess(dbUser);

            //将User对象放入到session中
            setSessionUser(request, dbUser);

            //从session中获取跳转到登陆页面前,用户所访问的URL
            String toUrl = (String)request.getSession().getAttribute(CommonConstant.LOGIN_TO_URL);
            //从session中移除此URL
            request.getSession().removeAttribute(CommonConstant.LOGIN_TO_URL);

            //如果当前会话中没有保存登录之前的请求URL，则直接跳转到主页
            if(StringUtils.isEmpty(toUrl))
            {
                toUrl = "/index.html";
            }

            //跳转到登陆页面前, 用户所访问的URL
            mav.setViewName("redirect:" + toUrl);
        }
        return mav;
    }

    /**
     * 登录注销
     * @param session
     * @return
     */
    @RequestMapping("/doLogout")
    public String logout(HttpSession session)
    {
        session.removeAttribute(CommonConstant.USER_CONTEXT);
        return "forward:/index.jsp";
    }
}

