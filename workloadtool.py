import requests
import pandas as pd

# GET request to get all courses (home page)
uri_home = 'http://127.0.0.1:8080/'
response_home = requests.get(uri_home)
print(response_home.url) 
print(response_home.status_code)      
print(response_home.elapsed.total_seconds())   

# GET request to load new course form
uri_new_course = 'http://127.0.0.1:8080/showNewCourseForm'
response_new = requests.get(uri_new_course)
print(response_new.url)   
print(response_new.status_code)      
print(response_new.elapsed.total_seconds())      

uri_save_course = 'http://127.0.0.1:8080/saveCourse'

# read CSV to da
newcourselist= pd.read_csv("courselist.csv")
for ind in newcourselist.index:
	# GET request load new course form
	response_new = requests.get(uri_new_course)
	print(response_new.url)
	print(response_new.status_code)      
	print(response_new.elapsed.total_seconds())     
	
	# Extract row data from dataframe
	courseCode_input = newcourselist['courseCode'][ind]
	courseName_input = newcourselist['courseName'][ind]
	lecturer_input = newcourselist['lecturer'][ind]
	credits_input = newcourselist['credits'][ind]
	
	# Assign row data to dictionary
	new_course_obj = {'courseCode': courseCode_input, 'courseName' : courseName_input, 'lecturer' : lecturer_input, 'credits' : credits_input}    

	# POST request to save course
	response_save = requests.post(uri_save_course, data = new_course_obj)
	print(response_save.status_code)     
	print(response_save.elapsed.total_seconds())   
	print(response_save.url)     
	 
"""
# GET request to delete course by ID
uri_delete_course = 'http://127.0.0.1:8080/deleteCourse/'
uri_delete_id = '40'
uri_delete_course_by_id = uri_delete_course + uri_delete_id
response_delete = requests.get(uri_delete_course_by_id)
print(response_delete.status_code)     
print(response_delete.elapsed.total_seconds())  
print(response_delete.url)       

# GET request to get course by ID
uri_update_form = 'http://127.0.0.1:8080/showFormForUpdate/'
uri_update_id = '2'
uri_update_form_with_id = uri_update_form + uri_update_id
response_getId = requests.get(uri_update_form_with_id)
print(response_getId.status_code)     
print(response_getId.elapsed.total_seconds())  
print(response_getId.url)       
"""

