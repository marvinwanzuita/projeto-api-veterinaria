-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema clinica-veterinaria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clinica-veterinaria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clinica-veterinaria` DEFAULT CHARACTER SET utf8 ;
USE `clinica-veterinaria` ;

-- -----------------------------------------------------
-- Table `clinica-veterinaria`.`tutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica-veterinaria`.`tutor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clinica-veterinaria`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica-veterinaria`.`paciente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `sexo` INT NULL,
  `data_nascimento` DATE NULL,
  `tutor_id` INT NOT NULL,
  PRIMARY KEY (`id`, `tutor_id`),
  INDEX `fk_paciente_tutor1_idx` (`tutor_id` ASC) VISIBLE,
  CONSTRAINT `fk_paciente_tutor1`
    FOREIGN KEY (`tutor_id`)
    REFERENCES `clinica-veterinaria`.`tutor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clinica-veterinaria`.`agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica-veterinaria`.`agendamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_atendimento` DATE NOT NULL,
  `hora_atendimento` TIME NOT NULL,
  `status_atendimento` INT NOT NULL,
  `peso_paciente` DOUBLE NULL,
  `paciente_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_agendamento_paciente1_idx` (`paciente_id` ASC) VISIBLE,
  CONSTRAINT `fk_agendamento_paciente1`
    FOREIGN KEY (`paciente_id`)
    REFERENCES `clinica-veterinaria`.`paciente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clinica-veterinaria`.`procedimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica-veterinaria`.`procedimento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clinica-veterinaria`.`agendamento_do_procedimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clinica-veterinaria`.`agendamento_do_procedimento` (
  `procedimento_id` INT NOT NULL,
  `agendamento_id` INT NOT NULL,
  PRIMARY KEY (`procedimento_id`, `agendamento_id`),
  INDEX `fk_procedimento_has_agendamento_agendamento1_idx` (`agendamento_id` ASC) VISIBLE,
  INDEX `fk_procedimento_has_agendamento_procedimento1_idx` (`procedimento_id` ASC) VISIBLE,
  CONSTRAINT `fk_procedimento_has_agendamento_procedimento1`
    FOREIGN KEY (`procedimento_id`)
    REFERENCES `clinica-veterinaria`.`procedimento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procedimento_has_agendamento_agendamento1`
    FOREIGN KEY (`agendamento_id`)
    REFERENCES `clinica-veterinaria`.`agendamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
