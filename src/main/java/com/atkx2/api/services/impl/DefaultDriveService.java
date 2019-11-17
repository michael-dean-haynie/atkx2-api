package com.atkx2.api.services.impl;

import com.atkx2.api.contracts.DriveDTO;
import com.atkx2.api.domain.Drive;
import com.atkx2.api.repositories.DriveRepository;
import com.atkx2.api.services.DriveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultDriveService implements DriveService {

    @Autowired
    ModelMapper mapper;
    @Autowired
    DriveRepository driveRepository;

    @Override
    public DriveDTO post(DriveDTO driveDTO) {
        Drive drive = mapper.map(driveDTO, Drive.class);
        driveRepository.save(drive);
        DriveDTO newDriveDTO = mapper.map(drive, DriveDTO.class);
        return  newDriveDTO;
    }

    @Override
    public DriveDTO get(Long id) {
        return driveRepository.findById(id)
                .map(goal -> mapper.map(goal, DriveDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
