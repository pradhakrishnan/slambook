package org.slambook.application.service.search;

public class SearchQuery {
	private String firstname;
	private String lastname;
	private String email;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isByFirstName(){
		return (null != firstname && !firstname.isEmpty());
	}
	
	public boolean isByLastname(){
		return (null != lastname && !lastname.isEmpty());
	}
	
	public boolean isByEmail(){
		return (null != email && !email.isEmpty());
	}
	
}
