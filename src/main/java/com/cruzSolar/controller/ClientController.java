package com.cruzSolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.service.ClientService;

 

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	
	private Client client;
	List<Client> clients;
	
	@GetMapping
    public String showAllClients(Model model){
		try {
        model.addAttribute("clients", clientService.getAll());
		}catch (Exception e){
			model.addAttribute("Error Cliente:", e.getMessage());
		}
        return "/tickets/new";
    }
	
	
	public List<Client> searchClient(String dni, Model model){
		
		try {
			if(!dni.isEmpty()) {
				clients=clientService.fetchClientByDni(dni);
				model.addAttribute("success", "Búsqueda realizada correctamente");
			if(!clients.isEmpty()) {
				
				model.addAttribute("clients", clients);
			}else {
				clients=clientService.getAll();
				model.addAttribute("info", "No existen coincidencias");
				model.addAttribute("clients",clients);
				}
			}else {
				clients=clientService.getAll();
				model.addAttribute("error", "Debe completar el campo de búsqueda.");
				model.addAttribute("clients",clients);
			}
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}	
		return clients;
	}
	
	@GetMapping("/new")
    public String newClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "/clients/new";
    }
	
	@PostMapping("/save")
    public String saveNewClient(Client client) throws Exception {
        long id=clientService.create(client);
        return "tickets/new";
    }

	
	@GetMapping("/edit/{id}")
    public String editClientForm(@PathVariable("id") long id, Model model) throws Exception {
        Client client = clientService.getOneById(id);
        model.addAttribute("client", client);
        return "clients/edit";
    }
	

	
	@PostMapping("/update/{id}")
    public String updateClient(@PathVariable("id") long id, Client client) throws Exception {
        clientService.update(id, client);
        return "tickets/new";    
    }

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
