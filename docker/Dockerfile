FROM bitnami/cassandra:4.0
USER root
RUN mkdir /scripts
WORKDIR /scripts
COPY scripts .
RUN chmod +x cassandra-init.sh
#RUN ./cassandra-init.sh
