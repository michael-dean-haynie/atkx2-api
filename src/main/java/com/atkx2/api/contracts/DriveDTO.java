package com.atkx2.api.contracts;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DriveDTO {

    private Long id;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String title;
}
