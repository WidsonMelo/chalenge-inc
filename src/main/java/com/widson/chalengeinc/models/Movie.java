package com.widson.chalengeinc.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "filme")
@Getter @Setter @NoArgsConstructor
public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String title;
	public String year;
	public String released;
	public String runtime;
	public String genre;
	public String director;
	public String writer;
	public String actors;
	public String plot;
	public String language;
	public String country;
	public String poster;
	
}
