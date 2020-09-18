package com.widson.chalengeinc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widson.chalengeinc.enums.Visualization;
import com.widson.chalengeinc.models.Evaluation;
import com.widson.chalengeinc.models.User;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{
	List<Evaluation> findAllByVisualization(Visualization visualization);
	List<Evaluation> findAllByUser(User user);
}