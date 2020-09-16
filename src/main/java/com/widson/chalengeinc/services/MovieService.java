package com.widson.chalengeinc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.widson.chalengeinc.models.Movie;

import reactor.core.publisher.Mono;

@Service
public class MovieService {
	private final String apiKey = "7399ec1b";

	@Autowired
	private WebClient webClient;

	public Movie findByTitle(String title) {
		Mono<Movie> monoMovie = this.webClient.method(HttpMethod.GET)
				.uri("?apikey={apiKey}&t={title}&plot=full", apiKey, title).retrieve()
				.bodyToMono(Movie.class);
		Movie movie = monoMovie.block();
		return movie;
	}
	
	public Movie findById(String id) {
		Mono<Movie> monoMovie = this.webClient.method(HttpMethod.GET)
				.uri("?apikey={apiKey}&i={id}&plot=full", apiKey, id).retrieve()
				.bodyToMono(Movie.class);
		Movie movie = monoMovie.block();
		return movie;
	}

}
