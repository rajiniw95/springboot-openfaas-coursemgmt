### copy the yaml files for mysql contanier creation from the source to destination
### prompts for the cnet password if destination is river

start_time=$(date +%s)

# command for the actual work -- scp
scp -r $source_sql_k8s_yaml $destination_ssh:$destination_path

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo "Copy yaml files to generate sql instance from source to destination (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name