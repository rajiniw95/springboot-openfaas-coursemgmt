### generate the mysql dump file 

start_time=$(date +%s.%6N)

cd ..
cd mysql_dump

# find the running mysql kubernetes pod
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

### create the dump.sql file at source
sudo kubectl exec $podname -n $namespace -- mysqldump -u root -ppassword $database_name > $source_db_dump/dump.sql

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd ..
cd chameleon-prototype-ps

echo "Generate DB dump file at the source (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name
