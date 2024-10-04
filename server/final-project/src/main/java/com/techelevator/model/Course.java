package com.techelevator.model;

import java.util.Objects;

public class Course {

    private int courseId;
    private String name;
    private String blurb;

    public Course(){}

    public Course(int courseId, String name, String blurb){
        this.courseId = courseId;
        this.name = name;
        this.blurb = blurb;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", name='" + name + '\'' +
                ", blurb='" + blurb + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId && Objects.equals(name, course.name) && Objects.equals(blurb, course.blurb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name, blurb);
    }
}
