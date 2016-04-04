DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE moviedb;

CREATE TABLE moviedb.movies (
	id INTEGER NOT NULL AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	year INTEGER NOT NULL,
	director VARCHAR(100) NOT NULL,
	banner_url VARCHAR(200) DEFAULT NULL,
	trailer_url varchar(200) DEFAULT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE moviedb.stars (
	id INTEGER NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	dob DATE DEFAULT NULL,
	photo_url VARCHAR(200) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE moviedb.stars_in_movies (
	star_id INTEGER NOT NULL,
	movie_id INTEGER NOT NULL,
	FOREIGN KEY (star_id) REFERENCES moviedb.stars(id),
	FOREIGN KEY (movie_id) REFERENCES moviedb.movies(id)
);

CREATE TABLE moviedb.genres (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(32) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE moviedb.genres_in_movies (
	genre_id INTEGER NOT NULL,
	movie_id INTEGER NOT NULL,
	FOREIGN KEY (genre_id) REFERENCES moviedb.genres(id),
	FOREIGN KEY (movie_id) REFERENCES moviedb.movies(id)
);

CREATE TABLE moviedb.creditcards (
	id VARCHAR(20) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	expiration DATE NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE moviedb.customers (
	id INTEGER NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	cc_id VARCHAR(20) NOT NULL,
	address VARCHAR(200) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(20) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (cc_id) REFERENCES moviedb.creditcards(id)
);

CREATE TABLE moviedb.sales (
	id INTEGER NOT NULL AUTO_INCREMENT,
	customer_id INTEGER NOT NULL,
	movie_id INTEGER NOT NULL,
	sale_date DATE NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (customer_id) REFERENCES moviedb.customers(id),
	FOREIGN KEY (movie_id) REFERENCES moviedb.movies(id)
);