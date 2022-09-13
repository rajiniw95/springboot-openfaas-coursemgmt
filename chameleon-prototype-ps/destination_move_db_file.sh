start_time=$(date +%s)

sudo su <<EOF

# load path/ filename variables
source variables.sh

db_disk_path=''
db_disk_path+=$destination_path
db_disk_path+=$source_db_folder_name

cp -r $db_disk_path $destination_db_file_path
EOF

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo $elapsed 

