CREATE TABLE `matchingsystem`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `buyerId` INT NOT NULL,
  `sellerId` INT NOT NULL,
  `bondid` INT NOT NULL,
  `quantity` INT NOT NULL,
  `dealingPrice` INT NOT NULL,
  `timestamp` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);