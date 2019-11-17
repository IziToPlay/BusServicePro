package com.cruzSolar.controller;


import java.util.Collections;
import java.util.Comparator;
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
	private Coupon coupon;
	private Trip trip;
	private Trip tripp;
	private Ticket ticket;
	private Seat seatSelected;
	private Seat seat;
	List<Ticket> tickets;
	private Ticket ticketSelect;
	List<Ticket> ticketsBought;
	List<Coupon> coupons;
	List<Seat> seats;
	//private double amountTicket = 0;
	private int counter = 0;
	// private long i=0;

	@GetMapping("/list")
	public String showAllTickets(Model model) throws Exception {
		try {
		
		model.addAttribute("tickets", ticketService.getAllReservedTickets());
		
		model.addAttribute("amountTicket", ticketService.calculateAmountTickets());
	} catch(Exception e) {
		model.addAttribute("error",e.getMessage());
	}
		return "tickets/list";
	}

	@GetMapping("/listBoughtTickets")
	public String showAllBoughtTickets(Model model) throws Exception {
		model.addAttribute("tickets", ticketService.getAllBoughtTickets());
		return "tickets/listBoughtTickets";
	}

	@GetMapping("/new/{id}")
	public String newTicketForm(@PathVariable("id") long id, Model model) throws Exception {
		
		try {
		
		model.addAttribute("ticket", new Ticket());
		client = new Client();
		seat = new Seat();
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		trip = tripService.getOneById(id);
		seats = seatService.findAllSeatsAvailables(id);
		orderseat();
	
		model.addAttribute("seats", seats);
		}catch(Exception e) {
			model.addAttribute("info",e.getMessage());
		}
		return "tickets/new";
	}
    public void orderseat() {
    	Collections.sort(seats, new Comparator<Seat>() {
    		
			@Override
			public int compare(Seat o1, Seat o2) {
				 int number = (int) o1.getId().compareTo(o2.getId());	 
				return o1.getId() > o2.getId() ? 1 : o1.getId() < o2.getId() ? -1 : 0;
			}
			
		});
    }
    
	@GetMapping("/newToBuy/{id}")
	public String newInstantTicketForm(@PathVariable("id") long id, Model model) throws Exception {
		try {
		model.addAttribute("ticket", new Ticket());
		client = new Client();
		seat = new Seat();
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		trip = tripService.getOneById(id);
		List<Seat> seats = seatService.findAllSeatsAvailables(id);
		model.addAttribute("seats", seats);
		orderseat();
		}catch(Exception e) {
			model.addAttribute("info",e.getMessage());
		}
		return "tickets/newToBuy";
	}
	
	@GetMapping("/searchBoughtTicket")
	public String searchBoughtTicket(@RequestParam("id") String id, Model model) throws Exception {
		if (!id.isEmpty()) {
			if(!searchBoughtTickets(id, model).isEmpty())
			{model.addAttribute("tickets", searchBoughtTickets(id, model));
			model.addAttribute("info", "Busqueda realizada correctamente");	}	
			else
				model.addAttribute("info", "No se han encontrado coincidencias.");
		}
		else 
			model.addAttribute("info", "Debe completar el campo de búsqueda.");		
			
		return "tickets/listBoughtTickets";
	}

	public List<Ticket> searchBoughtTickets(String filtroid, Model model) {
		try {
			if (!filtroid.isEmpty()) {

				long id = Long.parseLong(filtroid);
				model.addAttribute("info", "Busqueda realizada correctamente");
				ticketsBought = ticketService.fetchTicketById(id);
				if (!tickets.isEmpty()) {
					model.addAttribute("tickets", ticketsBought);
				} else {
					model.addAttribute("info", "No se han encontrado coincidencias");
					model.addAttribute("tickets", ticketService.getAllBoughtTickets());
				}
			} else {
				model.addAttribute("info", "Debe completar el campo de búsqueda.");
				// model.addAttribute("coupons", couponService.getAll());
			}
		} catch (Exception e) {
			model.addAttribute("Error Ticket:", e.getMessage());
		}
		return ticketsBought;
	}

	@GetMapping("/searchClient")
	public String searchClient(@RequestParam("dni") String dni, Model model) throws Exception {
		try {
		
		model.addAttribute("ticket", new Ticket());
		List<Client> clients = clientController.searchClient(dni, model);
		model.addAttribute("clients", clients);
		List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		model.addAttribute("seats", seats);
		}
		catch(Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "tickets/new";
	}
	
	@GetMapping("/connect/{id}")
	public String connectClient(@PathVariable("id") long id, Model model) throws Exception {
		
		try {
		model.addAttribute("ticket", new Ticket());	
		client = clientService.getOneById(id);
		// List<Client> clients = clientService.getAll();
		model.addAttribute("clients", client);
		List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		
		if(seat.getId() != null) { model.addAttribute("seats", seat);}
	
		else {model.addAttribute("seats", seats);}
		model.addAttribute("success", "Cliente seleccionado correctamente");
		}	catch (Exception e) {
			
		}
		return "tickets/new";
	}
	@GetMapping("/connectseattoedit/{id}")
	public String connectSeatToEdit(@PathVariable("id") long id, Model model) throws Exception {
		try 
		{
				List<Client> clients = clientService.getAll();
				model.addAttribute("clients", clients);	
				model.addAttribute("ticket", ticket);	
				
				seatSelected = seatService.findSeatbyBus(ticket.getTrip().getBus().getId(),id);				
				seat = seatService.findSeatbyBus(ticket.getTrip().getBus().getId(),ticket.getSeat().getId());
			
			     model.addAttribute("success", "Se ha cambiado el asiento " + seat.getId() + " por el asiento asiento " + seatSelected.getId());
			    
			     seats = seatService.findAllSeatsAvailables(id);
			     model.addAttribute("seats", seats);
				 orderseat();
		 }
			catch (Exception e)
			{
					
			}
			return "tickets/edit";
	}
	
	@GetMapping("/connectseat/{id}")
	public String connectSeat(@PathVariable("id") long id, Model model) throws Exception {
		
		try 
		{
			model.addAttribute("ticket", new Ticket());	
			seat = seatService.findSeatbyBus(trip.getBus().getId(),id);
			if(seat.getAvailable() == true) {
			//seatService.updateSeat(seat.getId());
			//List<Seat> seats = seatService.findAllSeatsAvailables(id);
			  if(client.getDni() != null ) { model.addAttribute("clients", client); }
			  else {
				List<Client> clients = clientService.getAll();
			    model.addAttribute("clients", clients); 
			   }
		        model.addAttribute("seats", seat);

			    //model.addAttribute("clients", clients);
		      	model.addAttribute("success", "Se ha seleccionado correctamente el Asiento " + seat.getId());
			}
		    else {
		      	model.addAttribute("error", "Este asiento ya se encuentra ocupado");
		        seats = seatService.findAllSeatsAvailables(trip.getId());  
		        model.addAttribute("seats", seats);
		    	orderseat();
				
		        if(client.getDni() != null ) { model.addAttribute("clients", client); }
				 
		        else {
					List<Client> clients = clientService.getAll();
				    model.addAttribute("clients", clients); 
			    }
			}
		}
		catch (Exception e)
		{
				
		}
		return "tickets/new";
	}
	@GetMapping("/connectseattobuy/{id}")

	public String connectSeatToBuy(@PathVariable("id") long id, Model model) throws Exception{

		try {

			model.addAttribute("ticket", new Ticket());	
			seat = seatService.findSeatbyBus(trip.getBus().getId(),id);
			if(seat.getAvailable() == true) {
			//seatService.updateSeat(seat.getId());
			//List<Seat> seats = seatService.findAllSeatsAvailables(id);
			  if(client.getDni() != null ) { model.addAttribute("clients", client); }
			  else {
				List<Client> clients = clientService.getAll();
			    model.addAttribute("clients", clients); 
			   }
		        model.addAttribute("seats", seat);

			    //model.addAttribute("clients", clients);
		      	model.addAttribute("success", "Se ha seleccionado correctamente el Asiento " + seat.getId());
			}
		    else {
		      	model.addAttribute("error", "Este asiento ya se encuentra ocupado");
		        seats = seatService.findAllSeatsAvailables(trip.getId());  
		        model.addAttribute("seats", seats);
		    	orderseat();
				
		        if(client.getDni() != null ) { model.addAttribute("clients", client); }
				 
		        else {
					List<Client> clients = clientService.getAll();
				    model.addAttribute("clients", clients); 
			    }
			}
		}
		catch (Exception e)
		{
				
		}
			return "tickets/newToBuy";
	}
	@GetMapping("/connectClient/{id}")
	public String connectClientToBuy(@PathVariable("id") long id, Model model) throws Exception {
		
		try {
		model.addAttribute("ticket", new Ticket());	
		client = clientService.getOneById(id);
		// List<Client> clients = clientService.getAll();
		model.addAttribute("clients", client);
		List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
		if(seat.getId() != null) { model.addAttribute("seats", seat);}
		
		else {model.addAttribute("seats", seats);}
		model.addAttribute("success", "Cliente seleccionado correctamente");
		}	catch (Exception e) {
			
		}

		return "tickets/newToBuy";
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

		if (client.getId() != null && seat.getId() != null) {
			ticket.setCondition(false);
			ticket.setPrice(trip.getPrice());
			ticket.setTrip(trip);
			ticket.setSeat(seat);
			ticket.setClient(client);
			long id = ticketService.create(ticket);
			seat.setAvailable(false);
			seatService.update(trip.getBus().getId(),seat);
			return "redirect:/trips/list";
		} else {
			model.addAttribute("error", "Cliente no seleccionado");
			List<Client> clients = clientService.getAll();
			List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
			model.addAttribute("clients", clients);
			model.addAttribute("seats", seats);
			return "tickets/new";
		}
	}
	
	@PostMapping("/saveInstantTicket")
	public String saveInstantNewTicket(Ticket ticket, Model model) throws Exception {

		if (client.getId() != null && seat.getId() != null) {
			ticket.setCondition(false);
			ticket.setPrice(trip.getPrice());
			ticket.setTrip(trip);
			ticket.setSeat(seat);
			ticket.setClient(client);
			ticket.setCondition(false);
			long id = ticketService.create(ticket);
			seat.setAvailable(false);
			seatService.update(trip.getBus().getId(),seat);
			buyInstantTicket(id, model);
			return "redirect:/trips/list";
		} else {
			model.addAttribute("error", "Cliente no seleccionado");
			List<Client> clients = clientService.getAll();
			List<Seat> seats = seatService.findAllSeatsAvailables(trip.getId());
			model.addAttribute("clients", clients);
			model.addAttribute("seats", seats);
			return "tickets/newToBuy";
		}
	}

	@GetMapping("/payTicket/{id}")
	public String payTicket(@PathVariable("id") long id, Model model) throws Exception {
		ticketSelect = ticketService.getOneById(id);
		Ticket ticket = ticketService.getOneById(id);
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		List<Seat> seats = seatService.findAllSeatsAvailables(ticket.getTrip().getBus().getId());
		model.addAttribute("seats", seats);
		model.addAttribute("ticket", ticket);
		return "tickets/payTicket";
	}

	@GetMapping("/updateStatus/{id}")
	public String updateStatus(@PathVariable("id") long id, Model model) throws Exception {

		Coupon couponFound = couponService.getOneById(id);
		couponService.updateStatus(id);
		model.addAttribute("tickets", ticketService.getAllReservedTickets());
		ticketService.udpatePrice(ticketSelect.getId(),
				(couponFound.getDiscount() / 100) * couponFound.getTrip().getPrice());
		return "redirect:/tickets/list";
	}

	@GetMapping("/searchCoupon")
	public String searchCoupon(@RequestParam("special") String special, Model model) throws Exception {
		if (!special.isEmpty()) {
			List<Coupon> coupons = couponController.searchCouponBySpecial(special, model);
			Coupon cu = couponService.fetchCouponBySpe(special);

			if (!coupons.isEmpty()) {
				if (ticketSelect.getTrip().getId() == cu.getTrip().getId()) {
					if (cu.getStatus() == false && cu.getGiftStatus() == true)
						model.addAttribute("coupons", coupons);
					else
						model.addAttribute("info", "El cupon no esta disponible");
				} else {
					model.addAttribute("infor", "El cupon no corresponde a este viaje");
				}
			} else {
				model.addAttribute("info", "No se han encontrado coincidencias");
			}
		} else
			model.addAttribute("info", "Debe completar el campo de búsqueda");
		return "tickets/payTicket";
	}

	@GetMapping("/edit/{id}")
	public String editTicketForm(@PathVariable("id") long id, Model model) throws Exception {
		
		ticket = ticketService.getOneById(id);
		model.addAttribute("ticket", ticket);
		List<Client> clients = clientService.getAll();
		model.addAttribute("clients", clients);
		seats = seatService.findAllSeatsAvailables(id);
		orderseat();
		model.addAttribute("seats", seats);
		
		return "tickets/edit";
	}

	@PostMapping("/update/{id}")
	public String updateTicket(@PathVariable("id") long id, Ticket ticketv, Model model) throws Exception {
		
		ticketv.setSeat(seatSelected);
		
		seat.setAvailable(true);	
		seatService.update(ticket.getTrip().getBus().getId(),seat);
		
		seatSelected.setAvailable(false);
		seatService.update(ticket.getTrip().getBus().getId(),seatSelected);

		ticketService.update(id, ticketv);
	
		model.addAttribute("success", "Ticket actualizado correctamente");
		tickets = ticketService.getAllReservedTickets();
		return "redirect:/tickets/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteTicket(@PathVariable("id") long id, Model model) throws Exception {
		counter--;
		//amountTicket -= ticketService.getOneById(id).getPrice();
		Ticket ticket = ticketService.getOneById(id);
		ticketService.delete(id);
		Seat seat = seatService.findSeatbyBus(ticket.getTrip().getBus().getId(),ticket.getSeat().getId());
		seat.setAvailable(true);
		seatService.update(trip.getBus().getId(),seat);	
		model.addAttribute("success", "Ticket eliminado correctamente");
		return "redirect:/tickets/list";
	}

	@GetMapping("/buy/{id}")
	public String buyTicket(@PathVariable("id") long id, Model model) throws Exception {
		counter++;
		// update condition to TRUE
		ticketService.updateCondition(id);
		//amountTicket -= ticketService.getOneById(id).getPrice();
		if (counter % 3 == 0) {
			// i++;
			couponController.account();
			model.addAttribute("info", "Cupón activado por compra de 3 tickets.");
		} else {
			model.addAttribute("success", "Ticket comprado correctamente");
		}
		return "redirect:/tickets/list";
	}
	
	public String buyInstantTicket(long id, Model model) throws Exception {
		counter++;
		// update condition to TRUE
		ticketService.updateCondition(id);
		//amountTicket -= ticketService.getOneById(id).getPrice();
		if (counter % 3 == 0) {
			// i++;
			couponController.account();
			model.addAttribute("info", "Cupón activado por compra de 3 tickets.");
		} else {
			model.addAttribute("success", "Ticket comprado correctamente");
		}
		return "redirect:/trips/list";
	}

	@PostMapping("/selectTicketToDiscount/{id}")
	public void selectTicketToDiscount(@PathVariable("id") long id, Model model) throws Exception {
		Ticket ticket = ticketService.getOneById(id);
		model.addAttribute("success", "Ticket seleccionado para el descuento correctamente");

		// Enviar a formulario "Insert Coupon" para el viaje seleccionado, el cupon debe
		// ser acorde al viaje, si se ingresa un cupon
		// no valido para el viaje seleccionado entonces saldra mensaje de error
	}

	/*public void calcularAmountTickets(Model model) {
		amountTicket = 0;
		try {
			List<Ticket> ticketReserved=ticketService.getAllReservedTickets();
			for (long i = 1; i <= (long) ticketService.getAllReservedTickets().size(); i++) {
				int index=(int)i-1;
				amountTicket += ticketReserved.get(index).getPrice();
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getStackTrace());
		}
	}*/

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

	public TripService getTripService() {
		return tripService;
	}

	public void setTripService(TripService tripService) {
		this.tripService = tripService;
	}

	public CouponService getCouponService() {
		return couponService;
	}

	public void setCouponService(CouponService couponService) {
		this.couponService = couponService;
	}

	public ClientController getClientController() {
		return clientController;
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

	public CouponController getCouponController() {
		return couponController;
	}

	public void setCouponController(CouponController couponController) {
		this.couponController = couponController;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Trip getTripp() {
		return tripp;
	}

	public void setTripp(Trip tripp) {
		this.tripp = tripp;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/*public double getAmountTicket() {
		return amountTicket;
	}

	public void setAmountTicket(double amountTicket) {
		this.amountTicket = amountTicket;
	}*/

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
