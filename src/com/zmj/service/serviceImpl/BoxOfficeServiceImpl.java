package com.zmj.service.serviceImpl;

import com.zmj.dao.BoxDao;
import com.zmj.dao.daoImpl.BoxDaoImpl;
import com.zmj.entity.BoxOffice;

import java.util.List;

public class BoxOfficeServiceImpl implements com.zmj.service.BoxOfficeService {
    private BoxDao boxDao;

    public BoxOfficeServiceImpl() {
        boxDao=new BoxDaoImpl();
    }

    @Override
    public List<BoxOffice> findAllOffice() {
        return boxDao.findAllOffice();
    }

    @Override
    public boolean findOfficeByMid(int movie_id) throws Exception {
        return boxDao.findOfficeByMid(movie_id);
    }

    @Override
    public boolean updateOffice(int movie_id, int movie_count, double v) {
        return boxDao.updateOffice(movie_id,movie_count,v);
    }

    @Override
    public boolean insertOffice(int movie_id, int movie_count, double v) {
        return boxDao.insertOffice(movie_id,movie_count,v);
    }
}
