package com.zmj.service;

import com.zmj.entity.Cinema;
import java.util.List;

public interface CinemaService {
    public List<Cinema> allCinema() throws Exception;//查找所有
    public Cinema findCinemaById(Cinema cinema) throws Exception;//通过id查找
    public boolean findCinemaByName(Cinema cinema) throws Exception;//查重
    public boolean addCinema(Cinema cinema);//添加
    public boolean deleteCinemaByNameAddress(Cinema cinema);//删除

    public void updateCinemaNById(int id, String name);
    public void updateCinemaAById(int id, String address);
}
