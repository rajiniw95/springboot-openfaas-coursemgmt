## Summary of Latency Distributions for 500000 records

Types of HTTP Repests 
- load\_form : *http://127.0.0.1:8080/showNewCourseForm/*
- create : *http://127.0.0.1:8080/saveCourse*
- retrieve : *http://127.0.0.1:8080/*
- retrieve\_by\_id : *http://127.0.0.1:8080/showFormForUpdate/*
- update : *http://127.0.0.1:8080/updateCourse*
- delete : *http://127.0.0.1:8080/deleteCourse/*

<strong>workload\_A</strong> : CREATE\_HEAVY \[50% load\_form, 50% create\]

<strong>workload\_B</strong> : CREATE\_ONLY \[100% create\]

<strong>workload\_C</strong> : RETRIEVE\_ONLY\_STATIC \[100% retrieve\]

<strong>workload\_D</strong> : RETRIEVE\_DYNAMIC \[50% create, 50% retrieve\]

<strong>workload\_E</strong> : DELETE\_ONLY \[100% delete\]

<strong>workload\_F</strong> : CREATE\_AND\_DELETE \[50% create, 50% delete\]

<strong>workload\_G</strong> : RETRIEVE\_AND\_UPDATE \[50% retrieve\_by\_id, 50% update\]

<strong>workload\_H</strong> : ALL\_OPERATIONS \[25% create, 25% retrieve\_by\_id, 25% update, 25% delete\]

|Metric   		|A:load\_form|A:create|B:create|C:retrieve|D:create|D:retrieve|E:delete|F:create|F:delete|G:retrieve\_by\_id|G:update|H:create|H:retrieve\_by\_id|H:update|H:delete|
|---			|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|---	|	
|Total Count    	|500000|500000|500000|500000|500000|500000|500000|500000|500000|500000|500000|500000|500000|500000|500000|
|Mean   	  	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|Std Deviation   	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|50 th Percentile   	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|90 th Percentile    	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|95 th Percentile    	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|99 th Percentile    	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|99.9 th Percentile    |	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|99.99 th Percentile   |	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|99.999 th Percentile  |	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
|Max    		|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	|	
