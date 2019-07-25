package com.example.user;

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

import com.example.rest.Mobile;





@Path("/users")
@Stateless
@LocalBean
public class UsersWS {
	
	@EJB
	private UsersDAO userDao;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response findAll() {
		System.out.println("Get all users");
		List<Users> users = userDao.getAllUsers();
		return Response.status(200).entity(users).build();
	}
	
	

	 @GET @Path("search/{query}")
	   	@Produces({ MediaType.APPLICATION_JSON})
	   	public Response findByNameUsers(@PathParam("query") String query) {
	   		System.out.println("findByNameUsers: " + query);
	   		List<Users> users = userDao.getUserbyName(query);
	        return Response.status(200).entity(users).build();
	   		
	   	}
	
	 @GET @Path("validate/{query1}/{query2}")
	   	@Produces({ MediaType.APPLICATION_JSON})
	   	public Response getUserNameandPassword(@PathParam("query1") String query1, @PathParam("query2") String query2) {
	   		System.out.println("getUserNameandPassword: " + query1 + query2);
	   		//List<Users> users = userDao.getUserNameandPassword(query1,query2);
	   		String response1 = userDao.getUserNameandPassword(query1,query2);
	   		System.out.println("bm:" + response1);
	   		if(response1 == null) {
	   		return Response.status(200).entity(0).build();
	   	    }
	        return Response.status(200).entity(1).build();
	   		
	   	}
	 
	 @GET 
	 @Path("/countU/")
	 @Produces({MediaType.APPLICATION_JSON})
		   	public Response findTotalUsers() {
		 System.out.println("Get count-----------------------");
	   		//List<Users> users = userDao.getUserbyName(query);
	   		long countU = userDao.getUsersTotal();
	        return Response.status(200).entity(countU).build();
	   
	   	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/{id}")
	public Response findUserById(@PathParam("id") int id) {
		Users users = userDao.getUser(id);
		return Response.status(200).entity(users).build();
	}
	
	
	
	
	    @POST
	    @Produces({MediaType.APPLICATION_JSON})
	    public Response addUsers(Users user) {
	    	userDao.addUser(user);
	    	return Response.status(201).entity(user).build();
	    }
	    
	    @PUT
	    @Path("/{id}")
	    @Consumes("application/json")
	    @Produces({MediaType.APPLICATION_JSON})
	    public Response updateUsers(Users user) {
	    	userDao.updateUser(user);
	    	return Response.status(200).entity(user).build();
	    	
	    }
	    
	    @DELETE
	    @Path("/{id}")
	    public Response deleteUser(@PathParam("id") int id) {
	    	userDao.deleteUser(id);
	    	return Response.status(204).build();
	    }
	

}
