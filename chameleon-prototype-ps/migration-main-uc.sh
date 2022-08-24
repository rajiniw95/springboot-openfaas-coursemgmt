# get start time of the sequential migration scheme
start_time=$(date +%s)

# load path/ filename variables 
source variables.sh

################ MIGRATE SERVERLESS FUNCTIONS ################  

### Deploy openfaas at the destination
#deploy_openfaas=`ssh cc@192.5.87.211 'bash -s' < destination_deploy_openfaas.sh` 
#echo $deploy_openfaas
#deploy_openfaas=${deploy_openfaas: -7}
#echo $deploy_openfaas
#echo "Deploy OpenFaas at the destination (seconds) = " >> $latency_file_name
#echo $deploy_openfaas >> $latency_file_name

### Copy serverless function files from the source to the destination
source s2t_copy_sl_files.sh 

### Copy MySQL yaml for container creation from source to destination
cd chameleon-prototype-ps # cd to folder with shell script -- directory changed in the previous script
source s2t_copy_sql_yaml.sh

### Create the database dump at the source
cd chameleon-prototype-ps # cd to folder with shell script -- directory changed in the previous script
source source_generate_db_file.sh

### Copy the database dump file from the source to the target
# upload the dump file and get the link
#dump_link=$(source source_upload_db_file.sh)

### Deploy the serverless functions at the destination
#deploy_funcs=`ssh cc@192.5.87.211 'bash -s' < destination_deploy_funcs.sh`
# TODO: extract the last space delimited substring
#echo "DEPLOYED!!!!"
#deploy_funcs=${deploy_funcs: -6}
#echo "Deploy serverless functions at the destination (seconds) = " >> $latency_file_name
#echo $deploy_funcs >> $latency_file_name
