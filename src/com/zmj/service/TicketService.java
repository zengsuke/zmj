package com.zmj.service;

import com.zmj.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> findTicketBySid(int sid) throws Exception;

    void addTicket(Ticket ticket);

    List<Ticket> findTicketByUid(int user_id) throws Exception;
}
