CREATE KEYSPACE tickets
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 4
  };

CREATE TABLE tickets.reservation(
    id int PRIMARY KEY,
    movie_name text,
    reservation_date date,
    reservation_time time,
    row int,
    seat int,
    cinema_id int,
    theater_id int,
    client_nick text,
    price float,
    created_at timestamp
);

CREATE TABLE tickets.movie(
    id int PRIMARY KEY,
    name text,
    cinema_id int,
    theater_id int,
    date date,
    time time
);

CREATE TABLE tickets.cinema(
    id int PRIMARY KEY,
    cinema_name text,
    number_of_theaters int
);

CREATE TABLE tickets.theater(
    id int PRIMARY KEY,
    number_of_rows int,
    number_of_seats int,
);
