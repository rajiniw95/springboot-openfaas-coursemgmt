package com.coursemgmt.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coursemgmt.courses.monitoring.App;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

}
