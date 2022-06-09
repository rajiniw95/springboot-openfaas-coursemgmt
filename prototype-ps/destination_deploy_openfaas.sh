### deploy openfaas at the destination machine
### runs at the destination

start_time=$(date +%s)

docker login 

kubectl port-forward svc/gateway -n openfaas 31112:8080 &

cat openfaas-password | faas-cli login --password-stdin --gateway 127.0.0.1:31112

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo $elapsed 