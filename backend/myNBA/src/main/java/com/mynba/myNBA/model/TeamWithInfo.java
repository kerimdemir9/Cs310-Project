package com.mynba.myNBA.model;

public class TeamWithInfo {
	private Object team;
	private Object players;
	private Object standings;
	private Object stats;
	
	public TeamWithInfo(Object team, Object players, Object standings, Object stats) {
		super();
		this.team = team;
		this.players = players;
		this.standings = standings;
		this.stats = stats;
	}

	public Object getTeam() {
		return team;
	}

	public void setTeam(Object team) {
		this.team = team;
	}

	public Object getPlayers() {
		return players;
	}

	public void setPlayers(Object players) {
		this.players = players;
	}

	public Object getStandings() {
		return standings;
	}

	public void setStandings(Object standings) {
		this.standings = standings;
	}

	public Object getStats() {
		return stats;
	}

	public void setStats(Object stats) {
		this.stats = stats;
	}
	
	
}
