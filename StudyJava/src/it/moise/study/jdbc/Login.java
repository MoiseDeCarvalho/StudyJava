package it.moise.study.jdbc;

import javax.swing.SwingUtilities;

public class Login {

	public Login() {
	}
	private static void createAndShowFrame() throws Exception{
		new LoginForm();	
	}
	
	public static void Main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try{
					createAndShowFrame();
				}
				catch(Exception ex){ ex.printStackTrace();}
			}
		});
	}

}
