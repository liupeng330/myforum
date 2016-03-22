package myforum.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_topic", schema = "myForum", catalog = "")
public class Topic
{
    /**
     * 精华主题帖子
     */
    public static final int DIGEST_TOPIC = 1;
    /**
     * 普通的主题帖子
     */
    public static final int NOT_DIGEST_TOPIC = 0;

    private Integer topicId;
    private Integer boardId;
    private String topicTitle;
    private User user;
    private Timestamp createTime;
    private Timestamp lastPost;
    private Integer topicViews;
    private Integer topicReplies;
    private Integer digest = NOT_DIGEST_TOPIC;
    private MainPost mainPost = new MainPost();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    public Integer getTopicId()
    {
        return topicId;
    }

    public void setTopicId(Integer topicId)
    {
        this.topicId = topicId;
    }

    @Basic
    @Column(name = "board_id")
    public Integer getBoardId()
    {
        return boardId;
    }

    public void setBoardId(Integer boardId)
    {
        this.boardId = boardId;
    }

    @Basic
    @Column(name = "topic_title")
    public String getTopicTitle()
    {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle)
    {
        this.topicTitle = topicTitle;
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
    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_post")
    public Timestamp getLastPost()
    {
        return lastPost;
    }

    public void setLastPost(Timestamp lastPost)
    {
        this.lastPost = lastPost;
    }

    @Basic
    @Column(name = "topic_views")
    public Integer getTopicViews()
    {
        return topicViews;
    }

    public void setTopicViews(Integer topicViews)
    {
        this.topicViews = topicViews;
    }

    @Basic
    @Column(name = "topic_replies")
    public Integer getTopicReplies()
    {
        return topicReplies;
    }

    public void setTopicReplies(Integer topicReplies)
    {
        this.topicReplies = topicReplies;
    }

    @Basic
    @Column(name = "digest")
    public Integer getDigest()
    {
        return digest;
    }


    public void setDigest(Integer digest)
    {
        this.digest = digest;
    }

    @Transient
    public MainPost getMainPost() {
        return mainPost;
    }

    public void setMainPost(MainPost mainPost) {
        this.mainPost = mainPost;
    }
}
