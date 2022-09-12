### create the mysql container instance at the destination from the copied yaml
### runs at the destination

echo "Executing at the destination site ..."

start_time=$(date +%s.%6N)

# load path/ filename variables 
source variables.sh

cd $destination_path

cd mysql-k8s

sudo kubectl delete deployment,svc mysql
sudo kubectl apply -f mysql-pv.yaml
sudo kubectl apply -f mysql-deployment.yaml

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed
