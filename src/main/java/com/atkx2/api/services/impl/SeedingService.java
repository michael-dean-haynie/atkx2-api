package com.atkx2.api.services.impl;

import com.atkx2.api.domain.Drive;
import com.atkx2.api.domain.Goal;
import com.atkx2.api.repositories.DriveRepository;
import com.atkx2.api.repositories.GoalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SeedingService {
    private static Logger LOG = LoggerFactory.getLogger(SeedingService.class);

    @Value("${atkx2.seed-db-on-startup}")
    private boolean seedDbOnStartup;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DriveRepository driveRepository;
    @Autowired
    private GoalRepository goalRepository;

    private Map<String, Drive> driveMap = new HashMap<>();
    private Map<String, Goal> goalMap = new HashMap<>();

    @Transactional
    @EventListener()
    public void seedDatabase(ContextRefreshedEvent event) {
        LOG.info("seed-on-startup functionality enabled: " + seedDbOnStartup);
        if (!seedDbOnStartup) { return; }

        emptyTables();
        resetAutoIncrement();

        /**
         * -------------------------------------------------------------------------------------------------------------------
         * Seed Drive Entities
         * -------------------------------------------------------------------------------------------------------------------
         */
        createDrive("Drive 1");

        /**
         * -------------------------------------------------------------------------------------------------------------------
         * Seed Goal Entities
         * -------------------------------------------------------------------------------------------------------------------
         */
        createGoal(Goal.builder().title("Goal 1").acceptanceCriteria("criteria").description("description")
                .start(hoursAgo(0)).end(hoursAgo(0))
                .drive(driveMap.get("Drive 1")).build());
        createGoal(Goal.builder().title("Goal 2").acceptanceCriteria("criteria").description("description")
                .start(hoursAgo(1)).end(hoursAgo(1))
                .drive(driveMap.get("Drive 1")).build());
        createGoal(Goal.builder().title("Goal 3").acceptanceCriteria("criteria").description("description")
                .start(hoursAgo(2)).end(hoursAgo(3))
                .drive(driveMap.get("Drive 1")).build());

    }

    private void emptyTables() {
        // order probably matters
        jdbcTemplate.execute("DELETE FROM `atkx2`.`goal`  WHERE `id` > 0;");
        jdbcTemplate.execute("DELETE FROM `atkx2`.`drive` WHERE `id` > 0;");
    }

    private void resetAutoIncrement(){
        jdbcTemplate.execute("ALTER TABLE `atkx2`.`drive` AUTO_INCREMENT = 1;");
        jdbcTemplate.execute("ALTER TABLE `atkx2`.`goal`  AUTO_INCREMENT = 1;");
    }


    private void createDrive(String title) {
        Drive drive = new Drive();
        drive.setTitle(title);
        driveRepository.save(drive);
        driveMap.put(drive.getTitle(), drive);
    }

    private void createGoal(Goal goal) {
        goal.setRetroComplete(false);
        goalRepository.save(goal);
        goalMap.put(goal.getTitle(), goal);
    }

    private Timestamp hoursAgo(int hours) {
        return new Timestamp(System.currentTimeMillis() - (TimeUnit.HOURS.toMillis(hours)));
    }
}
