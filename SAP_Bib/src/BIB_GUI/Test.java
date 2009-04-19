package BIB_GUI;

import javax.swing.SpringLayout;

public class Test {

	public static BIB_Haupt bib;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double e1 = 5.0 / 2 + 7 / 2 + 9 % 6;
		double e2 = 7 / 2 ;
		System.out.println(e1);
		System.out.println(e2);
		System.out.println();
		Thread t1 =   new Thread( BIB_Steuer.getInstance() );
	      t1.start();
	      
	      try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t2 =   new Thread( BIB_Haupt.getInstance() );
		
		BIB_Haupt.getInstance().setController(BIB_Steuer.getInstance());

	 
	}
	
	

}
