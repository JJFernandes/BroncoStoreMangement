package test;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.entities.Address;
import model.entities.Order;
import model.entities.Customer;
import model.entities.HistoricalPrice;
import model.entities.Product;
import model.entities.Professor;
import model.entities.Student;
import model.entities.StudentProfessor;

public class CreateDBdemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Professor.class)
				.addAnnotatedClass(StudentProfessor.class)
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(HistoricalPrice.class)
				.addAnnotatedClass(Order.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Customer studentHolder = new Student(
					"012071033", //bronco id
					"Joshua Fernandes", //name
					Date.valueOf("1997-01-01"), //dob
					"917-285-7362", //phone
					new Address("8438", "Lake St.", "Savannah", "CA", "31404"), //address
					Date.valueOf("2016-08-20"), //enter date
					Date.valueOf("2021-06-03"), //grad date
					"Computer Science", //major
					"N/A" //minor
					);
			
			Customer professorHolder = new Professor(
					"011580335", //bronco id
					"Neil Gilligan", //name
					Date.valueOf("1992-06-09"), //dob
					"981-454-0666", //phone
					new Address("9743", "Thompson Ave.", "Montclair", "CA", "31694"), //address
					"Computer Science", //department
					"8-43", //ofice
					"Cyber Security" //research
					);
			
			Customer studentProfessorHolder = new StudentProfessor(
					"013648922", //bronco id
					"Morris Wagner", //name
					Date.valueOf("1993-07-20"), //dob
					"011-962-7516", //phone
					new Address("9278", "Newland Rd.", "Waterford", "CA", "51694"), //address
					Date.valueOf("2013-08-20"), //enter date
					Date.valueOf("2071-07-05"), //grad date
					"Biology", //major
					"Biochemistry", //minor
					"Biology", //department
					"4-32", //ofice
					"Genetics" //research
					);
			
			session.save(studentHolder);
			session.save(professorHolder);
			session.save(studentProfessorHolder);
			
			session.getTransaction().commit();
			
		}catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            
        }finally {
			session.close();
			factory.close();
		}
		
	}

}
