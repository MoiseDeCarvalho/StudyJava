/**
 * How to use a FilteredRowSet
 */
package it.moise.study.jdbc;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;

public class FilteredRowSetExample {

	public FilteredRowSetExample() {
		getFilteredRowSet();
	}
	
	private void getFilteredRowSet(){
		try{
			class MyFilter implements Predicate { // implements Predicate
				// var for matching
				private String col_name;
				private int col_nr;
				private int low;
				private int high;
				/**
				 * Costructor
				 * @param low
				 * @param high
				 * @param col_nr
				 * @param col_name
				 */
				public MyFilter(int low, int high, int col_nr, String col_name){
					this.low = low;
					this.high = high;
					this.col_nr = col_nr;
					this.col_name = col_name;
				}
				
				/**
				 * Implements method
				 */
				public boolean evaluate(RowSet rs){
					try{
						String value = rs.getString(col_name);
						return value != null && (value.charAt(0) == 'L' || value.charAt(0) == 'l');
					}
					catch(SQLException ex) { return false; }
				}
				
				public boolean evaluate(Object value, int column) throws SQLException{
					return false;
				}
				
				public boolean evaluate(Object value, String columnName) throws SQLException{
					return false;
				}
			}
			
			RowSetFactory rf = RowSetProvider.newFactory();
			FilteredRowSet frs = rf.createFilteredRowSet();
			
			
			// set connection parameter
			frs.setUrl("jdbc:mysql://localhost/articoli");
			frs.setUsername("root");
			frs.setPassword("");
			
			// set execution query
			frs.setCommand("SELECT * FROM genere");
			frs.execute();
			
			frs.setFilter(new MyFilter(-1, -1, -1, "descrizione")); // assign filter
			
			while(frs.next()){  // show result
				String n = frs.getString("descrizione");
				System.out.println(n);
			}
		}
		catch(SQLException ex) { System.out.println (ex.getMessage()); }
	}
	// singleton pattern
	public static FilteredRowSetExample getInstance(){
		return new FilteredRowSetExample();
	}
	
	public static void main (String args[]){
		getInstance();
	}

}
