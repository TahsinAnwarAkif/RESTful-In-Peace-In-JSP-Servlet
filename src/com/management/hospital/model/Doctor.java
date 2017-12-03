package com.management.hospital.model;

public class Doctor {

	private int id;
	private String name;
	private String isAvailable;
	private String specialty;
	private String address;
	private String phone;
	private String email;

	public Doctor(String name, String isAvailable, String specialty, String address, String phone, String email) {
		this.name = name;
		this.isAvailable = isAvailable;
		this.specialty = specialty;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public Doctor(int id, String name, String isAvailable, String specialty, String address, String phone,
			String email) {
		this.id = id;
		this.name = name;
		this.isAvailable = isAvailable;
		this.specialty = specialty;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", is_available=" + isAvailable + ", specialty=" + specialty
				+ ", address=" + address + ", phone=" + phone + ", email=" + email + "]";
	}

}
