
## Summary of Latency Distribution for Workload C

<strong>workload\_C</strong> : RETRIEVE\_ONLY\_STATIC \[100% retrieve\]

Types of HTTP Repests in Workload C
- retrieve : *http://127.0.0.1:8080/*

Number of records in the database = 500

|HTTP Request Type: retrieve 		|C:retrieve (500)		|C:retrieve (5,000)			|C:retrieve (50,000)		|C:retrieve (500,000)		|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|68.410					|74.076						| 							|							|
|Std Deviation   					|34.435					|53.868						|							|							|		
|50 th Percentile   				|63						|64							|							|							|		
|90 th Percentile    				|87						|88							|							|							|		
|95 th Percentile    				|98						|115						|							|							|
|99 th Percentile    				|154					|297						|							|							|	
|99.9 th Percentile   	 			|708					|738						|							|							|
|99.99 th Percentile  				|708					|1294						|							|							|
|99.999 th Percentile  				|708					|1294						|							|							|
|Max    							|708					|1294						|							|							|
<br>

|Serverless Invocation: getAllCourses 	|C:getAllCourses (500)	|C:getAllCourses (5,000)	|C:getAllCourses (50,000)	|C:getAllCourses (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000						|
|Mean   	  							|10.820 				|15.946						|							|							|
|Std Deviation   						|14.596					|44.891						|							|							|	
|50 th Percentile   					|7						|8							|							|							|	
|90 th Percentile    					|16						|21							|							|							|		
|95 th Percentile    					|44						|40							|							|							|	
|99 th Percentile    					|53						|160						|							|							|	
|99.9 th Percentile   	 				|220					|573						|							|							|
|99.99 th Percentile  					|220					|1178						|							|							|
|99.999 th Percentile  					|220					|1178						|							|							|
|Max    								|220					|1178						|							|							|
<br>

|Database Operation: RETRIEVE 	|C:RETRIEVE (500)	|C:RETRIEVE (5,000)	|C:RETRIEVE (50,000)	|C:RETRIEVE (500,000)	|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|4994				|49898					|500000					|
|Mean   	  					|1.300				|2.745				|						|						|
|Std Deviation   				|2.228				|15.642				|						|						|	
|50 th Percentile   			|1					|1					|						|						|	
|90 th Percentile    			|2					|3					|						|						|	
|95 th Percentile    			|2					|5					|						|						|	
|99 th Percentile    			|4					|28					|						|						|	
|99.9 th Percentile   		 	|29					|138				|						|						|
|99.99 th Percentile  			|29					|965				|						|						|
|99.999 th Percentile  			|29					|965				|						|						|
|Max    						|29					|965				|						|						|
|<strong>No response rate 		|0%					|0.12%				|						|						|