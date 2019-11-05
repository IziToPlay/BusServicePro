package com.cruzSolar.service;

import java.util.List;
import com.cruzSolar.model.entity.Coupon;

public interface CouponService extends CrudService<Coupon, Long>{
	
	List<Coupon> fetchCouponById(long id) throws Exception;
	
	List<Coupon> fetchCouponBySpecial(String specialCode) throws Exception;

	void updateStatus(Long id) throws Exception;

	Coupon fetchCouponBySpe(String specialCode) throws Exception;
}

