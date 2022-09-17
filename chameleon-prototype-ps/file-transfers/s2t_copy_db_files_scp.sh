start_time=$(date +%s.%6N)

sudo su <<EOF

eval `ssh-agent -s`

ssh-add $destination_pem_path

# command for the actual work -- scp
scp -r $source_db_complete_file_path $destination_ssh:$destination_path
EOF

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd latency-output/

echo "Copy database disk files from source to destination (seconds) -- scp = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
