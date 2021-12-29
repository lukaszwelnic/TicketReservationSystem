echo "Running cassandra-init.sh"
cqlsh -u cassandra -p cassandra -f create_tables.cql
echo "Done"