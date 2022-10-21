DROP TABLE IF EXISTS movie_tbl;
CREATE TABLE movie_tbl AS SELECT * FROM CSVREAD('classpath:movies.csv');