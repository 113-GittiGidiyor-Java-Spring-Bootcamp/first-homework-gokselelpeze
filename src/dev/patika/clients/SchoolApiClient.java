package dev.patika.clients;

import dev.patika.controller.InstructorController;
import dev.patika.controller.StudentController;
import dev.patika.models.*;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;

public class SchoolApiClient {
    public static void main(String[] args) {
        if (checkTestData() == 0) {
            saveTestData();
        }

        StudentController studentController = new StudentController();

        // print students
        System.out.println("### ALL STUDENTS ###");
        for (Student s : studentController.findAllStudents()) {
            System.out.println(s);
        }
        // create new student
        System.out.println("### CREATING NEW STUDENT ###");
        Student tempStudent = new Student("Nehru Wheeler", LocalDate.of(1999, Month.JANUARY, 3), "Lot", 'F');
        studentController.saveStudent(tempStudent);
        // check student
        System.out.println("### READING NEW ADDED STUDENT ###");
        System.out.println(studentController.findStudent(tempStudent.getId()));

        System.out.println("### UPDATING NEW ADDED STUDENT ###");
        // update student
        tempStudent.setGender('M');
        studentController.updateStudent(tempStudent, tempStudent.getId());
        // check student
        System.out.println(studentController.findStudent(tempStudent.getId()));

        System.out.println("### DELETING NEW ADDED STUDENT ###");
        studentController.deleteStudent(tempStudent.getId());

        // print students
        System.out.println("### ALL STUDENTS ###");
        for (Student s : studentController.findAllStudents()) {
            System.out.println(s);
        }


        // INSTRUCTOR

        InstructorController instructorController = new InstructorController();

        System.out.println("### ALL INSTRUCTORS ###");
        // print instructors
        for (Instructor i : instructorController.findAllInstructors()) {
            System.out.println(i);
        }
        // create new instructor
        PermanentInstructor tempInstructor = new PermanentInstructor("Daphne Hess", "Lafayette", "8456189", 500);
        System.out.println("### CREATING NEW INSTRUCTOR ###");

        instructorController.saveInstructor(tempInstructor);
        System.out.println("### READING NEW ADDED INSTRUCTOR ###");
        // check instructor
        System.out.println(instructorController.findInstructor(tempInstructor.getId()));
        System.out.println("### UPDATING NEW ADDED INSTRUCTOR ###");

        // update instructor
        tempInstructor.setAddress("Balkier");
        instructorController.updateInstructor(tempInstructor, tempInstructor.getId());

        // check instructor
        System.out.println(instructorController.findInstructor(tempInstructor.getId()));
        System.out.println("### DELETING NEW ADDED INSTRUCTOR ###");
        // delete instructor
        instructorController.deleteInstructor(tempInstructor.getId());

        System.out.println("### ALL INSTRUCTORS ###");
        // print instructors
        for (Instructor i : instructorController.findAllInstructors()) {
            System.out.println(i);
        }

    }


    private static int checkTestData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return em.createQuery("from Student", Student.class).getResultList().size();

    }

    private static void saveTestData() {
        Student student1 = new Student("Gökhan Göksel Elpeze", LocalDate.of(1996, Month.APRIL, 24), "Istanbul", 'M');
        Student student2 = new Student("Clinton Duran", LocalDate.of(1992, Month.MAY, 11), "Veraval", 'M');
        Student student3 = new Student("Edan Buchanan", LocalDate.of(1991, Month.SEPTEMBER, 15), "Pematangsiantar", 'F');
        Student student4 = new Student("Colorado Hurst", LocalDate.of(1993, Month.OCTOBER, 22), "Mondolfo", 'F');

        Course course1 = new Course("Data Structures", "CME3204", 4);
        Course course2 = new Course("Algorithms", "CME3202", 3);
        Course course3 = new Course("Design Patterns", "CME3201", 4);

        VisitingResearcher instructor1 = new VisitingResearcher("Chaim Cabrera", "Grande Prairie", "8456189233",10);
        PermanentInstructor instructor2 = new PermanentInstructor("Yvonne West", "Mazy", "4019439",200);
        VisitingResearcher instructor3 = new VisitingResearcher("Dacey Wright", "Clarksville", "4197630",5);
        PermanentInstructor instructor4 = new PermanentInstructor("Gregory Carr", "Hattiesburg", "841893",300);


        course1.setInstructor(instructor1);
        course2.setInstructor(instructor2);
        course3.setInstructor(instructor3);
        course1.getStudents().add(student1);
        course1.getStudents().add(student2);


        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            em.getTransaction().begin();

            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);
            em.persist(course1);
            em.persist(course2);
            em.persist(course3);
            em.persist(instructor1);
            em.persist(instructor2);
            em.persist(instructor3);
            em.persist(instructor4);
            em.getTransaction().commit();

            System.out.println("All data persisted...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }

    }
}
