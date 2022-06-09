### create the mysql container instance at the destination from the copied yaml
### runs at the destination

start_time=$(date +%s.%6N)

# load path/ filename variables 
source variables.sh

cd $destination_path

cd mysql-k8s

kubectl delete deployment,svc mysql
kubectl apply -f mysql-pv.yaml
kubectl apply -f mysql-deployment.yaml

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed