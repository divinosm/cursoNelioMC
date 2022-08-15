-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nelio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nelio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nelio` DEFAULT CHARACTER SET utf8 ;
USE `nelio` ;

-- -----------------------------------------------------
-- Table `nelio`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`categoria` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`estado` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`cidade` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `estado_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKkworrwk40xj58kevvh3evi500` (`estado_id` ASC) VISIBLE,
  CONSTRAINT `FKkworrwk40xj58kevvh3evi500`
    FOREIGN KEY (`estado_id`)
    REFERENCES `nelio`.`estado` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`cliente` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cpfoucnpj` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `tipocliente` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`endereco` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(255) NULL DEFAULT NULL,
  `cep` VARCHAR(255) NULL DEFAULT NULL,
  `complemento` VARCHAR(255) NULL DEFAULT NULL,
  `logradouro` VARCHAR(255) NOT NULL,
  `numero` VARCHAR(255) NULL DEFAULT NULL,
  `cidade_id` BIGINT(20) NULL DEFAULT NULL,
  `cliente_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK8b1kcb3wucapb8dejshyn5fsx` (`cidade_id` ASC) VISIBLE,
  INDEX `FK8s7ivtl4foyhrfam9xqom73n9` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `FK8b1kcb3wucapb8dejshyn5fsx`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `nelio`.`cidade` (`id`),
  CONSTRAINT `FK8s7ivtl4foyhrfam9xqom73n9`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `nelio`.`cliente` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`pedido` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `instante` DATETIME(6) NULL DEFAULT NULL,
  `cliente_id` BIGINT(20) NULL DEFAULT NULL,
  `endereco_entrega_id` BIGINT(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id` ASC) VISIBLE,
  INDEX `FKcrxxe5rpllxbh0sfi4a6rwphb` (`endereco_entrega_id` ASC) VISIBLE,
  CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `nelio`.`cliente` (`id`),
  CONSTRAINT `FKcrxxe5rpllxbh0sfi4a6rwphb`
    FOREIGN KEY (`endereco_entrega_id`)
    REFERENCES `nelio`.`endereco` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`produto` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `preco` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`item_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`item_pedido` (
  `desconto` DOUBLE NULL DEFAULT NULL,
  `preco` DOUBLE NULL DEFAULT NULL,
  `quantidade` INT(11) NULL DEFAULT NULL,
  `pedido_id` BIGINT(20) NOT NULL,
  `produto_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`pedido_id`, `produto_id`),
  INDEX `FKtk55mn6d6bvl5h0no5uagi3sf` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pedido` (`id`),
  CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf`
    FOREIGN KEY (`produto_id`)
    REFERENCES `nelio`.`produto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`pagamento` (
  `pedido_id` BIGINT(20) NOT NULL,
  `estado_pagamento_int` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKthad9tkw4188hb3qo1lm5ueb0`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pedido` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`pagamento_boleto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`pagamento_boleto` (
  `data_pagamento` DATETIME(6) NULL DEFAULT NULL,
  `data_vencimento` DATETIME(6) NULL DEFAULT NULL,
  `pedido_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKdqk2a6n9it7vodk056daxe4jy`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pagamento` (`pedido_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`pagamento_cartao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`pagamento_cartao` (
  `numero_parcelas` INT(11) NULL DEFAULT NULL,
  `pedido_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FK936sknk6dwq1oa3p2y7d4s8ol`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pagamento` (`pedido_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`produto_categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`produto_categoria` (
  `produto_id` BIGINT(20) NOT NULL,
  `categoria_id` BIGINT(20) NOT NULL,
  INDEX `FK1c0y58d3n6x3m6euv2j3h64vt` (`produto_id` ASC) VISIBLE,
  INDEX `FKq3g33tp7xk2juh53fbw6y4y57` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `FK1c0y58d3n6x3m6euv2j3h64vt`
    FOREIGN KEY (`produto_id`)
    REFERENCES `nelio`.`produto` (`id`),
  CONSTRAINT `FKq3g33tp7xk2juh53fbw6y4y57`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `nelio`.`categoria` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nelio`.`telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nelio`.`telefone` (
  `cliente_id` BIGINT(20) NOT NULL,
  `telefones` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FK8aafha0njkoyoe3kvrwsy3g8u` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `FK8aafha0njkoyoe3kvrwsy3g8u`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `nelio`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
