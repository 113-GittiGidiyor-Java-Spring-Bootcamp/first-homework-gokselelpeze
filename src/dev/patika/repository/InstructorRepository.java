package dev.patika.repository;

import dev.patika.models.Course;

import java.util.List;

public interface InstructorRepository {

    List<Course> findCoursesOfInstructor(int id);

}
