package myforum.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_post", schema = "myForum", catalog = "")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("1")
public class Post
{
    private Integer postId;
    private Integer boardId;
    private Integer postType;
    private String postTitle;
    private String postText;
    private Timestamp createTime;
    private User user;
    private Topic topic;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    public Integer getPostId()
    {
        return postId;
    }

    public void setPostId(Integer postId)
    {
        this.postId = postId;
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
    @Column(name = "post_type")
    public Integer getPostType()
    {
        return postType;
    }

    public void setPostType(Integer postType)
    {
        this.postType = postType;
    }

    @Basic
    @Column(name = "post_title")
    public String getPostTitle()
    {
        return postTitle;
    }

    public void setPostTitle(String postTitle)
    {
        this.postTitle = postTitle;
    }

    @Basic
    @Column(name = "post_text")
    public String getPostText()
    {
        return postText;
    }

    public void setPostText(String postText)
    {
        this.postText = postText;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "topic_id")
    public Topic getTopic()
    {
        return topic;
    }

    public void setTopic(Topic topic)
    {
        this.topic = topic;
    }
}
