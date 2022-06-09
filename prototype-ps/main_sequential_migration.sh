# get start time of the sequential migration scheme
start_time=$(date +%s)

# load path/ filename variables 
source variables.sh

################ MIGRATE SERVERLESS FUNCTIONS ################  

### Deploy openfaas at the destination
deploy_sql=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < destination_deploy_openfaas.sh` 
echo $deploy_sql
deploy_sql=${deploy_sql: -7}
echo $deploy_sql
echo "Deploy OpenFaas at the destination (seconds) = " >> $latency_file_name
echo $deploy_sql >> $latency_file_name


### Copy serverless function files from the source to the destination
source s2d_copy_sl_files.sh 


### Deploy the serverless functions at the destination
deploy_funcs=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < destination_deploy_funcs.sh` 
# TODO: extract the last space delimited substring
echo "DEPLOYED!!!!"
deploy_funcs=${deploy_funcs: -6}
echo "Deploy serverless functions at the destination (seconds) = " >> $latency_file_name
echo $deploy_funcs >> $latency_file_name



################ MIGRATE MYSQL DATABASE ################  

### Copy MySQL yaml for container creation from source to destination
source s2d_copy_sql_yaml.sh 


### Create the MySQL container instance at the destination
setup_sql=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < destination_setup_sql.sh` 
echo $setup_sql
setup_sql=${setup_sql: -7}
echo $setup_sql
echo "Set up the SQL instance at the destination (seconds) = " >> $latency_file_name
echo $setup_sql >> $latency_file_name


### Create the database dump at the source
source source_generate_db_file.sh 


### Copy the database dump file from the source to the target 
# upload the dump file and get the link
dump_link=$(source source_upload_db_file.sh  


### Reload the database at the destination
reload_db=`ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < destination_load_db.sh` 
echo $reload_db
setup_sql=${reload_db: -7}
echo $reload_db
echo "Reload database at the destination (seconds) = " >> $latency_file_name
echo $reload_db >> $latency_file_name


# start_time1=$(date +%s)
# ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < destination_deploy_funcs.sh
# end_time1=$(date +%s)
# elapsed1=$(( end_time - start_time ))
# echo "Deploy serverless functions at the destination (seconds) = " >> $latency_file_name
# echo $elapsed1 >> $latency_file_name


# get end time of the sequential migration scheme
end_time=$(date +%s)
# calculate elapsted time for sequential migration
elapsed=$(( end_time - start_time ))
echo "Total time for sequential execution of migration (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name
