package com.cruzSolar.service;

import java.util.List;

import com.cruzSolar.model.entity.Trip;

public interface TripService extends CrudService<Trip, Long>{
	
	List<Trip> fetchTripByDptArrival(String dptArrival) throws Exception;
	
	List<Trip> fetchTripByDpt(String dptDeparture,String dptArrival,String startDate) throws Exception;
	
	
	//List<Trip> fetchTripByDptArrival(String depa1,String depa2, String date) throws Exception;
	
	
}

