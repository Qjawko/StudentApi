ALTER TABLE `student` ADD FULLTEXT (`second_name`, `first_name`, `patronymic`);
ALTER TABLE `department` ADD FULLTEXT (`name`);
ALTER TABLE `institution` ADD FULLTEXT (`name`);