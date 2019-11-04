package com.cruzSolar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cruzSolar.model.entity.Coupon;
import com.cruzSolar.model.repository.CouponRepository;

import com.cruzSolar.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public List<Coupon> getAll() throws Exception {		
		
		//List<Coupon> coupons = new ArrayList<>();
		//couponRepository.findAll().iterator().forEachRemaining(coupons::add);
		//return coupons
		return couponRepository.findAll();
		
	}
	
	@Override
	public List<Coupon> fetchCouponById(long id) throws Exception {
		return couponRepository.fetchCouponById(id);
	}

	@Override
	public Long create(Coupon entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long id, Coupon entity) throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Transactional
	@Override
	public void updateStatus(Long id) throws Exception {
		Coupon currentCoupon=getOneById(id);
		currentCoupon.setStatus(true);
		couponRepository.save(currentCoupon);
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coupon getOneById(Long id) throws Exception {
	   return couponRepository.findById(id).orElseThrow(() -> new RuntimeException("Coupon Not Found!"));
	}

	@Override
	public List<Coupon> fetchCouponBySpecial(String specialCode) throws Exception {
		return couponRepository.fetchCouponBySpecial(specialCode);
	}	
	
}


