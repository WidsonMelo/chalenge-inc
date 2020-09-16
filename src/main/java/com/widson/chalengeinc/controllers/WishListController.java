package com.widson.chalengeinc.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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

import com.widson.chalengeinc.models.Movie;
import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.models.WishList;
import com.widson.chalengeinc.repositories.UserRepository;
import com.widson.chalengeinc.repositories.MovieRepository;
import com.widson.chalengeinc.repositories.WishListRepository;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
	@Autowired
	private WishListRepository wishListRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@GetMapping("/all")
	public List<WishList> readAll() {
		return wishListRepository.findAll();	
	}
	
	// Faz a busca, se houver algum registro, retorna ele, caso contrário retorna o código 404 (não encontrado)
	@GetMapping("/{wishListId}")
	public ResponseEntity<WishList> readById(@PathVariable Integer wishListId) {
		Optional<WishList> wishList = wishListRepository.findById(wishListId);
		if(wishList.isPresent()) {
			return ResponseEntity.ok(wishList.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// Transforma o json recebido no corpo em um objeto Usuario e cria no banco de dados
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public WishList create(@Valid @RequestBody WishList wishList) {	
		Optional<User> user =  userRepository.findById(wishList.getUser().getId());
		if (user.isPresent()) {
			wishList.setUser(user.get());
		}
		
		Optional<Movie> movie =  movieRepository. findById(wishList.getMovie().getId());
		if (movie.isPresent()) {
			wishList.setMovie(movie.get());
		}

		return wishListRepository.save(wishList);
	}
	
	// Faz a busca, se o elemento existir, atualiza, caso contrrário retorna 404
	// @Valid valida as regrasdos campos para ficar igual as definições do model (@size)
	@PutMapping("/{wishListId}")
	public ResponseEntity<WishList> updateById(@Valid @PathVariable Integer wishListId, @RequestBody WishList wishList) {
		if (wishListRepository.existsById(wishListId)) {
			wishList.setId(wishListId);
			wishList = wishListRepository.save(wishList);
			return ResponseEntity.ok().body(wishList);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{wishListId}")
	public ResponseEntity<Void> delete(@PathVariable Integer wishListId){
		if (wishListRepository.existsById(wishListId)) {
			wishListRepository.deleteById(wishListId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
