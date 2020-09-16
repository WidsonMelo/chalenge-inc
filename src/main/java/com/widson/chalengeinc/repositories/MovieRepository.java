package com.widson.chalengeinc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widson.chalengeinc.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
//	List<Movie> findByTitle(String title);
//	List<Movie> findByYear(String year);
//	List<Movie> findByRuntime(String runtime);
//	List<Movie> findByGenre(String genre);
//	List<Movie> findByDirector(String director);
//	List<Movie> findByWriter(String writer);
//	List<Movie> findByActors(String actors);
//	List<Movie> findByPlot(String plot);
//	List<Movie> findByLanguage(String language);
//	List<Movie> findByCountry(String country);
}
