package com.mynba.myNBA.model;

import java.util.List;

public class Standings {
	private List<Object> east;
	private List<Object> west;
	
	public Standings(List<Object> east, List<Object> west) {
		this.east = east;
		this.west = west;
	}
	
	public List<Object> getEast() {
		return east;
	}
	
	public List<Object> getWest() {
		return west;
	}
}
