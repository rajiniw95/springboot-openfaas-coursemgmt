start_time=$(date +%s)

source variables.sh

source source_copy_sl_files.sh 

source source_generate_db_file.sh 

source source_copy_db_file.sh  

source source_copy_sql_yaml.sh 

setup_sql=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_setup_sql.sh` 
setup_sql=${setup_sql: -7}
echo "Set up the SQL instance at the target (seconds) = " >> $latency_file_name
echo $setup_sql >> $latency_file_name

# ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_load_db.sh 


start_time1=$(date +%s)
ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_deploy_funcs.sh
end_time1=$(date +%s)
elapsed1=$(( end_time - start_time ))
echo "Deploy serverless functions at the target (seconds) = " >> $latency_file_name
echo $elapsed1 >> $latency_file_name

# deploy_funcs=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_deploy_funcs.sh` 
# # TODO: extract the last space delimited substring
# echo "DEPLOYED!!!!"
# deploy_funcs=${deploy_funcs: -6}
# echo "Deploy serverless functions at the target (seconds) = " >> $latency_file_name
# echo $deploy_funcs >> $latency_file_name

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo "Total time for sequential execution of migration (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name
