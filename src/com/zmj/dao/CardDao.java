package com.zmj.dao;

import com.zmj.entity.Card;

import java.util.List;

/**
 * vip卡
 */
public interface CardDao {
    List<Card> findCardById(int user_id) throws Exception;//查找卡

    boolean addCard(double money, int id);//添加卡

    boolean updateCardById(double money, int user_id);

    boolean reduceCardById(double movie_price, int user_id);
}
