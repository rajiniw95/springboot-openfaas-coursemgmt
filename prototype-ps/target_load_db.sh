# TODO : get from variables.sh
target_path='/home/rajini/prototype_ps/'

db_file_path=''
db_file_path+=$target_path 
db_file_path+='dump.sql'

kubectl delete pod mysql-client

echo "deleted mysql-client pod if exists"

kubectl run --image=mysql:5.6 --restart=Never mysql-client -- mysql -h mysql -ppassword 

kubectl exec -i mysql-client -- /bin/bash -c "mysql -ppassword coursedb < $db_file_path"