package com.widson.chalengeinc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.widson.chalengeinc.models.Movie;
import com.widson.chalengeinc.repositories.MovieRepository;

import reactor.core.publisher.Mono;

@Service
public class MovieService {
	private final String apiKey = "7399ec1b";

	@Autowired
	private WebClient webClient;
	
	@Autowired
	private MovieRepository movieRepository;

	public Movie findByTitleImdb(String title) {
		Mono<Movie> monoMovie = this.webClient.method(HttpMethod.GET)
				.uri("?apikey={apiKey}&t={title}&plot=short", apiKey, title).retrieve()
				.bodyToMono(Movie.class);
		Movie movie = monoMovie.block();
		return movie;
	}
	
	public Movie findByIdImdb(String id) {
		Mono<Movie> monoMovie = this.webClient.method(HttpMethod.GET)
				.uri("?apikey={apiKey}&i={id}&plot=short", apiKey, id).retrieve()
				.bodyToMono(Movie.class);
		Movie movie = monoMovie.block();
		return movie;
	}
	
	public Movie findById(Integer id) {
		Optional<Movie> movieOptional = movieRepository.findById(id);
		if(movieOptional.isPresent()) {
			return movieOptional.get();
		} else {
			return null;
		} 
	}
	
	public Movie create(Movie movie) {
		Movie m = new Movie();
		m.setImdbid(movie.getImdbid());
		return movieRepository.save(m);
	}

}
