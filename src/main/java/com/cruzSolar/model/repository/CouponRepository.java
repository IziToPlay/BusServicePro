package com.cruzSolar.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

	//Page<Coupon> findAll(Pageable pageable);
	
}
