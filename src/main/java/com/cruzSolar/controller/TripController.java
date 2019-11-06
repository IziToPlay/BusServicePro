package com.cruzSolar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cruzSolar.common.PageTripListPagination;
import com.cruzSolar.model.entity.Trip;
import com.cruzSolar.service.BusService;
import com.cruzSolar.service.DepartmentService;
import com.cruzSolar.service.TripService;

@Controller
@RequestMapping("/trips")
public class TripController {

	@Autowired
	private TripService tripService;
	
	@Autowired
	private BusService busService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PageTripListPagination pageTripListPagination;
	
	private Trip trip;
	List<Trip> trips;
	
	@GetMapping("/list")
	public ModelAndView getAllArticles(
			@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page
			) {
		ModelAndView modelAndView = 
				pageTripListPagination.initPaginationTrip(pageSize
						,page
						, "trips/list");
		return modelAndView;
	}
	
	public  List<Trip> searchTrip(String dptDeparture,String dptArrival,String startDate, Model model){
		try {
			if(!dptDeparture.isEmpty() && !dptArrival.isEmpty() && !startDate.isEmpty()) {
				model.addAttribute("info", "Búsqueda realizada correctamente");
				trips=tripService.fetchTripByDpt(dptDeparture,dptArrival,startDate);
			if(!trips.isEmpty()) {
				model.addAttribute("trips", trips);
			}else {
				model.addAttribute("info", "No se han encontrado coincidencias");
				model.addAttribute("trips",tripService.getAll());
				}
			}else {
				model.addAttribute("info", "Debe completar el campo de búsqueda.");
				model.addAttribute("trips",tripService.getAll());
			}
		}catch(Exception e) {
			model.addAttribute("Error Trip:", e.getMessage());
		}
		return trips;
	}
	
	@GetMapping("/searchTrip")
	public String searchTrips(@RequestParam("dptDeparture") String dptDeparture, @RequestParam("dptArrival") String dptArrival, @RequestParam("startDate") String startDate, Model model) throws Exception{
		model.addAttribute("trips",searchTrip(dptDeparture,dptArrival,startDate, model));
		return "trips/listbusqueda";
	}
	
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public TripService getTripService() {
		return tripService;
	}

	public void setTripService(TripService tripService) {
		this.tripService = tripService;
	}

	public BusService getBusService() {
		return busService;
	}

	public void setBusService(BusService busService) {
		this.busService = busService;
	}

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}

