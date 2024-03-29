-- MySQL Script generated by MySQL Workbench
-- Sat Aug 13 10:40:49 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema algalog
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `algalog` ;

-- -----------------------------------------------------
-- Schema algalog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `algalog` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema nelio
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nelio` ;

-- -----------------------------------------------------
-- Schema nelio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nelio` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
SHOW WARNINGS;
USE `algalog` ;

-- -----------------------------------------------------
-- Table `algalog`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `algalog`.`cliente` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `algalog`.`cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `algalog`.`entrega`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `algalog`.`entrega` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `algalog`.`entrega` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cliente_id` BIGINT NOT NULL,
  `taxa` DECIMAL(10,2) NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `data_pedido` DATETIME NOT NULL,
  `data_finalizacao` DATETIME NULL DEFAULT NULL,
  `destinatario_nome` VARCHAR(60) NOT NULL,
  `destinatario_logradouro` VARCHAR(255) NOT NULL,
  `destinatario_numero` VARCHAR(30) NOT NULL,
  `destinatario_complemento` VARCHAR(60) NOT NULL,
  `destinatario_bairro` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_entrega_cliente` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_entrega_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `algalog`.`cliente` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `algalog`.`flyway_schema_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `algalog`.`flyway_schema_history` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `algalog`.`flyway_schema_history` (
  `installed_rank` INT NOT NULL,
  `version` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(200) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `script` VARCHAR(1000) NOT NULL,
  `checksum` INT NULL DEFAULT NULL,
  `installed_by` VARCHAR(100) NOT NULL,
  `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` INT NOT NULL,
  `success` TINYINT(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  INDEX `flyway_schema_history_s_idx` (`success` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;
USE `nelio` ;

-- -----------------------------------------------------
-- Table `nelio`.`categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`categoria` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`categoria` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`estado` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`estado` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`cidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`cidade` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`cidade` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `estado_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKkworrwk40xj58kevvh3evi500` (`estado_id` ASC) VISIBLE,
  CONSTRAINT `FKkworrwk40xj58kevvh3evi500`
    FOREIGN KEY (`estado_id`)
    REFERENCES `nelio`.`estado` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`cliente` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cpfoucnpj` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `tipocliente` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_cmxo70m08n43599l3h0h07cc6` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`endereco`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`endereco` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`endereco` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(255) NULL DEFAULT NULL,
  `cep` VARCHAR(255) NULL DEFAULT NULL,
  `complemento` VARCHAR(255) NULL DEFAULT NULL,
  `logradouro` VARCHAR(255) NOT NULL,
  `numero` VARCHAR(255) NULL DEFAULT NULL,
  `cidade_id` BIGINT NULL DEFAULT NULL,
  `cliente_id` BIGINT NULL DEFAULT NULL,
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
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`flyway_schema_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`flyway_schema_history` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`flyway_schema_history` (
  `installed_rank` INT NOT NULL,
  `version` VARCHAR(50) NULL DEFAULT NULL,
  `description` VARCHAR(200) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `script` VARCHAR(1000) NOT NULL,
  `checksum` INT NULL DEFAULT NULL,
  `installed_by` VARCHAR(100) NOT NULL,
  `installed_on` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` INT NOT NULL,
  `success` TINYINT(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  INDEX `flyway_schema_history_s_idx` (`success` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`pedido` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`pedido` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `instante` DATETIME(6) NULL DEFAULT NULL,
  `cliente_id` BIGINT NULL DEFAULT NULL,
  `endereco_entrega_id` BIGINT NULL DEFAULT NULL,
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
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`produto` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `preco` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`item_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`item_pedido` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`item_pedido` (
  `desconto` DOUBLE NULL DEFAULT NULL,
  `preco` DOUBLE NULL DEFAULT NULL,
  `quantidade` INT NULL DEFAULT NULL,
  `pedido_id` BIGINT NOT NULL,
  `produto_id` BIGINT NOT NULL,
  PRIMARY KEY (`pedido_id`, `produto_id`),
  INDEX `FKtk55mn6d6bvl5h0no5uagi3sf` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pedido` (`id`),
  CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf`
    FOREIGN KEY (`produto_id`)
    REFERENCES `nelio`.`produto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`pagamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`pagamento` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`pagamento` (
  `pedido_id` BIGINT NOT NULL,
  `estado_pagamento_int` INT NULL DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKthad9tkw4188hb3qo1lm5ueb0`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pedido` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`pagamento_boleto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`pagamento_boleto` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`pagamento_boleto` (
  `data_pagamento` DATETIME(6) NULL DEFAULT NULL,
  `data_vencimento` DATETIME(6) NULL DEFAULT NULL,
  `pedido_id` BIGINT NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKdqk2a6n9it7vodk056daxe4jy`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pagamento` (`pedido_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`pagamento_cartao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`pagamento_cartao` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`pagamento_cartao` (
  `numero_parcelas` INT NULL DEFAULT NULL,
  `pedido_id` BIGINT NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FK936sknk6dwq1oa3p2y7d4s8ol`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `nelio`.`pagamento` (`pedido_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`pessoa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`pessoa` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`pessoa` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`produto_categoria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`produto_categoria` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`produto_categoria` (
  `produto_id` BIGINT NOT NULL,
  `categoria_id` BIGINT NOT NULL,
  INDEX `FKq3g33tp7xk2juh53fbw6y4y57` (`categoria_id` ASC) VISIBLE,
  INDEX `FK1c0y58d3n6x3m6euv2j3h64vt` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `FK1c0y58d3n6x3m6euv2j3h64vt`
    FOREIGN KEY (`produto_id`)
    REFERENCES `nelio`.`produto` (`id`),
  CONSTRAINT `FKq3g33tp7xk2juh53fbw6y4y57`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `nelio`.`categoria` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nelio`.`telefone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nelio`.`telefone` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `nelio`.`telefone` (
  `cliente_id` BIGINT NOT NULL,
  `telefones` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FK8aafha0njkoyoe3kvrwsy3g8u` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `FK8aafha0njkoyoe3kvrwsy3g8u`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `nelio`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
