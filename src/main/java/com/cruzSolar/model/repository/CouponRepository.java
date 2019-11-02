package com.cruzSolar.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.entity.Coupon;
import com.cruzSolar.model.entity.Trip;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

	@Query("select c from Coupon c where c.id = ?1")
	List<Coupon> fetchCouponById(long id);

	//Page<Coupon> findAll(Pageable pageable);
	
}
