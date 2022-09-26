start_time=$(date +%s.%6N)

sudo kubectl exec $podname_source -- tar cf - /var/lib/mysql | ssh $destination_ssh sudo kubectl exec -i $podname_target -- tar xf - -C /

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd ..
cd latency-output/

echo "Transfer mysql database files from source to target = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
cd critical-path/
