package com.zmj.dao.daoImpl;

import com.zmj.dao.BaseDao;
import com.zmj.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TickeDaoImpl implements com.zmj.dao.TicketDao {
    @Override
    public List<Ticket> findTicketBySid(int sid) throws Exception {
        String sql="select * from dvd_ticket where session_id=?";
        List<Object> list=new ArrayList<>();
        list.add(sid);
        Class<Ticket> cls=Ticket.class;
        return BaseDao.executeQuery(sql,cls,list);
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        String sql="insert into dvd_ticket(session_id,user_id,ticket_type,ticket_line,ticket_colume,ticket_price)values(?,?,?,?,?,?)";
        List<Object> list=new ArrayList<>();
        list.add(ticket.getSession_id());
        list.add(ticket.getUser_id());
        list.add(ticket.getTicket_type());
        list.add(ticket.getTicket_line());
        list.add(ticket.getTicket_colume());
        list.add(ticket.getTicket_price());
        return BaseDao.excuteUpdate(sql,list);
    }

    @Override
    public List<Ticket> findTicketByUid(int user_id) throws Exception {
        String sql="select * from dvd_ticket where user_id=?";
        List<Object> list=new ArrayList<>();
        list.add(user_id);
        Class<Ticket> cls=Ticket.class;
        return BaseDao.executeQuery(sql,cls,list);
    }
}
