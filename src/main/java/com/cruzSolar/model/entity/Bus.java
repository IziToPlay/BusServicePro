package com.cruzSolar.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "buses")
public class Bus {
	@Id
	@Column(name = "bus_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "plate", nullable = false)
	private String plate;
	
	@NotEmpty
	@Column(name = "quantity_seat", nullable = false)
	private Integer quantitySeat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public Integer getQuantitySeat() {
		return quantitySeat;
	}

	public void setQuantitySeat(Integer quantitySeat) {
		this.quantitySeat = quantitySeat;
	}
	
	
}