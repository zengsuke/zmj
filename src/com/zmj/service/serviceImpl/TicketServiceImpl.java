package com.zmj.service.serviceImpl;

import com.zmj.dao.TicketDao;
import com.zmj.dao.daoImpl.TickeDaoImpl;
import com.zmj.entity.Ticket;

import java.util.List;

public class TicketServiceImpl implements com.zmj.service.TicketService {
    private TicketDao ticketDao;

    public TicketServiceImpl() {
        ticketDao=new TickeDaoImpl();
    }

    @Override
    public List<Ticket> findTicketBySid(int sid) throws Exception {
        return ticketDao.findTicketBySid(sid);
    }

    @Override
    public void addTicket(Ticket ticket) {
        if(ticketDao.addTicket(ticket)){
            System.out.println("购买成功");
        }else
            System.out.println("购买失败！");
    }

    @Override
    public List<Ticket> findTicketByUid(int user_id) throws Exception {
        return ticketDao.findTicketByUid(user_id);
    }
}
