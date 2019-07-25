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
public class MobileDAO {

    @PersistenceContext
    private EntityManager em;
    


	public List<Mobile> getAllPhones() {
		// TODO Auto-generated method stub
		Query query= em.createQuery("SELECT w FROM Mobile w");
		return query.getResultList();
	}

	public Mobile getMobile(int id) {
		// TODO Auto-generated method stub
		return em.find(Mobile.class, id);
	}

	public void addMobile(Mobile mobiles) {
		// TODO Auto-generated method stub
		em.persist(mobiles);
	}

	public void update(Mobile mobiles) {
		// TODO Auto-generated method stub
		em.merge(mobiles);
	}
	
    public void delete(int id) {
    	em.remove(getMobile(id));
    }

	
    public List<Mobile> getMobilebyName(String name) {
        
 
    	        Query q = em.createQuery("SELECT m FROM Mobile AS m "+
    	        "WHERE m.name LIKE ?1");
    	        q.setParameter(1, "%"+name.toUpperCase()+"%");
    	        System.out.println("the query string is"+q);
    	        return  q.getResultList();
    }
   public long getMobilesTotal() {
        
    	System.out.println("starting*****************************");
        Query q = em.createQuery("SELECT COUNT(m) FROM Mobile as m", Long.class);
        long mCount = (long) q.getSingleResult();
        System.out.println("the count is ++++++++ "+mCount);
        return  mCount;
}
    
}
