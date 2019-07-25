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

@Path("/books")
@Stateless
@LocalBean
public class BookWS {

    @EJB
    private BookDAO bookDAO;
    
    @GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response findAll() {
		System.out.println("Get all books");
		List<Book> books = bookDAO.getAllBooks();
		return Response.status(200).entity(books).build();
	}
	
    @GET @Path("search/{query}")
   	@Produces({ MediaType.APPLICATION_JSON})
   	public Response findByName(@PathParam("query") String query) {
   		System.out.println("findByTitle: " + query);
   		List<Book> books = bookDAO.getBookbyTitle(query);
        return Response.status(200).entity(books).build();
   		
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
   
    public Response findBookById(@PathParam("id") String id) {
    	Book book = bookDAO.getBook(Integer.parseInt(id));
        return Response.status(200).entity(book).build();
    }
    
    
   /* @GET
    @Path("search/{name}")
    @Produces({MediaType.APPLICATION_JSON})
        public Response findBookByName(@PathParam("name") String name) {
    	System.out.println("find by name:"+name);
    	Book book = bookDAO.getBookbyTitle(name);
    	return Response.status(200).entity(book).build();
    }*/
    
    
   
   
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Response addBook(Book books) {
    	bookDAO.addBook(books);
    	return Response.status(201).entity(books).build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateBook(Book books) {
    	bookDAO.update(books);
    	return Response.status(200).entity(books).build();
    	
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
    	bookDAO.delete(id);
    	return Response.status(204).build();
    }
    
    @GET 
	 @Path("/count/")
	 @Produces({MediaType.APPLICATION_JSON})
		   	public Response findTotalUsers() {
		 System.out.println("Get count-OF books----------------------");
	   		//List<Users> users = userDao.getUserbyName(query);
	   		long count = bookDAO.getBooksTotal();
	        return Response.status(200).entity(count).build();
	   
	   	}
}
