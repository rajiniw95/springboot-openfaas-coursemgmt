## Defined Workload Types

### workload_A : CREATE_HEAVY

50% HTTP requests with no serverless invokations, 50% CREATE new records (ZIPFIAN)

load new course form and then save new course (repeated for all records in dataset)  

follows the natural order of HTTP request (new course form --> enter data to form --> save new course), as sent from the front end. 

### workload_B : CREATE_ONLY

100% CREATE new records (ZIPFIAN)

send HTTP request for save new course (repeated for all records in dataset)  

### workload_C : RETRIEVE_ONLY_STATIC

100% RETRIEVE all records, when the database size is static (ZIPFIAN)

send HTTP request for get home page (repeated for user defined retrieve_count)  	

### workload_D : RETRIEVE_DYNAMIC

50% CREATE new record, 50% RETRIEVE all records in database (ZIPFIAN)

We increase the size of the database by one record upon each iteration (and there by the amount of data being retieved)

if dataset_size >= defined_retrieve_count --> then call save+get for retrieve_count # of times

else if dataset_size < defined_retrieve_count --> then call save+get for number_records # of times AND only get for the difference

### workload_E : DELETE_ONLY

100% DELETE a record by the course ID (ZIPFIAN)

Deletes all existing courses in the database

### workload_F : CREATE_AND_DELETE 

50% CREATE new record, 50% DELETE (ZIPFIAN)

Insert a set of records to the satabase and then delete all existing courses from database

### workload_G : RETRIEVE_AND_UPDATE

50% RETRIEVE record by course ID, 50% Update all records in database (ZIPFIAN)

### workload_H : ALL_OPERATIONS 

25% CREATE, 25% RETREIVE by ID, 25% UPDATE and 25% DELETE (UNIFORM)
