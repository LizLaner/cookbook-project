package com.techelevator.controller;

import com.techelevator.dao.CourseDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.model.Course;
import com.techelevator.model.Recipe;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/courses")
public class CoursesController {

    private final CourseDao courseDao;
    private final RecipeDao recipeDao;

    public CoursesController(CourseDao courseDao, RecipeDao recipeDao) {
        this.courseDao = courseDao;
        this.recipeDao = recipeDao;
    }

    @GetMapping
    public List<Course> listAllCourses(){
        return courseDao.getCourses();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(path = "/{courseId}/recipes")
    public List<Recipe> getAllRecipesForCourse(@PathVariable int courseId){
        return recipeDao.getRecipesByCourseId(courseId);
    }
}
