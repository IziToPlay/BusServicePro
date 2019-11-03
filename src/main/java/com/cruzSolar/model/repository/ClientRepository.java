package com.cruzSolar.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cruzSolar.model.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("select c from Client c where c.dni like ?1")
	List <Client> fetchClientByDni(String dni);
}
