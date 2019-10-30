package com.cruzSolar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cruzSolar.model.entity.Client;
import com.cruzSolar.model.repository.ClientRepository;
import com.cruzSolar.service.ClientService;
import com.cruzSolar.service.TicketService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public List<Client> getAll() throws Exception {
		return clientRepository.findAll();
	}

	@Override
	public Long create(Client entity) throws Exception {
		clientRepository.save(entity);
		return entity.getId();
	}

	@Override
	public void update(Long id, Client entity) throws Exception {
		Client currentClient=getOneById(id);
		
		currentClient.setBirthday(entity.getBirthday());
		currentClient.setCellPhone(entity.getCellPhone());
		currentClient.setDni(entity.getDni());
		currentClient.setEmail(entity.getEmail());
		currentClient.setLastName(entity.getLastName());
		currentClient.setName(entity.getName());
		
		clientRepository.save(currentClient);
	}

	@Override
	public void delete(Long id) throws Exception {
		clientRepository.deleteById(id);
		
	}

	@Override
	public Client getOneById(Long id) throws Exception {
		return clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client Not Found!"));
	}

	@Override
	public List<Client> fetchClientByDni(String dni) {
		return clientRepository.fetchClientByDni(dni);
	}

}
