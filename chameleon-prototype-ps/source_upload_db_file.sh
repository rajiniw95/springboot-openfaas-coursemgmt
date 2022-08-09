### upload the dump file and return the link 

start_time=$(date +%s.%6N)

cd ..

dump_folder='mysql_dump'
cd $dump_folder

curl --upload-file dump.sql https://transfer.sh/dump.sql

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo "Upload the dump file and get link (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name
