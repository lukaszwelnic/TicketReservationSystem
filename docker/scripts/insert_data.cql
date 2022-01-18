INSERT INTO tickets.cinema (cinema_name, number_of_theaters)
VALUES ('Multikino 51', 2);

INSERT INTO tickets.theater (cinema_name, theater_id, number_of_rows, number_of_seats)
VALUES ('Multikino 51', 1, 12, 15);

INSERT INTO tickets.movie (cinema_name, movie_name, date, theater_id)
VALUES ('Multikino 51', 'Spider-Man', '2022-02-16 13:30:00', 1);