package com.cruzSolar.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "trips")
public class Trip {
	@Id
	@Column(name = "trip_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "start_date", nullable = false)
	private String startDate;

	@NotEmpty
	@Column(name = "final_date", nullable = false)
	private String finalDate;

	@NotEmpty
	@Column(name = "price", nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn(name = "bus_id", nullable = false)
	private Bus bus;

	@ManyToOne
	@JoinColumn(name = "dpt_departure_id", nullable = false)
	private Department dptDeparture;

	@ManyToOne
	@JoinColumn(name = "dpt_arrival_id", nullable = false)
	private Department dptArrival;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	public int actualizarAsientos() {
		return bus.getQuantitySeat()-1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Department getDptDeparture() {
		return dptDeparture;
	}

	public void setDptDeparture(Department dptDeparture) {
		this.dptDeparture = dptDeparture;
	}

	public Department getDptArrival() {
		return dptArrival;
	}

	public void setDptArrival(Department dptArrival) {
		this.dptArrival = dptArrival;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}