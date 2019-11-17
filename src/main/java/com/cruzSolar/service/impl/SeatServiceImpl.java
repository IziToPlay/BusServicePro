package com.cruzSolar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.entity.Seat;
import com.cruzSolar.model.repository.SeatRepository;
import com.cruzSolar.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public List<Seat> getAll() throws Exception {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public Long create(Seat entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long id_bus, Seat entity) throws Exception {
		  Seat seat = findSeatbyBus(id_bus, entity.getId());
		  seat.setBus(entity.getBus());
		  seat.setFloor(entity.getFloor());
		  seat.setNumber(entity.getNumber());
		  seat.setId(entity.getId());
		  seat.setAvailable(entity.getAvailable());
		  seatRepository.save(seat);	
	}
	
	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seat getOneById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seat> findAllSeatsAvailables(Long bus_id) throws Exception {
		return seatRepository.findAllSeatsAvailables(bus_id);
	}

	@Override
	public Seat findSeatbyBus(Long bus_id, Long seat_id) throws Exception {	
		return seatRepository.findSeatbyBus(bus_id, seat_id);
	}

	//@Override
	//public void updateSeat(long id) throws Exception {
		
	   // Seat seat =getOneById(id);
		//seat.setFloor(10);;
		//seatRepository.save(seat);
		
	//}

}
