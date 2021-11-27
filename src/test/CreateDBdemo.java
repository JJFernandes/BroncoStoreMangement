package test;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

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
			
			//create Student customer
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
			
			//persist Student Customer to database
			session.save(studentHolder);
			
			
			//create Professor customer
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
			
			//persist Professor Customer to database
			session.save(professorHolder);
			
			
			
//			//create StudentProfessor customer
//			Customer studentProfessorHolder = new StudentProfessor(
//					"013648922", //bronco id
//					"Morris Wagner", //name
//					Date.valueOf("1993-07-20"), //dob
//					"011-962-7516", //phone
//					new Address("9278", "Newland Rd.", "Waterford", "CA", "51694"), //address
//					Date.valueOf("2013-08-20"), //enter date
//					Date.valueOf("2071-07-05"), //grad date
//					"Biology", //major
//					"Biochemistry", //minor
//					"Biology", //department
//					"4-32", //ofice
//					"Genetics" //research
//					);
//			
//			//persist StudentProfessor to database
//			session.save(studentProfessorHolder);
			
			
			
			//create Pencil product
			Product productHolder = new Product("Pencil");
			//create HistoricalPrice
			HistoricalPrice hpHolder = new HistoricalPrice(Date.valueOf(LocalDate.now()), BigDecimal.valueOf(0.25));
			//set Product price to HistoricalPrice and add HistoricalPrice to Product history
			productHolder.setPrice(hpHolder);
			//set HistoricalPrice product link to Product
			hpHolder.setProduct(productHolder);
			
			//persist Product and Historical Price to database
			session.save(productHolder);
			session.save(hpHolder);
			
			
			
			Product product2Holder = new Product("Paper");
			HistoricalPrice hp2Holder = new HistoricalPrice(Date.valueOf(LocalDate.now()), BigDecimal.valueOf(0.10));
			product2Holder.setPrice(hp2Holder);
			hp2Holder.setProduct(product2Holder);
			HistoricalPrice hp3Holder = new HistoricalPrice(Date.valueOf(LocalDate.now()), BigDecimal.valueOf(0.15));
			product2Holder.setPrice(hp3Holder);
			hp3Holder.setProduct(product2Holder);
			
			session.save(productHolder);
			session.save(hpHolder);
			session.save(product2Holder);
			session.save(hp2Holder);
			session.save(hp3Holder);
			
			
			
			//create Order
			Order orderHolder = new Order();
			//set Order customer to Student Customer
			orderHolder.setCustomer(studentHolder);
			//add Product and Quantity to product_quantity HashMap
			orderHolder.addProductAndQuantity(productHolder, 2);
			orderHolder.addProductAndQuantity(product2Holder, 5);	
			studentHolder.addNewOrder(orderHolder);
			
			//persist Order to database
			session.save(orderHolder);
			
			
			//commit changes to database
			session.getTransaction().commit();
			
		}catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }finally {
			session.close();
			factory.close();
		}
		
	}

}
