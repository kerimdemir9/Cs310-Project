package com.mynba.myNBA.model;

public class MatchCommentPayload {
	private String description;
	
	public MatchCommentPayload() {
		this.description = "";
	}
	
	public MatchCommentPayload(String description) {
		super();
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
