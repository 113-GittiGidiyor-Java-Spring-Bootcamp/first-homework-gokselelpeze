package dev.patika.services;

import dev.patika.models.Course;
import dev.patika.models.Instructor;
import dev.patika.repository.CrudRepository;
import dev.patika.repository.InstructorRepository;
import dev.patika.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class InstructorService implements CrudRepository<Instructor>, InstructorRepository {

    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

    @Override
    public List<Instructor> findAll() {
        return em.createQuery("from Instructor", Instructor.class).getResultList();

    }

    @Override
    public Instructor findById(int id) {
        return em.find(Instructor.class, id);
    }

    @Override
    public void saveToDatabase(Instructor instructor) {
        try {
            em.getTransaction().begin();
            em.persist(instructor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteFromDatabase(Instructor instructor) {
        try {
            em.getTransaction().begin();

            Instructor foundInstructor = em.createQuery("from Instructor i WHERE i.id =:id", Instructor.class).setParameter("id", instructor.getId()).getSingleResult();
            em.remove(foundInstructor);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteFromDatabase(int id) {
        try {
            em.getTransaction().begin();

            Instructor foundInstructor = em.find(Instructor.class, id);
            em.remove(foundInstructor);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void updateOnDatabase(Instructor instructor, int id) {
        try {
            em.getTransaction().begin();

            Instructor foundInstructor = em.find(Instructor.class, id);
            foundInstructor.setAddress(instructor.getAddress());
            foundInstructor.setName(instructor.getName());
            foundInstructor.setPhoneNumber(instructor.getPhoneNumber());
            em.merge(foundInstructor);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Course> findCoursesOfInstructor(int id) {
        return findById(id).getCourses();
    }
}
