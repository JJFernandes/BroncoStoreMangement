package model.logic;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entities.Address;
import model.entities.Customer;
import model.entities.HistoricalPrice;
import model.entities.Product;
import model.entities.Student;

public class ProductLogic {

	public static boolean  createNewProduct( 
			String name,
			BigDecimal price) {
		
		Session s = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		    Root<Product> root = criteria.from(Product.class);
		    criteria.select(root).where(root.get("name").in(name));
		    
		    try {
		    	s.createQuery(criteria).getSingleResult();
		    	
		    	return false;
		    	
		    } catch(javax.persistence.NoResultException e) {
		    	Product productHolder = new Product(name);
				HistoricalPrice hpHolder = new HistoricalPrice(Date.valueOf(LocalDate.now()), price);
				productHolder.setPrice(hpHolder);
				hpHolder.setProduct(productHolder);
				
				s.save(productHolder);
				s.save(hpHolder);
				
				s.getTransaction().commit();
				
				return true;
		    }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return true;
	}
	
	public static List<Product> getProductList() {
		
		Session s = null;
		
		List<Product> data = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			CriteriaBuilder builder = s.getCriteriaBuilder();
		    CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		    criteria.from(Product.class);
		    data = s.createQuery(criteria).getResultList();		    
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		return data;
		
	}
	
	public static void setProductPrice(Product prod, BigDecimal price) {
		Session s = null;
		
		try {
			s = ConnectionFactory.getSession();
			
			s.beginTransaction();
			
			HistoricalPrice hp = new HistoricalPrice(Date.valueOf(LocalDate.now()), price);
			prod.setPrice(hp);
			hp.setProduct(prod);
			
			s.update(prod);
			s.save(hp);
			
			s.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			s.close();
		}

		
	}
}
