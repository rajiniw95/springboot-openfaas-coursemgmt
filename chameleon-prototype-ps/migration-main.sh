# get start time of the sequential migration scheme
start_time=$(date +%s)

# load path/ filename variables 
source variables.sh

################ MIGRATE SERVERLESS FUNCTIONS ################  

### Deploy openfaas gateway at the destination
#deploy_openfaas_gateway=`ssh $destination_ssh 'bash -s' < destination_openfaas_gateway.sh` 
#echo $deploy_openfaas_gateway
#deploy_openfaas_gateway=${deploy_openfaas_gateway: -7}
#echo $deploy_openfaas_gateway
#cd latency-output
#echo "Open tunnel for OpenFaas from k8s to the destination machine (seconds) = " >> $latency_file_name
#echo $deploy_openfaas_gateway >> $latency_file_name
#cd ..

### Login to the openfaas gateway at the destination
#login_openfaas=`ssh $destination_ssh 'bash -s' < destination_login_openfaas.sh`
#echo $login_openfaas
#login_openfaas=${login_openfaas: -7}
#echo $login_openfaas
#cd latency-output
#echo "Login to the OpenFaas gateway at the destination (seconds) = " >> $latency_file_name
#echo $login_openfaas >> $latency_file_name
#cd ..



### Copy serverless function files from the source to the destination
#source s2t_copy_sl_files_scp.sh
#source s2t_copy_sl_files_tarscp.sh 
#source s2t_copy_sl_files_rcp.sh 

### Copy MySQL yaml for container creation from source to destination
#source s2t_copy_sql_yaml_scp.sh
#source s2t_copy_sql_yaml_tarscp.sh
#source s2t_copy_sql_yaml_rcp.sh

### Bundle all file transfers together
#source s2t_copy_bundled_scp.sh
#source s2t_copy_bundled_tarscp.sh
#source s2t_copy_bundled_rcp.sh

### Deploy the serverless functions at the destination
#deploy_funcs=`ssh $destination_ssh 'bash -s' < destination_deploy_funcs.sh`
# TODO: extract the last space delimited substring
#echo "DEPLOYED!!!!"
#deploy_funcs=${deploy_funcs: -6}
#cd latency-output
#echo "Deploy serverless functions at the destination (seconds) = " >> $latency_file_name
#echo $deploy_funcs >> $latency_file_name
#cd ..

################ MIGRATE MYSQL DATABASE ################

### Copy the database disk files from source to destination /home/cc/ directory
#source s2t_copy_db_files_scp.sh
#source s2t_copy_db_files_tarscp.sh
#source s2t_copy_db_files_rcp.sh

### Move the copied database disk files to the relevant priviledged folder at the destination machine
move_db_disk=`ssh $destination_ssh 'bash -s' < destination_move_db_file.sh`
echo $move_db_disk
move_db_disk=${move_db_disk: -7}
echo $move_db_disk
cd latency-output
echo "Move the database disk files to the K8S container at the destination (seconds) = " >> $latency_file_name
echo $move_db_disk >> $latency_file_name
cd ..

### Create the MySQL container instance at the destination
#setup_sql=`ssh $destination_ssh 'bash -s' < destination_setup_sql.sh`
#echo $setup_sql
#setup_sql=${setup_sql: -7}
#echo $setup_sql
#cd latency-output
#echo "Set up the SQL instance at the destination (seconds) = " >> $latency_file_name
#echo $setup_sql >> $latency_file_name
#cd ..
