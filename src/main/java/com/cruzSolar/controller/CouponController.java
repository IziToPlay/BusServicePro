package com.cruzSolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cruzSolar.model.entity.Coupon;

import com.cruzSolar.service.CouponService;

@Controller
@RequestMapping("/coupons")

public class CouponController {


	@Autowired
	private CouponService couponService;

	List<Coupon> coupons;
	
	@GetMapping("/list")
    public String showAllCoupon(Model model) throws Exception {
        model.addAttribute("coupons", couponService.getAll());
        return "coupons/list";
    }

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	
	
}
