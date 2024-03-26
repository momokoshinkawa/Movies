DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
 id int unsigned AUTO_INCREMENT,
 title VARCHAR(50) NOT NULL,
 release_year INTEGER unsigned,
 PRIMARY KEY(id)
);

INSERT INTO movies (title, release_year) VALUES ('Back to the Future', 1985);
INSERT INTO movies (title, release_year) VALUES ('Titanic', 1997);
The Devil Wears Prada
