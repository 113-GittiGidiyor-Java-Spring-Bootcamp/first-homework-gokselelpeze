package dev.patika.controller;

import dev.patika.models.Course;
import dev.patika.models.Student;
import dev.patika.services.StudentService;

import java.util.List;

public class StudentController {

    private StudentService studentService = new StudentService();

    public Student findStudent(int id) {
        return studentService.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    public void saveStudent(Student student) {
        studentService.saveToDatabase(student);
    }

    public void deleteStudent(int id) {
        studentService.deleteFromDatabase(id);
    }

    public void updateStudent(Student student, int id) {
        studentService.updateOnDatabase(student, id);
    }

    public List<Course> findCoursesOfStudent(int id){
        return studentService.findCoursesOfStudent(id);
    }
}
