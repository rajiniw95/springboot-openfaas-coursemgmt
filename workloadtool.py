import requests

uri_home_page = 'http://127.0.0.1:8080/'

uri_new_course_form = 'http://127.0.0.1:8080/showNewCourseForm'

uri_save_course = 'http://127.0.0.1:8080/saveCourse'

uri_delete_course = 'http://127.0.0.1:8080/deleteCourse'

course_obj = {'courseCode': 'CS1001', 'courseName' : 'testt', 'lecturer' : 'testerrr', 'credits' : 200}

delete_obj = {'id': '21'}

### GET request to get all courses

response = requests.get(uri_home_page)

print(response.status_code)      

print(response.elapsed.total_seconds())         

### GET request to load new course form

response = requests.get(uri_new_course_form)

print(response.status_code)      

print(response.elapsed.total_seconds())        

### POST request to save course

response = requests.post(uri_save_course, data = course_obj)

print(response.status_code)     

print(response.elapsed.total_seconds())         

### POST request to delete course

response = requests.post(uri_delete_course, data = delete_obj)

print(response.status_code)     

print(response.elapsed.total_seconds())         
