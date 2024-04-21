package com.saeed.springboot.security.dao;

import com.saeed.springboot.security.entity.Course;
import com.saeed.springboot.security.entity.Instructor;
import com.saeed.springboot.security.entity.InstructorDetail;
import com.saeed.springboot.security.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        this.entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return this.entityManager.find(Instructor.class, id);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return this.entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        instructorDetail.getInstructor().setInstructorDetail(null);
        this.entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesForInstructor(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class
        );

        query.setParameter("data", id);

        List<Course> courses = query.getResultList();

        return courses;
    }


    @Override
    public Instructor findCoursesForInstructorByJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        " JOIN FETCH i.courses " +
                        " JOIN FETCH i.instructorDetail " +
                        "where  i.id= :data", Instructor.class
        );

        query.setParameter("data", id);

        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        return course;
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        Instructor instructor =  entityManager.find(Instructor.class, id);
       List<Course> courses = instructor.getCourses();

        for(Course course: courses) {
           course.setInstructor(null);

        }

        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void SaveCourse(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviews(int id) {


      TypedQuery<Course> query = entityManager.createQuery(
              "select c from Course c "
              + "JOIN FETCH c.reviews "
              + "where c.id = :data", Course.class);

      query.setParameter("data", id);

      Course course = query.getSingleResult();

      return course;
    }

    @Override
    public Course findCourseStudentsByCourseId(int id) {

        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", id);

        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentWitCoursesById(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select c from Student c "
                        + "JOIN FETCH c.courses "
                        + "where c.id = :data", Student.class);

        query.setParameter("data", id);

        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
