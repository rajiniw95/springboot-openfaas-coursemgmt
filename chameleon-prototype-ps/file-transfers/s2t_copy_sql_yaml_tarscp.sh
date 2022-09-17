### copy the yaml files for mysql contanier creation from the source to destination
### prompts for the cnet password if destination is river

# line added on chameleon -- currently in chameleon-prototype-ps/ folder, need to get to the previous level to find the sl funcs folder
# cd before starting to measure latency, because navigating placement in folder structure is not migration related
cd .. 

start_time=$(date +%s.%6N)

# command for the actual work -- scp
sudo tar -czvf sql-yaml.tar.gz $source_sql_k8s_yaml && ssh $destination_ssh "tar -xzv" < sql-yaml.tar.gz

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd chameleon-prototype-ps/latency-output/

echo "Copy yaml files to generate sql instance from source to destination (seconds) -- tar|scp|untar = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd .. 
