package com.zmj.dao;

import com.zmj.entity.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> findTicketBySid(int sid) throws Exception;

    boolean addTicket(Ticket ticket);

    List<Ticket> findTicketByUid(int user_id) throws Exception;
}
