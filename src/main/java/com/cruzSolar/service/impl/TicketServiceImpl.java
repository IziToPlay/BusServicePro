package com.cruzSolar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.cruzSolar.model.entity.Ticket;
import com.cruzSolar.model.repository.TicketRepository;
import com.cruzSolar.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	private double amountTicket = 0;
	private int counter = 0;

	@Transactional
	@Override
	public Long create(Ticket entity) throws Exception {

		ticketRepository.save(entity);
		return entity.getId();
	}

	@Transactional
	@Override
	public void update(Long id, Ticket entity) throws Exception {
		Ticket currentTicket = getOneById(id);

		// currentTicket.setClient(entity.getClient());
		currentTicket.setEmissionDate(entity.getEmissionDate());
		// currentTicket.setEmployee(entity.getEmployee());
		currentTicket.setSeat(entity.getSeat());
		// currentTicket.setTrip(entity.getTrip());
		ticketRepository.save(currentTicket);
	}

	@Transactional
	@Override
	public void updateCondition(Long id) throws Exception {
		Ticket currentTicket = getOneById(id);
		currentTicket.setCondition(true);
		ticketRepository.save(currentTicket);
	}

	@Transactional
	@Override
	public void delete(Long id) throws Exception {
		ticketRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Ticket> getAll() {
		return ticketRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Ticket getOneById(Long id) throws Exception {
		return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket Not Found!"));
	}

	@Override
	public List<Ticket> fetchTicketByFechaEmission(String emision) {

		return ticketRepository.fetchTicketByFechaEmission(emision);
	}

	@Override
	public List<Ticket> getAllReservedTickets() {
		return ticketRepository.getAllReservedTickets();
	}

	@Override
	public List<Ticket> getAllBoughtTickets() {
		return ticketRepository.getAllBoughtTickets();
	}

	@Override
	public void udpatePrice(Long id, Double priceCon) throws Exception {
		Ticket currentTicket = getOneById(id);
		currentTicket.setPrice(priceCon);
		ticketRepository.save(currentTicket);
	}

	@Override
	public List<Ticket> fetchTicketById(long id) throws Exception {
		return ticketRepository.fetchTicketById(id);
	}

	@Override
	public double calculateAmountTickets() {
		amountTicket = 0;
		
			List<Ticket> ticketReserved=getAllReservedTickets();
			for (long i = 1; i <= (long) getAllReservedTickets().size(); i++) {
				int index=(int)i-1;
				amountTicket += ticketReserved.get(index).getPrice();
			}
		
			return amountTicket;
	}

}
