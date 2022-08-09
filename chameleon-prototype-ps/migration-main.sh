# get start time of the sequential migration scheme
start_time=$(date +%s)

# load path/ filename variables 
source variables.sh

################ MIGRATE SERVERLESS FUNCTIONS ################  

### Deploy openfaas at the destination
#deploy_sql=`ssh cc@192.5.87.211 'bash -s' < destination_deploy_openfaas.sh` 
#echo $deploy_sql
#deploy_sql=${deploy_sql: -7}
#echo $deploy_sql
#echo "Deploy OpenFaas at the destination (seconds) = " >> $latency_file_name
#echo $deploy_sql >> $latency_file_name

### Copy serverless function files from the source to the destination
source s2t_copy_sl_files.sh 

### Copy MySQL yaml for container creation from source to destination
source s2t_copy_sql_yaml.sh

### Create the database dump at the source
source source_generate_db_file.sh

### Copy the database dump file from the source to the target
# upload the dump file and get the link
dump_link=$(source source_upload_db_file.sh)
