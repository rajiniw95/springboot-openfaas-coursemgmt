docker login 

kubectl port-forward svc/gateway -n openfaas 31112:8080 &

cat openfaas-password | faas-cli login --password-stdin --gateway 127.0.0.1:31112