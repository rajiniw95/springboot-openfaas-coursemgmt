import requests

### GET request to get all courses

uri_home_page = 'http://127.0.0.1:8080/'

response = requests.get(uri_home_page)

print(response.status_code)      

print(response.elapsed.total_seconds())   

print(response.url)       

### GET request to load new course form

uri_new_course_form = 'http://127.0.0.1:8080/showNewCourseForm'

response = requests.get(uri_new_course_form)

print(response.status_code)      

print(response.elapsed.total_seconds()) 

print(response.url)        

### POST request to save course

uri_save_course = 'http://127.0.0.1:8080/saveCourse'

course_obj = {'courseCode': 'CS1001', 'courseName' : 'testt', 'lecturer' : 'testerrr', 'credits' : 200}

response = requests.post(uri_save_course, data = course_obj)

print(response.status_code)     

print(response.elapsed.total_seconds())   

print(response.url)       

### GET request to delete course by ID

uri_delete_course = 'http://127.0.0.1:8080/deleteCourse/'

uri_delete_id = '40'

uri_delete_course_by_id = uri_delete_course + uri_delete_id

response = requests.get(uri_delete_course_by_id)

print(response.status_code)     

print(response.elapsed.total_seconds())  

print(response.url)       

### GET request to get course by ID

uri_update_form = 'http://127.0.0.1:8080/showFormForUpdate/'

uri_update_id = '2'

uri_update_form_with_id = uri_update_form + uri_update_id

response = requests.get(uri_update_form_with_id)

print(response.status_code)     

print(response.elapsed.total_seconds())  

print(response.url)       

