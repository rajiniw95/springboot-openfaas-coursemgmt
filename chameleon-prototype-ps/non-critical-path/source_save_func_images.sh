# save the function docker images as tar files in the bundled folder

start_time=$(date +%s.%6N)

# cd to folder that is to be copied over to the target
cd ..
cd ..
mkdir $bundled_folder && cd "$_"
mkdir $sl_func_images && cd "$_"

# update function image names to variables 
sudo docker save -o deletecoursebyid.tar localhost:5001/deletecoursebyid
sudo docker save -o getallcourses.tar localhost:5001/getallcourses
sudo docker save -o getcoursebyid.tar localhost:5001/getcoursebyid
sudo docker save -o savecourse.tar localhost:5001/savecourse
sudo docker save -o updatecourse.tar localhost:5001/updatecourse

end_time=$(date +%s.%6N)

elapsed=$(echo "scale=6; $end_time - $start_time" | bc)

cd ..
cd ../chameleon-prototype-ps/latency-output/

echo "Save tar files of the function images at the source (seconds) = " >> $latency_file_name
echo $elapsed >> $latency_file_name

cd ../non-critical-path/
