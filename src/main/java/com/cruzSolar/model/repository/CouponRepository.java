package com.cruzSolar.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cruzSolar.model.entity.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

	@Query("select c from Coupon c where c.id = ?1")
	List<Coupon> fetchCouponById(long id);

	@Query("select c from Coupon c where c.specialCode =?1")
	List<Coupon> fetchCouponBySpecial(String specialCode);
}
