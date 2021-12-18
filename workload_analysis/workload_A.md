
## Summary of Latency Distribution for Workload A

<strong>workload\_A</strong> : CREATE\_HEAVY \[50% load\_form, 50% create\]

Types of HTTP Repests in Workload A
- load\_form : *http://127.0.0.1:8080/showNewCourseForm/*
- create : *http://127.0.0.1:8080/saveCourse*

|HTTP Request Type: load\_form 			|A:load\_form (500)	|A:load\_form (5,000)	|A:load\_form (50,000)	|A:load\_form (500,000)	|
|---									|---				|---					|---					|---					|
|<strong>Total Count</strong>    		|500				|5000					|50000					|500000					|
|Mean   	  							|13.888				|7.013					|						|						|
|Std Deviation   						|61.389				|8.111					|						|						|
|50 th Percentile   					|10	 				|7						|						|						|
|90 th Percentile    					|18					|9						|						|						|
|95 th Percentile    					|26					|11						|						|						|	
|99 th Percentile    					|42					|18						|						|						|
|99.9 th Percentile   	 				|1376				|83						|						|						|
|99.99 th Percentile  					|1376				|440					|						|						|
|99.999 th Percentile  					|1376				|440					|						|						|
|Max    								|1376				|440					|						|						|
<br>

|HTTP Request Type: create_course 	|A:create_course (500)	|A:create_course (5,000)	|A:create_course (50,000)	|A:create_course (500,000)	|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|100.634				|101.023					|							|							|
|Std Deviation   					|75.107					|89.991						|							|							|		
|50 th Percentile   				|80						|71							|							|							|		
|90 th Percentile    				|149					|169						|							|							|		
|95 th Percentile    				|198					|276						|							|							|
|99 th Percentile    				|452					|525						|							|							|	
|99.9 th Percentile   	 			|823					|732						|							|							|
|99.99 th Percentile  				|823					|970						|							|							|
|99.999 th Percentile  				|823					|970						|							|							|
|Max    							|823					|970						|							|							|
<br>

|Serverless Invocation: saveCourse 	|A:saveCourse (500)	|A:saveCourse (5,000)	|A:saveCourse (50,000)	|A:saveCourse (500,000)	|
|---								|---				|---					|---					|---					|
|<strong>Total Count</strong>    	|500				|5000					|50000					|500000					|
|Mean   	  						|88.154				|93.696					|						|						|
|Std Deviation   					|74.252				|90.084					|						|						|	
|50 th Percentile   				|67					|63						|						|						|	
|90 th Percentile    				|133				|161					|						|						|		
|95 th Percentile    				|187				|269					|						|						|	
|99 th Percentile    				|444				|518					|						|						|	
|99.9 th Percentile   	 			|781				|725					|						|						|
|99.99 th Percentile  				|781				|963					|						|						|
|99.999 th Percentile  				|781				|963					|						|						|
|Max    							|781				|963					|						|						|
<br>

|Database Operation: CREATE 	|A:CREATE (500)	|A:CREATE (5,000)	|A:CREATE (50,000)	|A:CREATE (500,000)	|
|---							|---			|---				|---				|---				|
|<strong>Total Count</strong>   |499			|4947				|50,000				|500,000			|
|Mean   	  					|61.251			|71.761				|					|					|
|Std Deviation   				|59.748			|87.132				|					|					|	
|50 th Percentile   			|45				|44					|					|					|	
|90 th Percentile    			|103			|135				|					|					|	
|95 th Percentile    			|137			|240				|					|					|	
|99 th Percentile    			|350			|491				|					|					|	
|99.9 th Percentile   		 	|683			|719				|					|					|
|99.99 th Percentile  			|683			|913				|					|					|
|99.999 th Percentile  			|683			|913				|					|					|
|Max    						|683			|913				|					|					|
|<strong>No response rate 		|0.2%			|1.06%				| 					|					|