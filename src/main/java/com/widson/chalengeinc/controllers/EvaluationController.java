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

import com.widson.chalengeinc.enuns.Visualization;
import com.widson.chalengeinc.models.Evaluation;
import com.widson.chalengeinc.models.Movie;
import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.repositories.EvaluationRepository;
import com.widson.chalengeinc.repositories.UserRepository;
import com.widson.chalengeinc.services.MovieService;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
	@Autowired
	private EvaluationRepository evaluationRepository;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/userId/{id}")
	public ResponseEntity<List<Evaluation>> findAllByUser(@PathVariable Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<Evaluation> evaluations = evaluationRepository.findAllByUser(user);
			return ResponseEntity.ok(evaluations);
		} else {
			return ResponseEntity.notFound().build();
		}
	}	
	
	@GetMapping
	public List<Evaluation> findAllPublic() {
		return evaluationRepository.findAllByVisualization(Visualization.PUBLIC);
	}	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Evaluation> findById(@PathVariable Integer evaluationId) {
		Optional<Evaluation> evaluation = evaluationRepository.findById(evaluationId);
		if(evaluation.isPresent()) {
			return ResponseEntity.ok(evaluation.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// Transforma o json recebido no corpo em um objeto Usuario e cria no banco de dados
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Evaluation create(@Valid @RequestBody Evaluation evaluation) {		
		return evaluationRepository.save(evaluation);
	}
	
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