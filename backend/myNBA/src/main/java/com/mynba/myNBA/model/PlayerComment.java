package com.mynba.myNBA.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PlayerComment {
	
	@Id private String id;
	
	private String description;
	private LocalDateTime createdAt;
	private String playerId;
	
	// constructor
	public PlayerComment(String description, String playerId) {
		super();
		this.description = description;
		this.createdAt = LocalDateTime.now();
		this.playerId = playerId;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlayerId() {
		return playerId;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
