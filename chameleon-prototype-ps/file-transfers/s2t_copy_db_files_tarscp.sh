start_time=$(date +%s.%6N)

sudo su <<EOF

eval `ssh-agent -s`

ssh-add $destination_pem_path

cd $source_db_file_path

# command for the actual work -- tar|scp|untar
sudo tar -czvf db-disk-files.tar.gz $source_db_folder_name && ssh $destination_ssh "tar -xzv" < db-disk-files.tar.gz
EOF

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd latency-output/

echo "Copy database disk files from source to destination (seconds) -- tar|scp|untar = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..

