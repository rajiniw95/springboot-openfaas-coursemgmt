
## Summary of Latency Distribution for Workload G

<strong>workload\_G</strong> : RETRIEVE\_AND\_UPDATE \[50% retrieve\_by\_id, 50% update\]

Types of HTTP Repests in Workload G
- retrieve\_by\_id : *http://127.0.0.1:8080/showFormForUpdate/*
- update : *http://127.0.0.1:8080/updateCourse*

|HTTP Request Type: retrieve\_by\_id 	|G:retrieve\_by\_id (500)	|G:retrieve\_by\_id (5,000)	|G:retrieve\_by\_id (50,000)	|G:retrieve\_by\_id (500,000)	|
|---									|---						|---						|---							|---							|
|<strong>Total Count</strong>    		|488						|4994						|49957							|500000							|
|Mean   	  							|31.568						|23.136						|23.212							|								|
|Std Deviation   						|35.385						|23.804						|26.726							|								|		
|50 th Percentile   					|29							|21							|22								|								|		
|90 th Percentile    					|44							|31							|30								|								|		
|95 th Percentile    					|51							|36							|36								|								|
|99 th Percentile    					|113						|85							|81								|								|	
|99.9 th Percentile   	 				|695						|471						|312							|								|
|99.99 th Percentile  					|695						|566						|676							|								|
|99.999 th Percentile  					|695						|566						|3529							|								|
|Max    								|695						|566						|3529							|								|
<br>

|Serverless Invocation: getCourseById	|G:getCourseById (500)	|G:getCourseById (5,000)	|G:getCourseById (50,000)	|G:getCourseById (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|488					|4994						|49957						|500000				 		|
|Mean   	  							|19.744 				|15.534						|15.961						|							|
|Std Deviation   						|19.093					|21.830						|25.928						|							|	
|50 th Percentile   					|18						|13							|14							|							|	
|90 th Percentile    					|28						|21							|21							|							|		
|95 th Percentile    					|32						|26							|27							|							|	
|99 th Percentile    					|110					|66							|64							|							|	
|99.9 th Percentile   	 				|221					|417						|298						|							|
|99.99 th Percentile  					|221					|563						|673						|							|
|99.999 th Percentile  					|221					|563						|3523						|							|
|Max    								|221					|563						|3523						|							|
<br>

|Database Operation: RETRIEVE 	|G:RETRIEVE (500)	|G:RETRIEVE (5,000)	|G:RETRIEVE (50,000)	|G:RETRIEVE (500,000)	|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |488				|4990				|49848					|500000					|
|Mean   	  					|3.511				|2.059				|2.257					|						|
|Std Deviation   				|10.328				|10.631				|11.131					|						|	
|50 th Percentile   			|2					|1					|1						|						|	
|90 th Percentile    			|4					|2					|2						|						|	
|95 th Percentile    			|6					|3					|4						|						|	
|99 th Percentile    			|55					|15					|17						|						|	
|99.9 th Percentile   		 	|123				|168				|141					|						|
|99.99 th Percentile  			|123				|400				|344					|						|
|99.999 th Percentile  			|123				|400				|1223					|						|
|Max    						|123				|400				|1223					|						|
|<strong>No response rate 		|0%					|0.08%				|0.21%					|						|
<br>

|HTTP Request Type: update 		 	|G:update (500)			|G:update (5,000)			|G:update (50,000)			|G:update (500,000)			|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|488					|4994						|49957						|500000						|
|Mean   	  						|105.307				|115.134					|115.712 					|							|
|Std Deviation   					|88.716					|110.892					|112.787					|							|		
|50 th Percentile   				|82						|71							|70							|							|		
|90 th Percentile    				|166					|244						|253						|							|		
|95 th Percentile    				|242					|374						|387						|							|
|99 th Percentile    				|542					|575						|576						|							|	
|99.9 th Percentile   	 			|750					|835						|795						|							|
|99.99 th Percentile  				|750					|925						|1051						|							|
|99.999 th Percentile  				|750					|925						|1323						|							|
|Max    							|750					|925						|1323						|							|
<br>

|Serverless Invocation: updateCourse 	|G:updateCourse (500)	|G:updateCourse (5,000)		|G:updateCourse (50,000)	|G:updateCourse (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|488					|4994						|49957						|500000				 		|
|Mean   	  							|80.421 				|107.806					|108.801					|							|
|Std Deviation   						|94.693					|110.533					|112.651					|							|	
|50 th Percentile   					|68						|63							|62							|							|	
|90 th Percentile    					|156					|237						|246						|							|		
|95 th Percentile    					|218					|364						|379						|							|	
|99 th Percentile    					|533					|568						|569						|							|	
|99.9 th Percentile   	 				|737					|806						|790						|							|
|99.99 th Percentile  					|737					|919						|1046						|							|
|99.999 th Percentile  					|737					|919						|1311						|							|
|Max    								|737					|919						|1311						|							|
<br>

|Database Operation: UPDATE 	|G:UPDATE (500)		|G:UPDATE (5,000)	|G:UPDATE (50,000)		|G:UPDATE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |488				|4987				|49819					|500000					|
|Mean   	  					|76.230				|88.787				|89.926					|						|
|Std Deviation   				|79.496				|107.440			|107.792				|						|	
|50 th Percentile   			|49					|46					|46						|						|	
|90 th Percentile    			|137				|214				|222					|						|	
|95 th Percentile    			|200				|346				|352					|						|	
|99 th Percentile    			|514				|546				|534					|						|	
|99.9 th Percentile   		 	|715				|757				|729					|						|
|99.99 th Percentile  			|715				|833				|864					|						|
|99.999 th Percentile  			|715				|833				|1087					|						|
|Max    						|715				|833				|1087 					|						|
|<strong>No response rate 		|0%					|0.14%				|0.12%					|						|