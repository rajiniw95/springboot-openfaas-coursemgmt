start_time=$(date +%s)

# load path/ filename variables
source bundled-transfers/variables.sh

sl_funcs_path=''
sl_funcs_path+=$destination_path 
sl_funcs_path+=$bundled_folder
sl_funcs_path+=$source_sl_registry_path
echo $sl_funcs_path

cd $sl_funcs_path

for FILE in *;
do
cd $FILE;
FILE=${FILE,,}
sudo faas-cli build -f $FILE.yml;
cd ..;
echo $FILE;
done

end_time=$(date +%s)

elapsed=$(( end_time - start_time ))

echo $elapsed 
