package com.zmj.service;

import com.zmj.entity.BoxOffice;

import java.util.List;

public interface BoxOfficeService {
    List<BoxOffice> findAllOffice();

    boolean findOfficeByMid(int movie_id) throws Exception;

    boolean updateOffice(int movie_id, int movie_count, double v);

    boolean insertOffice(int movie_id, int movie_count, double v);
}
