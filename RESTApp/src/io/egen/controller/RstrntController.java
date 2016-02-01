package io.egen.controller;

import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;

import io.egen.model.*;
import io.egen.dao.*;
import java.util.ArrayList;


// comment section for the Controller 

@Path("/tables")
public class RstrntController 
{
	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> findTable(@QueryParam("dt") String d,
			@QueryParam("time") String tm)
	{
		List<Table> tbls = new ArrayList<Table>();
		RestuarantDAO rt = new RestuarantDAO();
		tbls = rt.findTables(d,tm);
		return tbls;
	}
	
	@GET
	@Path("/reservations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> findAllReservation(@QueryParam("dt") String d,
			@QueryParam("time") String tm)
	{
		List<Reservation> reservtns = new ArrayList<Reservation>();
		
		return reservtns;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void reservationSubmit(Reservation rs)
	{
		if(rs.getEmailid()==null ||rs.getMembers()==0||rs.getTablenum()==null||rs.getDate()==null)
		{
			System.out.println("One of the Parameter is wrong");
		}
		
		try{
		RestuarantDAO r = new RestuarantDAO();
		r.submit(rs);
		}
		
		catch(Exception e)
		{
			System.out.println("");
			e.printStackTrace();
		}	
	}
	
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id)
	{
		try{
			RestuarantDAO r = new RestuarantDAO();
			r.delete(id);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Response.ok().build();
	}
	
	
	
	
	

}
