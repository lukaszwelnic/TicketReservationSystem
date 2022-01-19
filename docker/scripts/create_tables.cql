DROP KEYSPACE IF EXISTS tickets;

CREATE KEYSPACE tickets
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 3
  };

CREATE TABLE tickets.client_reservation(
    client_nick text,
    movie_name text,
    reservation_date timestamp,
    row int,
    seat int,
    cinema_name text,
    theater_id int,
    price float,
    created_at timestamp,
    PRIMARY KEY ( client_nick, movie_name )
);

CREATE TABLE tickets.seat_reservation(
     date timestamp,
     cinema_name text,
     theater_id int,
     client_nick text,
     movie_name text,
     row int,
     seat int,
     PRIMARY KEY ( date, cinema_name, theater_id, client_nick, movie_name )
);

CREATE TABLE tickets.movie(
    cinema_name text,
    movie_name text,
    theater_id int,
    date timestamp,
    PRIMARY KEY ( cinema_name, movie_name )
);

CREATE TABLE tickets.cinema(
    cinema_name text,
    number_of_theaters int,
    PRIMARY KEY ( cinema_name )
);

CREATE TABLE tickets.theater(
    cinema_name text,
    theater_id int,
    number_of_rows int,
    number_of_seats int,
    PRIMARY KEY ( cinema_name, theater_id )
);
