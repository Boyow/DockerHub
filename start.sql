CREATE DATABASE IF NOT EXISTS `pfwco_lab4`;
CREATE TABLE `pfwco_lab4`.`pracownik` ( 
`id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
`nazwisko` VARCHAR(50) NOT NULL , 
`pensja` VARCHAR(10) NOT NULL , 
`firma` VARCHAR(400) NOT NULL
); 
INSERT INTO `pfwco_lab4`.`pracownik`(nazwisko, pensja, firma) VALUES 
("Cichy", "3", "Goudex"),
("Marcinek", "333", "testowa"),
("Komar","23","Dostra"),
("Wesoły","52","Kwantowa");