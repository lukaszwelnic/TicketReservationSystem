Ticket Reservation System
=====================

Cinema tickets reservation system build in Java and deployed on Docker containers using Cassandra database.

Docker Setup
---------------------
To launch the Cassandra containers called cassandra-cassandra*-1 simply run the shell command:

    ./docker-up.sh

To stop and delete all the Cassandra containers run the shell command:
    
    ./docker-rm.sh

Cassandra Setup
---------------------
- Connect to a docker cassandra container using:

      docker exec -it cassandra-cassandra*-1 /bin/bash
- Connect to a cassandra cluster:

      cqlsh -u cassandra -p cassandra
- Create a database using the shell command:

      ./cassandra-init.sh
