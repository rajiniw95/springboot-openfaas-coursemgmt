# load path/ filename variables 
cd ..
source variables.sh
cd non-critical-path

#### Create bundled folder with all files for transfer to target 
# source source_bundle_files.sh 

#### Transfer bundled folder to target
# source s2t_transfer_bundled_tar.sh

#### Deploy openfaas gateway at the target
# deploy_openfaas_gateway=`ssh $destination_ssh 'bash -s' < target_openfaas_gateway.sh` 
# echo $deploy_openfaas_gateway
# deploy_openfaas_gateway=${deploy_openfaas_gateway: -7}
# echo $deploy_openfaas_gateway
# cd ../latency-output
# echo "Open tunnel for OpenFaas from k8s to the target (seconds) = " >> $latency_file_name
# echo $deploy_openfaas_gateway >> $latency_file_name
# cd ..
# cd non-critical-path

#### Login to the openfaas gateway at the target
# login_openfaas=`ssh $destination_ssh 'bash -s' < target_login_openfaas.sh`
# echo $login_openfaas
# login_openfaas=${login_openfaas: -7}
# echo $login_openfaas
# cd ../latency-output
# echo "Login to the OpenFaas gateway at the target (seconds) = " >> $latency_file_name
# echo $login_openfaas >> $latency_file_name
# cd ..
# cd non-critical-path

#### Build the serverless functions at the target
build_funcs=`ssh $destination_ssh 'bash -s' < target_build_funcs.sh`
echo "Function built!!!!"
build_funcs=${build_funcs: -6}
cd ../latency-output
echo "Build serverless functions at the target (seconds) = " >> $latency_file_name
echo $build_funcs >> $latency_file_name
cd ..
cd non-critical-path



