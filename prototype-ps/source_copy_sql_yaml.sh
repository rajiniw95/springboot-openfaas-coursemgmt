start_time=$(date +%s)

scp -r $source_sql_k8s_yaml rajini@river-fe3.cs.uchicago.edu:$target_path

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo "Copy yaml files to generate sql instance from source to target (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name