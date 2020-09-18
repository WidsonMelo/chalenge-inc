package com.widson.chalengeinc.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.widson.chalengeinc.exceptions.ObjectNotFoundException;
import com.widson.chalengeinc.models.Movie;
import com.widson.chalengeinc.repositories.MovieRepository;
import com.widson.chalengeinc.services.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepository movieRepository;

	@ApiOperation(value = "Returns all movies")
	@GetMapping("/all")
	public List<Movie> readAll() {
		return movieRepository.findAll();
	}
	
	@ApiOperation(value = "Returns a movie search by title")
	@GetMapping("/movieTitle/{movieTitle}")
	public ResponseEntity<Movie> findByTitleImdb(@PathVariable String movieTitle) {
		Movie movie = movieService.findByTitleImdb(movieTitle);
		return ResponseEntity.ok(movie);
	}

	@ApiOperation(value = "Returns a movie registered in the external api (www.omdbapi.com) according to its id (String)")
	@GetMapping("/movieId/{id}")
	public ResponseEntity<Movie> findByIdImdb(@PathVariable String id) {
		Movie movie = movieService.findByIdImdb(id);
		return ResponseEntity.ok(movie);
	}

	@ApiOperation(value = "Returns a movie used in this api."
			+ "It is considered that the film returned in this method is being used by the Wish List or Evaluation feature.")
	@GetMapping("/id/{movieId}")
	public ResponseEntity<Movie> readById(@PathVariable Integer movieId) {
		Optional<Movie> movie = movieRepository.findById(movieId);
		if (movie.isPresent()) {
			Movie m = movieService.findByIdImdb(movie.get().getImdbid());
			return ResponseEntity.ok(m);
		} else {

			throw new ObjectNotFoundException("movie.not-found");

		}
	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Movie create(@Valid @RequestBody Movie movie) {
//		Movie v = movieService.findById(movie.getImdbid());
//
////		Movie xx = new Movie();
////		xx.setImdbID(v.getImdbID());
//		return movieRepository.save(v);
//	}

}
