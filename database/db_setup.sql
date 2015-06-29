-- A script to make a fresh db
-- Please, be careful, as it drops all the current data

DROP DATABASE IF EXISTS hotel;

CREATE DATABASE hotel CHARACTER SET utf8 COLLATE utf8_general_ci;
USE hotel;

GRANT USAGE ON *.* TO 'hotel'@'localhost';
DROP USER 'hotel'@'localhost';
CREATE USER 'hotel'@localhost IDENTIFIED BY 'hotel';
GRANT ALL ON TABLE hotel.* TO 'hotel'@'localhost';

CREATE TABLE users(
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(32) NOT NULL UNIQUE,
	password VARCHAR(512) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
) ENGINE='InnoDB';

INSERT INTO users(username, password) VALUES ('admin','admin');

CREATE TABLE apartments(
	id INT NOT NULL AUTO_INCREMENT,
	type ENUM('Single', 'Double', 'Twin', 'Cabana', 'Deluxe') NOT NULL,
	wifi BOOLEAN DEFAULT 1,
	tv_set BOOLEAN DEFAULT 1,
	air_condition BOOLEAN DEFAULT 1,
	view ENUM('Sea', 'City'),
	price_per_night INT NOT NULL,
	CONSTRAINT apartments_pk PRIMARY KEY (id)
) ENGINE='InnoDB';

CREATE TABLE customers(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(25) NOT NULL,
	surname VARCHAR(25) NOT NULL,
	gender ENUM('Male', 'Female') NOT NULL,
	passport_id VARCHAR(25) NOT NULL,
	email VARCHAR(50) NOT NULL,
	CONSTRAINT customers_pk PRIMARY KEY (id)
) ENGINE='InnoDB';

CREATE TABLE requests(
	id INT NOT NULL AUTO_INCREMENT,
	customer_id INT NOT NULL,
	guests TINYINT NOT NULL CHECK (guests > 0 AND guests <= 4),
	check_in_date DATE NOT NULL,
	check_out_date DATE NOT NULL CHECK (check_out_date > check_in_date),
	CONSTRAINT requests_pk PRIMARY KEY (id),
	CONSTRAINT customer_id_fk FOREIGN KEY (customer_id) REFERENCES customers(id)
) ENGINE='InnoDB';

CREATE TABLE orders(
	id INT NOT NULL AUTO_INCREMENT,
	customer_id INT NOT NULL,
	apartment_id INT NOT NULL,
	check_in_date DATE NOT NULL,
	check_out_date DATE NOT NULL CHECK (check_out_date > check_in_date),
	price INT NOT NULL,
	CONSTRAINT orders_pk PRIMARY KEY (id),
	CONSTRAINT customer2_id_fk FOREIGN KEY (customer_id) REFERENCES customers(id),
	CONSTRAINT apartment_id_fk FOREIGN KEY (apartment_id) REFERENCES apartments(id)
) ENGINE='InnoDB';

CREATE INDEX check_in_date_idx ON orders(check_in_date);
CREATE INDEX check_out_date_idx ON orders(check_out_date);

INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Single', 1, 1, 1, 'City', '65');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Single', 1, 0, 1, 'City', '60');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Single', 1, 1, 1, 'Sea', '80');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Single', 1, 0, 1, 'Sea', '75');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Twin', 1, 1, 1, 'Sea', '140');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Twin', 1, 0, 1, 'City', '110');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Double', 1, 1, 1, 'City', '110');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Double', 1, 0, 1, 'City', '100');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Double', 1, 0, 1, 'Sea', '130');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Cabana', 1, 1, 1, 'Sea', '160');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Cabana', 1, 0, 1, 'Sea', '150');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Cabana', 1, 0, 1, 'City', '130');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Deluxe', 1, 1, 1, 'Sea', '260');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Deluxe', 1, 1, 1, 'Sea', '260');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Deluxe', 1, 1, 1, 'City', '220');
INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night)
VALUES ('Deluxe', 1, 1, 1, 'City', '220');

INSERT INTO customers(name, surname, gender, passport_id, email)
VALUES ('Stephen', 'Chidwick', 'Male', 'ADS231212', "stevie44@gmail.com");
INSERT INTO customers(name, surname, gender, passport_id, email)
VALUES ('Maria', 'Ho', 'Female', '10a333232', "marho@gmail.com");
INSERT INTO customers(name, surname, gender, passport_id, email)
VALUES ('Eric', 'Wasserson', 'Male', '9b345632', "waser@yahoo.com");
INSERT INTO customers(name, surname, gender, passport_id, email)
VALUES ('Олексій', 'Ковальчук', 'Male', 'CE201354', "alex_ko@ukr.net");

INSERT INTO requests(customer_id, guests, check_in_date, check_out_date)
VALUES (2, 2, '2015-06-19', '2015-06-23');
INSERT INTO requests(customer_id, guests, check_in_date, check_out_date)
VALUES (3, 4, '2015-07-02', '2015-07-04');
INSERT INTO requests(customer_id, guests, check_in_date, check_out_date)
VALUES (4, 1, '2015-07-03', '2015-07-08');

INSERT INTO orders(customer_id, apartment_id, check_in_date, check_out_date, price)
VALUES (1, 1, '2015-06-20', '2015-06-24', 260);
INSERT INTO orders(customer_id, apartment_id, check_in_date, check_out_date, price)
VALUES (4, 7, '2015-06-24', '2015-06-26', 200);
