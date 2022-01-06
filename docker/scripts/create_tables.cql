CREATE KEYSPACE tickets
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 4
  };

CREATE TABLE tickets.test(
    column1 int PRIMARY KEY,
    column2 text,
    column3 text
);

CREATE TABLE tickets.reservation(
    id int PRIMARY KEY,
    name text
);