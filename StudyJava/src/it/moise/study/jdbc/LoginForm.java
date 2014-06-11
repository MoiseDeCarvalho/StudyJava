package it.moise.study.jdbc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

/**
 * Crea una GUI mediante la quale un utente può interagire per provare la connessione verso una base di dati
 * @author De Carvalho Moise
 *
 */
public class LoginForm extends javax.swing.JFrame {
	JComboBox cb_rdbms;
	JTextField txt_url;
	JTextField txt_dbname;
	JTextField txt_port;
	JTextField txt_uname;
	JPasswordField txt_pwd;
	JTextArea ta_result;
	
	JButton cmdOk;
	JButton reset;
	
	private void btn_accessActionPerformed(java.awt.event.ActionEvent evt){
		// otteniamo tutti i valori dalla form
		String rdbms = (String) cb_rdbms.getSelectedItem();
		String url = txt_url.getText();
		String dbname= txt_dbname.getText();
		String port = txt_port.getText();
		String uname = txt_uname.getText();
		String pwd = String.copyValueOf(txt_pwd.getPassword());
		
		// use DBHelper
		DBHelper.dbtype = rdbms;
		DBHelper.output = ta_result;
		DBHelper.loadDriver();
		DBHelper.doConnection(url, port, dbname, uname, pwd);
	}
	
	private void btn_undoActionPerformed(java.awt.event.ActionEvent evt){
		DBHelper.closeConnection();
	}
	public LoginForm(){
		String[] bdtype = {"MySQL", "Oracle", "DB2", "SQLServer"};
		cb_rdbms = new JComboBox(bdtype);
		cb_rdbms.setSelectedItem(0);
		txt_url = new JTextField();
		txt_dbname = new JTextField();
		txt_port = new JTextField();
		txt_uname = new JTextField();
		txt_pwd = new JPasswordField();
		ta_result = new JTextArea();
		cb_rdbms.setName("RDBMS");
		txt_url.setName("URL");
		txt_port.setName("PORTA");
		txt_dbname.setName("DATABASE");
		txt_uname.setName("Nome Utente");
		txt_pwd.setName("Password");
		ta_result.setName("ta_result");
		ta_result.setToolTipText("Risultato");
		
		cmdOk = new JButton("Connessione...");
		reset = new JButton("Disconnessione...");
		
		cmdOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				btn_accessActionPerformed(e);
			}			
		});
		
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				btn_undoActionPerformed(e);
			}
		});
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(400,600));
		this.setVisible(true);
		this.pack();
		
	}
}
