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
