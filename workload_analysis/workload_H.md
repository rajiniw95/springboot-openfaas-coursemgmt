
## Summary of Latency Distribution for Workload H

<strong>workload\_H</strong> : ALL\_OPERATIONS \[25% create, 25% retrieve\_by\_id, 25% update, 25% delete\]

Types of HTTP Repests in Workload H
- create : *http://127.0.0.1:8080/saveCourse*
- retrieve\_by\_id : *http://127.0.0.1:8080/showFormForUpdate/*
- update : *http://127.0.0.1:8080/updateCourse*
- delete : *http://127.0.0.1:8080/deleteCourse/*

|HTTP Request Type: create 				|H:create (500)				|H:create (5,000)			|H:create (50,000)			|H:create (500,000)				|
|---									|---						|---						|---						|---							|
|<strong>Total Count</strong>    		|500						|5000						|50000 						|500000							|
|Mean   	  							|107.038					|85.731						|							|								|
|Std Deviation   						|81.868						|68.644						| 							|								|		
|50 th Percentile   					|83							|68							|							|								|		
|90 th Percentile    					|156						|120						|							|								|		
|95 th Percentile    					|239						|181						|							|								|
|99 th Percentile    					|496						|421						|							|								|	
|99.9 th Percentile   	 				|873						|681						|							|								|
|99.99 th Percentile  					|873						|1114						|							|								|
|99.999 th Percentile  					|873						|1114						|							|								|
|Max    								|873						|1114						|							|								|
<br>

|Serverless Invocation: saveCourse		|H:saveCourse (500)		|H:saveCourse (5,000)		|H:saveCourse (50,000)		|H:saveCourse (500,000)		|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000				 		|
|Mean   	  							|92.110 				|77.572						| 							|							|
|Std Deviation   						|80.241					|67.184						|							|							|	
|50 th Percentile   					|66						|60							|							|							|	
|90 th Percentile    					|143					|109						| 							|							|		
|95 th Percentile    					|228					|172						| 							|							|	
|99 th Percentile    					|467					|416						| 							|							|	
|99.9 th Percentile   	 				|869					|656						| 							|							|
|99.99 th Percentile  					|869					|1101						| 							|							|
|99.999 th Percentile  					|869					|1101						| 							|							|
|Max    								|869					|1101						| 							|							|
<br>

|Database Operation: CREATE 	|H:CREATE (500)		|H:CREATE (5,000)	|H:CREATE (50,000)		|H:CREATE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|5000				|49848					|500000					|
|Mean   	  					|64.360				|57.101				|2.257					|						|
|Std Deviation   				|71.752				|63.059				|11.131					|						|	
|50 th Percentile   			|43					|42					|1						|						|	
|90 th Percentile    			|104				|81					|2						|						|	
|95 th Percentile    			|184				|139				|4						|						|	
|99 th Percentile    			|419				|399				|17						|						|	
|99.9 th Percentile   		 	|713				|655				|141					|						|
|99.99 th Percentile  			|713				|1086				|344					|						|
|99.999 th Percentile  			|713				|1086				|1223					|						|
|Max    						|713				|1086				|1223					|						|
|<strong>No response rate 		|0%					|0%					|0.21%					|						|
<br>

|HTTP Request Type: retrieve\_by\_id 	|H:retrieve\_by\_id (500)	|H:retrieve\_by\_id (5,000)	|H:retrieve\_by\_id (50,000)	|H:retrieve\_by\_id (500,000)	|
|---									|---						|---						|---							|---							|
|<strong>Total Count</strong>    		|500						|5000						|50000							|500000							|
|Mean   	  							|24.792						|22.523						| 								|								|
|Std Deviation   						|9.956						|11.427						| 								|								|		
|50 th Percentile   					|25							|22							| 								|								|		
|90 th Percentile    					|34							|30							| 								|								|		
|95 th Percentile    					|37							|34							| 								|								|
|99 th Percentile    					|54							|50							| 								|								|	
|99.9 th Percentile   	 				|117						|140						| 								|								|
|99.99 th Percentile  					|117						|321						| 								|								|
|99.999 th Percentile  					|117						|321						| 								|								|
|Max    								|117						|321						| 								|								|
<br>

