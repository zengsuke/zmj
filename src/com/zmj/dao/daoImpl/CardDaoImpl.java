package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.dao.CardDao;
import com.zmj.entity.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDaoImpl implements CardDao {
    @Override
    public List<Card> findCardById(int user_id) throws Exception {
        String sql="select * from dvd_card where user_id=?";
        List<Object> list=new ArrayList<>();
        list.add(user_id);
        Class<Card> cls=Card.class;
        List<Card> cards= BaseDao.executeQuery(sql,cls,list);
        return cards;
    }

    @Override
    public boolean addCard(double money, int id) {
        String sql="insert into dvd_card(user_id,user_money)values(?,?)";
        List<Object> list=new ArrayList<>();
        list.add(id);
        list.add(money);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean updateCardById(double money, int user_id) {
        String sql="update dvd_card set user_money=user_money+? where user_id=?";
        List<Object> list=new ArrayList<>();
        list.add(money);
        list.add(user_id);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean reduceCardById(double movie_price, int user_id) {
        String sql="update dvd_card set user_money=user_money-? where user_id=?";
        List<Object> list=new ArrayList<>();
        list.add(movie_price);
        list.add(user_id);
        return BaseDao.excuteUpdate(sql,list);
    }
}
