-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema personal_access_database
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `personal_access_database` ;

-- -----------------------------------------------------
-- Schema personal_access_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `personal_access_database` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `personal_access_database` ;

-- -----------------------------------------------------
-- Table `personal_access_database`.`unit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`unit` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`unit` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Наименование',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB
  COMMENT = 'Подразделение';


-- -----------------------------------------------------
-- Table `personal_access_database`.`faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`faculty` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`faculty` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `full_name` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Полное название факультета',
  `short_name` VARCHAR(15) NULL DEFAULT NULL COMMENT 'Сокращенное название факультета',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB
  COMMENT = 'Факультет';


-- -----------------------------------------------------
-- Table `personal_access_database`.`position`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`position` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`position` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT 'Наименование должности',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB
  COMMENT = 'Должность';


-- -----------------------------------------------------
-- Table `personal_access_database`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`role` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `name` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Наименование роли',
  `code` VARCHAR(15) NULL DEFAULT NULL COMMENT 'Код роли для разграничения ролей',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB
  COMMENT = 'Роль пользователя';


-- -----------------------------------------------------
-- Table `personal_access_database`.`form`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`form` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`form` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `first_name` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Имя',
  `last_name` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Фамилия',
  `middle_name` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Отчество',
  `unit_id` BIGINT(20) NULL DEFAULT NULL COMMENT 'Код подразделения',
  `faculty_id` BIGINT(20) NULL DEFAULT NULL COMMENT 'Код факультета',
  `position_id` BIGINT(20) NULL DEFAULT NULL COMMENT 'Код должности',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_form_unit1_idx` (`unit_id` ASC)  COMMENT '',
  INDEX `fk_form_position1_idx` (`position_id` ASC)  COMMENT '',
  INDEX `fk_form_faculty1_idx` (`faculty_id` ASC)  COMMENT '',
  CONSTRAINT `fk_form_unit1`
  FOREIGN KEY (`unit_id`)
  REFERENCES `personal_access_database`.`unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_position1`
  FOREIGN KEY (`position_id`)
  REFERENCES `personal_access_database`.`position` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_form_faculty1`
  FOREIGN KEY (`faculty_id`)
  REFERENCES `personal_access_database`.`faculty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Анкета сотрудника';


-- -----------------------------------------------------
-- Table `personal_access_database`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`user` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `username` VARCHAR(50) NOT NULL COMMENT 'Имя пользователя',
  `password` VARCHAR(25) NOT NULL COMMENT 'Пароль пользователя',
  `form_id` BIGINT(20) NOT NULL COMMENT 'Код анкеты сотрудника',
  `role_id` BIGINT(20) NOT NULL COMMENT 'Код роли',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_user_role1_idx` (`role_id` ASC)  COMMENT '',
  INDEX `fk_user_form1_idx` (`form_id` ASC)  COMMENT '',
  CONSTRAINT `fk_user_role1`
  FOREIGN KEY (`role_id`)
  REFERENCES `personal_access_database`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_form1`
  FOREIGN KEY (`form_id`)
  REFERENCES `personal_access_database`.`form` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Пользователь';


-- -----------------------------------------------------
-- Table `personal_access_database`.`_index`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`_index` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`_index` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Наименование',
  `estimate` DOUBLE NULL DEFAULT NULL COMMENT 'Оценка (максимальная/за единицу работы)',
  `multiplier` INT NULL DEFAULT NULL COMMENT 'Множитель оценки работы (единица работы)',
  `work_name` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Наименование единицы работы',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB
  COMMENT = 'Показатель';


-- -----------------------------------------------------
-- Table `personal_access_database`.`document`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`document` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`document` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Имя файла',
  `system_name` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Уникальное имя файла на сервере',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE = InnoDB
  COMMENT = 'Документ';


-- -----------------------------------------------------
-- Table `personal_access_database`.`available_index`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`available_index` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`available_index` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `index_id` BIGINT(20) NOT NULL COMMENT 'Код показателя',
  `position_id` BIGINT(20) NOT NULL COMMENT 'Код должности',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_available_index_position1_idx` (`position_id` ASC)  COMMENT '',
  INDEX `fk_available_index_index1_idx` (`index_id` ASC)  COMMENT '',
  CONSTRAINT `fk_available_index_position1`
  FOREIGN KEY (`position_id`)
  REFERENCES `personal_access_database`.`position` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_available_index_index1`
  FOREIGN KEY (`index_id`)
  REFERENCES `personal_access_database`.`_index` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Доступный показатель';


-- -----------------------------------------------------
-- Table `personal_access_database`.`user_index`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`user_index` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`user_index` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `index_id` BIGINT(20) NOT NULL COMMENT 'Код показателя',
  `user_id` BIGINT(20) NOT NULL COMMENT 'Код пользователя',
  `self_estimate` DOUBLE NULL DEFAULT NULL COMMENT 'Личная оценка пользователя',
  `lead_estimate` DOUBLE NULL DEFAULT NULL COMMENT 'Оценка руководителя',
  `fill_date` DATE NULL DEFAULT NULL COMMENT 'Дата заполнения показателя',
  `description` LONGTEXT NULL DEFAULT NULL COMMENT 'Краткое описание о проделанной работе',
  `is_complete` TINYINT(1) NULL DEFAULT 0 COMMENT 'Поле указывает на завершенность заполнения индекса',
  `document_id` BIGINT(20) NULL DEFAULT NULL COMMENT 'Код подтверждающего документа',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_user_index_document1_idx` (`document_id` ASC)  COMMENT '',
  INDEX `fk_user_index_user1_idx` (`user_id` ASC)  COMMENT '',
  INDEX `fk_user_index_index1_idx` (`index_id` ASC)  COMMENT '',
  CONSTRAINT `fk_user_index_document1`
  FOREIGN KEY (`document_id`)
  REFERENCES `personal_access_database`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_index_user1`
  FOREIGN KEY (`user_id`)
  REFERENCES `personal_access_database`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_index_index1`
  FOREIGN KEY (`index_id`)
  REFERENCES `personal_access_database`.`_index` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Показатель пользователя';


-- -----------------------------------------------------
-- Table `personal_access_database`.`dependency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `personal_access_database`.`dependency` ;

CREATE TABLE IF NOT EXISTS `personal_access_database`.`dependency` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор',
  `user_lead_id` BIGINT(20) NOT NULL COMMENT 'Код пользователя (руководителя)',
  `user_sub_id` BIGINT(20) NOT NULL COMMENT 'Код пользователя (подчиненного)',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_dependency_user1_idx` (`user_lead_id` ASC)  COMMENT '',
  INDEX `fk_dependency_user2_idx` (`user_sub_id` ASC)  COMMENT '',
  CONSTRAINT `fk_dependency_user1`
  FOREIGN KEY (`user_lead_id`)
  REFERENCES `personal_access_database`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dependency_user2`
  FOREIGN KEY (`user_sub_id`)
  REFERENCES `personal_access_database`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  COMMENT = 'Зависимость подчиненных от руководителей';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;