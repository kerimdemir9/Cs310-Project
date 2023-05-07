package com.mynba.myNBA.response;

import java.util.ArrayList;

import com.mynba.myNBA.model.Team;

public class TeamsResponse {
    private ArrayList<Team> teams;
	
	public TeamsResponse(ArrayList<Team> teams) {
		this.teams = teams;
	}
	
	public ArrayList<Team> getTeams() {
		return teams;
	}
}
