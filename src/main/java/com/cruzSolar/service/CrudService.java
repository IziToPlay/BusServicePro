package com.cruzSolar.service;

import java.util.List;

public interface CrudService<T, ID> {
	
	public List<T> getAll() throws Exception;

	public Long create(T entity) throws Exception;

	public void update(ID id, T entity) throws Exception;

	public void delete(ID id) throws Exception;
	
	public T getOneById(Long id) throws Exception;
<<<<<<< HEAD
=======
	
	public void updateCondition(ID id) throws Exception;
>>>>>>> ddbbdcf2b597e5838e3c3d38a3e70a0df846c901
}
