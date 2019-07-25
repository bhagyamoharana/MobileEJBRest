package com.example.rest;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



@Stateless
@LocalBean
public class BookDAO {

    @PersistenceContext
    private EntityManager em;
    


	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		Query query= em.createQuery("SELECT b FROM book b");
		return query.getResultList();
	}

	public Book getBook(int id) {
		// TODO Auto-generated method stub
		return em.find(Book.class, id);
	}

	public void addBook(Book Books) {
		// TODO Auto-generated method stub
		em.persist(Books);
	}

	public void update(Book Books) {
		// TODO Auto-generated method stub
		em.merge(Books);
	}
	
    public void delete(int id) {
    	em.remove(getBook(id));
    }

	
    public List<Book> getBookbyTitle(String title) {
        
 
    	        Query q = em.createQuery("SELECT m FROM book AS m "+
    	        "WHERE m.title LIKE ?1");
    	        q.setParameter(1, "%"+title.toUpperCase()+"%");
    	        System.out.println("the query string is"+q);
    	        return  q.getResultList();
    }
   public long getBooksTotal() {
        
    	System.out.println("starting*****************************");
        Query q = em.createQuery("SELECT COUNT(m) FROM book as m", Long.class);
        long mCount = (long) q.getSingleResult();
        System.out.println("the count is ++++++++ "+mCount);
        return  mCount;
}
    
}
