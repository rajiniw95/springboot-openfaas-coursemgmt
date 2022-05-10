start_time=$(date +%s)

source variables.sh

# source source_copy_sl_files.sh 

# source source_copy_db_file.sh  

# source source_copy_sql_yaml.sh 

# setup_sql=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_setup_sql.sh` 
# setup_sql=${setup_sql: -7}
# echo "Set up the SQL instance at the target (seconds) = " >> $latency_file_name
# echo $setup_sql >> $latency_file_name

ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_load_db.sh 

# deploy_funcs=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_deploy_funcs.sh` 
# # TODO: extract the last space delimited substring
# deploy_funcs=${deploy_funcs: -2}
# echo "Deploy serverless functions at the target (seconds) = " >> $latency_file_name
# echo $deploy_funcs >> $latency_file_name

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo "Total time for sequential execution of migration (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name
