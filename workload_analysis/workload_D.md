
## Summary of Latency Distribution for Workload D

<strong>workload\_D</strong> : RETRIEVE\_DYNAMIC \[50% create, 50% retrieve\]

Types of HTTP Repests in Workload D
- create : *http://127.0.0.1:8080/saveCourse*
- retrieve : *http://127.0.0.1:8080/*

Number of records in the database = 500

|HTTP Request Type: create	 		|D:create (500)			|D:create (5,000)			|D:create (50,000)			|D:create (500,000)		|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|95.478					| 							| 							|							|
|Std Deviation   					|100.609				| 							| 							|							|	
|50 th Percentile   				|62						| 							| 							|							|		
|90 th Percentile    				|156					| 							| 							|							|		
|95 th Percentile    				|344					|							|							|							|
|99 th Percentile    				|556					|							|							|							|	
|99.9 th Percentile   	 			|746					|							|							|							|
|99.99 th Percentile  				|746					|							|							|							|
|99.999 th Percentile  				|746					|							|							|							|
|Max    							|746					|							|							|							|
<br>

|Serverless Invocation: saveCourse	 	|D:saveCourse (500)		|D:saveCourse (5,000)		|D:saveCourse (50,000)		|D:saveCourse (500,000)		|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000						|
|Mean   	  							|88.152 				|							|							|							|
|Std Deviation   						|97.673					| 							|							|							|	
|50 th Percentile   					|55						| 							|							|							|	
|90 th Percentile    					|149						| 							|							|							|		
|95 th Percentile    					|309						| 							|							|							|	
|99 th Percentile    					|500						|							|							|							|	
|99.9 th Percentile   	 				|737					|							|							|							|
|99.99 th Percentile  					|737					|							|							|							|
|99.999 th Percentile  					|737					|							|							|							|
|Max    								|737					|							|							|							|
<br>

|Database Operation: CREATE 	|D:CREATE (500)	 	|D:CREATE (5,000)	|D:CREATE (50,000)		|D:CREATE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|5000				|50000					|500000					|
|Mean   	  					|78.208				|					|						|						|
|Std Deviation   				|95.281				|					|						|						|	
|50 th Percentile   			|46					| 					|						|						|	
|90 th Percentile    			|136				| 					|						|						|	
|95 th Percentile    			|300				| 					|						|						|	
|99 th Percentile    			|490				| 					|						|						|	
|99.9 th Percentile   		 	|723				|					|						|						|
|99.99 th Percentile  			|723				|					|						|						|
|99.999 th Percentile  			|723				|					|						|						|
|Max    						|723				|					|						|						|
|<strong>No response rate 		|0%					|					|						|						|

<br>

|HTTP Request Type: retrieve 		|D:retrieve (500)		|D:retrieve (5,000)			|D:retrieve (50,000)		|D:retrieve (500,000)		|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|69.966					| 							| 							|							|
|Std Deviation   					|39.941					| 							| 							|							|	
|50 th Percentile   				|59						| 							| 							|							|		
|90 th Percentile    				|121					| 							| 							|							|		
|95 th Percentile    				|133					|							|							|							|
|99 th Percentile    				|162					|							|							|							|	
|99.9 th Percentile   	 			|302					|							|							|							|
|99.99 th Percentile  				|302					|							|							|							|
|99.999 th Percentile  				|302					|							|							|							|
|Max    							|302					|							|							|							|
<br>

|Serverless Invocation: getAllCourses 	|D:getAllCourses (500)	|D:getAllCourses (5,000)	|D:getAllCourses (50,000)	|D:getAllCourses (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000						|
|Mean   	  							|17.674 				|							|							|							|
|Std Deviation   						|19.083					| 							|							|							|	
|50 th Percentile   					|13						| 							|							|							|	
|90 th Percentile    					|46						| 							|							|							|		
|95 th Percentile    					|54						| 							|							|							|	
|99 th Percentile    					|66						|							|							|							|	
|99.9 th Percentile   	 				|249					|							|							|							|
|99.99 th Percentile  					|249					|							|							|							|
|99.999 th Percentile  					|249					|							|							|							|
|Max    								|249					|							|							|							|
<br>

|Database Operation: RETRIEVE 	|D:RETRIEVE (500)	|D:RETRIEVE (5,000)	|D:RETRIEVE (50,000)	|D:RETRIEVE (500,000)	|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|5000				|50000					|500000					|
|Mean   	  					|1.726				|					|						|						|
|Std Deviation   				|1.580				|					|						|						|	
|50 th Percentile   			|1					| 					|						|						|	
|90 th Percentile    			|3					| 					|						|						|	
|95 th Percentile    			|4					| 					|						|						|	
|99 th Percentile    			|7					| 					|						|						|	
|99.9 th Percentile   		 	|20					|					|						|						|
|99.99 th Percentile  			|20					|					|						|						|
|99.999 th Percentile  			|20					|					|						|						|
|Max    						|20 				|					|						|						|
|<strong>No response rate 		|0%					|					|						|						|
