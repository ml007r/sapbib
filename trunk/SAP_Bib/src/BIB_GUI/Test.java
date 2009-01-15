package BIB_GUI;

import javax.swing.SpringLayout;

public class Test {

	public static BIB_Haupt bib;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 =   new Thread( BIB_Steuer.getInstance() );
	      t1.start();
	      
	      try {
			Thread.sleep(2800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t2 =   new Thread( BIB_Haupt.getInstance() );
		
		BIB_Haupt.getInstance().setController(BIB_Steuer.getInstance());

	 
	}
	
	

}
