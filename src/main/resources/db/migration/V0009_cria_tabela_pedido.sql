CREATE TABLE `nelio`.`pedido` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `instante` DATETIME NOT NULL,
  `cliente_id` BIGINT NOT NULL,
  `enderecoEntrega_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_endereco_pedido_idx` (`enderecoEntrega_id` ASC) INVISIBLE,
  INDEX `fk_cliente_pedido_idx` (`cliente_id` ASC) INVISIBLE,
  CONSTRAINT `fk_endereco_pedido`
    FOREIGN KEY (`enderecoEntrega_id`)
    REFERENCES `nelio`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_pedido`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `nelio`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);