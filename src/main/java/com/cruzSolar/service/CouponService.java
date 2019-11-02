package com.cruzSolar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cruzSolar.model.entity.Coupon;

public interface CouponService extends CrudService<Coupon, Long>{

	//Page<Coupon> findAll(Pageable pageable);
	
}

