### create the mysql container instance at the target from the copied yaml

# to fix: get service/kubernetes ClusterIP and increment by 1 to get service/mysql ClusterIP
# ClusterIp currently hardcoded in the yaml file to the value at source 

# load path/ filename variables 
source bundled-transfers/variables.sh

cd $destination_path

cd $bundled_folder/mysql-k8s

start_time=$(date +%s.%6N)

sudo kubectl delete deployment,svc mysql
sudo kubectl apply -f mysql-pv.yaml
sudo kubectl apply -f mysql-deployment.yaml

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed
