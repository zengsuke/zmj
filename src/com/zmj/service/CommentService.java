package com.zmj.service;

import com.zmj.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentByMid(int mid) throws Exception;

    boolean insertComment(Comment comment);
}
