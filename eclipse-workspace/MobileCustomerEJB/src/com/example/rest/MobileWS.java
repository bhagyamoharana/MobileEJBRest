package com.example.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

@Path("/mobiles")
@Stateless
@LocalBean
public class MobileWS {

    @EJB
    private MobileDAO mobileDao;
    
    @GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response findAll() {
		System.out.println("Get all mobiles");
		List<Mobile> mobiles = mobileDao.getAllPhones();
		return Response.status(200).entity(mobiles).build();
	}
	
    @GET @Path("search/{query}")
   	@Produces({ MediaType.APPLICATION_JSON})
   	public Response findByName(@PathParam("query") String query) {
   		System.out.println("findByName: " + query);
   		List<Mobile> mobiles = mobileDao.getMobilebyName(query);
        return Response.status(200).entity(mobiles).build();
   		
   	}
    
	/*@GET
	@Path("/search/{query}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findByName(@PathParam("query") String query) {
		System.out.println("findByName: " + query);
		List<Wine> wines=wineDao.getWinesByName(query);
		return Response.status(200).entity(wines).build();
	}*/

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
   
    public Response findMobileById(@PathParam("id") String id) {
    	Mobile mobile = mobileDao.getMobile(Integer.parseInt(id));
        return Response.status(200).entity(mobile).build();
    }
    
    
   /* @GET
    @Path("search/{name}")
    @Produces({MediaType.APPLICATION_JSON})
        public Response findMobileByName(@PathParam("name") String name) {
    	System.out.println("find by name:"+name);
    	Mobile mobile = mobileDao.getMobilebyName(name);
    	return Response.status(200).entity(mobile).build();
    }*/
    
    
   
   
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response addMobile(Mobile mobiles) {
    	mobileDao.addMobile(mobiles);
    	return Response.status(201).entity(mobiles).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateMobile(Mobile mobiles) {
    	mobileDao.update(mobiles);
    	return Response.status(200).entity(mobiles).build();
    	
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteMobile(@PathParam("id") int id) {
    	mobileDao.delete(id);
    	return Response.status(204).build();
    }
    
    @GET 
	 @Path("/count/")
	 @Produces({MediaType.APPLICATION_JSON})
		   	public Response findTotalUsers() {
		 System.out.println("Get count-OF MOBILES----------------------");
	   		//List<Users> users = userDao.getUserbyName(query);
	   		long count = mobileDao.getMobilesTotal();
	        return Response.status(200).entity(count).build();
	   
	   	}
}
