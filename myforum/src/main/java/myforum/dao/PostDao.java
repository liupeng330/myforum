package myforum.dao;

import myforum.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao extends BaseDaoHibernate4<Post>
{
    protected final String GET_PAGED_POSTS = "from Post where topic.topicId =? order by createTime desc";

    public Page getPagedPosts(int topicId, int pageNo, int pageSize)
    {
        return pagedQuery(GET_PAGED_POSTS, pageNo, pageSize, topicId);
    }

    /**
     * 删除主题下的所有帖子
     * @param topicId 主题ID
     */
    public void deleteTopicPosts(int topicId)
    {
        this.delete("topicId", topicId);
    }
}
