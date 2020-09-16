package com.widson.chalengeinc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widson.chalengeinc.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{

}
