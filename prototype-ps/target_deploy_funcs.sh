# start_time=$(date +%s)

# TODO : get from variables.sh
target_path='/home/rajini/prototype_ps/'

sl_funcs_path=''
sl_funcs_path+=$target_path 
sl_funcs_path+='serverlessFuncs'
echo $sl_funcs_path

cd $sl_funcs_path

for FILE in *;
do
cd $FILE;
FILE=${FILE,,}
faas-cli up -f $FILE.yml;
cd ..;
echo $FILE;
done

# end_time=$(date +%s)

# elapsed=$(( end_time - start_time ))

# echo $elapsed 