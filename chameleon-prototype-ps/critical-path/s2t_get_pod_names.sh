start_time=$(date +%s.%6N)

### get source mysql pod name ###

k_source=$(sudo kubectl get pods --all-namespaces | grep "mysql*" | grep "Running")
j_source=${k_source#*  }
# extract mysql pod name
j_source=${k_source#*  }
declare -a podname_list_source=()
for i in $j_source
do 
podname_list_source+=([$i])
done
podname_source=${podname_list_source:1:-1}

### get target mysql pods name ###
k_target=`ssh $destination_ssh 'sudo kubectl get pods --all-namespaces | grep "mysql*" | grep "Running"'`
j_target=${k_target#*  }
# extract mysql pod name
j_target=${k_target#*  }
declare -a podname_list_target=()
for l in $j_target
do
podname_list_target+=([$l])
done
podname_target=${podname_list_target:1:-1}

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd ..
cd latency-output/

echo "Get mysql pod names for source and target = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
cd critical-path/
