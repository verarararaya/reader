package com.app.model;

import java.util.Date;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

@Table(name = "configmodel",
onCreated = "") 
public class NoteModel {
   @Column(
           name = "ID",
           isId = true,
           autoGen = true
   )
	   private long id;
   
	   @Column(name = "TITLE",property = "NOT NULL")//the content is not null
	   private String title;
	   	
	   @Column(name = "CONTENT",property = "NOT NULL")//the content is not null
	   private String content;
	
	   @Column(name = "DATE",property = "NOT NULL")//the content is not null
	   private Date date;
	   
	   
	   

	public NoteModel() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "NoteModel [id=" + id + ", title=" + title + ", content="
				+ content + ", date=" + date + "]";
	}

	public NoteModel(String title, String content, Date date) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
	}
	   
	   
	   
	   
	   
   
   
   
   
}
