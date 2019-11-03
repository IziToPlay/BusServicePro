package com.cruzSolar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruzSolar.model.entity.Trip;
import com.cruzSolar.model.repository.TripRepository;
import com.cruzSolar.service.TripService;

@Service
public class TripServiceImpl implements TripService{

	@Autowired
	private TripRepository tripRepository;

	@Override
	public Long create(Trip entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long id, Trip entity) throws Exception {
		Trip currentTrip=getOneById(id);
		currentTrip.actualizarAsientos();
		tripRepository.save(currentTrip);
	}

	@Override
	public void delete(Long id) throws Exception {
		tripRepository.deleteById(id);
	}

	@Override
	public Trip getOneById(Long id) throws Exception {
		return tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip Not Found"));
	}

	@Override
	public List<Trip> getAll() throws Exception {
		return tripRepository.findAll();
	}

	@Override
	public List<Trip> fetchTripByDptArrival(String dptArrival) throws Exception {
		return tripRepository.fetchTripByDptArrival(dptArrival);
	}
	
	@Override
	public List<Trip> fetchTripByDpt(String dptDeparture,String dptArrival, String startDate) throws Exception {
		return tripRepository.fetchTripByDpt(dptDeparture,dptArrival, startDate);
	}

	/*@Override
	public List<Trip> fetchTripByDptArrival(String depa1, String depa2, String date) throws Exception {
		// TODO Auto-generated method stub
		
		Date dateTrips;
		//convert date ====> dateTrips
		
		
		
		return null;
	}*/
	
	

}

