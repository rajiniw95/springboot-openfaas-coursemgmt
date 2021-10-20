import requests
import pandas as pd
import numpy as np
from matplotlib import pyplot as plt
import os

# declare variables for URIs
uri_home = 'http://127.0.0.1:8080/'
uri_new_course = 'http://127.0.0.1:8080/showNewCourseForm'
uri_update_form = 'http://127.0.0.1:8080/showFormForUpdate/'
uri_save_course = 'http://127.0.0.1:8080/saveCourse'
uri_delete_course = 'http://127.0.0.1:8080/deleteCourse/'

# define arrays for elapsed time (for analysis)
elapsedtime_home = np.array([])
elapsedtime_new_course = np.array([])
elapsedtime_update_form = np.array([])
elapsedtime_save_course = np.array([])
elapsedtime_delete_course = np.array([])

# GET request to get all courses (load home page)
response_home = requests.get(uri_home)
print("Loading Home Page ... ")
print(response_home.url) 
print("Status code : " + str(response_home.status_code))      
print("Elapsed time : " + str(response_home.elapsed.total_seconds())) 
# add elapsed time to array (for analysis)
elapsedtime_home= np.append(elapsedtime_home, response_home.elapsed.total_seconds())    
  
print("=========== LOAD HOME PAGE ===========")

# read dataset CSV to dataframe
new_course_list = pd.read_csv("course_list_dataset.csv")
# get number of rows in dataframe
num_records = new_course_list.shape[0]

# iterate over dataframe and add new course for each item in dataframe
for ind in new_course_list.index:
	# GET request load new course form
	response_new = requests.get(uri_new_course)
	print("Loading Add New Course Page ... ")
	print(response_new.url)
	print("Status code : " + str(response_new.status_code))      
	print("Elapsed time : " + str(response_new.elapsed.total_seconds())) 
	# add elapsed time to array (for analysis)
	elapsedtime_new_course= np.append(elapsedtime_new_course, response_new.elapsed.total_seconds())     
	
	# Extract row data from dataframe
	courseCode_input = new_course_list['courseCode'][ind]
	courseName_input = new_course_list['courseName'][ind]
	lecturer_input = new_course_list['lecturer'][ind]
	credits_input = new_course_list['credits'][ind]
	
	# Assign new course data to dictionary
	new_course_obj = {'courseCode' : courseCode_input, 'courseName' : courseName_input, 'lecturer' : lecturer_input, 'credits' : credits_input}    

	# POST request to save course
	response_save = requests.post(uri_save_course, data = new_course_obj)
	print("Saving New Course ... ")
	print(response_save.url)  
	print("Status code : " + str(response_save.status_code))   
	print("Elapsed time : " + str(response_save.elapsed.total_seconds())) 
	# add elapsed time to array (for analysis)
	elapsedtime_save_course= np.append(elapsedtime_save_course, response_save.elapsed.total_seconds())     
	
print("=========== END ADD NEW COURSE ===========")

# read database CSV to dataframe (testing update course)		
db_course_list = pd.read_csv("courses/db_course_list.csv", header=None)

# iterate over dataframe and update every course in dataframe
for ind in db_course_list.index:
	# GET request to get course by ID
	# find courseId at 0th index
	uri_update_id = db_course_list[0][ind]
	# set uri to include courseId
	uri_update_form_with_id = uri_update_form + str(uri_update_id)
	response_getId = requests.get(uri_update_form_with_id)
	print("Loading Update Form ... ")
	print(response_getId.url) 
	print("Status code : " + str(response_getId.status_code))     
	print("Elapsed time : " + str(response_getId.elapsed.total_seconds())) 
	# add elapsed time to array (for analysis)
	elapsedtime_update_form= np.append(elapsedtime_update_form, response_getId.elapsed.total_seconds()) 
	
	# read existing attribute values from dataframe
	courseCode_updated = db_course_list[1][ind]
	courseName_updated = db_course_list[2][ind]
	lecturer_updated = db_course_list[3][ind]
	# Update credits for all courses with '100'
	credits_updated = 100
	
	# Assign update course data to dictionary
	update_course_obj = {'id' : uri_update_id, 'courseCode' : courseCode_updated, 'courseName' : courseName_updated, 'lecturer' : lecturer_updated, 'credits' : credits_updated}
	
	# POST request to save course
	response_update = requests.post(uri_save_course, data = update_course_obj)
	print("Updating Course ... ")
	print(response_update.url)  
	print("Status code : " + str(response_update.status_code))     
	print("Elapsed time : " + str(response_update.elapsed.total_seconds()))  
	# add elapsed time to array (for analysis)
	elapsedtime_save_course= np.append(elapsedtime_save_course, response_update.elapsed.total_seconds())  

