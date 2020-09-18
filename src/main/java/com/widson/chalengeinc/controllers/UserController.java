package com.widson.chalengeinc.controllers;

import java.util.List;

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

import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.services.UserService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Returns the complete list of all users.")
	@GetMapping("/all")
	public ResponseEntity<List<User>> findAll() {
		List<User> uses = userService.findAll(); 
		if(!uses.isEmpty()) {
			return ResponseEntity.ok(uses);
		} else {
			return ResponseEntity.notFound().build();
		}	
	}
	
	@ApiOperation(value = "Find a user by id")
	@GetMapping("/id/{userId}")
	public ResponseEntity<User> findById(@PathVariable Integer userId) {
		User user = userService.findById(userId);
		return ResponseEntity.ok().body(user);
	}
	
	@ApiOperation(value = "Create a new user.")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user) {		
		return userService.save(user);
	}
	
	@ApiOperation(value = "Updates an existing user.")
	@PutMapping("/id/{userId}")
	public ResponseEntity<User> updateById(@PathVariable Integer userId, @RequestBody User user) {
		
		if (userService.findById(userId).getId() > 0) {
			user.setId(userId);
			user = userService.save(user);
			return ResponseEntity.ok().body(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Delete an existing user.")
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> delete(@PathVariable Integer userId){
		User user = new User();
		user.setId(userId);
		userService.delete(user);
		
		return ResponseEntity.noContent().build();
	}
}
