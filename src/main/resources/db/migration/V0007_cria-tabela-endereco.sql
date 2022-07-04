CREATE TABLE `nelio`.`endereco` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(60) NULL,
  `numero` VARCHAR(10) NULL,
  `complemento` VARCHAR(60) NULL,
  `bairro` VARCHAR(60) NULL,
  `cep` VARCHAR(20) NULL,
  `cidade_id` BIGINT NULL,
  `cliente_id` BIGINT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cliente_endereco`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `nelio`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cidade_endereco`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `nelio`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)