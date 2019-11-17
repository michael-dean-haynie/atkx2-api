package com.atkx2.api.services;

import com.atkx2.api.contracts.DriveDTO;

public interface DriveService {

    DriveDTO post(DriveDTO driveDTO);

    DriveDTO get(Long id);
}
