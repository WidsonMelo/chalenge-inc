package com.widson.chalengeinc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widson.chalengeinc.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
