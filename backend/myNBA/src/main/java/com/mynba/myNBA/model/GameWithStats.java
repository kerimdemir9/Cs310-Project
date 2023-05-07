package com.mynba.myNBA.model;

public class GameWithStats {
	public Object game;
	public Object teamsStats;
	public Object playersStats;
	
	public GameWithStats(Object game, Object teamsStats, Object playersStats) {
		super();
		this.game = game;
		this.teamsStats = teamsStats;
		this.playersStats = playersStats;
	}
}
