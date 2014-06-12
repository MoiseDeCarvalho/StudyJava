package it.moise.study.jdbc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	JPanel bodyPanel;
	JPanel topPanel;
	JPanel bottomPanel;
	JButton cmdOk;
	JButton reset;
	JLabel rdbms_lbl;
	JLabel url_lbl;
	JLabel port_lbl;
	JLabel dbname_lbl;
	JLabel uname_lbl;
	JLabel pwd_lbl;
	GroupLayout groupLayout;
	
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
		bodyPanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		
		
		cb_rdbms = new JComboBox(bdtype);
		cb_rdbms.setSelectedItem(0);
		txt_url = new JTextField();
		txt_dbname = new JTextField();
		txt_port = new JTextField();
		txt_uname = new JTextField();
		txt_pwd = new JPasswordField();
		ta_result = new JTextArea("Risultato:");
		cb_rdbms.setName("RDBMS");
		txt_url.setName("URL");
		txt_port.setName("PORTA");
		txt_dbname.setName("DATABASE");
		txt_uname.setName("Nome Utente");
		txt_pwd.setName("Password");
		ta_result.setName("ta_result");
		
		rdbms_lbl = new JLabel(cb_rdbms.getName()+":");
		url_lbl = new JLabel(txt_url.getName()+":");
		port_lbl = new JLabel(txt_port.getName()+":");
		dbname_lbl = new JLabel(txt_dbname.getName()+":");
		uname_lbl = new JLabel(txt_uname.getName()+":");
		pwd_lbl = new JLabel(txt_pwd.getName()+":");
		Dimension dimesion = new Dimension(100,30);
		cb_rdbms.setSize(dimesion);
		txt_url.setSize(dimesion);
		
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
		
		groupLayout = new GroupLayout(topPanel);
		topPanel.setLayout(groupLayout);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
		
		// define align criteria
		GroupLayout.Alignment h_align = GroupLayout.Alignment.TRAILING;
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;
		
		// create sequential horizontal group
		GroupLayout.SequentialGroup for_horizontal = groupLayout.createSequentialGroup();
		
		// add the groups
		for_horizontal.addGroup(groupLayout.createParallelGroup(h_align).
			addComponent(rdbms_lbl).
			addComponent(url_lbl).
			addComponent(port_lbl).
			addComponent(dbname_lbl).
			addComponent(uname_lbl).
			addComponent(pwd_lbl).
			addComponent(reset));
		
		for_horizontal.addGroup(groupLayout.createParallelGroup(h_align).
				addComponent(cb_rdbms).
				addComponent(txt_url).
				addComponent(txt_port).
				addComponent(txt_dbname).
				addComponent(txt_uname).
				addComponent(txt_pwd).
				addComponent(cmdOk));
		groupLayout.setHorizontalGroup(for_horizontal);
		
		// create vertical sequential group
		GroupLayout.SequentialGroup for_vertical = groupLayout.createSequentialGroup();
		
		// add property
		for_vertical.addGroup(groupLayout.createParallelGroup(v_align).
				addComponent(rdbms_lbl).
				addComponent(cb_rdbms));
		for_vertical.addGroup(groupLayout.createParallelGroup(v_align).
				addComponent(url_lbl).
				addComponent(txt_url));
		for_vertical.addGroup(groupLayout.createParallelGroup(v_align).
				addComponent(port_lbl).
				addComponent(txt_port));
		for_vertical.addGroup(groupLayout.createParallelGroup(v_align).
				addComponent(dbname_lbl).
				addComponent(txt_dbname));
		for_vertical.addGroup(groupLayout.createParallelGroup(v_align).
				addComponent(uname_lbl).
				addComponent(txt_uname));
		for_vertical.addGroup(groupLayout.createParallelGroup(v_align).
				addComponent(pwd_lbl).
				addComponent(txt_pwd));
		for_vertical.addGroup(groupLayout.createParallelGroup(v_align).
				addComponent(reset).
				addComponent(cmdOk));
		groupLayout.setVerticalGroup(for_vertical);
		bodyPanel.add(topPanel, BorderLayout.PAGE_START);
		
		/* topPanel.add(rdbms_lbl, BorderLayout.WEST);
		topPanel.add(cb_rdbms, BorderLayout.EAST);
		topPanel.setToolTipText("Parameter");
		
		
		topPanel.add(url_lbl);
		topPanel.add(txt_url);
		topPanel.add(port_lbl);
		topPanel.add(txt_port);
		topPanel.add(dbname_lbl);
		topPanel.add(txt_dbname);
		topPanel.add(uname_lbl);
		topPanel.add(txt_uname);
		topPanel.add(pwd_lbl);
		topPanel.add(txt_pwd);
		topPanel.add(reset);
		topPanel.add(cmdOk);
		*/
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(ta_result, BorderLayout.CENTER);
		bottomPanel.setSize(400,400);
	//	bodyPanel.setLayout(new BorderLayout());
		//bodyPanel.add(topPanel, BorderLayout.NORTH);
		bodyPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		bodyPanel.setSize(200, 400);
		this.add(bodyPanel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,400);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		//this.pack();
		
	}
}
