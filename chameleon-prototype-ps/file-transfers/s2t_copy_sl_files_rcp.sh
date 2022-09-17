### copy the serverless function folder from the source to destination
### prompts for the cnet password if destination is river

# line added on chameleon -- currently in chameleon-prototype-ps/ folder, need to get to the previous level to find the sl funcs folder
# cd before starting to measure latency, because navigating placement in folder structure is not migration related 
cd ..

start_time=$(date +%s.%6N)

# command for the actual work -- rcp
rcp -r $source_sl_path $destination_ssh:$destination_path

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd chameleon-prototype-ps/latency-output/

echo "Copy serverless function files from source to destination (seconds) -- rcp = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
