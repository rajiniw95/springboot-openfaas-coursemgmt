package com.coursemgmt.courses.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @Column(name = "cid")
    private long id;

    /*@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;*/

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "lecturer")
    private String lecturer;

    @Column(name = "credits")
    private int credits;
    
    /*
    public Course(long id, String courseCode, String courseName, String lecturer, int credits)
    {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.credits = credits;
    }
    */
    
    public long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
