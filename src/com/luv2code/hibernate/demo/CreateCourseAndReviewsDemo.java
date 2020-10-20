package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		//Create session factory 
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		//Create Session
		Session session = factory.getCurrentSession();
		
		try {
			
			System.out.println("transacion started");
			session.beginTransaction();
			
			//create course
			Course tempCourse = new Course("Pacman- How to get millions of points");
			
			// add reviews to the course
			tempCourse.addReview(new Review("This is a good course"));
			tempCourse.addReview(new Review("Pure Wastage..."));
			tempCourse.addReview(new Review("A good one indeed :)"));
			
			//save the course that will also save reviews because of CascadeType.ALL
			System.out.println("Saving the course");
			System.out.println("tempCourse:"+tempCourse);
			System.out.println("that reviews:"+tempCourse.getReviews());
			
			session.save(tempCourse);
			
			session.getTransaction().commit();
			System.out.println("All done!");
		}

		finally {
			session.close();
			factory.close();
		}
	}

}
