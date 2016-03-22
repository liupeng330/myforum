package myforum.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_board", schema = "myForum", catalog = "")
public class Board
{
    private Integer boardId;
    private String boardName;
    private String boardDesc;
    private Integer topicNum;
    private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "board_name")
    public String getBoardName()
    {
        return boardName;
    }

    public void setBoardName(String boardName)
    {
        this.boardName = boardName;
    }

    @Basic
    @Column(name = "board_desc")
    public String getBoardDesc()
    {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc)
    {
        this.boardDesc = boardDesc;
    }

    @Basic
    @Column(name = "topic_num")
    public Integer getTopicNum()
    {
        return topicNum;
    }

    public void setTopicNum(Integer topicNum)
    {
        this.topicNum = topicNum;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "manBoards", fetch = FetchType.LAZY)
    public Set<User> getUsers()
    {
        return users;
    }

    public void setUsers(Set<User> users)
    {
        this.users = users;
    }
}
