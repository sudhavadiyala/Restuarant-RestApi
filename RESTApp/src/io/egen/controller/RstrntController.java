package io.egen.controller;

import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import io.egen.model.*;
import io.egen.dao.*;
import java.util.ArrayList;


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
	
	
	
	
	
	

}
