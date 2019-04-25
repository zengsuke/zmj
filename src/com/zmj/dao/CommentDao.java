package com.zmj.dao;

import com.zmj.entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findCommentByMid(int mid) throws Exception;

    boolean insertComment(Comment comment);
}
