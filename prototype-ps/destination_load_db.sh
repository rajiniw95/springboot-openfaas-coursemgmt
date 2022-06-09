# load path/ filename variables 
source variables.sh

start_time=$(date +%s)

db_file_path=''
db_file_path+=$destination_path 
db_file_path+='dump.sql'

kubectl delete pod mysql-client

# echo "deleted mysql-client pod if exists"

# kubectl run --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword 

# download the dump file
kubectl exec -i mysql-client -- /bin/bash -c "curl https://transfer.sh/WmQ5QD/dump.sql -o dump.sql"

# load database from the dump file
kubectl exec -i mysql-client -- /bin/bash -c "mysql -ppassword coursedb < dump.sql"

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo $elapsed 