package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements com.zmj.dao.CommentDao {
    @Override
    public List<Comment> findCommentByMid(int mid) throws Exception {
       String sql="select * from dvd_comment where movie_id=?";
       List<Object> list=new ArrayList<>();
       list.add(mid);
       Class<Comment> cls=Comment.class;
       return BaseDao.executeQuery(sql,cls,list);
    }

    @Override
    public boolean insertComment(Comment comment) {
        String sql="insert into dvd_comment(user_id,movie_id,comment_content)values(?,?,?)";
        List<Object> list=new ArrayList<>();
        list.add(comment.getUser_id());
        list.add(comment.getMovie_id());
        list.add(comment.getComment_content());
        return BaseDao.excuteUpdate(sql,list);
    }
}
