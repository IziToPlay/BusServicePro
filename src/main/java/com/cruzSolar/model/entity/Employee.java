package com.cruzSolar.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Enter your name")
	@Column(name = "name", nullable = false)
	private String name;
	
	@NotEmpty(message="Enter your last name")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotEmpty(message="Enter your dni")
	@Column(name = "dni", nullable = false)
	private String dni;
	
	@Size(min=8,max=8)
	@NotEmpty(message="Enter your birhtday")
	@Column(name = "birthday", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date birthday;
	
	@NotEmpty(message="Enter your email")
	@Column(name = "email", nullable = false)
	private String email;
	
	@Size(min=9,max=9)
	@NotEmpty(message="Enter your cellphone")
	@Column(name = "cell_phone", nullable = false)
	private Long cellPhone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(Long cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}