package com.cruzSolar.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cruzSolar.model.entity.Seat;


@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{
	
	@Query("select s from Seat s where s.bus.id=?1  and s.id NOT IN (select s1.id from Ticket t inner join Seat s1 on t.seat.id=s1.id)")
	List<Seat> findAllSeatsAvailables(Long bus_id);
}
