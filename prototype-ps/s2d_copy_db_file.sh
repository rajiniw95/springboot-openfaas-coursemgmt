### copy the database dump file from the source to destination
### prompts for the cnet password if destination is river

start_time=$(date +%s)

# command for the actual work -- scp (copy dump.sql file to destination)
scp -r $source_db_dump/dump.sql $destination_ssh:$destination_path

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo "Copy DB file from source to destination (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name