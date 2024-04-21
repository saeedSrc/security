package com.saeed.springboot.security.dao;

import com.saeed.springboot.security.entity.Course;
import com.saeed.springboot.security.entity.Instructor;
import com.saeed.springboot.security.entity.InstructorDetail;
import com.saeed.springboot.security.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor instructor);
    Instructor findInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesForInstructor(int id);

    Instructor findCoursesForInstructorByJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int id);

    void deleteInstructor(int id);

    void deleteCourseById(int id);

    void SaveCourse(Course course);

    Course findCourseAndReviews(int id);

    Course findCourseStudentsByCourseId(int id);

    Student findStudentWitCoursesById(int id);


    void update(Student student);
}
