
## Summary of Latency Distribution for Workload E

<strong>workload\_E</strong> : DELETE\_ONLY \[100% delete\]

Types of HTTP Repests in Workload E
- delete : *http://127.0.0.1:8080/deleteCourse/*

|HTTP Request Type: delete 		 	|E:delete (500)			|E:delete (5,000)			|E:delete (50,000)			|E:delete (500,000)			|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|4999						|49957						|500000						|
|Mean   	  						|97.694					|97.877						|73.367 					|							|
|Std Deviation   					|68.100					|88.928						|53.107						|							|		
|50 th Percentile   				|87						|69							|63							|							|		
|90 th Percentile    				|136					|150						|97							|							|		
|95 th Percentile    				|166					|234						|134						|							|
|99 th Percentile    				|310					|534						|332						|							|	
|99.9 th Percentile   	 			|919					|877						|706						|							|
|99.99 th Percentile  				|919					|1257						|1005						|							|
|99.999 th Percentile  				|919					|1257						|1075						|							|
|Max    							|919					|1257						|1075						|							|
<br>

|Serverless Invocation: deleteCourse 	|E:deleteCourse (500)	|E:deleteCourse (5,000)		|E:deleteCourse (50,000)	|E:deleteCourse (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|4999						|49957						|500000				 		|
|Mean   	  							|85.710 				|91.545						|67.765						|							|
|Std Deviation   						|58.713					|86.278						|52.891						|							|	
|50 th Percentile   					|75						|63							|57							|							|	
|90 th Percentile    					|124					|144						|91							|							|		
|95 th Percentile    					|153					|230						|129						|							|	
|99 th Percentile    					|300					|526						|327						|							|	
|99.9 th Percentile   	 				|740					|846						|702						|							|
|99.99 th Percentile  					|740					|1150						|1000						|							|
|99.999 th Percentile  					|740					|1150						|1071						|							|
|Max    								|740					|1150						|1071						|							|
<br>

|Database Operation: DELETE 	|E:DELETE (500)		|E:DELETE (5,000)	|E:DELETE (50,000)		|E:DELETE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|4995				|49949					|500000					|
|Mean   	  					|57.526				|68.685				|51.359					|						|
|Std Deviation   				|57.061				|83.330				|50.718					|						|	
|50 th Percentile   			|44					|44					|40						|						|	
|90 th Percentile    			|89					|117				|69						|						|	
|95 th Percentile    			|115				|192				|106					|						|	
|99 th Percentile    			|242				|500				|300					|						|	
|99.9 th Percentile   		 	|727				|810				|666					|						|
|99.99 th Percentile  			|727				|1131				|982					|						|
|99.999 th Percentile  			|727				|1131				|1054					|						|
|Max    						|727				|1131				|1054					|						|
|<strong>No response rate 		|0%					|0.08%				|0.016%					|						|