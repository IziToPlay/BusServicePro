package com.cruzSolar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void update(Long id, Seat entity) throws Exception {
		// TODO Auto-generated method stub
		
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

<<<<<<< HEAD
=======
	@Override
	public void updateCondition(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

>>>>>>> ddbbdcf2b597e5838e3c3d38a3e70a0df846c901
}
