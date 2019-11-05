package com.cruzSolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cruzSolar.model.entity.Coupon;
import com.cruzSolar.service.ClientService;
import com.cruzSolar.service.CouponService;
import com.cruzSolar.service.TripService;

@Controller
@RequestMapping("/coupons")
public class CouponController {


	@Autowired
	private CouponService couponService;
	private ClientService clientService;
	private TripService tripService;
    List<Coupon> couponSpecial;
	List<Coupon> couponsAdded;
	List<Coupon> coupons;
	private long i=0;
	
	public void account() throws Exception {
		i++;
		couponService.updateGiftStatus(i);
	}
	
	@GetMapping("/list")
    public String showAllCoupon(Model model) throws Exception {
		couponsAdded=couponService.couponsAddToList(i);
        model.addAttribute("coupons", couponsAdded);
        
        return "coupons/list";
    }
	
	@GetMapping("/searchCoupon")
	public String searchCoupon(@RequestParam("id") String id, Model model) throws Exception
	{
		model.addAttribute("coupons",searchCoupons(id, model));
		return "coupons/list";
	}
	
	public List<Coupon> searchCouponBySpecial(String special, Model model) throws Exception
	{
		couponSpecial=couponService.fetchCouponBySpecial(special);
		model.addAttribute("couponSpecial", couponSpecial);
		return couponSpecial;
	}
	
	public  List<Coupon> searchCoupons(String filtroid, Model model) {
		try {
		
			if(!filtroid.isEmpty()) {
				
			    long id = Long.parseLong(filtroid);
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
			    //model.addAttribute("coupons", couponService.getAll());				
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
	public List<Coupon> getCouponSpecial() {
		return couponSpecial;
	}
	public void setCouponSpecial(List<Coupon> couponSpecial) {
		this.couponSpecial = couponSpecial;
	}
	public CouponService getCouponService() {
		return couponService;
	}
	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	public TripService getTripService() {
		return tripService;
	}
	public void setTripService(TripService tripService) {
		this.tripService = tripService;
	}
	public List<Coupon> getCouponsAdded() {
		return couponsAdded;
	}
	public void setCouponsAdded(List<Coupon> couponsAdded) {
		this.couponsAdded = couponsAdded;
	}
	public long getI() {
		return i;
	}
	public void setI(long i) {
		this.i = i;
	}
	
}
