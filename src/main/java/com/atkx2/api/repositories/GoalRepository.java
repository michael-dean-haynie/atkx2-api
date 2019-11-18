package com.atkx2.api.repositories;

import com.atkx2.api.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    @Query("SELECT g FROM Goal g WHERE g.end < ?1")
//    @Query("SELECT g FROM Goal g")
    List<Goal> findGoalsThatHaveEnded(Timestamp now);
}