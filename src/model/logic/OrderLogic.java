package model.logic;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import model.entities.Customer;
import model.entities.Order;

public class OrderLogic {


	public static List<Order> getOrderList() {
		
		Session s = null;
		
		List<Order> data = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		    criteria.from(Order.class);
		    data = s.createQuery(criteria).getResultList();		    
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return data;
		
	}
	
	public static List<Order> getOrderRangeList(Date begin, Date end) {
		
		Session s = null;
		
		List<Order> data = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
		    
		    CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
		    Root<Order> root = criteria.from(Order.class);
		    criteria.select(root).where(builder.between(root.get("date"), begin, end));
		    data = s.createQuery(criteria).getResultList();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return data;
		
	}
	
	public static boolean createNewOrder(Order o) {
		
		Session s = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			s.save(o);
			
			s.getTransaction().commit();
			
			return true;
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return false;
	}
	
}
