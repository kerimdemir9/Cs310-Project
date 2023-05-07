package com.mynba.myNBA.model;

public class PlayerCommentPayload {
    private String description;
	
	public PlayerCommentPayload() {
		this.description = "";
	}
	
	public PlayerCommentPayload(String description) {
		super();
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
