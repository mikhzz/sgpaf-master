-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema vga_audit
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `vga_audit` ;

-- -----------------------------------------------------
-- Schema vga_audit
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vga_audit` DEFAULT CHARACTER SET utf8mb4 ;
USE `vga_audit` ;

-- -----------------------------------------------------
-- Table `vga_audit`.`empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`empresa` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`empresa` (
  `ID_EMPRESA` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NOT NULL,
  `CNPJ` VARCHAR(14) NOT NULL,
  `ENDERECO` VARCHAR(255) NULL DEFAULT NULL,
  `TELEFONE` VARCHAR(14) NULL DEFAULT NULL,
  `TIPO` VARCHAR(50) NULL DEFAULT NULL,
  `EMAIL` VARCHAR(255) NULL DEFAULT NULL,
  `RESPONSAVEL` VARCHAR(255) NULL DEFAULT NULL,
  `ATIVO` TINYINT(2) NULL DEFAULT 1,
  PRIMARY KEY (`ID_EMPRESA`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`balancete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`balancete` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`balancete` (
  `ID_BALANCETE` INT(11) NOT NULL AUTO_INCREMENT,
  `SALDOTOTAL` DOUBLE NULL DEFAULT NULL,
  `DATA_BASE` INT(11) NULL DEFAULT NULL,
  `DATA_BALANCO_INICIO` DATE NULL DEFAULT NULL,
  `DATA_BALANCO_FIM` DATE NULL DEFAULT NULL,
  `ATIVO` TINYINT(2) NULL DEFAULT 1,
  `ID_EMPRESA` INT(11) NOT NULL,
  PRIMARY KEY (`ID_BALANCETE`),
  INDEX `FK_EMPRESA_BALANCETE` (`ID_EMPRESA` ASC) VISIBLE,
  CONSTRAINT `FK_EMPRESA_BALANCETE`
    FOREIGN KEY (`ID_EMPRESA`)
    REFERENCES `vga_audit`.`empresa` (`ID_EMPRESA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 154
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`procedimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`procedimento` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`procedimento` (
  `ID_PROCEDIMENTO` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_BALANCETE` INT(11) NOT NULL,
  `REFPT` VARCHAR(10) NOT NULL,
  `CONCLUSOES` VARCHAR(1024) NULL DEFAULT NULL,
  `ULTIMA_ALTERACAO` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`ID_PROCEDIMENTO`),
  UNIQUE INDEX `REFPT_unique` (`ID_BALANCETE` ASC, `REFPT` ASC) VISIBLE,
  INDEX `FK_PROCEDIMENTO_BALANCETE` (`ID_BALANCETE` ASC) VISIBLE,
  CONSTRAINT `FK_PROCEDIMENTO_BALANCETE`
    FOREIGN KEY (`ID_BALANCETE`)
    REFERENCES `vga_audit`.`balancete` (`ID_BALANCETE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 142
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`checkbox`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`checkbox` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`checkbox` (
  `ID_CHECKBOX` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(100) NULL DEFAULT NULL,
  `SELECIONADO` TINYINT(1) NOT NULL DEFAULT 0,
  `ID_PROCEDIMENTO` INT(11) NULL DEFAULT NULL,
  `DATA_CHECK` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`ID_CHECKBOX`),
  INDEX `FK_CHECKBOX_PROCEDIMENTO_idx` (`ID_PROCEDIMENTO` ASC) VISIBLE,
  CONSTRAINT `FK_CHECKBOX_PROCEDIMENTO`
    FOREIGN KEY (`ID_PROCEDIMENTO`)
    REFERENCES `vga_audit`.`procedimento` (`ID_PROCEDIMENTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 39
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`folhamestra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`folhamestra` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`folhamestra` (
  `ID_FOLHAMESTRA` INT(11) NOT NULL AUTO_INCREMENT,
  `CONCLUSAO` VARCHAR(1024) NULL DEFAULT NULL,
  `ULTIMA_ALTERACAO` DATE NULL DEFAULT NULL,
  `ATIVO` TINYINT(2) NULL DEFAULT 1,
  `ID_BALANCETE` INT(11) NOT NULL,
  `CHECKBOX_REVISADO` TINYINT(1) NOT NULL DEFAULT 0,
  `DATA_REVISADO` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`ID_FOLHAMESTRA`),
  INDEX `FK_FOLHAMESTRA_BALANCETE` (`ID_BALANCETE` ASC) VISIBLE,
  CONSTRAINT `FK_FOLHAMESTRA_BALANCETE`
    FOREIGN KEY (`ID_BALANCETE`)
    REFERENCES `vga_audit`.`balancete` (`ID_BALANCETE`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 148
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`lancamento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`lancamento` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`lancamento` (
  `ID_LANCAMENTO` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `REFPT` VARCHAR(10) NOT NULL,
  `NATUREZA_CONTA` VARCHAR(32) NOT NULL,
  `NIVEL` INT(11) NOT NULL,
  `CODIGO_ESTRUTURADO` VARCHAR(25) NOT NULL,
  `CODIGO_REDUZIDO` INT(11) NOT NULL,
  `DESCRICAO` VARCHAR(255) NOT NULL,
  `SALDO_INICIAL` DECIMAL(20,2) NOT NULL,
  `DEBITO` DECIMAL(20,2) NOT NULL,
  `CREDITO` DECIMAL(20,2) NOT NULL,
  `SALDO_FINAL` DECIMAL(20,2) NOT NULL,
  `ID_BALANCETE` INT(11) NOT NULL,
  `AH` DECIMAL(20,2) NULL DEFAULT 0.00,
  `AV` DECIMAL(20,2) NULL DEFAULT 0.00,
  `SALDO_VALIDADO` DECIMAL(20,2) NULL DEFAULT 0.00,
  `DIFERENCA` DECIMAL(20,2) NULL DEFAULT 0.00,
  `DIVERGENCIA` TINYINT(1) NULL DEFAULT 0,
  `RECOMENDACAO` VARCHAR(1204) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_LANCAMENTO`),
  INDEX `FK_LANCAMENTO_BALANCETE` (`ID_BALANCETE` ASC) VISIBLE,
  CONSTRAINT `FK_LANCAMENTO_BALANCETE`
    FOREIGN KEY (`ID_BALANCETE`)
    REFERENCES `vga_audit`.`balancete` (`ID_BALANCETE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9030
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`materialidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`materialidade` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`materialidade` (
  `ID_MATERIALIDADE` INT(11) NOT NULL AUTO_INCREMENT,
  `LUCRO` DECIMAL(20,2) NULL DEFAULT NULL,
  `LUCRO_PORC` DECIMAL(4,1) NULL DEFAULT NULL,
  `ATIVO_TOTAL` DECIMAL(20,2) NULL DEFAULT NULL,
  `ATIVO_PORC` DECIMAL(4,1) NULL DEFAULT NULL,
  `RECEITA` DECIMAL(20,2) NULL DEFAULT NULL,
  `RECEITA_PORC` DECIMAL(4,1) NULL DEFAULT NULL,
  `PATRIMONIO` DECIMAL(20,2) NULL DEFAULT NULL,
  `PATRIMONIO_PORC` DECIMAL(4,1) NULL DEFAULT NULL,
  `MATERIALIDADE_GLOBAL` DECIMAL(20,2) NULL DEFAULT NULL,
  `ATIVO` TINYINT(2) NULL DEFAULT 1,
  `ID_BALANCETE` INT(11) NOT NULL,
  PRIMARY KEY (`ID_MATERIALIDADE`),
  INDEX `FK_MATERIALIDADE_BALANCETE_idx` (`ID_BALANCETE` ASC) VISIBLE,
  CONSTRAINT `FK_MATERIALIDADE_BALANCETE`
    FOREIGN KEY (`ID_BALANCETE`)
    REFERENCES `vga_audit`.`balancete` (`ID_BALANCETE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`perfil` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`perfil` (
  `ID_PERFIL` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(255) NULL DEFAULT NULL,
  `TIPO` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_PERFIL`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`reg_empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`reg_empresa` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`reg_empresa` (
  `IDREG_EMPRESA` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_EMPRESA` INT(11) NOT NULL,
  `NOME` VARCHAR(255) NOT NULL,
  `CNPJ` VARCHAR(20) NOT NULL,
  `ENDERECO` VARCHAR(255) NULL DEFAULT NULL,
  `TELEFONE` VARCHAR(15) NULL DEFAULT NULL,
  `TIPO` VARCHAR(50) NULL DEFAULT NULL,
  `EMAIL` VARCHAR(30) NULL DEFAULT NULL,
  `RESPONSAVEL` VARCHAR(255) NULL DEFAULT NULL,
  `COD_OPERACAO` VARCHAR(20) NOT NULL,
  `CURRENT_USERNAME` VARCHAR(30) NOT NULL,
  `DT_ULT_ALTERACAO` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `ATIVO` INT(11) NULL DEFAULT 1,
  PRIMARY KEY (`IDREG_EMPRESA`),
  INDEX `fk_empresa_has_reg_empresa_idx` (`ID_EMPRESA` ASC) VISIBLE,
  CONSTRAINT `fk_empresa_has_reg_empresa_idx`
    FOREIGN KEY (`ID_EMPRESA`)
    REFERENCES `vga_audit`.`empresa` (`ID_EMPRESA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`usuario` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`usuario` (
  `ID_USUARIO` INT(11) NOT NULL AUTO_INCREMENT,
  `NOME` VARCHAR(255) NOT NULL,
  `CPF` VARCHAR(11) NOT NULL,
  `CARGO` VARCHAR(30) NOT NULL,
  `LOGIN` VARCHAR(100) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NOT NULL,
  `SENHA` VARCHAR(565) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `ATIVO` TINYINT(2) NULL DEFAULT 1,
  `ENABLE` TINYINT(2) NULL DEFAULT 1,
  `ID_PERFIL` INT(11) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  INDEX `FK_USUARIO_PERFIL` (`ID_PERFIL` ASC) VISIBLE,
  CONSTRAINT `FK_USUARIO_PERFIL`
    FOREIGN KEY (`ID_PERFIL`)
    REFERENCES `vga_audit`.`perfil` (`ID_PERFIL`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 56
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`reg_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`reg_usuario` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`reg_usuario` (
  `IDREG_USUARIO` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` INT(11) NOT NULL,
  `NOME` VARCHAR(255) NOT NULL,
  `CPF` VARCHAR(14) NOT NULL,
  `CARGO` VARCHAR(30) NOT NULL,
  `LOGIN` VARCHAR(100) NOT NULL,
  `SENHA` VARCHAR(565) CHARACTER SET 'latin1' COLLATE 'latin1_bin' NULL DEFAULT NULL,
  `ATIVO` INT(11) NULL DEFAULT 1,
  `HABILITADO` INT(11) NULL DEFAULT 1,
  `ID_PERFIL` INT(11) NOT NULL,
  `COD_OPERACAO` VARCHAR(20) NOT NULL,
  `CURRENT_USERNAME` VARCHAR(30) NOT NULL,
  `DT_ULT_ALTERACAO` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`IDREG_USUARIO`),
  INDEX `fk_reg_usuario_has_usuario_usuario1_idx` (`ID_USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_reg_usuario_has_usuario_usuario1_idx`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `vga_audit`.`usuario` (`ID_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`usuario_empresa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`usuario_empresa` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`usuario_empresa` (
  `ID_USUARIO_EMPRESA` INT(11) NOT NULL AUTO_INCREMENT,
  `DATA_INICIO` DATE NULL DEFAULT NULL,
  `DATA_FIM` DATE NULL DEFAULT NULL,
  `ID_EMPRESA` INT(11) NOT NULL,
  `ID_USUARIO` INT(11) NOT NULL,
  `ATIVO` TINYINT(2) NULL DEFAULT 1,
  PRIMARY KEY (`ID_USUARIO_EMPRESA`),
  INDEX `fk_empresa_has_usuario_usuario1_idx` (`ID_USUARIO` ASC) VISIBLE,
  INDEX `fk_empresa_has_usuario_empresa1_idx` (`ID_EMPRESA` ASC) VISIBLE,
  CONSTRAINT `fk_empresa_has_usuario_empresa1`
    FOREIGN KEY (`ID_EMPRESA`)
    REFERENCES `vga_audit`.`empresa` (`ID_EMPRESA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_empresa_has_usuario_usuario1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `vga_audit`.`usuario` (`ID_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 75
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`usuario_folhamestra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`usuario_folhamestra` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`usuario_folhamestra` (
  `ID_USUARIO_FOLHAMESTRA` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` INT(11) NOT NULL,
  `ID_FOLHAMESTRA` INT(11) NOT NULL,
  `DATA_VINCULO` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `DESCRICAO` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO_FOLHAMESTRA`),
  INDEX `FK_USUARIO_FOLHAMESTRA_USUARIO_idx` (`ID_USUARIO` ASC) VISIBLE,
  INDEX `FK_USUARIO_FOLHAMESTRA_FOLHAMESTRA_idx` (`ID_FOLHAMESTRA` ASC) VISIBLE,
  CONSTRAINT `FK_USUARIO_FOLHAMESTRA_FOLHAMESTRA`
    FOREIGN KEY (`ID_FOLHAMESTRA`)
    REFERENCES `vga_audit`.`folhamestra` (`ID_FOLHAMESTRA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO_FOLHAMESTRA_USUARIO`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `vga_audit`.`usuario` (`ID_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `vga_audit`.`usuario_procedimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vga_audit`.`usuario_procedimento` ;

CREATE TABLE IF NOT EXISTS `vga_audit`.`usuario_procedimento` (
  `ID_USUARIO_PROCEDIMENTO` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_PROCEDIMENTO` INT(11) NOT NULL,
  `ID_USUARIO` INT(11) NULL DEFAULT NULL,
  `DATA_VINCULO` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `DESCRICAO` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO_PROCEDIMENTO`),
  INDEX `FK_USUARIO_PROCEDIMENTO_USUARIO_idx` (`ID_USUARIO` ASC) VISIBLE,
  INDEX `FK_USUARIO_PROCEDIMENTO_PROCEDIMENTO_idx` (`ID_PROCEDIMENTO` ASC) VISIBLE,
  CONSTRAINT `FK_USUARIO_PROCEDIMENTO_PROCEDIMENTO`
    FOREIGN KEY (`ID_PROCEDIMENTO`)
    REFERENCES `vga_audit`.`procedimento` (`ID_PROCEDIMENTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO_PROCEDIMENTO_USUARIO`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `vga_audit`.`usuario` (`ID_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
