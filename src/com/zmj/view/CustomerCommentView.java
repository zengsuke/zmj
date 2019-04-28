package com.zmj.view;

import com.zmj.entity.Comment;
import com.zmj.service.CommentService;
import com.zmj.service.MovieService;
import com.zmj.service.serviceImpl.CommentServiceImpl;
import com.zmj.service.serviceImpl.MovieServiceImpl;
import com.zmj.util.InputUtil;

import java.util.Scanner;

/**
 * 评论界面
 */
public class CustomerCommentView {
    private MovieService movieService;
    private CommentService commentService;
    private ManagerMovieView managerMovieView;

    public CustomerCommentView() {
        movieService=new MovieServiceImpl();
        commentService=new CommentServiceImpl();
        managerMovieView=new ManagerMovieView();
    }

    public void commentWelcome(int user_id) {
        System.out.println("欢迎来到影评界面：");
        while (true){
            Scanner input=new Scanner(System.in);
            System.out.println("请输入你的选择：");
            System.out.println("1、查看影评2、写影评0、返回");
            int choice= InputUtil.getInputByInt(input);
            switch (choice){
                case 1:
                    findComment();
                    break;
                case 2:
                    takeComment(user_id);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误请重新输入！");
                    break;
            }
        }

    }

    /**
     * 写影评
     * @param user_id
     */
    private void takeComment(int user_id) {

        try {
            managerMovieView.findAllMovie();
            Scanner input=new Scanner(System.in);
            System.out.println("请输入你要评论的电影id:");
            int mid=InputUtil.getInputByInt(input);
            if(movieService.findMovieById(mid)!= null){
                System.out.println("请对这部电影进行影评：");
                String comment_content=InputUtil.getInputByString(input);
                Comment comment=new Comment();
                comment.setUser_id(user_id);
                comment.setComment_content(comment_content);
                comment.setMovie_id(mid);
                if(commentService.insertComment(comment)){
                    System.out.println("评论成功");
                }else
                    System.out.println("评论失败！");
            }else
                System.out.println("你输入的电影id并不存在！");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查找影评
     */
    private void findComment() {
        try {
            managerMovieView.findAllMovie();
            Scanner input=new Scanner(System.in);
            System.out.println("请输入你要查询的电影id:");
            int mid=InputUtil.getInputByInt(input);
            if(movieService.findMovieById(mid)!= null){
                if (commentService.findCommentByMid(mid).size()>0){
                    for (Comment c:commentService.findCommentByMid(mid)) {
                        System.out.println(c);
                    }
                }else
                    System.out.println("此电影目前还未有人评论！");
            }else
                System.out.println("你输入的电影id并不存在！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
