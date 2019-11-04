package com.cruzSolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.entity.Coupon;
import com.cruzSolar.model.entity.Seat;
import com.cruzSolar.model.entity.Ticket;
import com.cruzSolar.model.entity.Trip;
import com.cruzSolar.service.ClientService;
import com.cruzSolar.service.CouponService;
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
	private CouponService couponService;

	@Autowired
	private ClientController clientController;

	@Autowired
	private CouponController couponController;

	private Client client;
	private Trip trip;
	private Trip tripp;
	List<Ticket> tickets;
	private double amountTicket;
	private int counter = 0;

	@GetMapping("/list")
	public String showAllTickets(Model model) throws Exception {
		model.addAttribute("tickets", ticketService.getAllReservedTickets());
		// calculareAmountTickets(model);
		return "tickets/list";
	}

	@GetMapping("/listBoughtTickets")
	public String showAllBoughtTickets(Model model) throws Exception {
		model.addAttribute("tickets", ticketService.getAllBoughtTickets());
		return "tickets/listBoughtTickets";
	}

	@GetMapping("/new/{id}")
	public String newTicketForm(@PathVariable("id") long id, Model model) throws Exception {
		model.addAttribute("ticket", new Ticket());
		client = new Client();
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		trip = tripService.getOneById(id);
		List<Seat> seats = seatService.findAllSeatsAvailables(id);
		model.addAttribute("seats", seats);
		return "tickets/new";
	}

	@GetMapping("/searchClient")
	public String searchClient(@RequestParam("dni") String dni, Model model) throws Exception {
		model.addAttribute("ticket", new Ticket());
		List<Client> clients = clientController.searchClient(dni, model);
		model.addAttribute("clients", clients);
		List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		model.addAttribute("seats", seats);
		return "tickets/new";
	}

	@GetMapping("/searchCoupon")
	public String searchCoupon(@RequestParam("special") String special, Model model) throws Exception {
		List<Coupon> coupons=couponController.searchCouponBySpecial(special, model);
        model.addAttribute("coupons", coupons);
	    return "tickets/payTicket";
	}

	@GetMapping("/connect/{id}")
	public String connectClient(@PathVariable("id") long id, Model model) throws Exception {
		model.addAttribute("ticket", new Ticket());
		client = clientService.getOneById(id);
		// List<Client> clients = clientService.getAll();
		model.addAttribute("clients", client);
		List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		model.addAttribute("seats", seats);
		model.addAttribute("success", "Cliente seleccionado correctamente");

		return "tickets/new";
	}

	public List<Ticket> searchTickets(String emissionDate, Model model) {

		try {
			if (!emissionDate.isEmpty()) {
				tickets = ticketService.fetchTicketByFechaEmission(emissionDate);

				if (!tickets.isEmpty()) {
					model.addAttribute("tickets", tickets);
				} else {
					model.addAttribute("info", "No existen coincidencias");
					model.addAttribute("tickets", ticketService.getAllReservedTickets());
				}
			} else {
				model.addAttribute("info", "Debe completar el campo de búsqueda.");
				model.addAttribute("tickets", ticketService.getAllReservedTickets());
			}
		} catch (Exception e) {
			model.addAttribute("Error Ticket:", e.getMessage());
		}
		return tickets;
	}

	@GetMapping("/searchTicket")
	public String searchTicket(@RequestParam("emissionDate") String emissionDate, Model model) throws Exception {

		model.addAttribute("ticket", new Ticket());
		List<Ticket> tickets = searchTickets(emissionDate, model);
		model.addAttribute("tickets", tickets);

		// List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		// model.addAttribute("seats",seats);
		return "tickets/list";

	}

	@PostMapping("/save")
	public String saveNewTicket(Ticket ticket, Model model) throws Exception {

		if (client.getId() != null) {
			ticket.setCondition(false);
			ticket.setPrice(trip.getPrice());
			ticket.setTrip(trip);
			ticket.setClient(client);
			long id = ticketService.create(ticket);
			return "redirect:/trips/list";
		} else {
			model.addAttribute("error", "Cliente no seleccionado");
			model.addAttribute("clients", clientService.getAll());
			return "tickets/new";
		}
	}

	@GetMapping("/payTicket/{id}")
	public String payTicket(@PathVariable("id") long id, Model model) throws Exception {

		Ticket ticket = ticketService.getOneById(id);
		model.addAttribute("clients", clientService.getAll());
		model.addAttribute("seats", seatService.findAllSeatsAvailables(ticket.getTrip().getBus().getId()));
		model.addAttribute("trips", tripService.getAll());
		model.addAttribute("ticket", ticket);
		return "tickets/payTicket";
	}

	@GetMapping("/edit/{id}")
	public String editTicketForm(@PathVariable("id") long id, Model model) throws Exception {

		Ticket ticket = ticketService.getOneById(id);
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		List<Seat> seats = seatService.findAllSeatsAvailables(ticket.getTrip().getBus().getId());
		model.addAttribute("seats", seats);
		model.addAttribute("ticket", ticket);

		return "tickets/edit";
	}

	@PostMapping("/update/{id}")
	public String updateTicket(@PathVariable("id") long id, Ticket ticket, Model model) throws Exception {
		ticketService.update(id, ticket);
		model.addAttribute("success", "Ticket actualizado correctamente");
		return "redirect:/tickets/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable("id") long id, Model model) throws Exception {
		amountTicket -= ticketService.getOneById(id).getPrice();
		ticketService.delete(id);
		model.addAttribute("success", "Ticket eliminado correctamente");
		return "redirect:/tickets/list";
	}

	@GetMapping("/buy/{id}")
	public String buyTicket(@PathVariable("id") long id, Model model) throws Exception {
		counter++;
		// update condition to TRUE
		ticketService.updateCondition(id);
		amountTicket -= ticketService.getOneById(id).getPrice();
		if (counter % 3 == 0) {
			couponController.addCoupon();
			model.addAttribute("info", "Cupón activado por compra de 3 tickets.");
		} else {
			model.addAttribute("success", "Ticket comprado correctamente");
		}
		return "redirect:/tickets/list";
	}
	
	@GetMapping("/updateStatus/{id}")
	public String updateStatus(@PathVariable("id") long id, Model model) throws Exception {
		
		couponService.updateStatus(id);
		model.addAttribute("tickets", ticketService.getAllReservedTickets());
		return "tickets/list";
	}

	@PostMapping("/selectTicketToDiscount/{id}")
	public void selectTicketToDiscount(@PathVariable("id") long id, Model model) throws Exception {
		Ticket ticket = ticketService.getOneById(id);
		model.addAttribute("success", "Ticket seleccionado para el descuento correctamente");

		// Enviar a formulario "Insert Coupon" para el viaje seleccionado, el cupon debe
		// ser acorde al viaje, si se ingresa un cupon
		// no valido para el viaje seleccionado entonces saldra mensaje de error
	}

	public void calculareAmountTickets(Model model) {
		try {
			for (long i = 1; i <= ticketService.getAllReservedTickets().size(); i++) {
				amountTicket += ticketService.getOneById(i).getPrice();
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getStackTrace());
		}
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
