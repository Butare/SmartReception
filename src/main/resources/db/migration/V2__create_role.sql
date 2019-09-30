CREATE TABLE IF NOT EXISTS role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(30) NOT NULL,
  createdAt DATETIME NOT NULL,
  updatedAt DATETIME DEFAULT NULL,
  deleted tinyint(4) DEFAULT NULL,
  PRIMARY KEY (id)
);