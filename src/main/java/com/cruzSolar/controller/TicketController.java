package com.cruzSolar.controller;

import java.util.List;

import javax.persistence.Access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.entity.Seat;
import com.cruzSolar.model.entity.Ticket;
import com.cruzSolar.model.entity.Trip;
import com.cruzSolar.service.ClientService;
import com.cruzSolar.service.SeatService;
import com.cruzSolar.service.TicketService;
import com.cruzSolar.service.TripService;

@Controller
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private ClientController clientController;
	
	private Client client;
	private Trip trip;
	List<Ticket> tickets ;
	
	@GetMapping("/list")
    public String showAllTickets(Model model) throws Exception {
        model.addAttribute("tickets", ticketService.getAll());
        return "tickets/list";
    }
	
	@GetMapping("/new/{id}")
	 public String newTicketForm(@PathVariable("id") long id, Model model) throws Exception{
		model.addAttribute("ticket", new Ticket());
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients",clients);
		trip=tripService.getOneById(id);
		List<Seat> seats = seatService.findAllSeatsAvailables(id);
		model.addAttribute("seats",seats);
		return "tickets/new";
	}
	
	@GetMapping("/searchClient")
	public String searchClient(@RequestParam("dni") String dni, Model model) throws Exception{
		model.addAttribute("ticket", new Ticket());
		List<Client> clients=clientController.searchClient(dni, model);
		model.addAttribute("clients", clients);
		List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		model.addAttribute("seats",seats);
		return "tickets/new";
	}
	
	@GetMapping("/connect/{id}")
	public String connectClient(@PathVariable("id") long id,Model model) throws Exception {
		model.addAttribute("ticket", new Ticket());
		client=clientService.getOneById(id);
		//List<Client> clients = clientService.getAll();
		model.addAttribute("clients",client);
		List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		model.addAttribute("seats",seats);
		model.addAttribute("success","Cliente seleccionado correctamente");
		
		return "tickets/new";
	}
	
	public List<Ticket> searchTickets(String fechaemision, Model model){
		
		try {	
			if(!fechaemision.isEmpty()) {
				tickets=ticketService.fetchTicketByFechaEmission(fechaemision);
				
			if(!tickets.isEmpty()) {
				model.addAttribute("tickets", tickets);
			}else {
				model.addAttribute("info", "No existen coincidencias");
				model.addAttribute("tickets",ticketService.getAll());
				}
			}else {
				model.addAttribute("info", "Debe completar el campo de búsqueda.");
				model.addAttribute("tickets",ticketService.getAll());
			}
		}catch(Exception e) {
			model.addAttribute("Error Ticket:", e.getMessage());
		}	
		return tickets;
	}
	
	@GetMapping("/searchTicket")
	public String searchTicket(@RequestParam("fechaemision") String fechaemision, Model model) throws Exception{
		
		model.addAttribute("ticket", new Ticket());
		List<Ticket>tickets= searchTickets(fechaemision, model);
		model.addAttribute("tickets", tickets);
		//List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		//model.addAttribute("seats",seats);
		return "tickets/list";
		
	}
	
	@PostMapping("/save")
    public String saveNewTicket(Ticket ticket, Model model) throws Exception {
		
		ticket.setTrip(trip);
		ticket.setClient(client);
        long id = ticketService.create(ticket);
        return "redirect:/trips/list";
    }
	
	@GetMapping("/edit/{id}")
    public String editTicketForm(@PathVariable("id") long id, Model model) throws Exception {
        
		Ticket ticket= ticketService.getOneById(id);
        List<Client> clients = clientService.getAll();
		model.addAttribute("clients",clients);
		List<Seat> seats = seatService.findAllSeatsAvailables(ticket.getTrip().getBus().getId());
		model.addAttribute("seats",seats);
		model.addAttribute("ticket",ticket);
		
        return "tickets/edit";
    }

	@PostMapping("/update/{id}")
    public String updateTicket(@PathVariable("id") long id, Ticket ticket,Model model) throws Exception {
        ticketService.update(id, ticket);
        model.addAttribute("success","Ticket actualizado correctamente");
        return "redirect:/tickets/list";    
    }
	
	@PostMapping("/delete/{id}")
	public void deleteTicket(@PathVariable("id") long id, Ticket ticket,Model model) throws Exception {
		ticketService.delete(id);
		model.addAttribute("success", "Ticket eliminado correctamente");
	}
	
	@PostMapping("/buy/{id}")
	public void buyTicket(@PathVariable("id") long id, Ticket ticket,Model model) throws Exception {
		ticketService.delete(id);
		model.addAttribute("success", "Ticket comprado correctamente");
	}
	
	@PostMapping("/selectTicketToDiscount/{id}")
	public void selectTicketToDiscount(@PathVariable("id") long id, Model model) throws Exception {
		Ticket ticket=ticketService.getOneById(id);
		model.addAttribute("success", "Ticket seleccionado para el descuento correctamente");
	}
	

	public TicketService getTicketService() {
		return ticketService;
	}

	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public SeatService getSeatService() {
		return seatService;
	}

	public void setSeatService(SeatService seatService) {
		this.seatService = seatService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}


}
