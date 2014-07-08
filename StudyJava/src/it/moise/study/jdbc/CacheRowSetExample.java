/**
 * Example how to use a CacheRowSet
 */
package it.moise.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;


public class CacheRowSetExample {
	
	
	public CacheRowSetExample() {
		getCacheRowSetExample();
	}
	private void getCacheRowSetExample(){
		try{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/articoli", "root", "");
			
			conn.setAutoCommit(false);
			
			RowSetFactory rf = RowSetProvider.newFactory();
			CachedRowSet crs = rf.createCachedRowSet();
			
			// set and execute the query
			crs.setCommand("SELECT * FROM genere");
			crs.execute(conn);
			
			// go firt row and update a column
			crs.first();
			crs.updateString(2, "C++ Modificato");
			crs.updateRow();
			
			// send update to the database source
			crs.acceptChanges();
			
			conn.setAutoCommit(true);
			
			conn.close();
		}
		catch(SQLException ex){ System.out.println(ex.getErrorCode()); }
	}
	public static CacheRowSetExample getInstance(){
		return new CacheRowSetExample();
	}
	public static void main(String args[]){
		getInstance();
	}
}
