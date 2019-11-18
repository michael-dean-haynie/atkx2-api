package com.atkx2.api.services.impl;

import com.atkx2.api.contracts.GoalDTO;
import com.atkx2.api.domain.Goal;
import com.atkx2.api.repositories.GoalRepository;
import com.atkx2.api.services.GoalService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.List;

@Service
public class DefaultGoalService implements GoalService {

    private static final Type GOAL_DTO_LIST_TYPE = new TypeToken<List<GoalDTO>>(){}.getType();

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

    @Override
    public List<GoalDTO> getGoalsThatHaveEnded() {
        return mapper.map(
                goalRepository.findGoalsThatHaveEnded(new Timestamp(System.currentTimeMillis())),
                GOAL_DTO_LIST_TYPE);
    }
}
