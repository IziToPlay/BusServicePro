package com.cruzSolar.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cruzSolar.model.entity.Coupon;
import com.cruzSolar.model.entity.Trip;

public interface CouponService extends CrudService<Coupon, Long>{

	//Page<Coupon> findAll(Pageable pageable);
	
	List<Coupon> fetchCouponById(long id) throws Exception;
	
}

