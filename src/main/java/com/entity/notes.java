 package com.entity;

import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name="notes")
public class notes {
  @Id
	private int id;
	private String title;
	private String content;
	private Date addeddate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAddeddate() {
		return addeddate;
	}
	public void setAddeddate(Date addeddate) {
		this.addeddate = addeddate;
	}
	int r =new Random().nextInt(100000);   
	public notes( String title, String content, Date addeddate) {
		super();
		this.id = new Random().nextInt(100000);   	
		this.title = title;
		this.content = content;
		this.addeddate = addeddate;
	}
	public notes() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
