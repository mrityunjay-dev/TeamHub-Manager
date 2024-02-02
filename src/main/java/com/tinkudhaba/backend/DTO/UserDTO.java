package com.tinkudhaba.backend.DTO;

public class UserDTO {
	private int id;
	private int cityId;
	private int roleId;
	private String firstName;
	private String lastName;
	private String contact;
	private int Isactive;
	private String email;
	private String password;
	private String dob;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public int getIsactive() {
		return Isactive;
	}

	public void setIsactive(int i) {
		Isactive = i;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int string) {
		this.cityId = string;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	

	

}
