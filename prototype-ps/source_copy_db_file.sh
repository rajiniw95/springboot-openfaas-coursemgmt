start_time=$(date +%s)

# copy dump.sql file to target
scp -r $source_db_dump/dump.sql rajini@river-fe3.cs.uchicago.edu:$target_path

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo "Copy DB file from source to target (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name