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
faas-cli up -f $FILE.yml;
cd ..;
echo $FILE;
done