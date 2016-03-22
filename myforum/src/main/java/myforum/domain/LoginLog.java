package myforum.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_login_log", schema = "myForum", catalog = "")
public class LoginLog
{
    private Integer loginLogId;
    private String ip;
    private Timestamp loginDatetime;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_log_id")
    public Integer getLoginLogId()
    {
        return loginLogId;
    }

    public void setLoginLogId(Integer loginLogId)
    {
        this.loginLogId = loginLogId;
    }

    @Basic
    @Column(name = "ip")
    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    @Basic
    @Column(name = "login_datetime")
    public Timestamp getLoginDatetime()
    {
        return loginDatetime;
    }

    public void setLoginDatetime(Timestamp loginDatetime)
    {
        this.loginDatetime = loginDatetime;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
