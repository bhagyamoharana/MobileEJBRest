package com.example.rawdata;
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

@Path("/rawdata")
@Stateless
@LocalBean
public class RawdataWS {
	
	 @EJB
	 private RawdataDAO rawdataDao;
	    
	    @GET
		@Produces({MediaType.APPLICATION_JSON})
		public Response findAll() {
			System.out.println("Get all rawdatas");
			List<Rawdata> rawdata = rawdataDao.getAllDatas();
			return Response.status(200).entity(rawdata).build();
		}
	
	

}
