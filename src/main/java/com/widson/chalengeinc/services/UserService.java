package com.widson.chalengeinc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widson.chalengeinc.exceptions.ObjectNotFoundException;
import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.models.WishList;
import com.widson.chalengeinc.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {

		return userRepository.findAll();

	}

	public User findById(Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {

			throw new ObjectNotFoundException("user.not-found");

		}

		return user.get();

	}

	public User create(User user) {

		user.setWishList(new WishList(user));

		return this.userRepository.save(user);

	}

	public void update(Integer id, User updateUser) {

		User user = this.findById(id);

		BeanUtils.copyProperties(updateUser, user, "id", "wishList");

		this.userRepository.save(user);

	}

	public void delete(Integer id) {

		User user = this.findById(id);

		this.userRepository.delete(user);

	}

}
