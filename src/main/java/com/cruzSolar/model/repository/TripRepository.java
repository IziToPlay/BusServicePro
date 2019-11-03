package com.cruzSolar.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cruzSolar.model.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long>{
	
	@Query("select t from Trip t where t.dptDeparture.name like %?1% and t.dptArrival.name like %?2% and t.startDate like %?3%")
	List<Trip> fetchTripByDpt(String dptDeparture,String dptArrival, String startDate);
	
	@Query("select t from Trip t where t.dptArrival like %?1%")
	List<Trip> fetchTripByDptArrival(String dptArrival);
	
	
	//List<Trip> fetchTripByDptArrival(String depa1,String depa2, Date date) throws Exception;

}

