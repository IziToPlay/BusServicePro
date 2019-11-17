package com.cruzSolar.service;

import java.util.List;

import com.cruzSolar.model.entity.Seat;

public interface SeatService extends CrudService<Seat, Long>{
	
	List<Seat> findAllSeatsAvailables(Long bus_id) throws Exception;
	Seat findSeatbyBus(Long bus_id, Long seat_id) throws Exception;
	//void updateSeat(long id) throws Exception;
}
