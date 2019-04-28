package com.zmj.view;

import com.zmj.entity.Cinema;
import com.zmj.entity.Movie;
import com.zmj.entity.Session;
import com.zmj.entity.Ticket;
import com.zmj.service.CinemaService;
import com.zmj.service.MovieService;
import com.zmj.service.SessionService;
import com.zmj.service.TicketService;
import com.zmj.service.serviceImpl.*;
import com.zmj.util.InputUtil;

import java.util.*;

public class CustomerBuyView {
    private int user_id = MainView.id;
    private CinemaService cinemaService;
    private MovieService movieService;
    private SessionService sessionService;
    private CustomerTiketView customerTiketView;
    private TicketService ticketService;
    private int lottery_id=0;

    public int getLottery_id() {
        return lottery_id;
    }

    public void setLottery_id(int lottery_id) {
        this.lottery_id = lottery_id;
    }

    public CustomerBuyView() {
        cinemaService = new CinemaServiceImpl();
        movieService = new MovieServiceImpl();
        sessionService = new SessionServiceImpl();
        ticketService=new TicketServiceImpl();
    }

    /**
     * 购买界面
     */
    public void buy() {
        while (true) {
            System.out.println("请输入你的选择：");
            System.out.println("1、选择电影 2、查看热门电影 3、推荐电影 4、抽奖免单 5、搜索电影 0、返回");
            Scanner input = new Scanner(System.in);
            int choice = InputUtil.getInputByInt(input);
            switch (choice) {
                case 1:
                    choiceMovie();//选择电影
                    lottery_id=0;//清空一次免单机会
                    break;
                case 2:
                    hotMovie();//前三
                    break;
                case 3:
                    guessMovie();//种类
                    break;
                case 4:
                    lottery();//抽免单
                    break;
                case 5:
                    findMovie();//查看电影是否上映
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入有误，请重新输入！");
                    break;
            }
        }
    }

    /**
     * 查询电影
     */
    private void findMovie() {
        System.out.println("请输入你要查询的电影名字：");
        Scanner input=new Scanner(System.in);
        String name=InputUtil.getInputByString(input);
        try {
            if(movieService.findMovieByName(name)!=null){
                System.out.println("此电影基本信息如下：\n"+movieService.findMovieByName(name));
            }else
                System.out.println("此电影目前还会上线，敬请期待！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 抽奖
     */
    private void lottery() {//抽奖
        if(lottery_id==1){
            System.out.println("您已中过一次奖，下次光临~！");
        }else {
            Random random=new Random();
            int i=random.nextInt(10);
            if(i==5){
                System.out.println("恭喜你抽取到一次免单机会!");
                setLottery_id(1);//抽中
            }else{
                System.out.println("您未抽中，谢谢光临。");
                setLottery_id(0);//未抽中
            }
        }
    }

    /**
     * 猜你喜欢，根据ticket_type与movie_type进行匹配
     */
    private void guessMovie() {
        try {
            List<Ticket> tickets=ticketService.findTicketByUid(user_id);
            int[] type=new int[tickets.size()];
            for (int i = 0; i <tickets.size(); i++) {
                type[i]=tickets.get(i).getTicket_type();
            }
            Random random=new Random();
            if(type.length !=0) {
                int i = random.nextInt(type.length);//随机抽取种类
                List<Movie> movies = movieService.findMovieByType(type[i]);
                System.out.println("可能喜欢的电影类型：");
                for (Movie m : movies) {
                    System.out.println(m);
                }
            }else {
                System.out.println("您还未购票，无法推荐~");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 选择电影
     */
    private void choiceMovie() {
        AllMovie();
        System.out.println("请输入你想要去看的电影编号：");//movie
        Scanner input = new Scanner(System.in);
        int mid = InputUtil.getInputByInt(input);
        try {
            if (movieService.findMovieById(mid) != null) {
                if(findSession(mid)){
                    sessionService.deleteSessionByTime();
                    System.out.println("请输入你想要去的电影院编号：");//cinema
                    Cinema cinema = new Cinema();
                    Scanner input1 = new Scanner(System.in);
                    int cinema_id = InputUtil.getInputByInt(input1);
                    cinema.setCinema_id(cinema_id);
                    try {
                        if (cinemaService.findCinemaById(cinema) != null) {
                            choiceHall(cinema_id,mid);
                        } else
                            System.out.println("没有此编号的电影院，请重新选择！");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else
                System.out.println("目前没有此电影，请重新选择！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找到场次
     * @param mid
     * @return
     */
    private boolean findSession(int mid) {
        try {
            if(sessionService.findSessionBymovieid(mid).size()>0){
                System.out.println("现有电影院在播放的有：");
                for (Session s:sessionService.findSessionBymovieid(mid)) {
                    String movie_name=movieService.findMovieById(s.getMovie_id()).getMovie_name();
                    Cinema cinema=new Cinema();
                    cinema.setCinema_id(s.getCinema_id());
                    String cinema_name=cinemaService.findCinemaById(cinema).getCinema_name();
                    System.out.println("【电影名="+movie_name+", 电影院id="+s.getCinema_id()+",电影院名="+cinema_name
                            +", 场厅id="+s.getHall_id()+", 票价="+s.getMovie_price()+"】");
                }
                return true;
            }else{
                System.out.println("目前此电影还未安排场次！");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 选择场次
     * @param cid
     * @param mid
     */
    private void choiceHall(int cid, int mid) {
        try {
            if(sessionService.findSessionByIdMid(cid,mid).size()>0){
                System.out.println("你选择场次是：");
                for (Session s:sessionService.findSessionByIdMid(cid,mid)) {
                    System.out.println(s);
                }
                System.out.println("请选择你想要的场次编号：");
                Scanner input=new Scanner(System.in);
                int sid=InputUtil.getInputByInt(input);
                if(sessionService.findSessionById(sid)){
                    customerTiketView=new CustomerTiketView(lottery_id);
                    customerTiketView.TicketCome(sid);
                }else
                    System.out.println("此场次相关内容不存在！");
            }else
                System.out.println("此电影院没有安排此电影的场次！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 所有影院
     */
    public void AllCinema() {
        try {
            if (cinemaService.allCinema().size() > 0) {
                System.out.println("电影院有：");
                for (Cinema c : cinemaService.allCinema()) {
                    System.out.println(c);
                }
            } else
                System.out.println("目前没有电影院！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 所有电影
     */
    public void AllMovie() {
        if (movieService.findAll().size() > 0) {
            System.out.println("电影有：");
            for (Movie m : movieService.findAll()) {
                System.out.println(m);
            }
        } else
            System.out.println("目前没有电影！");
    }

    /**
     * 热门电影，展示前三
     */
    public void hotMovie() {
        if (movieService.findAll().size() > 0) {
            System.out.println("热门的电影有：");
            List<Movie> movies = movieService.findAll();
            movies.sort(new Comparator<Movie>() {
                @Override
                public int compare(Movie o1, Movie o2) {
                    return o2.getMovie_count() - o1.getMovie_count();//降序
                }
            });
            if (movies.size()<=3){
                for (int i = 0; i <movies.size(); i++) {
                    System.out.println(movies.get(i).toString());
                }
            }else {
                for (int i = 0; i <3; i++) {
                    System.out.println(movies.get(i).toString());
                }
            }
        } else
            System.out.println("目前没有电影！");
    }
}
