package com.atkx2.api.endpoints;

import com.atkx2.api.contracts.DriveDTO;
import com.atkx2.api.services.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(DriveController.PATH)
public class DriveController {

    public static final String PATH = "/drive";

    @Autowired
    DriveService driveService;

    @PostMapping
    public DriveDTO post(@RequestBody DriveDTO driveDTO) {
        return driveService.post(driveDTO);
    }

    @GetMapping("{id}")
    public DriveDTO get(@PathVariable Long id){
        return driveService.get(id);
    }
}
