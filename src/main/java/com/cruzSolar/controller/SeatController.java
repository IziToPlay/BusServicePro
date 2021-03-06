package com.cruzSolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import com.cruzSolar.service.SeatService;

@Controller
@RequestMapping("/seats")
public class SeatController {

	@Autowired
	private SeatService seatService;
	
	@GetMapping
    public String showAllSeats(Model model, Long bus_id,Long trip_id) throws Exception {
        model.addAttribute("seats", seatService.findAllSeatsAvailables(bus_id,trip_id));
        return "tickets/new";
    }
	
}
