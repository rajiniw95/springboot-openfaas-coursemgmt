start_time=$(date +%s)

# load path/ filename variables 
source variables.sh

sl_funcs_path=''
sl_funcs_path+=$destination_path 
sl_funcs_path+='serverlessFuncs'
echo $sl_funcs_path

#cd $sl_funcs_path

#for FILE in *;
#do
#cd $FILE;
#FILE=${FILE,,}
#sudo faas-cli up -f $FILE.yml;
#cd ..;
#echo $FILE;
#done

#end_time=$(date +%s)

#elapsed=$(( end_time - start_time ))

#echo $elapsed 
