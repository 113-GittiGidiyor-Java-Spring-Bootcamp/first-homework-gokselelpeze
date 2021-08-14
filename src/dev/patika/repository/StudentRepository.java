package dev.patika.repository;

import dev.patika.models.Course;

import java.util.List;

public interface StudentRepository {

    List<Course> findCoursesOfStudent(int id);

}
