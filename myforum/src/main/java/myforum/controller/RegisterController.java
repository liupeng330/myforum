package myforum.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myforum.domain.User;
import myforum.exception.UserExistException;
import myforum.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * <br>
 * <b>类描述:</b>
 *
 * <pre>
 * 用户注册的Action
 * </pre>
 *
 * @see
 * @since
 */
@Controller
public class RegisterController extends BaseController
{
    private Logger logger = Logger.getLogger(RegisterController.class);
    /**
     * 自动注入
     */
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param request
     * @param user
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, User user)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("/success");
        try
        {
            this.logger.info("开始注册用户");
            userService.register(user);
            this.logger.info("完成注册用户");
        }
        catch (UserExistException e)
        {
            this.logger.error("用户注册出错： ", e);
            view.addObject("errorMsg", "用户名已经存在，请选择其它的名字。");
            view.setViewName("forward:/register.jsp");
        }
        catch (Throwable e)
        {
            this.logger.error("用户注册出错： ", e);
        }
        setSessionUser(request, user);
        return view;
    }
}

