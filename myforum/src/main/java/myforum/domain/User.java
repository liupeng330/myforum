package myforum.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Table(name = "t_user", schema = "myForum", catalog = "")
public class User
{
    /**
     *锁定用户对应的状态值
     */
    public static final int USER_LOCK = 1;
    /**
     * 用户解锁对应的状态值
     */
    public static final int USER_UNLOCK = 0;
    /**
     * 管理员类型
     */
    public static final int FORUM_ADMIN = 2;
    /**
     * 普通用户类型
     */
    public static final int NORMAL_USER = 1;


    private Integer userId;
    private String userName;
    private String password;
    private Integer userType = NORMAL_USER;
    private Integer locked;
    private Integer credit;
    private Timestamp lastVisit;
    private String lastIp;
    private String tUsercol;
    private Set<Board> manBoards;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    @Column(name = "user_type")
    public Integer getUserType()
    {
        return userType;
    }

    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }

    @Basic
    @Column(name = "locked")
    public Integer getLocked()
    {
        return locked;
    }

    public void setLocked(Integer locked)
    {
        this.locked = locked;
    }

    @Basic
    @Column(name = "credit")
    public Integer getCredit()
    {
        return credit;
    }

    public void setCredit(Integer credit)
    {
        this.credit = credit;
    }

    @Basic
    @Column(name = "last_visit")
    public Timestamp getLastVisit()
    {
        return lastVisit;
    }

    public void setLastVisit(Timestamp lastVisit)
    {
        this.lastVisit = lastVisit;
    }

    @Basic
    @Column(name = "last_ip")
    public String getLastIp()
    {
        return lastIp;
    }

    public void setLastIp(String lastIp)
    {
        this.lastIp = lastIp;
    }

    @Basic
    @Column(name = "t_usercol")
    public String gettUsercol()
    {
        return tUsercol;
    }

    public void settUsercol(String tUsercol)
    {
        this.tUsercol = tUsercol;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "t_board_manager", joinColumns = {@JoinColumn(name ="user_id" )}, inverseJoinColumns = {@JoinColumn(name = "board_id") })
    public Set<Board> getManBoards()
    {
        return manBoards;
    }

    public void setManBoards(Set<Board> manBoards)
    {
        this.manBoards = manBoards;
    }
}
