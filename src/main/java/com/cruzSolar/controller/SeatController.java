package com.cruzSolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cruzSolar.model.entity.Bus;
import com.cruzSolar.model.entity.Seat;
import com.cruzSolar.service.SeatService;

@Controller
@RequestMapping("/seats")
public class SeatController {

	@Autowired
	private SeatService seatService;
	private String hola;
	
	@GetMapping
    public String showAllSeats(Model model, Long bus_id) throws Exception {
        model.addAttribute("seats", seatService.findAllSeatsAvailables(bus_id));
        return "tickets/new";
    }
	
}
