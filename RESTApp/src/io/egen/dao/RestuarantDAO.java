package io.egen.dao;

import io.egen.exception.AppException;
import io.egen.model.Reservation;
import io.egen.model.Table;
import io.egen.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RestuarantDAO 
{
    public List<Table> findaTable() throws AppException
    {
    	List<Table> tb = new ArrayList<Table>();
    	Connection con = DBUtil.connect();
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	
    	try{
    		ps = con.prepareStatement("SELECT * FROM tables");
    		
    		rs = ps.executeQuery();
    		while(rs.next())
    		{
    			Table t = new Table();
    			t.setTablenum(rs.getString(1));
    			tb.add(t);
    		}
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println("SQLException is caught");
    	}
    	
    	return tb;
    }
    
    
    public List<Table> findTables(String dt,String tm)
    {
    	
    	Connection con = DBUtil.connect();
    	List<Table> tabls = new ArrayList<Table>();
    	ResultSet rs = null;
    	
    	try{
    		
    	PreparedStatement ps = null;	 
    	ps = con.prepareStatement("SELECT tablenum FROM tables WHERE tablenum NOT IN (SELECT tablenum FROM reservation WHERE date='"+dt+"'"+"and starttime='"+tm+"')");
    	rs = ps.executeQuery();
    	while(rs.next())
    	{
    		Table t = new Table();
    		t.setTablenum(rs.getString("tablenum"));
    		tabls.add(t);
    	}
    	}
    	
    	catch(SQLException ex)
    	{
    		System.out.println("SQLException has been caught");
    	}
    	
    	return tabls;
    }
    
    
    public void submit(Reservation rs)
    {
    	Connection con = null;
    	Connection con1 = null;
    	
    	PreparedStatement ps = null;
    	PreparedStatement ps1 = null;
    	con = DBUtil.connect();
    	con1 = DBUtil.connect();
    	try{
    	ps = con.prepareStatement("INSERT INTO reservation (emailid,tablenum,members,date) VALUES(?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
    	ps.setString(1, rs.getEmailid());
    	ps.setString(2, rs.getTablenum());
    	ps.setInt(3, rs.getMembers());
    	ps.setString(4, rs.getDate());
    	ps.setString(5, rs.getTime());
    	int r  = ps.executeUpdate();
    	
    	   	
    	
    	if(r==1)
    	{
    		System.out.println("The execution is successfull");
    	}
    	
    	
    	
    	else
    	{
    		System.out.println("the insert operation seem to have failed , please send the message to the Devloper");
    	}
    	
    	}
    	
    	catch(SQLException e)
    	{
    		System.out.println("SQL Exception has been caught");
    		e.printStackTrace();
    		
    	}
    	
    	
    	
    }
}
