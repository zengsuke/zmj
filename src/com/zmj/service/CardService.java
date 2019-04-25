package com.zmj.service;

import com.zmj.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findCardById(int user_id) throws Exception;

    boolean addCard(double money,int user_id);

    boolean updateCardById(double money, int user_id);

    void reduceCardById(double movie_price, int user_id);
}
