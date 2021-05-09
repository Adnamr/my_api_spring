package com.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="customers")
public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customernumber")
	@NotNull(message = "customerNumber is required")
	@Min(value=3,message="customerNumber must have 3 number")
	private int customerNumber;
	
	@Column(name="customername")
	@NotNull(message = "customerName is required")
	private String customerName;
	
	@Column(name="contactlastname")
	@NotNull(message = "contactLastName is required")
	private String contactLastName;
	
	@NotNull
	@Column(name="contactfirstname")
	private String contactFirstName;
	
	@NotNull
	@Column(name="phone")
	private String phone;
	
	@NotNull
	@Column(name="addressline1")
	private String addressLine1;
	
	@Column(name="addressline2")
	private String addressLine2;
	
	@NotNull
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="postalcode")
	private String postalCode;
	
	@NotNull
	@Column(name="country")
	private String country;
	
//	@Column(name="salesrepemployeenumber")
//	private int salesRepEmployeeNumber;
	
	@Column(name="creditlimit")
	private int creditLimit;

	public int getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

//	public int getSalesRepEmployeeNumber() {
//		return salesRepEmployeeNumber;
//	}
//
//	public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
//		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
//	}

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}

}
