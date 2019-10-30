package com.cruzSolar.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cruzSolar.model.entity.Bus;
import com.cruzSolar.model.repository.BusRepository;
import com.cruzSolar.service.BusService;

@Service
public class BusServiceImpl implements BusService{

	@Autowired
	private BusRepository busRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Bus> getAll() throws Exception {
		return busRepository.findAll();
	}
	
	@Override
	public Bus getOneById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long create(Bus entity) throws Exception  {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Long id, Bus entity) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
