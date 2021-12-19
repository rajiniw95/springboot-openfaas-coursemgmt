
## Summary of Latency Distribution for Workload A

<strong>workload\_A</strong> : CREATE\_HEAVY \[50% load\_form, 50% create\]

Types of HTTP Repests in Workload A
- load\_form : *http://127.0.0.1:8080/showNewCourseForm/*
- create : *http://127.0.0.1:8080/saveCourse*

|HTTP Request Type: load\_form 			|A:load\_form (500)	|A:load\_form (5,000)	|A:load\_form (50,000)	|A:load\_form (500,000)	|
|---									|---				|---					|---					|---					|
|<strong>Total Count</strong>    		|500				|5000					|50000					|500000					|
|Mean   	  							|13.888				|7.013					|						|6.659					|
|Std Deviation   						|61.389				|8.111					|						|2.362					|
|50 th Percentile   					|10	 				|7						|						|7						|
|90 th Percentile    					|18					|9						|						|9						|
|95 th Percentile    					|26					|11						|						|10						|	
|99 th Percentile    					|42					|18						|						|12						|
|99.9 th Percentile   	 				|1376				|83						|						|17						|
|99.99 th Percentile  					|1376				|440					|						|38						|
|99.999 th Percentile  					|1376				|440					|						|62						|
|Max    								|1376				|440					|						|546					|
<br>

|HTTP Request Type: create_course 	|A:create_course (500)	|A:create_course (5,000)	|A:create_course (50,000)	|A:create_course (500,000)	|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|100.634				|101.023					|							|65.279						|
|Std Deviation   					|75.107					|89.991						|							|29.289						|		
|50 th Percentile   				|80						|71							|							|61							|		
|90 th Percentile    				|149					|169						|							|74							|		
|95 th Percentile    				|198					|276						|							|94							|
|99 th Percentile    				|452					|525						|							|174						|	
|99.9 th Percentile   	 			|823					|732						|							|438						|
|99.99 th Percentile  				|823					|970						|							|627						|
|99.999 th Percentile  				|823					|970						|							|818						|
|Max    							|823					|970						|							|1373						|
<br>

|Serverless Invocation: saveCourse 	|A:saveCourse (500)	|A:saveCourse (5,000)	|A:saveCourse (50,000)	|A:saveCourse (500,000)	|
|---								|---				|---					|---					|---					|
|<strong>Total Count</strong>    	|500				|5000					|50000					|500000					|
|Mean   	  						|88.154				|93.696					|						|58.357					|
|Std Deviation   					|74.252				|90.084					|						|29.039					|	
|50 th Percentile   				|67					|63						|						|53						|	
|90 th Percentile    				|133				|161					|						|67						|		
|95 th Percentile    				|187				|269					|						|87						|	
|99 th Percentile    				|444				|518					|						|167					|	
|99.9 th Percentile   	 			|781				|725					|						|432					|
|99.99 th Percentile  				|781				|963					|						|621					|
|99.999 th Percentile  				|781				|963					|						|808					|
|Max    							|781				|963					|						|1000					|
<br>

|Database Operation: CREATE 	|A:CREATE (500)	|A:CREATE (5,000)	|A:CREATE (50,000)	|A:CREATE (500,000)	|
|---							|---			|---				|---				|---				|
|<strong>Total Count</strong>   |499			|4947				|50000				|499873				|
|Mean   	  					|61.251			|71.761				|					|44.632				|
|Std Deviation   				|59.748			|87.132				|					|28.285				|	
|50 th Percentile   			|45				|44					|					|40					|	
|90 th Percentile    			|103			|135				|					|50					|	
|95 th Percentile    			|137			|240				|					|71					|	
|99 th Percentile    			|350			|491				|					|151				|	
|99.9 th Percentile   		 	|683			|719				|					|414				|
|99.99 th Percentile  			|683			|913				|					|592				|
|99.999 th Percentile  			|683			|913				|					|787				|
|Max    						|683			|913				|					|988				|
|<strong>No response rate 		|0.2%			|1.06%				| 					|0.03%				|