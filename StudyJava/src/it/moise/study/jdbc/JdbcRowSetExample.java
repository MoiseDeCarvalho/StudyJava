/**
 * Effettua una connessione ed utilizza un JdbcRowSet
 */
package it.moise.study.jdbc;

import java.sql.SQLException;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.JdbcRowSet;
	
public class JdbcRowSetExample {
	
	
	public JdbcRowSetExample() {
		getRowSet();
	}
	
	private void getRowSet(){
		try{
			RowSetFactory rf = RowSetProvider.newFactory();
			JdbcRowSet jdbcrs = rf.createJdbcRowSet();
			// imposto i parametri di connessione
			jdbcrs.setUrl("jdbc:mysql://localhost/articoli");
			jdbcrs.setUsername("root");
			jdbcrs.setPassword("");
			
			// query di esecuzione
			jdbcrs.setCommand("SELECT * FROM genere");
			jdbcrs.execute();
			
			while(jdbcrs.next()) {
				System.out.println(jdbcrs.getString(2));
			}
		}
		catch(SQLException ex){System.out.println(ex.getErrorCode());}
	}
	// pattern singleton
	public static JdbcRowSetExample getInstance(){
		return new JdbcRowSetExample();
	}
	public static void main(String args[]){
		JdbcRowSetExample.getInstance();
	}

}
