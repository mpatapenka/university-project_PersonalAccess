-- Populate table 'faculty'
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Факультет автоматизированных и информационных систем', 'ФАИС');



-- Populate table 'unit'
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Ректорат');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Учебно-методический отдел');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Отдел кадров');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Библиотека');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Деканат');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Канцелярия');



-- Populate table 'position'
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Сотрудник');
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Заведующий кафедрой');
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Декан');
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Проректор');



-- Populate table 'form'
INSERT INTO `personal_access_database`.`form` (`first_name`, `last_name`, `middle_name`, `unit_id`, `faculty_id`, `position_id`) VALUES ('Максим', 'Потапенко', 'Сергеевич', '2', '1', '1');



-- Populate table 'role'
INSERT INTO `personal_access_database`.`role` (`name`, `code`) VALUES ('Пользователь', 'ROLE_USER');
INSERT INTO `personal_access_database`.`role` (`name`, `code`) VALUES ('Администратор', 'ROLE_ADMIN');



-- Populate table 'user'
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('maxim', 'maxim', '1', '2');
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('user', 'user', '1', '1');