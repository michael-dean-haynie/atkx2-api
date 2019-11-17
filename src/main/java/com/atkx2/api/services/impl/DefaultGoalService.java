package com.atkx2.api.services.impl;

import com.atkx2.api.contracts.GoalDTO;
import com.atkx2.api.domain.Goal;
import com.atkx2.api.repositories.GoalRepository;
import com.atkx2.api.services.GoalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultGoalService implements GoalService {

    @Autowired
    ModelMapper mapper;
    @Autowired
    GoalRepository goalRepository;

    @Override
    public GoalDTO post(GoalDTO goalDTO) {
        Goal goal = mapper.map(goalDTO, Goal.class);
        goalRepository.save(goal);
        GoalDTO newGoalDTO = mapper.map(goal, GoalDTO.class);
        return  newGoalDTO;
    }

    @Override
    public GoalDTO get(Long id) {
        return goalRepository.findById(id)
                .map(goal -> mapper.map(goal, GoalDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
