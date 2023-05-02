package com.sabanciuniv.myNBA.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("Matches")
public class Matches {
	
	@Id
	private String id;
	
	private String home;
	private String away;

	
	public Matches() {
		// TODO Auto-generated constructor stub
	}

	public Matches(String home, String away){
		this.setHome(home);
		this.setAway(away);
		// TODO Auto-generated constructor stub
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}
	
}
