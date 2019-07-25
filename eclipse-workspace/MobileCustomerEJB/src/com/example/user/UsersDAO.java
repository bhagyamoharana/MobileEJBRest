package com.example.user;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;




@Stateless
@LocalBean
public class UsersDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Users> getAllUsers(){
		//System.out.println("777777777777777777777777Shoma7777777777777777777777777");
		Query query= em.createQuery("SELECT u FROM Users u");
		//getUsersTotal();
		//getUserNameandPassword();
		
		
		return query.getResultList();
		
	}
	


	public Users getUser(int uid) {
		// TODO Auto-generated method stub
		return em.find(Users.class, uid);
	}
	
	

	public void addUser(Users user) {
		// TODO Auto-generated method stub
		em.persist(user);
	}

	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		em.merge(user);
	}
	
    public void deleteUser(int uid) {
    	em.remove(getUser(uid));
    }
    
    public List<Users> getUserbyName(String nameu) {
        
    	System.out.println("bhagya code.....");
        Query q = em.createQuery("SELECT u FROM Users AS u "+
        "WHERE u.nameu LIKE ?1");
        q.setParameter(1, "%"+nameu.toUpperCase()+"%");
        System.out.println("the query string is--------"+q);
   //     System.out.println(""+q.);
        return  q.getResultList();
}
   
    public long getUsersTotal() {
        
    	System.out.println("starting*****************************");
        Query qU = em.createQuery("SELECT COUNT(u) FROM Users as u", Long.class);
        long uCount = (long) qU.getSingleResult();
        System.out.println("the count is ++++++++ "+uCount);
        return  uCount;
}
    
  public String getUserNameandPassword(String nameu, String pwd) {
  System.out.println("starting******* validating user name and pwd");
  String dbpassword = null;
 // Query q = em.createQuery("SELECT u FROM Users AS u WHERE u.username LIKE ?1 and u.password ?1");
  Query q = em.createQuery("SELECT u FROM Users AS u WHERE u.username LIKE ?1");
             q.setParameter(1, "%"+nameu+"%");
           
             List<Users> listOfSimpleEntities = q.getResultList();
     		for (Users entity : listOfSimpleEntities) {
     			dbpassword = entity.getPassword(); 
     		}
     			if (dbpassword.equals(pwd)) {
     		System.out.println("sucessssssssssssssssss");
     				return "success"; 
     			}
     			else {
     				System.out.println("failll");
     				return null;
     			}
     			          
     		}
 
    
}
