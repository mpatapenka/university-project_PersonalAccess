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



-- Populate table 'index'

-- Indexes for deans
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Выполнение контрольных цифр приема для получения образования, в т.ч. за счет средств республиканского бюджета',
  '50',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Обеспечение не менее 90% выпускников, обучавшихся за счет средств республиканского бюджета в дневной форме получения образования, первым рабочим местом',
  '50',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Определение востребованной подготовки специалистов с учетом анализа рынка труда, организация заключения (заявок) договоров о взаимодействии с организациями-заказчиками кадров',
  '40',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Осуществление профориентационной деятельности',
  '40',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Укомплектованность факультета кадрами: доля численности научных работников высшей квалификации свыше 40%',
  '50',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Прием для работы в штат высоквалифицированного преподавателя с ученой степенью',
  '20',
  '1',
  'преподавателя'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Количество студентов из числа резидентов Республики Беларусь, обучающихся на платной основе',
  '10',
  '100',
  'студентов'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Количество студентов из числа нерезидентов Республики Беларусь, обучающихся на платной основе',
  '10',
  '50',
  'студентов'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Создание и функционирование филиалов кафедр по месту нахождения организаций промышленности, других отраслей экономики и социальной сферы',
  '40',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Организация научно-методического обеспечения образовательного процесса (своевременная разработка, обновление и экспертиза учебно-программной документации образовательных программ, учебно-методической документации, подготовка и, в том числе электронных учебных изданий)',
  '30',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Подготовка и издание учебников и учебных пособий с грифом министерства образования Республики Беларусь',
  '50',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Использование новых форм управления образовательным процессом, научное и организационно-методическое обеспечение мониторинга качества образования и удовлетворенности потребителей, разработка и внедрение в образовательный процесс современных образовательных технологий и средств обучения',
  '20',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Прохождение студентами производственных практик на оплачиваемых рабочих местах по профилю их специальности, получение во время практик рабочих профессий',
  '20',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Открытие подготовки по новым специальностям (направлениям специальностей)',
  '100',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Введение в учебные планы новых современных учебных дисциплин, востребованных работодателями и абитуриентами',
  '5',
  '1',
  'учебный курс'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Результативное участие работников и студентов в международных и республиканских конкурсах, олимпиадах, Всемирной студенческой Универсиаде, Республиканской Универсиаде, фестивалях, слетах, выставках, спортивно-массовых и др. мероприятиях',
  '50',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Организация обучения на иностранном языке по отдельным специальностям (направлениям специальностей)',
  '50',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Организация обучения на иностранном языке по отдельным учебным дисциплинам',
  '30',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Организация обучения на иностранном языке по программам дополнительного образования взрослых',
  '20',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Обучение студентов в организациях иностранных государств на основании международных программ, международных договоров Республики Беларусь и организацией иностранного государства (международной организацией, иностранным гражданином, лицом без гражданства), участие в программах академической мобильности',
  '20',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Участие сотрудников и студентов в выполнение международных проектов',
  '10',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Прохождение стажировок профессорско-преподавательского состава факультета в ведущих учебных заведениях за рубежом',
  '10',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Приглашение высоквалифицированного иностранного специалиста-преподавателя для проведения учебных занятий для студентов профильной специальности',
  '10',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Количество публикаций в рецензируемых изданиях по профилю деятельности факультета из перечня ВАК',
  '2',
  '1',
  'публикацию'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Участие сотрудников факультета в выполнении государственных, отраслевых, региональных научных и научно-технических программ, научном сопровождении государственных программ',
  '20',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Подготовка и издание научных монографий сотрудниками факультета по профилю его деятельности',
  '30',
  '1',
  'монографию'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Участие в выполнении мероприятий государственной (отраслевой) программы инновационного развития',
  '30',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Создание специализированных, образовательно-научно-производственных объединений по приоритетным направлениям развития экономики, науки и социальной сферы и др.',
  '20',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Увеличение объемов выполняемых научно-исследовательских работ, финансируемых за счет внебюджетных средств, исчисленное по сравнению с соответствующим периодом прошлого года в сопоставимых ценах, млн. руб.%',
  '30',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Обеспечение эффективной работы аспирантуры',
  '20',
  '1',
  'защитившегося'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Количество эффективно функционирующих учебно-исследовательских и научно-исследовательских студенческих лабораторий (бюро)',
  '20',
  '1',
  'лабораторию'
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Работа по обновлению материальной базы, освоение средств по государственным программам, инвестиционной программе, ремонту и приобретению оборудования и др.',
  '20',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Выполнение доведенных показателей по экспорту образовательных услуг на 100%',
  '100',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Выполнение доведенных показателей по экспорту научных услуг на 100%',
  '100',
  '1',
  ''
);
INSERT INTO `personal_access_database`.`_index` (`name`, `estimate`, `multiplier`, `work_name`) VALUES (
  'Своевременность выполнения приказов, распоряжений, поручений- заполняется проректорами',
  '20',
  '1',
  ''
);