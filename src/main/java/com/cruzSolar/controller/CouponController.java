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
import com.cruzSolar.model.entity.Trip;
import com.cruzSolar.service.CouponService;

@Controller
@RequestMapping("/coupons")

public class CouponController {


	@Autowired
	private CouponService couponService;
    private Coupon coupon;
	List<Coupon> coupons;
	
	@GetMapping("/list")
    public String showAllCoupon(Model model) throws Exception {
        model.addAttribute("coupons", couponService.getAll());
        return "coupons/list";
    }

	@GetMapping("/searchCoupon")
	public String searchCoupon(@RequestParam("id") long id, Model model) throws Exception
	{
		model.addAttribute("coupons",searchCoupons(id, model));
		return "coupons/list";
	}
	
	public  List<Coupon> searchCoupons(long id, Model model) {
		try {
			
			
			String filtroID = Long.toString(id);
			
			if(!filtroID.isEmpty()) {
				model.addAttribute("info", "Búsqueda realizada correctamente");
				coupons=couponService.fetchCouponById(id);
				if(!coupons.isEmpty()) {
					model.addAttribute("coupons", coupons);
				}
				else {
						model.addAttribute("info", "No se han encontrado coincidencias");
						model.addAttribute("coupons",couponService.getAll());
				}
			}
			else {
				model.addAttribute("info", "Debe completar el campo de búsqueda.");
				model.addAttribute("coupons",couponService.getAll()); 
				
			}
		}   
	    catch(Exception e) 
		{
			model.addAttribute("Error Coupon:", e.getMessage());
		}
		return coupons;
	}
	
	

	
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	
	
}
