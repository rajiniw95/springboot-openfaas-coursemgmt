source variables.sh

source source_copy_sl_files.sh

source source_copy_db_file.sh

ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_setup_sql.sh 

# ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_deploy_sl.sh

# ssh rajini@river-fe3.cs.uchicago.edu 'bash -s' < target_deploy_funcs.sh 

