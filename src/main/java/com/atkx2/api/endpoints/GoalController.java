package com.atkx2.api.endpoints;

import com.atkx2.api.contracts.GoalDTO;
import com.atkx2.api.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(GoalController.PATH)
public class GoalController {

    public static final String PATH = "/goal";

    @Autowired
    GoalService goalService;

    @PostMapping
    public GoalDTO post(@RequestBody GoalDTO goalDTO) {
        return goalService.post(goalDTO);
    }

    @GetMapping("{id}")
    public GoalDTO get(@PathVariable Long id){
        return goalService.get(id);
    }

    @GetMapping("/feed/retro")
    public List<GoalDTO> getFeedRetro() {
        return goalService.getGoalsThatHaveEnded();
    }
}
