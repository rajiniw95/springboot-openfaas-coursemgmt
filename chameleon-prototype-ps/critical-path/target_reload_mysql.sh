start_time=$(date +%s.%6N)

sudo kubectl exec mysql-0 -- service mysql reload

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed

