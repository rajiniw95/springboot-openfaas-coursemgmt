### deploy openfaas at the target machine
### runs at the destination

start_time=$(date +%s.%6N)

# Check the gateway is ready
sudo kubectl rollout status -n openfaas deploy/gateway

# This command will open a tunnel from your Kubernetes cluster to your local computer so that you can access the OpenFaaS gateway. 

# Nohuping backgrounded jobs is for example useful when logged in via SSH, 
# since backgrounded jobs can cause the shell to hang on logout due to a race condition [2].
# This problem can also be overcome by redirecting all three I/O streams:
# https://stackoverflow.com/questions/29142/getting-ssh-to-execute-a-command-in-the-background-on-target-machine
nohup sudo kubectl port-forward svc/gateway -n openfaas 31112:8080 > foo.out 2> foo.err < /dev/null &

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed 
