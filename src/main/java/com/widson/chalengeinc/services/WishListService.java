package com.widson.chalengeinc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.widson.chalengeinc.models.Movie;
import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.models.WishList;
import com.widson.chalengeinc.repositories.MovieRepository;
import com.widson.chalengeinc.repositories.UserRepository;
import com.widson.chalengeinc.repositories.WishListRepository;

public class WishListService {
	@Autowired
	public WishListRepository wishListRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public MovieRepository movieRepository;
	
	@Autowired
	public MovieService movieService;
	
//	@Autowired
//	public MovieService movieService;
//	
//	Optional<WishList> create(WishList wishList) {
//		Optional<User> user = userRepository.findById(wishList.getUser().getId());
//		
//		userService.findB
//		
//		
//		
//		
//		
//		
//		if (user.isPresent()) {
//			Movie movie = movieService.findByIdImdb(wishList.getMovie().getImdbid());
//			WishList wishL = new WishList();
//			wishL.setMovie(movie);
//			wishL.setUser(user.get());
//			wishListRepository.save(wishL);
//			return wishListRepository.save(wishL);
//		}
//		return null;
//	}
}
