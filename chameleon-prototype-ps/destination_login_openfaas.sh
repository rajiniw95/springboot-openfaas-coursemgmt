### deploy openfaas at the destination machine
### runs at the destination

start_time=$(date +%s.%6N)

export OPENFAAS_URL="127.0.0.1:31112"

# This command retrieves your password
PASSWORD=$(sudo kubectl get secret -n openfaas basic-auth -o jsonpath="{.data.basic-auth-password}" | base64 --decode; echo)

# This command logs in and saves a file to ~/.openfaas/config.yml
echo -n $PASSWORD | sudo faas-cli login --password-stdin --gateway 127.0.0.1:31112

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed 
