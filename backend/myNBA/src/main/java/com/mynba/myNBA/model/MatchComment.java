package com.mynba.myNBA.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MatchComment {
	
	@Id private String id;
	
	private String description;
	private LocalDateTime createdAt;
	private String matchId;
	
	// constructor
	public MatchComment(String description, String matchId) {
		super();
		this.description = description;
		this.createdAt = LocalDateTime.now();
		this.matchId = matchId;
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

	public String getMatchId() {
		return matchId;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
