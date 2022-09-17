### Transfer the bundled folder across the network to the target
cd ..
cd ..

start_time=$(date +%s.%6N)

sudo tar -czvf bundled-folder.tar.gz $bundled_folder && ssh $destination_ssh "tar -xzv" < bundled-folder.tar.gz

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd chameleon-prototype-ps/latency-output/

echo "Copy bundled folder from source to target (seconds) -- tar = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
cd non-critical-path/