print("=========== END UPDATE COURSE ===========")

# iterate over dataframe to delete every course in dataframe
for ind in db_course_list.index:
	# GET request to delete course by ID
	# find courseId at 0th index
	uri_delete_id = db_course_list[0][ind]
	# append courseId to delete course URI
	uri_delete_course_by_id = uri_delete_course + str(uri_delete_id)
	response_delete = requests.get(uri_delete_course_by_id)
	print("Deleting Course ... ")
	print(response_delete.url)  
	print("Status code : " + str(response_delete.status_code))     
	print("Elapsed time : " + str(response_delete.elapsed.total_seconds()))
	# add elapsed time to array (for analysis)
	elapsedtime_delete_course= np.append(elapsedtime_delete_course, response_delete.elapsed.total_seconds())
	  	
print("=========== END DELETE COURSE ===========")

# PLOTTING GRAPHS FOR ELAPSED TIME

print("Elapsed time for home page HTTP requests : ") 
print(elapsedtime_home)  
print("Elapsed time for load new course page HTTP requests : ")
print(elapsedtime_new_course)  
print("Elapsed time for load update course form HTTP requests : ")  
print(elapsedtime_update_form)  
print("Elapsed time for save course HTTP requests : ")  
print(elapsedtime_save_course)  
print("Elapsed time for delete course HTTP requests : ")  
print(elapsedtime_delete_course)  

os.chdir("monitoring_graphs")

print("=========== PLOTTING GRAPH : Load New Course Page ===========")

x = np.arange(start=1, stop=num_records+1, step=1)
y = elapsedtime_new_course
  
plt.title("Elapsed time for Load New Course Page HTTP requests")
plt.xlabel("Request number")
plt.ylabel("Elapsed time (seconds)")
plt.plot(x, y, color ="green")
plt.savefig('new_course_page.png') 
plt.clf()

print("=========== PLOTTING GRAPH : Load Update Course Page ===========")

a = np.arange(start=1, stop=num_records+1, step=1)
b = elapsedtime_update_form
  
plt.title("Elapsed time for Load Update Course Page HTTP requests")
plt.xlabel("Request number")
plt.ylabel("Elapsed time (seconds)")
plt.plot(a, b, color ="blue")
plt.savefig('update_course_page.png') 
plt.clf()

print("=========== PLOTTING GRAPH : Save Course ===========")

c = np.arange(start=1, stop=2*(num_records)+1, step=1)
d = elapsedtime_save_course
  
plt.title("Elapsed time for Save Course HTTP requests")
plt.xlabel("Request number")
plt.ylabel("Elapsed time (seconds)")
plt.plot(c, d, color ="red")
plt.savefig('save_course.png') 
plt.clf()

print("=========== PLOTTING GRAPH : Delete Course ===========")

e = np.arange(start=1, stop=num_records+1, step=1)
f = elapsedtime_delete_course
  
plt.title("Elapsed time for Delete Course HTTP requests")
plt.xlabel("Request number")
plt.ylabel("Elapsed time (seconds)")
plt.plot(e, f, color ="orange")
plt.savefig('delete_course.png') 
plt.clf()
