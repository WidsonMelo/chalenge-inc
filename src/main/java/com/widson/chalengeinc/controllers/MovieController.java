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

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping("/all")
	public List<Movie> readAll() {
		return movieRepository.findAll();
	}

	@GetMapping("/movieTitle/{movieTitle}")
	public ResponseEntity<Movie> readByTitle(@PathVariable String movieTitle) {
		Movie movie = movieService.findByTitle(movieTitle);
		return ResponseEntity.ok(movie);
	}

	@GetMapping("/movieId/{id}")
	public ResponseEntity<Movie> readById(@PathVariable String id) {
		Movie movie = movieService.findById(id);
		return ResponseEntity.ok(movie);
	}

	// Faz a busca, se houver algum registro, retorna ele, caso contrário retorna o
	// código 404 (não encontrado)
	@GetMapping("/id/{movieId}")
	public ResponseEntity<Movie> readById(@PathVariable Integer movieId) {
		Optional<Movie> movie = movieRepository.findById(movieId);
		if (movie.isPresent()) {
			return ResponseEntity.ok(movie.get());
		} else {

			throw new ObjectNotFoundException("movie.not-found");

		}
	}

	// Transforma o json recebido no corpo em um objeto Usuario e cria no banco de
	// dados
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Movie create(@Valid @RequestBody Movie movie) {
		Movie v = movieService.findById(movie.getImdbid());

//		Movie xx = new Movie();
//		xx.setImdbID(v.getImdbID());
		return movieRepository.save(v);
	}

}
