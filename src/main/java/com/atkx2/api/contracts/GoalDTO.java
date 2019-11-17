package com.atkx2.api.contracts;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class GoalDTO {

    private Long id;

    private Timestamp createTime;

    private Timestamp updateTime;

    private String title;

    private String acceptanceCriteria;

    private String description;

    private Timestamp start;

    private Timestamp end;

    private DriveDTO drive;

    private Boolean retroComplete;

    private Boolean criteriaWasMet;

    private String criteriaNotMetReasons;

    private Boolean goalWasEffective;

    private String retroComments;
}
