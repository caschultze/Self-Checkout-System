package org.lsmr.software;

import java.util.ArrayList;
import java.util.HashMap;

import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.Attendant.LoginData;

public class AttendantLogin {

	public CurrentSessionData sessionData = new CurrentSessionData();
	
	private static class AttendantLoginDatabase {
		static Attendant Jake = new Attendant("Jake Kim", "geesjake", "freshwaterGORILLA@9to5", "Service Clerk", "Customer Service");
		static Attendant Sami = new Attendant("Sami Zeremariam", "samiz50", "monkeLegion!", "Service Clerk", "Customer Service");
		static Attendant Raymond = new Attendant("Raymond Vong", "rayray22", "meOrangutanOohOohAhAh111", "Utility Clerk", "Customer Service");
		static Attendant Austin = new Attendant("Austin Ficzere", "Bruh", "#$4CalgarybornChimpanzeeAustron", "Cashier", "Customer Service");
		static Attendant Manbir = new Attendant("Manbir Sandhu", "Mana24129", "SCARYguineaBaboon#587", "Grocery Clerk", "Produce");
		static Attendant Robert = new Attendant("Robert James Walker", "rjwalker", "realWorld123", "Store Manager", "Human Resources");
		
		static ArrayList<Attendant> employees = new ArrayList<Attendant>();
		static HashMap<String, String> loginDatabase= new HashMap<String, String>();
		
		private AttendantLoginDatabase() {
			employees.add(Jake);
			employees.add(Sami);
			employees.add(Raymond);
			employees.add(Austin);
			employees.add(Manbir);
			employees.add(Robert);
				
			loginDatabase.put("geesjake", "freshwaterGORILLA@9to5");
			loginDatabase.put("samiz50", "monkeLegion!");
			loginDatabase.put("rayray22", "meOrangutanOohOohAhAh111");
			loginDatabase.put("Bruh", "#$4CalgarybornChimpanzeeAustron");
			loginDatabase.put("Mana24129", "SCARYguineaBaboon#587");
			loginDatabase.put("rjwalker", "realWorld123");
		}
	}
	
	public boolean verifyLogin(String username, String password) {
		
		sessionData.setAttendantLoggedInMiddleCheck(false);
		sessionData.setAttendantLoggedIn(false);
		sessionData.setCurrentAttendant(null);
		
		if (username == null || password == null) {
			throw new SimulationException(new NullPointerException("No argument may be null"));
		}
		
		AttendantLoginDatabase database = new AttendantLoginDatabase();
		
		if (!(database.loginDatabase.containsKey(username))) {
			System.out.println("Sorry, your username entry does not match our records. Please try again.");
			return false;
		} else if (!(database.loginDatabase.get(username).equals(password))) {
			System.out.println("Sorry, your password entry does not match our records. Please try again.");
			return false;
		}
		
		sessionData.setAttendantLoggedInMiddleCheck(true);
		
		for (Attendant attendant : database.employees) {
			LoginData loginData = attendant.requestLogin();
			if (loginData.getUsername().equals(username)) {
				sessionData.setCurrentAttendant(attendant);
				sessionData.setAttendantLoggedIn(true);
				break;
			}
		}
		
		return true;
	}
	
	public void attendantLogOut() {
		
		if (sessionData.getAttendantLoggedIn()) {
			sessionData.setAttendantLoggedIn(false);
			sessionData.setCurrentAttendant(null);
		}
	}
}
