start_time=$(date +%s.%6N)

sudo kubectl exec mysql-0 -- mysqladmin flush-logs

#TODO: get binary log file name for the last written log file -- hardcode for now
sudo kubectl exec mysql-0 -- tar -cf - var/lib/mysql/mysqld-bin.000006 | ssh $destination_ssh sudo kubectl exec -i mysql-0 -- tar xf - -C /

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd ..
cd latency-output/

echo "Transfer cold data files from source to target = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
cd critical-path/