|Serverless Invocation: getCourseById	|H:getCourseById (500)	|H:getCourseById (5,000)	|H:getCourseById (50,000)	|H:getCourseById (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000				 		|
|Mean   	  							|15.516 				|15.197						| 							|							|
|Std Deviation   						|6.639					|7.950						|							|							|	
|50 th Percentile   					|15						|15							| 							|							|	
|90 th Percentile    					|22						|21							| 							|							|		
|95 th Percentile    					|25						|24							| 							|							|	
|99 th Percentile    					|36						|40							| 							|							|	
|99.9 th Percentile   	 				|64						|94							|							|							|
|99.99 th Percentile  					|64						|226						|							|							|
|99.999 th Percentile  					|64						|226						|							|							|
|Max    								|64						|226						|							|							|
<br>

|Database Operation: RETRIEVE 	|H:RETRIEVE (500)	|H:RETRIEVE (5,000)	|H:RETRIEVE (50,000)	|H:RETRIEVE (500,000)	|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|5000				|50000					|500000					|
|Mean   	  					|1.318				|1.418				| 						|						|
|Std Deviation   				|1.283				|2.291				|						|						|	
|50 th Percentile   			|1					|1					|						|						|	
|90 th Percentile    			|2					|2					|						|						|	
|95 th Percentile    			|2					|3					| 						|						|	
|99 th Percentile    			|5					|6					|						|						|	
|99.9 th Percentile   		 	|21					|22					|						|						|
|99.99 th Percentile  			|21					|101				|						|						|
|99.999 th Percentile  			|21					|101				|						|						|
|Max    						|21					|101				|						|						|
|<strong>No response rate 		|0%					|0.08%				|						|						|
<br>

|HTTP Request Type: update 		 	|H:update (500)			|H:update (5,000)			|H:update (50,000)			|H:update (500,000)			|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|107.208				|107.899					|		 					|							|
|Std Deviation   					|107.058				|118.804					|							|							|		
|50 th Percentile   				|71						|67							| 							|							|		
|90 th Percentile    				|185					|182						|							|							|		
|95 th Percentile    				|287					|335						|							|							|
|99 th Percentile    				|641					|655						|							|							|	
|99.9 th Percentile   	 			|765					|1091						|							|							|
|99.99 th Percentile  				|765					|1160						|							|							|
|99.999 th Percentile  				|765					|1160						|							|							|
|Max    							|765					|1160						|							|							|
<br>

|Serverless Invocation: updateCourse 	|H:updateCourse (500)	|H:updateCourse (5,000)		|H:updateCourse (50,000)	|H:updateCourse (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000				 		|
|Mean   	  							|98.470 				|101.058					|							|							|
|Std Deviation   						|105.418				|118.528					|							|							|	
|50 th Percentile   					|62						|60							|							|							|	
|90 th Percentile    					|173					|175						|							|							|		
|95 th Percentile    					|281					|327						|							|							|	
|99 th Percentile    					|632					|650						|							|							|	
|99.9 th Percentile   	 				|757					|1084						|							|							|
|99.99 th Percentile  					|757					|1155						|							|							|
|99.999 th Percentile  					|757					|1155						|							|							|
|Max    								|757					|1155						|							|							|
<br>

|Database Operation: UPDATE 	|H:UPDATE (500)		|H:UPDATE (5,000)	|H:UPDATE (50,000)		|H:UPDATE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|5000				|49819					|500000					|
|Mean   	  					|85.094				|87.725				|89.926					|						|
|Std Deviation   				|104.447			|117.131			|107.792				|						|	
|50 th Percentile   			|47					|47					|46						|						|	
|90 th Percentile    			|163				|161				|222					|						|	
|95 th Percentile    			|258				|315				|352					|						|	
|99 th Percentile    			|610				|631				|534					|						|	
|99.9 th Percentile   		 	|745				|1069				|729					|						|
|99.99 th Percentile  			|745				|1137				|864					|						|
|99.999 th Percentile  			|745				|1137				|1087					|						|
|Max    						|745				|1137				|1087 					|						|
|<strong>No response rate 		|0%					|0%					|0.12%					|						|

|HTTP Request Type: delete 		 	|H:delete (500)			|H:delete (5,000)			|H:delete (50,000)			|H:delete (500,000)			|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|500					|5000						|50000						|500000						|
|Mean   	  						|92.622					|97.672						|		 					|							|
|Std Deviation   					|89.966					|98.194						|							|							|		
|50 th Percentile   				|65						|66							| 							|							|		
|90 th Percentile    				|132					|162						|							|							|		
|95 th Percentile    				|298					|308						|							|							|
|99 th Percentile    				|530					|548						|							|							|	
|99.9 th Percentile   	 			|663					|755						|							|							|
|99.99 th Percentile  				|663					|933						|							|							|
|99.999 th Percentile  				|663					|933						|							|							|
|Max    							|663					|933						|							|							|
<br>

|Serverless Invocation: deleteCourseById 	|H:deleteCourseById (500)	|H:deleteCourseById (5,000)		|H:deleteCourseById (50,000)	|H:deleteCourseById (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|500					|5000						|50000						|500000				 		|
|Mean   	  							|85.528 				|92.585						| 							|							|
|Std Deviation   						|88.373					|98.050						|							|							|	
|50 th Percentile   					|57						|60							| 							|							|	
|90 th Percentile    					|124					|157						|							|							|		
|95 th Percentile    					|293					|303						|							|							|	
|99 th Percentile    					|524					|544						|							|							|	
|99.9 th Percentile   	 				|646					|749						|							|							|
|99.99 th Percentile  					|646					|927						|							|							|
|99.999 th Percentile  					|646					|927						|							|							|
|Max    								|646					|927						|							|							|
<br>

|Database Operation: DELETE 	|H:DELETE (500)		|H:DELETE (5,000)	|H:DELETE (50,000)		|H:DELETE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |500				|5000				|50000					|500000					|
|Mean   	  					|72.198				|79.291				|						|						|
|Std Deviation   				|87.035				|97.106				|						|						|	
|50 th Percentile   			|44					|47					|						|						|	
|90 th Percentile    			|113				|143				|						|						|	
|95 th Percentile    			|274				|289				|						|						|	
|99 th Percentile    			|507				|525				|						|						|	
|99.9 th Percentile   		 	|624				|735				|						|						|
|99.99 th Percentile  			|624				|907				|						|						|
|99.999 th Percentile  			|624				|907				|						|						|
|Max    						|624				|907				|	 					|						|
|<strong>No response rate 		|0%					|0%					|						|						|