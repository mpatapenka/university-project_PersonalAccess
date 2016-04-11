-- -----------------------------------------------------
-- Schema personal_access_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS personal_access_database;


-- -----------------------------------------------------
-- Table personal_access_database.unit
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.unit (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table personal_access_database.faculty
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.faculty (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  full_name VARCHAR(255) NULL DEFAULT NULL,
  short_name VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table personal_access_database.position
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.position (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table personal_access_database.role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.role (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NULL DEFAULT NULL,
  code VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table personal_access_database.form
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.form (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50) NULL DEFAULT NULL,
  last_name VARCHAR(50) NULL DEFAULT NULL,
  middle_name VARCHAR(50) NULL DEFAULT NULL,
  unit_id BIGINT(20) NULL DEFAULT NULL,
  faculty_id BIGINT(20) NULL DEFAULT NULL,
  position_id BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_form_unit1
  FOREIGN KEY (unit_id)
  REFERENCES personal_access_database.unit (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_form_position1
  FOREIGN KEY (position_id)
  REFERENCES personal_access_database.position (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_form_faculty1
  FOREIGN KEY (faculty_id)
  REFERENCES personal_access_database.faculty (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table personal_access_database.user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.user (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(25) NOT NULL,
  form_id BIGINT(20) NOT NULL,
  role_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_role1
  FOREIGN KEY (role_id)
  REFERENCES personal_access_database.role (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_user_form1
  FOREIGN KEY (form_id)
  REFERENCES personal_access_database.form (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table personal_access_database._index
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database._index (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(400) NULL DEFAULT NULL,
  estimate DOUBLE NULL DEFAULT NULL,
  multiplier INT NULL DEFAULT NULL,
  work_name VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table personal_access_database.document
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.document (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NULL DEFAULT NULL,
  system_name VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table personal_access_database.available_index
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.available_index (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  index_id BIGINT(20) NOT NULL,
  position_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_available_index_position1
  FOREIGN KEY (position_id)
  REFERENCES personal_access_database.position (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_available_index_index1
  FOREIGN KEY (index_id)
  REFERENCES personal_access_database._index (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table personal_access_database.user_index
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.user_index (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  index_id BIGINT(20) NOT NULL,
  user_id BIGINT(20) NOT NULL,
  self_estimate DOUBLE NULL DEFAULT NULL,
  lead_estimate DOUBLE NULL DEFAULT NULL,
  fill_date DATE NULL DEFAULT NULL,
  description VARCHAR(500) NULL DEFAULT NULL,
  is_complete BOOLEAN NULL DEFAULT 0,
  document_id BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_index_document1
  FOREIGN KEY (document_id)
  REFERENCES personal_access_database.document (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_user_index_user1
  FOREIGN KEY (user_id)
  REFERENCES personal_access_database.user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_user_index_index1
  FOREIGN KEY (index_id)
  REFERENCES personal_access_database._index (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table personal_access_database.dependency
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS personal_access_database.dependency (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  position_lead_id BIGINT(20) NOT NULL,
  position_sub_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_dependency_position1
  FOREIGN KEY (position_lead_id)
  REFERENCES personal_access_database.position (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_dependency_position2
  FOREIGN KEY (position_sub_id)
  REFERENCES personal_access_database.position (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);