package dev.patika.controller;

import dev.patika.models.Course;
import dev.patika.models.Instructor;
import dev.patika.models.Student;
import dev.patika.services.InstructorService;
import dev.patika.services.StudentService;

import java.util.List;

public class InstructorController {

    private InstructorService instructorService = new InstructorService();

    public Instructor findInstructor(int id) {
        return instructorService.findById(id);
    }

    public List<Instructor> findAllInstructors() {
        return instructorService.findAll();
    }

    public void saveInstructor(Instructor instructor) {
        instructorService.saveToDatabase(instructor);
    }

    public void deleteInstructor(int id) {
        instructorService.deleteFromDatabase(id);
    }

    public void updateInstructor(Instructor instructor, int id) {
        instructorService.updateOnDatabase(instructor, id);
    }

    public List<Course> findCoursesOfInstructor(int id){
        return instructorService.findCoursesOfInstructor(id);
    }
}
