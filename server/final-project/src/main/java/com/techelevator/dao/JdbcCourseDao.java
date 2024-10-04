package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Course;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCourseDao implements CourseDao{

    private final String COURSE_SELECT = "SELECT c.course_id, c.name, c.blurb FROM course c ";

    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Course getCourseById(int courseId) {
        Course course = null;
        String sql = COURSE_SELECT +
                " WHERE c.course_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
            if (results.next()) {
                course = mapRowToCourse(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return course;
    }

//    @Override
//    public Course getCourseByName(String name) {
//        Course course = null;
//        String sql = COURSE_SELECT +
//                " WHERE c.name = ?";
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
//            if (results.next()) {
//                course = mapRowToCourse(results);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        }
//
//        return course;
//
//    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = COURSE_SELECT;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                courses.add(mapRowToCourse(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return courses;
    }

//    @Override
//    public Course updateCourse(Course updatedCourse) {
//        Course course = null;
//
//        String sql = "UPDATE course SET name = ?, blurb = ? WHERE course_id = ?";
//
//        try {
//            jdbcTemplate.update(sql, updatedCourse.getName(), updatedCourse.getBlurb(), updatedCourse.getCourseId());
//
//            course = getCourseById(updatedCourse.getCourseId());
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to database or server", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//
//        return course;
//    }

    private Course mapRowToCourse(SqlRowSet results) {
        Course course = new Course();
        course.setCourseId(results.getInt("course_id"));
        course.setName(results.getString("name"));
        course.setBlurb(results.getString("blurb"));
        return course;
    }

}
