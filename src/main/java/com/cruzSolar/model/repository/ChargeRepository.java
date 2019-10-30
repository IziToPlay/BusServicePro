package com.cruzSolar.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cruzSolar.model.entity.Charge;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long>{

}
