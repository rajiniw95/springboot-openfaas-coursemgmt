#add path/ filename variables 
cd ..
source variables.sh
cd critical-path

source s2t_copy_hotdata.sh

#load_hotdata=`ssh $destination_ssh 'bash -s' < target_load_hotdata.sh`
#load_hotdata=${load_hotdata: -7}
#cd ../latency-output
#echo "Load hot data at the target (seconds) = " >> $latency_file_name
#echo $load_hotdata >> $latency_file_name
#cd ..
#cd critical-path
