# load path/ filename variables
source bundled-transfers/variables.sh

sl_funcs_path=''
sl_funcs_path+=$destination_path 
sl_funcs_path+=$bundled_folder
sl_funcs_path+=$sl_func_images
echo $sl_funcs_path

cd $sl_funcs_path

start_time=$(date +%s.%6N)

for FILE in *;
do
sudo docker load -i $FILE;
done

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

echo $elapsed 
