package com.widson.chalengeinc.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movie")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	@JsonProperty("Title")
	private String title;
	
	@Transient
	@JsonProperty("Year")
	private String year;
	
	@Transient
	@JsonProperty("Runtime")
	private String runtime;
	
	@Transient
	@JsonProperty("Genre")
	private String genre;
	
	@Transient
	@JsonProperty("Director")
	private String director;
	
	@Transient
	@JsonProperty("Writer")
	private String writer;
	
	@Transient
	@JsonProperty("Actors")
	private String actors;
	
	@Transient
	@JsonProperty("Plot")
	private String plot;
	
	@Transient
	@JsonProperty("Language")
	private String language;
	
	@Transient
	@JsonProperty("Country")
	private String country;
	
	@Transient
	@JsonProperty("Poster")
	private String poster;
	
	@JoinColumn(name = "imdbid")
	@JsonProperty("imdbID")
	private String imdbid;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonProperty("Title")
	public String getTitle() {
		return title;
	}
	@JsonProperty("Title")
	public void setTitle(String title) {
		this.title = title;
	}
	@JsonProperty("Year")
	public String getYear() {
		return year;
	}
	@JsonProperty("Year")
	public void setYear(String year) {
		this.year = year;
	}
	@JsonProperty("Runtime")
	public String getRuntime() {
		return runtime;
	}
	@JsonProperty("Runtime")
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	@JsonProperty("Genre")
	public String getGenre() {
		return genre;
	}
	@JsonProperty("Genre")
	public void setGenre(String genre) {
		this.genre = genre;
	}
	@JsonProperty("Director")
	public String getDirector() {
		return director;
	}
	@JsonProperty("Director")
	public void setDirector(String director) {
		this.director = director;
	}
	@JsonProperty("Writer")
	public String getWriter() {
		return writer;
	}
	@JsonProperty("Writer")
	public void setWriter(String writer) {
		this.writer = writer;
	}
	@JsonProperty("Actors")
	public String getActors() {
		return actors;
	}
	@JsonProperty("Actors")
	public void setActors(String actors) {
		this.actors = actors;
	}
	@JsonProperty("Plot")
	public String getPlot() {
		return plot;
	}
	@JsonProperty("Plot")
	public void setPlot(String plot) {
		this.plot = plot;
	}
	@JsonProperty("Language")
	public String getLanguage() {
		return language;
	}
	@JsonProperty("Language")
	public void setLanguage(String language) {
		this.language = language;
	}
	@JsonProperty("Country")
	public String getCountry() {
		return country;
	}
	@JsonProperty("Country")
	public void setCountry(String country) {
		this.country = country;
	}
	@JsonProperty("Poster")
	public String getPoster() {
		return poster;
	}
	@JsonProperty("Poster")
	public void setPoster(String poster) {
		this.poster = poster;
	}
	@JsonProperty("imdbID")
	public String getImdbid() {
		return imdbid;
	}
	@JsonProperty("imdbID")
	public void setImdbid(String imdbid) {
		this.imdbid = imdbid;
	}
	
}
