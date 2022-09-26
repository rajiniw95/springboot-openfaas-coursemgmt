# load path/ filename variables 
cd ..
source variables.sh
cd non-critical-path

#### Save docker function images as tar files in the folder to be transferred
source source_save_func_images.sh

#### Create bundled folder with files for transfer to target (mysql yaml, variable.sh)
source source_bundle_files.sh 

#### Transfer bundled folder to target
source s2t_transfer_bundled_tar.sh

#### Deploy openfaas gateway at the target
deploy_openfaas_gateway=`ssh $destination_ssh 'bash -s' < target_openfaas_gateway.sh` 
echo $deploy_openfaas_gateway
deploy_openfaas_gateway=${deploy_openfaas_gateway: -7}
echo $deploy_openfaas_gateway
cd ../latency-output
echo "Open tunnel for OpenFaas from k8s to the target (seconds) = " >> $latency_file_name
echo $deploy_openfaas_gateway >> $latency_file_name
cd ..
cd non-critical-path

#### Login to the openfaas gateway at the target
login_openfaas=`ssh $destination_ssh 'bash -s' < target_login_openfaas.sh`
echo $login_openfaas
login_openfaas=${login_openfaas: -7}
echo $login_openfaas
cd ../latency-output
echo "Login to the OpenFaas gateway at the target (seconds) = " >> $latency_file_name
echo $login_openfaas >> $latency_file_name
cd ..
cd non-critical-path

#### Create the MySQL container instance at the target
setup_sql=`ssh $destination_ssh 'bash -s' < target_setup_sql_instance.sh`
echo $setup_sql
setup_sql=${setup_sql: -7}
echo $setup_sql
cd ../latency-output
echo "Set up the SQL instance at the target (seconds) = " >> $latency_file_name
echo $setup_sql >> $latency_file_name
cd ..
cd non-critical-path

#### Load the serverless function images at the target
load_func_images=`ssh $destination_ssh 'bash -s' < target_load_func_images.sh`
load_func_images=${load_func_images: -15}
cd ../latency-output
echo "Load serverless function images at the target (seconds) = " >> $latency_file_name
echo $load_func_images >> $latency_file_name
cd ..
cd non-critical-path

#### Push function images to the local registry at the target
push_funcs=`ssh $destination_ssh 'bash -s' < target_push_func_images.sh`
push_funcs=${push_funcs: -15}
cd ../latency-output
echo "Push serverless function images to the local image registry at the target (seconds) = " >> $latency_file_name
echo $push_funcs >> $latency_file_name
cd ..
cd non-critical-path

#### Deploy serverless functions at the target
deploy_funcs=`ssh $destination_ssh 'bash -s' < target_deploy_funcs.sh`
deploy_funcs=${deploy_funcs: -15}
cd ../latency-output
echo "Deploy serverless functions at the target (seconds) = " >> $latency_file_name
echo $deploy_funcs >> $latency_file_name 
cd ..
cd non-critical-path
