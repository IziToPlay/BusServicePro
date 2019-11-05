package com.cruzSolar.service;

import java.util.List;

import com.cruzSolar.model.entity.Ticket;

public interface TicketService extends CrudService<Ticket, Long> {
	
	List<Ticket> fetchTicketByFechaEmission(String emision);
	
	List <Ticket> getAllReservedTickets();
	
	List <Ticket> getAllBoughtTickets();

	void updateCondition(Long id) throws Exception;
	
	void udpatePrice(Long id, Double priceDes)throws Exception;
	
	List <Ticket> fetchTicketById(long id) throws Exception;
}
