package com.cruzSolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.entity.Coupon;
import com.cruzSolar.model.entity.Seat;
import com.cruzSolar.model.entity.Ticket;
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

	@GetMapping("/searchCoupon")
	public String searchCoupon(@RequestParam("code") long codigo, Model model) throws Exception{
		model.addAttribute("coupon", new Coupon());
		//List<Coupon> coupons=searchCoupon(codigo, model);
		model.addAttribute("coupons", coupons);
		return "coupons/list";
	}
	

	
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	
	
}
