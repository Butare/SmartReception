CREATE TABLE IF NOT EXISTS `employees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(100) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `organizationName` varchar(100) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_UNIQUE` (`employeeId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `visitors` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `arrivalDate` date NOT NULL,
  `arrivalTime` time NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `hostId` bigint(20) NOT NULL,
  `note` varchar(200) DEFAULT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `createdBy` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_UsersVisitors` (`hostId`),
  CONSTRAINT `FK_UsersVisitors` FOREIGN KEY (`hostId`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

