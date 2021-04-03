package org.lsmr.software;

import org.lsmr.selfcheckout.devices.SimulationException;

public class Attendant {

	public CurrentSessionData sessionData;
	
	private String name;
	private String username;
	private String password;
	private String jobTitle;
	private String department;
	
	public Attendant(String name, String username, String password, String jobTitle, String department) {
		if (name == null) {
			throw new SimulationException(new NullPointerException("Name is null"));
		} 
		
		if (username == null) {
			throw new SimulationException(new NullPointerException("Username is null"));
		}
		
		if (password == null) {
			throw new SimulationException(new NullPointerException("Password is null"));
		}
		
		if (jobTitle == null) {
			throw new SimulationException(new NullPointerException("Job title is null"));
		}
		
		if (department == null) {
			throw new SimulationException(new NullPointerException("Department is null"));
		}
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.jobTitle = jobTitle;
		this.department = department;
	}
	
	public String getName() {
		return new String(name);
	}
	
	public String getJobTitle() {
		return new String(jobTitle);
	}
	
	public String getDepartment() {
		return new String(department);
	}
	
	public void displayInfo() {
		System.out.println("Employee name: " + getName());
		System.out.println("Employee job title: " + getJobTitle());
		System.out.println("Employee department: " + getDepartment());
	}
	
	public LoginData requestLogin() {
		
		if (sessionData.getAttendantLoggedInMiddleCheck()) {
			return new LoginData();
		}
		
		return null;
	}
	
	public class LoginData {
		
		public String getUsername() {
			return new String(username);
		}
		
		public String getPassword() {
			return new String(password);
		}
	}
}
