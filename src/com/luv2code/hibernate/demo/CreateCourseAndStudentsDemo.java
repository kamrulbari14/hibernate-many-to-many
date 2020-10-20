package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		//Create session factory 
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		//Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			System.out.println("transacion started");
			session.beginTransaction();
			
			//create course
			Course tempCourse = new Course("Pacman- How to get millions of points");
			
			System.out.println("\nSaving the course");
			session.save(tempCourse);
			
			//create student objects
			Student tempStudent1 = new Student("Jonhn","Doe","jd@gmail.com");
			Student tempStudent2 = new Student("Kamrul","Bari","KB@gmail.com");
			
			//add students to the course
			tempCourse.addStudents(tempStudent1);
			tempCourse.addStudents(tempStudent2);
			
			System.out.println("\nSaving the Students");
			//save the students
			session.save(tempStudent1);
			session.save(tempStudent2);
			
			session.getTransaction().commit();
			System.out.println("All done!");
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
