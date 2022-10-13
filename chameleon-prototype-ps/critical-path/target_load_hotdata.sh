start_time=$(date +%s.%6N)

#TODO: get bin_log file name and db name  
sudo kubectl exec mysql-0 -- mysqlbinlog /var/lib/mysql/mysqld-bin.000002 | mysql coursedb

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed
