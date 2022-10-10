### Run a Single-Instance Stateful Application

sudo kubectl apply -f mysql-pv.yaml

sudo kubectl apply -f mysql-deployment.yaml

[https://kubernetes.io/docs/tasks/run-application/run-single-instance-stateful-application/](https://kubernetes.io/docs/tasks/run-application/run-single-instance-stateful-application/)

### Run a Replicated Stateful Application -- binary loggin enabled for incremental backup 

sudo kubectl apply -f mysql-configmap.yaml

sudo kubectl apply -f mysql-service.yaml

sudo kubectl apply -f mysql-statefulset.yaml

[https://kubernetes.io/docs/tasks/run-application/run-replicated-stateful-application/](https://kubernetes.io/docs/tasks/run-application/run-replicated-stateful-application/)

To check whether binary logging is enabled;

- kubectl exec -it pod\_name bash

- mysql 

- show variables like 'log_bin';   
