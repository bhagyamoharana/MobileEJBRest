package com.example.rawdata;

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
public class RawdataDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	public List<Rawdata> getAllDatas() {
		// TODO Auto-generated method stub
		Query query= em.createQuery("SELECT r FROM Rawdata r");
		return query.getResultList();
	}

}
