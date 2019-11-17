DROP TABLE IF EXISTS `atkx2`.`goal`;
DROP TABLE IF EXISTS `atkx2`.`drive`;

CREATE TABLE `atkx2`.`drive` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_time` TIMESTAMP NULL,
  `update_time` TIMESTAMP NULL,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `atkx2`.`goal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_time` TIMESTAMP NULL,
  `update_time` TIMESTAMP NULL,
  `title` VARCHAR(45) NULL,
  `acceptance_criteria` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `start` DATETIME NULL,
  `end` DATETIME NULL,
  `drive_id` INT,
  `retro_complete` TINYINT NOT NULL DEFAULT 0,
  `criteria_was_met` TINYINT NULL,
  `criteria_not_met_reasons` VARCHAR(45) NULL,
  `goal_was_effective` TINYINT NULL,
  `retro_comments` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `drive_id`
    FOREIGN KEY (`drive_id`)
    REFERENCES `atkx2`.`drive` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
