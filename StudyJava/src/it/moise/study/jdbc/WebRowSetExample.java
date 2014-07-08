/**
 * Example how to use WebRowSet
 */
package it.moise.study.jdbc;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.RowSetProvider;

public class WebRowSetExample {

	public WebRowSetExample() {
		getWebRowSet();
	}
	
	
	private void getWebRowSet(){
		try{
			RowSetFactory rf = RowSetProvider.newFactory();
			WebRowSet wrs = rf.createWebRowSet();
			
			// set connection parameter
			wrs.setUrl("jdbc:mysql://localhost/articoli");
			wrs.setUsername("root");
			wrs.setPassword("");
			
			// set sql parameter
			wrs.setCommand("SELECT * FROM genere");
			wrs.execute();
			
			try (FileWriter writer = new FileWriter("out.xml")) // write the rowset
			{
				wrs.writeXml(writer);
			}
			
			wrs.close();
		}
		catch(IOException ex){ System.out.println(ex.getMessage()); }
		catch(SQLException e){ System.out.println(e.getMessage()); }
	}
	// singleton pattern
	public static WebRowSetExample getInstance(){
		return new WebRowSetExample();
	}
	
	public static void main (String args[]){
		getInstance();
	}

}
