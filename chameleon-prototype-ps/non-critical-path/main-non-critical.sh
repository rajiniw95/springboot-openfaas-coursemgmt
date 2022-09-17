# load path/ filename variables 
cd ..
source variables.sh
cd non-critical-path

#### Create bundled folder with all files for transfer to target 
source source_bundle_files.sh 

#### Transfer bundled folder to target
source s2t_transfer_bundled_tar.sh





