INSERT INTO tickets.clientReservation (id, movie_name, reservation_date, reservation_time, row,
                                 seat, cinema_id, theater_id, client_nick, price, created_at)
VALUES (1, 'Matrix 4: Ressurection', '2022-02-21', '19:20:00', 6, 8, 1, 4, 'jared', 10, toTimestamp(now()));

INSERT INTO tickets.cinema (id, cinema_name, number_of_theaters)
VALUES (1, 'Multikino 51', 8);

INSERT INTO tickets.theater (id, number_of_rows, number_of_seats)
VALUES (1, 12, 15);

INSERT INTO tickets.movie (id, name, cinema_id, theater_id, date, time)
VALUES (1, 'Spider-Man', 1, 4, '2022-02-16', '13:30:00');