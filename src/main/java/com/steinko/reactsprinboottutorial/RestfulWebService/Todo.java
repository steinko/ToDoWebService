package com.steinko.reactsprinboottutorial.RestfulWebService;

import java.util.Date;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Todo {
	private long id;
	private String username;
	private String description;
	@JsonSerialize(using = CustomDateSerializer.class)
	@JsonDeserialize(using = CustomDateDeserialize.class)
	private Date targetDate;
	private Boolean isDone;

	
	public Todo(long id, String username, String description, Date targetDate, Boolean isDone) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getTargetDate() {
		return targetDate;
	}


	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	
	public Boolean getIsDone() {
		return isDone;
	}


	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}
}
