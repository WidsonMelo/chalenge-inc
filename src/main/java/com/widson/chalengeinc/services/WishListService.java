package com.widson.chalengeinc.services;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widson.chalengeinc.models.Movie;
import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.models.WishList;
import com.widson.chalengeinc.repositories.WishListRepository;

@Service
public class WishListService {

	@Autowired
	private WishListRepository wishListRepository;

	@Autowired
	public UserService userService;

	@Autowired
	public MovieService movieService;

	public WishList createWishList(User user) {

		WishList wishList = new WishList(null, user, null);

		return this.wishListRepository.save(wishList);

	}

	public void addMovie(Integer userId, String movieImdbId) {

		User user = this.userService.findById(userId);

		WishList wishList = user.getWishList();

		Movie imdbMovie = this.movieService.findByIdImdb(movieImdbId);

		imdbMovie.setWishlist(wishList);
		
		if (wishList.getMovies() == null) {

			wishList.setMovies(new HashSet<>());

		}

		wishList.getMovies().add(imdbMovie);

		this.wishListRepository.save(wishList);

	}

}
