k=$(sudo kubectl get pods --all-namespaces | grep "mysql*" | grep "Running")

# extract namespace of mysql pod
declare -a namespace_list=()
for i in $k
do 
namespace_list+=([$i])
done

namespace=${namespace_list:1:-1}
echo $namespace

# extract mysql pod name
j=${k#*  }
declare -a podname_list=()
for i in $j
do 
podname_list+=([$i])
done

podname=${podname_list:1:-1}
echo $podname

# create the dump.sql file at source
sudo kubectl exec $podname -n $namespace -- mysqldump -u root -ppassword coursedb > $source_db_dump/dump.sql

# copy dump.sql file to target
scp -r $source_db_dump/dump.sql rajini@river-fe3.cs.uchicago.edu:$target_path

scp -r $source_sql_k8s_yaml rajini@river-fe3.cs.uchicago.edu:$target_path