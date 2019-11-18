package com.atkx2.api.services;

import com.atkx2.api.contracts.GoalDTO;

import java.util.List;

public interface GoalService {

    GoalDTO post(GoalDTO goalDTO);

    GoalDTO get(Long id);

    List<GoalDTO> getGoalsThatHaveEnded();
}
