package com.widson.chalengeinc.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.widson.chalengeinc.enums.Visualization;
import com.widson.chalengeinc.models.Evaluation;
import com.widson.chalengeinc.models.Movie;
import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.repositories.EvaluationRepository;
import com.widson.chalengeinc.repositories.UserRepository;
import com.widson.chalengeinc.services.MovieService;
import com.widson.chalengeinc.services.UserService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/evaluation")
@RestController
public class EvaluationController {
	@Autowired
	private EvaluationRepository evaluationRepository;
		
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
//	public ResponseEntity<List<Evaluation>> findAllByUser(@PathVariable Integer userId) {
//	User user = userService.findById(userId);
//	List<Evaluation> evaluations = evaluationService.findAllByUser(user);
//	return ResponseEntity.ok(evaluations);
//	
//}
	
	
	
	@ApiOperation(value = "Returns the complete list of all movie evaluation.")
	@GetMapping("/userId/{userId}")
	public ResponseEntity<List<Evaluation>> findAllByUser(Integer userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			List<Evaluation> evaluations = evaluationRepository.findAllByUser(user.get());
			
			for(Evaluation evaluation : evaluations){
				evaluation.setMovie(movieService.findByIdImdb(evaluation.getMovie().getImdbid()));
	        }
			
			return ResponseEntity.ok(evaluations);
		} else {
			return ResponseEntity.notFound().build();
		}
	}	
	
	@ApiOperation(value = "Returns a user's movie evaluation list with public status")
	@GetMapping("/public")
	public List<Evaluation> findAllPublic() {
		return evaluationRepository.findAllByVisualization(Visualization.PUBLIC);
	}
	
	@ApiOperation(value = "Returns a user's movie evaluation list with private status")
	@GetMapping("/private")
	public List<Evaluation> findAllPrivate() {
		return evaluationRepository.findAllByVisualization(Visualization.PRIVATE);
	}
	
	@ApiOperation(value = "Returns a movie evaluation by id")
	@GetMapping("/id/{id}")
	public ResponseEntity<Evaluation> findById(@PathVariable Integer id) {
		Optional<Evaluation> evaluation = evaluationRepository.findById(id);
		if(evaluation.isPresent()) {
			Evaluation e = evaluation.get();
			Movie movie = movieService.findByIdImdb(e.getMovie().getImdbid());
			e.setMovie(movie);
			return ResponseEntity.ok(e);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Create a new movie evaluation")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evaluation> create(@RequestBody Evaluation evaluation) {
		
		User user =  userService.findById(evaluation.getUser().getId());
		if (user.getId() > 0) {
			evaluation.setUser(user);
		}
		
		Movie movie =  movieService.findByIdImdb(evaluation.getMovie().getImdbid());
		movieService.create(movie);
		evaluation.setMovie(movie);
		
		return ResponseEntity.ok().body(evaluationRepository.save(evaluation));
	}
	
	@ApiOperation(value = "Update a movie evaluation")
	@PutMapping("/id/{id}")
	public ResponseEntity<Evaluation> updateById(@Valid @PathVariable Integer id, @RequestBody Evaluation evaluation) {
		if (evaluationRepository.existsById(id)) {
			evaluation.setId(id);
			evaluation = evaluationRepository.save(evaluation);
			return ResponseEntity.ok().body(evaluation);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Delete a movie evaluation by id")
	@DeleteMapping("/id/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		if (evaluationRepository.existsById(id)) {
			evaluationRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
