### copy the serverless function folder from the source to destination
### prompts for the cnet password if destination is river

start_time=$(date +%s.%6N)

# command for the actual work -- scp
scp -r $source_sl_path $destination_ssh:$destination_path

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo "Copy serverless function files from source to destination (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name
