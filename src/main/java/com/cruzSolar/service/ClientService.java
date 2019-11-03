package com.cruzSolar.service;

import java.util.List;

import com.cruzSolar.model.entity.Client;

public interface ClientService extends CrudService<Client, Long>{
	
	List<Client> fetchClientByDni(String dni);
}
