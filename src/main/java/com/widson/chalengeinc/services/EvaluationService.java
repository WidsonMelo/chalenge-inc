package com.widson.chalengeinc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.widson.chalengeinc.enums.Visualization;
import com.widson.chalengeinc.models.Evaluation;
import com.widson.chalengeinc.models.User;
import com.widson.chalengeinc.repositories.EvaluationRepository;

@Service
public class EvaluationService {

	private EvaluationRepository evaluationRepository;

	public List<Evaluation> findAllByUser(User user) {
		List<Evaluation> evaluations = evaluationRepository.findAllByUser(user);
		if (!evaluations.isEmpty()) {
			return evaluations;
		} else {
			return null;
		}
	}

	public List<Evaluation> findAllByVisualization(Visualization visualization) {
		List<Evaluation> evaluations = evaluationRepository.findAllByVisualization(visualization);
		return evaluations;
	}
}
