package com.widson.chalengeinc.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.widson.chalengeinc.models.WishList;
import com.widson.chalengeinc.repositories.WishListRepository;
import com.widson.chalengeinc.services.WishListService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
	@Autowired
	private WishListRepository wishListRepository;

	@Autowired
	private WishListService wishListService;

	@GetMapping("/all")
	public List<WishList> readAll() {
		return wishListRepository.findAll();
	}

	@ApiOperation(value = "Find a wishlist by id")
	@GetMapping("/{wishListId}")
	public ResponseEntity<WishList> readById(@PathVariable Integer wishListId) {
		Optional<WishList> wishList = wishListRepository.findById(wishListId);
		if (wishList.isPresent()) {
			return ResponseEntity.ok(wishList.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "Create a new wishlist")
	@GetMapping()
	public ResponseEntity<WishList> create(@RequestBody Integer wishListId) {
		Optional<WishList> wishList = wishListRepository.findById(wishListId);
		if (wishList.isPresent()) {
			return ResponseEntity.ok(wishList.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "Updates a wishlist by id")
	@PutMapping("/{wishListId}")
	public ResponseEntity<WishList> updateById(@Valid @PathVariable Integer wishListId,
			@RequestBody WishList wishList) {
		if (wishListRepository.existsById(wishListId)) {
			wishList.setId(wishListId);
			wishList = wishListRepository.save(wishList);
			return ResponseEntity.ok().body(wishList);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "Insert a new movie to wish list")
	@PatchMapping("/userId/{userId}/movieImdbId/{movieImdbId}")
	public ResponseEntity<WishList> addMovie(@PathVariable Integer userId, @PathVariable String movieImdbId) {

		this.wishListService.addMovie(userId, movieImdbId);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

	@ApiOperation(value = "Delete a wishlist by id")
	@DeleteMapping("/{wishListId}")
	public ResponseEntity<Void> delete(@PathVariable Integer wishListId) {
		if (wishListRepository.existsById(wishListId)) {
			wishListRepository.deleteById(wishListId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
