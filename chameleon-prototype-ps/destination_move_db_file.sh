start_time=$(date +%s.%6N)

source /home/cc/variables.sh
db_disk_path=''
db_disk_path+=$destination_path
db_disk_path+='data'

sudo su <<EOF
cp -r $db_disk_path $destination_db_file_path
EOF

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed 

