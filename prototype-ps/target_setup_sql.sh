start_time=$(date +%s.%6N)

# TODO : get from variables.sh
target_path='/home/rajini/prototype_ps/'

cd mysql-k8s

kubectl delete deployment,svc mysql
kubectl apply -f mysql-pv.yaml
kubectl apply -f mysql-deployment.yaml

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed