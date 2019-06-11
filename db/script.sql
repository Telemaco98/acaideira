-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`restaurant` (
  `id_restaurant` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  PRIMARY KEY (`id_restaurant`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `price` DECIMAL NULL,
  `name` VARCHAR(45) NULL,
  `quantity` INT NULL,
  `id_restaurant` INT NOT NULL,
  PRIMARY KEY (`id_product`),
  INDEX `fk_Consumível_Restaurante1_idx` (`id_restaurant` ASC),
  CONSTRAINT `fk_Consumível_Restaurante1`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `mydb`.`restaurant` (`id_restaurant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id_order`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`payment` (
  `id_payment` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL,
  `amount` DECIMAL NULL,
  `way` VARCHAR(45) NULL,
  PRIMARY KEY (`id_payment`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`client` (
  `id_client` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `cpf` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `phone` VARCHAR(15) NULL,
  PRIMARY KEY (`id_client`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`purchase` (
  `id_purchase` INT NOT NULL AUTO_INCREMENT,
  `id_payment` INT NOT NULL,
  `id_order` INT NOT NULL,
  `id_client` INT NOT NULL,
  `price` DECIMAL NULL,
  PRIMARY KEY (`id_purchase`),
  INDEX `fk_purchase_order_idx` (`id_order` ASC),
  CONSTRAINT `fk_purchase_payment`
    FOREIGN KEY (`id_payment`)
    REFERENCES `mydb`.`payment` (`id_payment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_order`
    FOREIGN KEY (`id_order`)
    REFERENCES `mydb`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`creditcard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`creditcard` (
  `id_creditcard` INT NOT NULL,
  `number` VARCHAR(45) NULL,
  `validity` VARCHAR(45) NULL,
  `owner_name` VARCHAR(100) NULL,
  PRIMARY KEY (`id_creditcard`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Produto_has_Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Produto_has_Pedido` (
  `Produto_id_consumível` INT NOT NULL,
  `Pedido_id_pedido` INT NOT NULL,
  PRIMARY KEY (`Produto_id_consumível`, `Pedido_id_pedido`),
  INDEX `fk_Produto_has_Pedido_Pedido1_idx` (`Pedido_id_pedido` ASC),
  INDEX `fk_Produto_has_Pedido_Produto1_idx` (`Produto_id_consumível` ASC),
  CONSTRAINT `fk_Produto_has_Pedido_Produto1`
    FOREIGN KEY (`Produto_id_consumível`)
    REFERENCES `mydb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_has_Pedido_Pedido1`
    FOREIGN KEY (`Pedido_id_pedido`)
    REFERENCES `mydb`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product_order` (
  `id_product` INT NOT NULL,
  `id_order` INT NOT NULL,
  `quantity` INT NULL,
  PRIMARY KEY (`id_product`, `id_order`),
  INDEX `fk_Product_has_Pedido_Pedido1_idx` (`id_order` ASC),
  CONSTRAINT `fk_product_order_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_order_order`
    FOREIGN KEY (`id_order`)
    REFERENCES `mydb`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`address` (
  `id_address` INT NOT NULL,
  `cep` VARCHAR(8) NULL,
  `street` VARCHAR(70) NULL,
  `neighbohood` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `number` INT NULL,
  `id_client` INT NULL,
  PRIMARY KEY (`id_address`),
  INDEX `fk_address_client_idx` (`id_client` ASC),
  CONSTRAINT `fk_address_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `mydb`.`client` (`id_client`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`purchase_restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`purchase_restaurant` (
  `id_purchase` INT NOT NULL,
  `id_restaurant` INT NOT NULL,
  INDEX `fk_purchase_restaurant_purchase_idx` (`id_purchase` ASC),
  INDEX `fk_purchase_restaurant_restaurant_idx` (`id_restaurant` ASC),
  CONSTRAINT `fk_purchase_restaurant_purchase`
    FOREIGN KEY (`id_purchase`)
    REFERENCES `mydb`.`purchase` (`id_purchase`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_restaurant_restaurant`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `mydb`.`restaurant` (`id_restaurant`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order_client` (
  `id_client` INT NOT NULL,
  `id_order` INT NOT NULL,
  INDEX `fk_purchase_client_client_idx` (`id_client` ASC),
  INDEX `fk_order_client_order_idx` (`id_order` ASC),
  CONSTRAINT `fk_order_client_order`
    FOREIGN KEY (`id_order`)
    REFERENCES `mydb`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_client_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `mydb`.`client` (`id_client`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`creditcard_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`creditcard_client` (
  `id_creditcard` INT NOT NULL,
  `id_client` INT NOT NULL,
  INDEX `fk_creditcard_client_creditcard_idx` (`id_creditcard` ASC),
  INDEX `fk_creditcard_client_client_idx` (`id_client` ASC),
  CONSTRAINT `fk_creditcard_client_creditcard`
    FOREIGN KEY (`id_creditcard`)
    REFERENCES `mydb`.`creditcard` (`id_creditcard`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_creditcard_client_client`
    FOREIGN KEY (`id_client`)
    REFERENCES `mydb`.`client` (`id_client`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

