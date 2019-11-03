package com.cruzSolar.service;

import java.util.List;
import com.cruzSolar.model.entity.Coupon;

public interface CouponService extends CrudService<Coupon, Long>{

	//Page<Coupon> findAll(Pageable pageable);
	
	List<Coupon> fetchCouponById(long id) throws Exception;
	
}

