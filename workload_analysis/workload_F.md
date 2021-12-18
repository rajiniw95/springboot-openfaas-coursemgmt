
## Summary of Latency Distribution for Workload F

<strong>workload\_F</strong> : CREATE\_AND\_DELETE \[50% create, 50% delete\]

Types of HTTP Repests in Workload F
- create : *http://127.0.0.1:8080/saveCourse*
- delete : *http://127.0.0.1:8080/deleteCourse/*

|HTTP Request Type: create 		 	|F:create (500)			|F:create (5,000)			|F:create (50,000)			|F:create (500,000)			|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|101.018				|105.270					|77.301						|							|
|Std Deviation   					|65.006					|94.821						|59.023						|							|		
|50 th Percentile   				|85						|74							|65							|							|		
|90 th Percentile    				|138					|170						|104						|							|		
|95 th Percentile    				|178					|302						|151						|							|
|99 th Percentile    				|369					|536						|396						|							|	
|99.9 th Percentile   	 			|811					|811						|646						|							|
|99.99 th Percentile  				|811					|982						|836						|							|
|99.999 th Percentile  				|811					|982						|880						|							|
|Max    							|811					|982						|880						|							|
<br>

|Serverless Invocation: saveCourse	 	|F:saveCourse (500)		|F:saveCourse (5,000)		|F:saveCourse (50,000)		|F:saveCourse (500,000)		|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000				 		|
|Mean   	  							|86.562 				|97.506						|69.997						|							|
|Std Deviation   						|62.121					|93.316						|58.751						|							|	
|50 th Percentile   					|68						|64							|57							|							|	
|90 th Percentile    					|124					|163						|97							|							|		
|95 th Percentile    					|173					|295						|144						|							|	
|99 th Percentile    					|355					|519						|388						|							|	
|99.9 th Percentile   	 				|797					|791						|636						|							|
|99.99 th Percentile  					|797					|938						|820						|							|
|99.999 th Percentile  					|797					|938						|872						|							|
|Max    								|797					|938						|872						|							|
<br>

|Database Operation: CREATE 	|F:CREATE (500)		|F:CREATE (5,000)	|F:CREATE (50,000)		|F:CREATE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|4994				|49959					|500000					|
|Mean   	  					|58.182				|73.234				|52.390					|						|
|Std Deviation   				|56.418				|87.436				|55.471					|						|	
|50 th Percentile   			|45					|44					|40						|						|	
|90 th Percentile    			|87					|131				|67						|						|	
|95 th Percentile    			|127				|256				|121					|						|	
|99 th Percentile    			|337				|493				|363					|						|	
|99.9 th Percentile   		 	|742				|720				|606					|						|
|99.99 th Percentile  			|742				|916				|800					|						|
|99.999 th Percentile  			|742				|916				|841					|						|
|Max    						|742				|916				|841					|						|
|<strong>No response rate 		|0%					|0.12%				|0.82%					|						|
<br>

|HTTP Request Type: delete 		 	|F:delete (500)			|F:delete (5,000)			|F:delete (50,000)			|F:delete (500,000)			|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|4994						|49959						|500000						|
|Mean   	  						|92.530					|90.375						|74.656 					|							|
|Std Deviation   					|63.515					|85.049						|59.478						|							|		
|50 th Percentile   				|77						|67							|63							|							|		
|90 th Percentile    				|120					|136						|100						|							|		
|95 th Percentile    				|149					|203						|144						|							|
|99 th Percentile    				|406					|511						|365						|							|	
|99.9 th Percentile   	 			|688					|925						|761						|							|
|99.99 th Percentile  				|688					|1242						|1145						|							|
|99.999 th Percentile  				|688					|1242						|1295						|							|
|Max    							|688					|1242						|1295						|							|
<br>

|Serverless Invocation: deleteCourse 	|F:deleteCourse (500)	|F:deleteCourse (5,000)		|F:deleteCourse (50,000)	|F:deleteCourse (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|4994						|49959						|500000				 		|
|Mean   	  							|83.686 				|85.026						|69.118						|							|
|Std Deviation   						|60.467					|84.576						|59.430						|							|	
|50 th Percentile   					|67						|61							|57							|							|	
|90 th Percentile    					|110					|132						|94							|							|		
|95 th Percentile    					|140					|198						|138						|							|	
|99 th Percentile    					|398					|504						|360						|							|	
|99.9 th Percentile   	 				|560					|921						|758						|							|
|99.99 th Percentile  					|560					|1234						|1139						|							|
|99.999 th Percentile  					|560					|1234						|1289						|							|
|Max    								|560					|1234						|1289						|							|
<br>

|Database Operation: DELETE 	|F:DELETE (500)		|F:DELETE (5,000)	|F:DELETE (50,000)		|F:DELETE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|4992				|49898					|500000					|
|Mean   	  					|55.730				|64.692				|52.833					|						|
|Std Deviation   				|56.802				|81.525				|57.023					|						|	
|50 th Percentile   			|43					|43					|40						|						|	
|90 th Percentile    			|75					|102				|71						|						|	
|95 th Percentile    			|116				|170				|118					|						|	
|99 th Percentile    			|382				|472				|333					|						|	
|99.9 th Percentile   		 	|490				|899				|735					|						|
|99.99 th Percentile  			|490				|1162				|1109					|						|
|99.999 th Percentile  			|490				|1162				|1270					|						|
|Max    						|490				|1162				|1270 					|						|
|<strong>No response rate 		|0%					|0.04%				|0.12%					|						|