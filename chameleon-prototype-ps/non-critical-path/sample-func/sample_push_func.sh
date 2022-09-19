start_time=$(date +%s.%6N)

cd /home/cc/sample-func

sudo faas-cli push -f hello-sept19.yml

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed 
