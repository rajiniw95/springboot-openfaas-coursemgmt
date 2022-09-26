# https://justyn.io/til/copy-files-directories-from-one-kubernetes-pod-to-another-kubernetes-pod/

# pipe the stdout of the tar command to the stdin of tar in a separate pod, similar to using tar to copy files between two servers without rsync/scp
# copies the contents of /dir1 from pod-01 to the same location in pod-0
# kubectl exec pod-01 -- tar cf - /dir1 /dir2 | kubectl exec -i pod-02 -- tar xvf - -C /

start_time=$(date +%s.%6N)

sudo kubectl exec $podname_source -- tar cf - /var/lib/mysql | ssh $destination_ssh sudo kubectl exec -i $podname_target -- tar xf - -C /

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd ..
cd latency-output/

echo "Transfer mysql database files from source to target = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
cd critical-path/
