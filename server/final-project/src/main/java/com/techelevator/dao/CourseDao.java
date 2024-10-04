package com.techelevator.dao;

import com.techelevator.model.Course;

import java.util.List;


public interface CourseDao {

    Course getCourseById(int courseId);

    List<Course> getCourses();

    //Course updateCourse(Course updatedCourse);

}
