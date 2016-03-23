package myforum.service;

import myforum.dao.LoginLogDao;
import myforum.dao.UserDao;
import myforum.domain.LoginLog;
import myforum.domain.User;
import myforum.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public void register(User user) throws UserExistException
    {
        User u = this.getUserByUserName(user.getUserName());
        if(u != null)
        {
            throw new UserExistException("用户名已经存在!!");
        }
        else
        {
            user.setCredit(100);
            user.setUserType(1);
            userDao.save(user);
        }
    }

    /**
     * 更新用户
     * @param user
     */
    public void update(User user)
    {
        userDao.update(user);
    }

    public User getUserByUserName(String userName)
    {
        return this.userDao.getUserByUserName(userName);
    }

    public User getUserById(Integer userId)
    {
        return this.userDao.get(userId);
    }

    public void lockUser(String userName)
    {
        User user = this.userDao.getUserByUserName(userName);
        user.setLocked(User.USER_LOCK);
        this.userDao.update(user);
    }

    public void unlockUser(String userName)
    {
        User user = this.userDao.getUserByUserName(userName);
        user.setLocked(User.USER_UNLOCK);
        this.userDao.update(user);
    }

    public List<User> queryUserByUserName(String userName)
    {
        return this.userDao.queryUserByUserName(userName);
    }

    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public void loginSuccess(User user) {
        user.setCredit( 5 + user.getCredit());
        LoginLog loginLog = new LoginLog();
        loginLog.setUser(user);
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDatetime(new Timestamp(new Date().getTime()));
        userDao.update(user);
        loginLogDao.save(loginLog);
    }
}
