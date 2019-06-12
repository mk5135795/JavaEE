CREATE DATABASE IF NOT EXISTS jee;
USE jee;

DROP TABLE IF EXISTS Relacje;
DROP TABLE IF EXISTS Czlonek_Wydarzenia;
DROP TABLE IF EXISTS Wydarzenie;
DROP TABLE IF EXISTS Uzytkownik;

CREATE TABLE  Uzytkownik (
  user_id int(10) NOT NULL auto_increment,
  login varchar(40) NOT NULL unique,
  password varchar(80) NOT NULL,
  rate_sum_frendliness int(10),
  rate_count_frendliness int(10),
  rate_sum_punctuality int(10),
  rate_count_punctuality int(10),
  rate_sum_involment int(10),
  rate_count_involment int(10),
  PRIMARY KEY (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE  Wydarzenie (
  event_id int(10) NOT NULL,
  event_name varchar(255) NOT NULL unique,
  event_city varchar(255) NOT NULL,
  event_date date NOT NULL,
  leader int(10) NOT NULL,
  free_space int(1) NOT NULL,
  game varchar(255) NOT NULL,
  info varchar(255),
  PRIMARY KEY (event_id),
  CONSTRAINT c_leader FOREIGN KEY (leader) REFERENCES Uzytkownik (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE  Czlonek_Wydarzenia (
  member int(10) NOT NULL,
  event int(10) NOT NULL,
  CONSTRAINT c_member FOREIGN KEY (member) REFERENCES Uzytkownik (user_id),
  CONSTRAINT c_event FOREIGN KEY (event) REFERENCES Wydarzenie (event_id)
) ENGINE=InnoDB;

CREATE TABLE  Relacje (
  author int(10) NOT NULL,
  target int(10) NOT NULL,
  relation char(1) NOT NULL,
  CONSTRAINT c_author FOREIGN KEY (author) REFERENCES Uzytkownik (user_id),
  CONSTRAINT c_target FOREIGN KEY (target) REFERENCES Uzytkownik (user_id)
) ENGINE=InnoDB;