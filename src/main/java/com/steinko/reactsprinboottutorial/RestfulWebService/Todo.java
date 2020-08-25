package com.steinko.reactsprinboottutorial.RestfulWebService;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;


@Entity

@Table(name = "todo")
public class Todo implements Serializable  {
	private static final long serialVersionUID = -2343243243242432341L;
	/**
	 * Id for a bank.
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(name = "name")
	private String name;
	
	protected Todo() {
		
	}
	
	/**
	    * Get the id of the bank.
	    * @return id for the bank
	    */
	public Long getId() {
	   		return id;
	    }
	
	public Todo(String name) {
		this.name = name;
	}
	
	public void setName( String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

}
