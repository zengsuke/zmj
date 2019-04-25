package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.entity.BoxOffice;

import java.util.ArrayList;
import java.util.List;

public class BoxDaoImpl implements com.zmj.dao.BoxDao {
    @Override
    public List<BoxOffice> findAllOffice() {
        String sql="select * from dvd_boxoffice";
        Class<BoxOffice> cls=BoxOffice.class;
        return BaseDao.executeAll(sql,cls);
    }

    @Override
    public boolean findOfficeByMid(int movie_id) throws Exception {
        String sql="select * from dvd_boxoffice where movie_id=?";
        List<Object> list=new ArrayList<>();
        list.add(movie_id);
        Class<BoxOffice> cls=BoxOffice.class;
        if(BaseDao.executeQuery(sql,cls,list).size()>0){
            return true;
        }else
            return false;
    }

    @Override
    public boolean updateOffice(int movie_id, int movie_count, double v) {
        String sql="update dvd_boxoffice set movie_money=movie_money+? , movie_count=movie_count+? where movie_id=?";
        List<Object> list=new ArrayList<>();
        list.add(v);
        list.add(movie_count);
        list.add(movie_id);
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public boolean insertOffice(int movie_id, int movie_count, double v) {
        String sql="insert into dvd_boxoffice(movie_id,movie_count,movie_money)values(?,?,?)";
        List<Object> list=new ArrayList<>();
        list.add(movie_id);
        list.add(movie_count);
        list.add(v);
        return BaseDao.excuteUpdate(sql,list);
    }
}
