
## Summary of Latency Distribution for Workload G

<strong>workload\_G</strong> : RETRIEVE\_AND\_UPDATE \[50% retrieve\_by\_id, 50% update\]

Types of HTTP Repests in Workload G
- retrieve\_by\_id : *http://127.0.0.1:8080/showFormForUpdate/*
- update : *http://127.0.0.1:8080/updateCourse*

|HTTP Request Type: retrieve\_by\_id 	|G:retrieve\_by\_id (500)	|G:retrieve\_by\_id (5,000)	|G:retrieve\_by\_id (50,000)	|G:retrieve\_by\_id (500,000)	|
|---									|---						|---						|---							|---							|
|<strong>Total Count</strong>    		|488						|4994						|49957							|499874							|
|Mean   	  							|31.568						|23.136						|23.212							|27.835								|
|Std Deviation   						|35.385						|23.804						|26.726							|174.539								|		
|50 th Percentile   					|29							|21							|22								|21								|		
|90 th Percentile    					|44							|31							|30								|30								|		
|95 th Percentile    					|51							|36							|36								|35								|
|99 th Percentile    					|113						|85							|81								|95								|	
|99.9 th Percentile   	 				|695						|471						|312							|2957								|
|99.99 th Percentile  					|695						|566						|676							|3345								|
|99.999 th Percentile  					|695						|566						|3529							|10031								|
|Max    								|695						|566						|3529							|60095								|
<br>

|Serverless Invocation: getCourseById	|G:getCourseById (500)	|G:getCourseById (5,000)	|G:getCourseById (50,000)	|G:getCourseById (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|488					|4994						|49957						|499870				 		|
|Mean   	  							|19.744 				|15.534						|15.961						|21.026							|
|Std Deviation   						|19.093					|21.830						|25.928						|175.478							|	
|50 th Percentile   					|18						|13							|14							|14							|	
|90 th Percentile    					|28						|21							|21							|21							|		
|95 th Percentile    					|32						|26							|27							|26							|	
|99 th Percentile    					|110					|66							|64							|76							|	
|99.9 th Percentile   	 				|221					|417						|298						|2953							|
|99.99 th Percentile  					|221					|563						|673						|3343							|
|99.999 th Percentile  					|221					|563						|3523						|10015							|
|Max    								|221					|563						|3523						|60063							|
<br>

|Database Operation: RETRIEVE 	|G:RETRIEVE (500)	|G:RETRIEVE (5,000)	|G:RETRIEVE (50,000)	|G:RETRIEVE (500,000)	|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |488				|4990				|49848					|497277					|
|Mean   	  					|3.511				|2.059				|2.257					|7.988						|
|Std Deviation   				|10.328				|10.631				|11.131					|121.432						|	
|50 th Percentile   			|2					|1					|1						|2						|	
|90 th Percentile    			|4					|2					|2						|4						|	
|95 th Percentile    			|6					|3					|4						|5						|	
|99 th Percentile    			|55					|15					|17						|22						|	
|99.9 th Percentile   		 	|123				|168				|141					|2935						|
|99.99 th Percentile  			|123				|400				|344					|3311						|
|99.999 th Percentile  			|123				|400				|1223					|4727						|
|Max    						|123				|400				|1223					|6943						|
|<strong>No response rate 		|0%					|0.08%				|0.21%					|0.52%						|
<br>

|HTTP Request Type: update 		 	|G:update (500)			|G:update (5,000)			|G:update (50,000)			|G:update (500,000)			|
|---								|---					|---						|---						|---						|
|<strong>Total Count</strong>    	|488					|4994						|49957						|499874						|
|Mean   	  						|105.307				|115.134					|115.712 					|119.507							|
|Std Deviation   					|88.716					|110.892					|112.787					|148.763							|		
|50 th Percentile   				|82						|71							|70							|73							|		
|90 th Percentile    				|166					|244						|253						|262							|		
|95 th Percentile    				|242					|374						|387						|389							|
|99 th Percentile    				|542					|575						|576						|587							|	
|99.9 th Percentile   	 			|750					|835						|795						|811							|
|99.99 th Percentile  				|750					|925						|1051						|1071							|
|99.999 th Percentile  				|750					|925						|1323						|10015							|
|Max    							|750					|925						|1323						|60031							|
<br>

|Serverless Invocation: updateCourse 	|G:updateCourse (500)	|G:updateCourse (5,000)		|G:updateCourse (50,000)	|G:updateCourse (500,000)	|
|---									|---					|---						|---						|---						|
|<strong>Total Count</strong>    		|488					|4994						|49957						|499873				 		|
|Mean   	  							|80.421 				|107.806					|108.801					|113.005							|
|Std Deviation   						|94.693					|110.533					|112.651					|148.145							|	
|50 th Percentile   					|68						|63							|62							|65							|	
|90 th Percentile    					|156					|237						|246						|256							|		
|95 th Percentile    					|218					|364						|379						|382							|	
|99 th Percentile    					|533					|568						|569						|580							|	
|99.9 th Percentile   	 				|737					|806						|790						|803							|
|99.99 th Percentile  					|737					|919						|1046						|1056							|
|99.999 th Percentile  					|737					|919						|1311						|10007							|
|Max    								|737					|919						|1311						|600031							|
<br>

|Database Operation: UPDATE 	|G:UPDATE (500)		|G:UPDATE (5,000)	|G:UPDATE (50,000)		|G:UPDATE (500,000)		|
|---							|---				|---				|---					|---					|
|<strong>Total Count</strong>   |488				|4987				|49819					|496702					|
|Mean   	  					|76.230				|88.787				|89.926					|94.081						|
|Std Deviation   				|79.496				|107.440			|107.792				|110.709						|	
|50 th Percentile   			|49					|46					|46						|48						|	
|90 th Percentile    			|137				|214				|222					|233						|	
|95 th Percentile    			|200				|346				|352					|359						|	
|99 th Percentile    			|514				|546				|534					|547						|	
|99.9 th Percentile   		 	|715				|757				|729					|752						|
|99.99 th Percentile  			|715				|833				|864					|941						|
|99.999 th Percentile  			|715				|833				|1087					|1329						|
|Max    						|715				|833				|1087 					|4599						|
|<strong>No response rate 		|0%					|0.14%				|0.12%					|0.63%						|