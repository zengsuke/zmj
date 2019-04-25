package com.zmj.service.serviceImpl;

import com.zmj.dao.CommentDao;
import com.zmj.dao.daoImpl.CommentDaoImpl;
import com.zmj.entity.Comment;

import java.util.List;

public class CommentServiceImpl implements com.zmj.service.CommentService {
    private CommentDao commentDao;

    public CommentServiceImpl() {
        commentDao=new CommentDaoImpl();
    }

    @Override
    public List<Comment> findCommentByMid(int mid) throws Exception {
        return commentDao.findCommentByMid(mid);
    }

    @Override
    public boolean insertComment(Comment comment) {
        return commentDao.insertComment(comment);
    }
}
