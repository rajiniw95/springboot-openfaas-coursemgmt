
## Summary of Latency Distribution for Workload E

<strong>workload\_E</strong> : DELETE\_ONLY \[100% delete\]

Types of HTTP Repests in Workload E
- delete : *http://127.0.0.1:8080/deleteCourse/*

|HTTP Request Type: delete 		 	|E:delete (500)			|E:delete (5,000)			|E:delete (50,000)			|E:delete (1,000,000)		|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|4999						|49957						|999379						|
|Mean   	  						|97.694					|97.877						|73.367 					|73.609						|
|Std Deviation   					|68.100					|88.928						|53.107						|67.764						|		
|50 th Percentile   				|87						|69							|63							|64							|		
|90 th Percentile    				|136					|150						|97							|98							|		
|95 th Percentile    				|166					|234						|134						|135						|
|99 th Percentile    				|310					|534						|332						|338						|	
|99.9 th Percentile   	 			|919					|877						|706						|717						|
|99.99 th Percentile  				|919					|1257						|1005						|1046						|
|99.999 th Percentile  				|919					|1257						|1075						|2231						|
|Max    							|919					|1257						|1075						|34559						|
<br>

|Serverless Invocation: deleteCourse 	|E:deleteCourse (500)	|E:deleteCourse (5,000)		|E:deleteCourse (50,000)	|E:deleteCourse (1,000,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|4999						|49957						|999379				 		|
|Mean   	  							|85.710 				|91.545						|67.765						|68.272						|
|Std Deviation   						|58.713					|86.278						|52.891						|67.166						|	
|50 th Percentile   					|75						|63							|57							|58							|	
|90 th Percentile    					|124					|144						|91							|93							|		
|95 th Percentile    					|153					|230						|129						|130						|	
|99 th Percentile    					|300					|526						|327						|333						|	
|99.9 th Percentile   	 				|740					|846						|702						|711						|
|99.99 th Percentile  					|740					|1150						|1000						|1037						|
|99.999 th Percentile  					|740					|1150						|1071						|1953						|
|Max    								|740					|1150						|1071						|34559						|
<br>

|Database Operation: DELETE 	|E:DELETE (500)		|E:DELETE (5,000)	|E:DELETE (50,000)		|E:DELETE (1,000,000)	|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|4995				|49949					|997953					|
|Mean   	  					|57.526				|68.685				|51.359					|52.569					|
|Std Deviation   				|57.061				|83.330				|50.718					|52.868					|	
|50 th Percentile   			|44					|44					|40						|42						|	
|90 th Percentile    			|89					|117				|69						|71						|	
|95 th Percentile    			|115				|192				|106					|107					|	
|99 th Percentile    			|242				|500				|300					|309					|	
|99.9 th Percentile   		 	|727				|810				|666					|682					|
|99.99 th Percentile  			|727				|1131				|982					|993					|
|99.999 th Percentile  			|727				|1131				|1054					|1468					|
|Max    						|727				|1131				|1054					|2213					|
|<strong>No response rate 		|0%					|0.08%				|0.016%					|0.14%					|