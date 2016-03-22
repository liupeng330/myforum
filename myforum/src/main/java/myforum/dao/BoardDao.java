package myforum.dao;

import myforum.domain.Board;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao extends BaseDaoHibernate4<Board>
{
    protected final String GET_BOARD_NUM = "select count(f.boardId) from Board f";

    public long getBoardNum() {
        return this.find(GET_BOARD_NUM).size();
    }
}

