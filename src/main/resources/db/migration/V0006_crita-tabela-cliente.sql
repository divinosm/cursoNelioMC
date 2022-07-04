CREATE TABLE `nelio`.`cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `email` VARCHAR(60) NULL,
  `cpfoucnpj` VARCHAR(20) NULL,
  `tipocliente` INT NULL,
  PRIMARY KEY (`id`));