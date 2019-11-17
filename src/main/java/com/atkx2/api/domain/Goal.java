package com.atkx2.api.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity()
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "title")
    private String title;

    @Column(name = "acceptance_criteria")
    private String acceptanceCriteria;

    @Column(name = "description")
    private String description;

    @Column(name = "start")
    private Timestamp start;

    @Column(name = "end")
    private Timestamp end;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "drive_id")
    private Drive drive;

    @Column(name = "retro_complete")
    private Boolean retroComplete;

    @Column(name = "criteria_was_met")
    private Boolean criteriaWasMet;

    @Column(name = "criteria_not_met_reasons")
    private String criteriaNotMetReasons;

    @Column(name = "goal_was_effective")
    private Boolean goalWasEffective;

    @Column(name = "retro_comments")
    private String retroComments;
}
