package com.atkx2.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "drive")
public class Drive {

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
}
