OPENFAAS_PASSWORD=591e159b645f4746fe5f24962add6f3dece66237

docker login 

kubectl port-forward svc/gateway -n openfaas 31112:8080 &

echo -n $OPENFAAS_PASSWORD | faas-cli login --password-stdin --gateway 127.0.0.1:31112

cd prototype_ps/serverlessfuncs/

for FILE in *;
cd $FILE;
faas-cli up -f $FILE.yml;
cd ..;
done

cd ..
cd mysql-k8s
kubectl delete deployment,svc mysql
kubectl apply -f mysql-pv.yaml
kubectl apply -f mysql-deployment.yaml

kubectl run -it --rm --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword
coursedb < /home/rajini/prototype-ps/dump.sql
exit;
