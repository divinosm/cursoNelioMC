CREATE TABLE `nelio`.`telefone` (
  `cliente_id` BIGINT NOT NULL,
  `telefones` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`cliente_id`, `telefones`),
  CONSTRAINT `fk_cliente_telefone`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `nelio`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);