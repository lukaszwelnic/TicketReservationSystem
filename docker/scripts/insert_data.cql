INSERT INTO tickets.cinema (cinema_name, number_of_theaters)
VALUES ('Multikino 51', 2);
INSERT INTO tickets.cinema (cinema_name, number_of_theaters)
VALUES ('Multikino Stary Browar', 2);
INSERT INTO tickets.cinema (cinema_name, number_of_theaters)
VALUES ('Multikino Malta', 1);

INSERT INTO tickets.theater (cinema_name, theater_id, number_of_rows, number_of_seats)
VALUES ('Multikino 51', 1, 12, 10);
INSERT INTO tickets.theater (cinema_name, theater_id, number_of_rows, number_of_seats)
VALUES ('Multikino 51', 2, 10, 8);
INSERT INTO tickets.theater (cinema_name, theater_id, number_of_rows, number_of_seats)
VALUES ('Multikino Stary Browar', 1, 12, 10);
INSERT INTO tickets.theater (cinema_name, theater_id, number_of_rows, number_of_seats)
VALUES ('Multikino Stary Browar', 2, 12, 12);
INSERT INTO tickets.theater (cinema_name, theater_id, number_of_rows, number_of_seats)
VALUES ('Multikino Malta', 1, 10, 11);

INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Spider-Man', '2022-02-16 13:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Scary Movie', '2022-02-16 13:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Matrix', '2022-02-17 13:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Don''t Look up', '2022-02-17 16:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Encanto', '2022-02-18 15:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Dune', '2022-02-19 17:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'James Bond', '2022-02-19 16:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Harry Potter', '2022-02-20 18:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Sing 2', '2022-02-20 19:30:00', 2);

INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Matrix', '2022-02-17 13:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Don''t Look up', '2022-02-17 16:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Harry Potter', '2022-02-20 18:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Sing 2', '2022-02-20 19:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Scary Movie', '2022-02-21 13:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Captain America', '2022-02-21 13:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Doctor Strange', '2022-02-22 16:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'The Gentlemen', '2022-02-22 18:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Focus', '2022-02-23 13:30:00', 2);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Stary Browar', 'Fightclub', '2022-02-23 19:30:00', 2);

INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Malta', 'Encanto', '2022-02-16 15:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Malta', 'Dune', '2022-02-19 17:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Malta', 'Don''t Look up', '2022-02-20 16:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Malta', 'The Witcher', '2022-02-21 15:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Malta', 'tick, tick...Boom!', '2022-02-21 17:30:00', 1);
INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino Malta', 'Ex Machina', '2022-02-22 16:30:00', 1);