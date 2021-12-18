
## Summary of Latency Distribution for Workload B

<strong>workload\_B</strong> : CREATE\_ONLY \[100% create\]

Types of HTTP Repests in Workload B
- create : *http://127.0.0.1:8080/saveCourse*

|HTTP Request Type: create_course 	|B:create_course (500)	|B:create_course (5,000)	|B:create_course (50,000)	|B:create_course (500,000)	|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|4999						|50000						|500,000					|
|Mean   	  						|100.396				|84.530						|67.234						|66.343						|
|Std Deviation   					|76.663					|69.230						|33.904						|26.852						|		
|50 th Percentile   				|80						|67							|62							|64							|		
|90 th Percentile    				|140					|119						|79							|78							|		
|95 th Percentile    				|177					|172						|104						|94							|
|99 th Percentile    				|581					|436						|188						|171						|	
|99.9 th Percentile   	 			|773					|709						|479						|388						|
|99.99 th Percentile  				|773					|837						|760						|563						|
|99.999 th Percentile  				|773					|837						|1124						|833						|
|Max    							|773					|837						|1124						|1292						|
<br>

|Serverless Invocation: saveCourse 	|B:saveCourse (500)	|B:saveCourse (5,000)	|B:saveCourse (50,000)	|B:saveCourse (500,000)	|
|---								|---				|---					|---					|---					|
|<strong>Total Count</strong>    	|500				|5000					|50000					|500000					|
|Mean   	  						|86.300 			|76.528					|59.832					|58.944					|
|Std Deviation   					|70.868				|68.037					|32.874					|26.506					|	
|50 th Percentile   				|66					|59						|55						|56						|	
|90 th Percentile    				|127				|108					|71						|69						|		
|95 th Percentile    				|165				|163					|96						|87						|	
|99 th Percentile    				|311				|428					|180					|164					|	
|99.9 th Percentile   	 			|763				|685					|467					|381					|
|99.99 th Percentile  				|763				|828					|696					|557					|
|99.999 th Percentile  				|763				|828					|864					|810					|
|Max    							|763				|828					|864					|1285					|
<br>

|Database Operation: CREATE 	|B:CREATE (500)	|B:CREATE (5,000)	|B:CREATE (50,000)	|B:CREATE (500,000)	|
|---							|---			|---				|---				|---				|
|<strong>Total Count</strong>   |500			|4999				|49898				|499853				|
|Mean   	  					|60.342			|56.920				|44.719				|44.402				|
|Std Deviation   				|67.274			|63.867				|31.311				|25.724				|	
|50 th Percentile   			|45				|42					|39					|40					|	
|90 th Percentile    			|97				|84					|50					|50					|	
|95 th Percentile    			|132			|136				|78					|70					|	
|99 th Percentile    			|302			|398				|162				|147				|	
|99.9 th Percentile   		 	|707			|652				|443				|364				|
|99.99 th Percentile  			|707			|813				|706				|541				|
|99.999 th Percentile  			|707			|813				|851				|798				|
|Max    						|707			|813				|851				|1269				|
|<strong>No response rate 		|0%				|0.02%				|0.204% 			|0.029%				|