package com.saeed.springboot.security;

import com.saeed.springboot.security.dao.AppDAO;
import com.saeed.springboot.security.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);

//			findInstructorById(appDAO);

//			findInstructorDetail(appDAO);

//			deleteInstructorDetail(appDAO);

//			createInstructorWithCourses(appDAO);

//			findInstructorWithCourses(appDAO);

//			findCoursesForInstructor(appDAO);


//			findCoursesForInstructorByJoinFetch(appDAO);


//			updateInstructor(appDAO);

//			updateCourse(appDAO);

//			deleteInstructor(appDAO);

//			deleteCourseById(appDAO);

//			createCourseAndReviews(appDAO);
//			findCourseWithReviews(appDAO);

//			createCourseAndStudents(appDAO);

//			findCourseWithStudents(appDAO);
//			findStudentWithCourses(appDAO);

			addCoursesToStudent(appDAO);
		};

	}

	private void addCoursesToStudent(AppDAO appDAO) {
		int id = 2;

		Student student = appDAO.findStudentWitCoursesById(id);
		Course course1  = new Course("new course 1");
		Course course2  = new Course("new course 2");
		Course course3  = new Course("new course 3");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);


		System.out.println("saving student ... ");


		appDAO.update(student);

		System.out.println("done!");

	}

	private void findStudentWithCourses(AppDAO appDAO) {
		int studentId= 2;
		Student student = appDAO.findStudentWitCoursesById(studentId);

		System.out.println("finding student with courses:");

		System.out.println(student);
		System.out.println(student.getCourses());

		System.out.println("done!");
	}

	private void findCourseWithStudents(AppDAO appDAO) {
		int courseId = 10;

		Course course = appDAO.findCourseStudentsByCourseId(courseId);

		System.out.println("courses with students:");
		System.out.println(course);
		System.out.println(course.getStudents());
		System.out.println("done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course course = new Course("new course");
		course.addStudent(new Student("saeed", "rasooli", "saeed@yahoo"));
		course.addStudent(new Student("majid", "akbari", "majidi@yahoo"));
		course.addStudent(new Student("hamid", "hamidi", "hamidi@yahoo"));

		System.out.println("saving course and students...");

		appDAO.SaveCourse(course);

		System.out.println("done! ");
	}

	private void findCourseWithReviews(AppDAO appDAO) {
		int courseId = 10;

		Course course = appDAO.findCourseAndReviews(courseId);

		System.out.println("course:" + course);
		System.out.println("reviews:" + course.getReviews());
		System.out.println("done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("saeed course");

		course.addReview(new Review("great"));
		course.addReview(new Review("not bad"));
		course.addReview(new Review("it was ok!"));
		System.out.println("saving course...");
		appDAO.SaveCourse(course);
		System.out.println("done!");
	}

	private void deleteCourseById(AppDAO appDAO) {
		System.out.println("deleting course ...");

		appDAO.deleteCourseById(10);
		System.out.println("done!");
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 4;

		System.out.println("deleting instructor ...");
		appDAO.deleteInstructor(4);

		System.out.println("done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;

		Course course = appDAO.findCourseById(id);

		course.setTitle("update course");

		System.out.println("updating course ....");
		appDAO.update(course);

		System.out.println("done!");

	}

	private void updateInstructor(AppDAO appDAO) {

		int id = 4 ;

		Instructor instructor = appDAO.findInstructorById(id);

		instructor.setEmail("new@email.com");


		System.out.println("updating instructor: ");
		appDAO.update(instructor);

		System.out.println("done!");
	}

	private void findCoursesForInstructorByJoinFetch(AppDAO appDAO) {
		int id= 4;

		System.out.println("finding instructor with courses:");
		Instructor instructor= appDAO.findCoursesForInstructorByJoinFetch(id);
		System.out.println("instructor:" + instructor);
		System.out.println("courses:" + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id= 4;

		List<Course> courses = appDAO.findCoursesForInstructor(id);
		System.out.println("courses:" + courses);

	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 4;

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("instructor:" + instructor);
		System.out.println("instructor:" + instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("abbas", "hasani", "saeed@rasooli");
		InstructorDetail instructorDetail = new InstructorDetail("http://youtube", "video games");

		instructor.setInstructorDetail(instructorDetail);
		Course course1 = new Course("ali gitar");
		Course course2 = new Course("hasan gitar");
		Course course3 = new Course("majid gitar");


		instructor.add(course1);
		instructor.add(course2);
		instructor.add(course3);

		System.out.println("saving instructor with courses...");
		appDAO.save(instructor);

		System.out.println("done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 2 ;
		System.out.println("deleting instructor detail ...");

		appDAO.deleteInstructorDetailById(id);
		System.out.println("done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id=1;

		System.out.println("finding by id ...");

		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("detail: " + instructorDetail);

		System.out.println("instructor: " + instructorDetail.getInstructor());
	}

	private void findInstructorById(AppDAO appDAO) {

		int id= 2;

		 System.out.println("finding by id...");
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("instructor: "  + instructor);

	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("saeed", "rasooli", "saeed@rasooli");
		InstructorDetail instructorDetail = new InstructorDetail("http://youtube", "football");

		instructor.setInstructorDetail(instructorDetail);
		System.out.println("saving instructor: "+ instructor);
		appDAO.save(instructor);
		System.out.println("done saving instructor!");

	}

}
