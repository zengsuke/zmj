package com.zmj.dao;

import com.zmj.entity.BoxOffice;

import java.util.List;

public interface BoxDao {
    List<BoxOffice> findAllOffice();

    boolean findOfficeByMid(int movie_id) throws Exception;

    boolean updateOffice(int movie_id, int movie_count, double v);

    boolean insertOffice(int movie_id, int movie_count, double v);
}
