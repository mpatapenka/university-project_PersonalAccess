-- Populate table 'faculty'
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Факультет автоматизированных и информационных систем', 'ФАИС');
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Гуманитарно-экономический факультет', 'ГЭФ');
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Машиностроительный факультет', 'МФ');
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Механико-технологический факультет', 'МТФ');
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Энергетический факультет', 'ЭФ');
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Заочный факультет', 'ЗФ');
INSERT INTO `personal_access_database`.`faculty` (`full_name`, `short_name`) VALUES ('Факультет довузовой подготовки', 'ФДП');



-- Populate table 'unit'
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Ректорат');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Учебно-методический отдел');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Отдел кадров');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Библиотека');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Деканат');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Канцелярия');
INSERT INTO `personal_access_database`.`unit` (`name`) VALUES ('Кафедра');



-- Populate table 'position'
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Сотрудник');
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Заведующий кафедрой');
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Декан');
INSERT INTO `personal_access_database`.`position` (`name`) VALUES ('Проректор');



-- Populate table 'dependency'
INSERT INTO `personal_access_database`.`dependency` (`position_lead_id`, `position_sub_id`) VALUES ('2', '1');
INSERT INTO `personal_access_database`.`dependency` (`position_lead_id`, `position_sub_id`) VALUES ('3', '2');
INSERT INTO `personal_access_database`.`dependency` (`position_lead_id`, `position_sub_id`) VALUES ('4', '3');



-- Populate table 'form'
INSERT INTO `personal_access_database`.`form` (`first_name`, `last_name`, `middle_name`, `unit_id`, `faculty_id`, `position_id`) VALUES ('Максим', 'Потапенко', 'Сергеевич', '2', '1', '1');
INSERT INTO `personal_access_database`.`form` (`first_name`, `last_name`, `middle_name`, `unit_id`, `faculty_id`, `position_id`) VALUES ('Михаил', 'Астапенко', 'Семенович', '4', '1', '2');
INSERT INTO `personal_access_database`.`form` (`first_name`, `last_name`, `middle_name`, `unit_id`, `faculty_id`, `position_id`) VALUES ('Игорь', 'Заримов', 'Игнатьевич', '6', '1', '1');
INSERT INTO `personal_access_database`.`form` (`first_name`, `last_name`, `middle_name`, `unit_id`, `faculty_id`, `position_id`) VALUES ('Тест', 'Проректор', 'Ректорат', '1', '1', '4');
INSERT INTO `personal_access_database`.`form` (`first_name`, `last_name`, `middle_name`, `unit_id`, `faculty_id`, `position_id`) VALUES ('Тест', 'Декан', 'Деканат', '5', '1', '3');
INSERT INTO `personal_access_database`.`form` (`first_name`, `last_name`, `middle_name`, `unit_id`, `faculty_id`, `position_id`) VALUES ('Тест', 'ЗаведующийКафедрой', 'Кафедра', '7', '1', '2');



-- Populate table 'role'
INSERT INTO `personal_access_database`.`role` (`name`, `code`) VALUES ('Пользователь', 'ROLE_USER');
INSERT INTO `personal_access_database`.`role` (`name`, `code`) VALUES ('Администратор', 'ROLE_ADMIN');



-- Populate table 'user'
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('maxim', 'maxim', '1', '2');
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('mihail', '', '2', '1');
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('ihar', '', '3', '1');
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('prorektor', '', '4', '1');
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('dekan', '', '5', '1');
INSERT INTO `personal_access_database`.`user` (`username`, `password`, `form_id`, `role_id`) VALUES ('zavkaf', '', '6', '1');