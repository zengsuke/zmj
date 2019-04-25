package com.zmj.service.serviceImpl;

import com.zmj.dao.CardDao;
import com.zmj.dao.daoImpl.CardDaoImpl;
import com.zmj.entity.Card;
import com.zmj.service.CardService;

import java.util.List;

public class CardServiceImpl implements CardService {
    CardDao cardDao=new CardDaoImpl();
    @Override
    public List<Card> findCardById(int user_id) throws Exception {
        return cardDao.findCardById(user_id);
    }

    @Override
    public boolean addCard(double money,int id) {
        return cardDao.addCard(money,id);
    }

    @Override
    public boolean updateCardById(double money, int user_id) {
        return cardDao.updateCardById(money,user_id);
    }

    @Override
    public void reduceCardById(double movie_price, int user_id) {
        if(cardDao.reduceCardById(movie_price,user_id)){
            System.out.println();
        }else
            System.out.println("消费卡内值存在异常！");
    }
}
