# load path/ filename variables 
cd ..
source variables.sh
cd critical-path

source s2t_get_pod_names.sh 

source s2t_transfer_db_files.sh

reload_db=`ssh $destination_ssh 'bash -s' < target_restart_mysql.sh`
#reload_db=${reload_db: -7}
cd ../latency-output
echo "Reload the database at the target (seconds) = " >> $latency_file_name
echo $reload_db >> $latency_file_name
cd ..
cd critical-path
