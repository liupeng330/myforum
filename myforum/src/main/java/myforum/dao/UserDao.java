package myforum.dao;

import java.util.List;

import myforum.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDaoHibernate4<User>
{
    private final String GET_USER_BY_USERNAME = "from User u where u.userName = ?";
    private final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";

    /**
     * 根据用户名查询User对象
     * @param userName 用户名
     * @return 对应userName的User对象，如果不存在，返回null。
     */
    public User getUserByUserName(String userName)
    {
        List<User> users = find(GET_USER_BY_USERNAME, userName);
        if (users.size() == 0) {
            return null;
        }else{
            return users.get(0);
        }
    }

    /**
     * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
     * @param userName 用户名查询条件
     * @return 用户名前缀匹配的所有User对象
     */
    public List<User> queryUserByUserName(String userName)
    {
        return find(QUERY_USER_BY_USERNAME, userName + "%");
    }

}

