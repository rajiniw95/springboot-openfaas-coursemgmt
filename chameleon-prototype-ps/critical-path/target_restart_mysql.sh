start_time=$(date +%s.%6N)

sudo kubectl exec mysql-79c846684f-2vrfm -- service mysql restart

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed

