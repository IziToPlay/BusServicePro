package com.cruzSolar.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
	
	public List<T> getAll() throws Exception;

	public Long create(T entity) throws Exception;

	public void update(ID id, T entity) throws Exception;

	public void delete(ID id) throws Exception;
	
	public T getOneById(Long id) throws Exception;
}
