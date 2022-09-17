# bundle files together to transfer to target site
# 1. serverless function files (with local registry updated)
# 2. mysql yaml files for creatign a single instance stateful application
# 3. variables.sh file whihccontains all user defined variables

start_time=$(date +%s.%6N)

sudo su <<EOF

cd ..
cd ..

mkdir $bundled_folder

cp -r $source_sl_registry_path $bundled_folder

cp -r $source_sql_k8s_yaml $bundled_folder

cd chameleon-prototype-ps

cp variables.sh ../$bundled_folder

EOF

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd ..
cd latency-output/

echo "Bundle files at the source (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ..
cd non-critical-path/
