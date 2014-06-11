package it.moise.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JTextArea;

/**
 * Classe che implementa una serie di metodi statici utili da utilizzare per la gestione del database
 * @author De Carvalho Moise
 *
 */
public class DBHelper {
	public static String dbtype; // typo of RDBMS
	public static JTextArea output; // output error
	public static Connection conn; // Connectoin object
	public static String drivers_name[] = {
			"com.mysql.jdbc.Driver", // MySQL 5.5
			"oracle.jdbc.OracleDriver", // Oracle 11g
			"com.ibm.db2.jcc.DB2Driver", // DB2 9 universal
			"com.microsoft.sqlserver.jdbc.SQLServerDriver" // SQL Server 2008
	};
	
	private static String url_format[] = {"jdbc:mysql://", "jdbc:oracle:thin@", "jdbc:db2://", "jdbc:sqlserver://"};
	
	public static void loadDriver(){
		String driver = "";
		switch (dbtype){
		case "MySQL":
			driver = drivers_name[0];
			break;
		case "Oracle":
			driver = drivers_name[1];
			break;
		case "DB2":
			driver = drivers_name[2];
			break;
		case "SQL Server":
			driver = drivers_name[3];
			break;
		default:
			break;
				
		}
		try{
			Class.forName(dbtype); 
		}
		catch(ClassNotFoundException ex) { output.setText(ex.getMessage()); }
	}
	
	public static void doConnection(String url, String port, String dbname, String u_name, String pwd){
		String complete_url = "";
		switch (dbtype){
			case "MySQL":
				complete_url = url_format[0] + url + ":" + port + "/" + dbname;
				break;
			case "Oracle":
				complete_url = url_format[1] + url + ":" + port + "/" + dbname;
				break;
			case "DB2":
				complete_url = url_format[2] + url + ":" + port + "/" + dbname;
				break;
			case "SQL Server":
				complete_url = url_format[3] + url + ":" + port + "/" + dbname;
				break;
			default:
				break;
		}
		
		try{
			conn = DriverManager.getConnection(complete_url, u_name, pwd);
			output.setText("Connessione effettuata con successo!!!");
		}
		catch(SQLException ex) {output.setText(ex.getMessage()); }
	}
	
	public static void closeConnection(){
		try{
			// chiudo esplicitamente la connessione
			if (conn != null){
				conn.close();
				output.setText("Disconnessione effettuata con successo!!!");
			}
		}
		catch(SQLException ex){ output.setText(ex.getMessage()); }
	}
}
	

