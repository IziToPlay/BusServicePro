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
@Table(name = "tickets")
public class Ticket {
	@Id
	@Column(name="ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Enter the emission date")
	@Column(name = "emission_date")
	private String emissionDate;

	@ManyToOne
	@JoinColumn(name = "client_id",nullable=false)
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "seat_id",nullable=false)
	private Seat seat;

	@ManyToOne
	@JoinColumn(name = "trip_id",nullable=false)
	private Trip trip;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmissionDate() {
		return emissionDate;
	}

	public void setEmissionDate(String emissionDate) {
		this.emissionDate = emissionDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}