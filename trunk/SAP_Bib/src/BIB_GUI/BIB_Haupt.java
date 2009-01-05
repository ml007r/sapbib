package BIB_GUI;

import javax.swing.*;
import javax.swing.event.*;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import BIB_Modell.Ausleihe;
import BIB_Modell.Buch;
import BIB_Modell.Leser;


public class BIB_Haupt extends JFrame
implements ActionListener, MouseListener, ListSelectionListener, KeyListener
{
	// Controller
	private BIB_Steuer controller = new BIB_Steuer();
	/**
	 * 
	 */
	private static final long serialVersionUID = -6612455908213542855L;

	/**
     * Alles für das Kundenpanel - und unter Panel...
     */
	
	
	// Leser-Panel
	JTabbedPane leserListePane = new JTabbedPane();
    JPanel LeserPanel = null;
    JButton btnLeserListeDel = null;
    JButton btnLeserListeEdit = null;
    JList leserListe = new JList();
    
    // Anlegen&Aendern-Panel
    JTextField txtKdName = null;
    JTextField txtKdNachname = null;
    JTextField txtKdStrasse = null;
    JTextField txtKdPLZ = null;
    JTextField txtKdOrt = null;
    JButton btnKdEdit = null;

    /**
     * Alles für das Buchpanel - und unter Panel...
     */
    JList buchListe = null;
    JTextField txtBuchISBN = null;
    JTextField txtBuchTitel = null;
    JTextField txtBuchAutor = null;
    JTextField txtBuchBeschreibung = null;
    JTextField txtBuchVerlag = null;
    JButton btnBuchEdit = null;
    JButton btnBuchNew = null;
    JButton btnBuchDel = null;
    
    /**
     * Alles für das Verleihnpanel - und unter Panel...
     */
    JList verleihListe = null;
    JList ausleihenLeserListe = null;
    JList auleihenBuecherListe = null;
    JButton btnVerleihEdit = null;
    JButton btnVerleihNew = null;
    

	public BIB_Haupt(){
		super();
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 800, 400 );
        
        JTabbedPane tp = new JTabbedPane();
        
        tp.addTab("Leser", initLeser());   
        tp.addTab("Medium", initBuch());
        tp.addTab("Ausleihe", initVerleihe());      
        tp.addKeyListener(this);
        this.add(tp);
        txtKdName.setText("sdfdsfdsa");
        this.addKeyListener(this);
        
		System.out.println();
               
        JMenuBar menuBar = new JMenuBar();

        JMenu menuDatei = new JMenu( "Datei" );
        JMenuItem menuItemOeffnen = new JMenuItem( "Oeffnen" );
        menuItemOeffnen.addActionListener( this );
        menuDatei.add( menuItemOeffnen );
        JMenuItem menuItemSpeichern = new JMenuItem( "Speichern" );
        menuItemSpeichern.addActionListener( this );
        menuDatei.add( menuItemSpeichern );
        JMenuItem menuItemBeenden = new JMenuItem( "Beenden" );
        menuItemBeenden.addActionListener( this );
        menuDatei.add( menuItemBeenden );

        menuBar.add( menuDatei );

        this.setJMenuBar( menuBar );

        this.pack();
        this.setSize( 800, 400 );
        this.setVisible(true);
        System.out.println("");
        
	}

	 
	public Component initLeser(){
				
		Container hilfsComp = new Container();
		// Zeile 1
        JLabel lblList = new JLabel( "Leser:" );
        lblList.setBounds( 15, 15, 75, 24 );
        hilfsComp.add( lblList );

        //JList leserListe = new JList();
        leserListe.addMouseListener( this );
        leserListe.addKeyListener( this );
        leserListe.addListSelectionListener( this );
        JScrollPane ortListeScrollPane = new JScrollPane( leserListe );
        ortListeScrollPane.setBounds( 100, 15, 275, 150 );
        leserListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        hilfsComp.add( ortListeScrollPane );

        // Zeile 2
        btnLeserListeEdit = new JButton( "edit" );
        btnLeserListeEdit.setActionCommand( "editLeserListe" );
        btnLeserListeEdit.setBounds( 100, 200, 75, 22 );
        btnLeserListeEdit.addActionListener( this );
        hilfsComp.add( btnLeserListeEdit );

        btnKdEdit = new JButton( "new" );
        btnKdEdit.setActionCommand( "newKd" );
        btnKdEdit.setBounds( 200, 200, 75, 22 );
        btnKdEdit.addActionListener( this );
	    hilfsComp.add( btnKdEdit ); 
        
        JButton btnLeserListeDel = new JButton( "del" );
        btnLeserListeDel.setActionCommand( "delleserListe" );
        btnLeserListeDel.setBounds( 300, 200, 75, 22 );
        btnLeserListeDel.addActionListener( this );
        hilfsComp.add( btnLeserListeDel );
		
		JLabel lblKdName = new JLabel( "Vorname:" );
		lblKdName.setBounds( 400, 15, 90, 24 );
        hilfsComp.add( lblKdName );
        txtKdName = new JTextField("");
        txtKdName.setBounds(520, 15, 100, 25);
        hilfsComp.add( txtKdName );       
        
        JLabel lblKdNachname = new JLabel( "Nachname:" );
        lblKdNachname.setBounds( 400, 45, 90, 24 );
        hilfsComp.add( lblKdNachname );
        txtKdNachname = new JTextField();
        txtKdNachname.setBounds(520, 45, 100, 25);
        hilfsComp.add( txtKdNachname );       
        
        JLabel lblKdStrasse = new JLabel( "Strasse&Hn:" );
        lblKdStrasse.setBounds( 400, 75, 90, 24 );
        hilfsComp.add( lblKdStrasse );
        txtKdStrasse = new JTextField();
        txtKdStrasse.setBounds(520, 75, 100, 25);
        hilfsComp.add( txtKdStrasse );        
        
        JLabel lblKdPLZ = new JLabel( "PLZ:" );
        lblKdPLZ.setBounds( 400, 105, 90, 24 );
        hilfsComp.add( lblKdPLZ );
        txtKdPLZ = new JTextField();
        txtKdPLZ.setBounds(520, 105, 100, 25);
        hilfsComp.add( txtKdPLZ );        
        
        JLabel lblKdOrt = new JLabel( "Ort:" );
        lblKdOrt.setBounds( 400, 135, 90, 24 );
        hilfsComp.add( lblKdOrt );
        txtKdOrt = new JTextField();
        txtKdOrt.setBounds(520, 135, 100, 25);
        hilfsComp.add( txtKdOrt );
   	    
	    
		
       return hilfsComp;
       
		
	}
	
	public Component initBuch(){
		
			
			Container hilfsComp = new Container();
			
	        JLabel lblList = new JLabel( "Buch:" );
	        lblList.setBounds( 15, 15, 75, 24 );
	        hilfsComp.add( lblList );

	        buchListe = new JList();
	        buchListe.addMouseListener( this );
	        buchListe.addKeyListener( this );
	        buchListe.addListSelectionListener( this );
	        JScrollPane buchListeScrollPane = new JScrollPane( buchListe );
	        buchListeScrollPane.setBounds( 100, 15, 275, 150 );
	        buchListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
	        hilfsComp.add( buchListeScrollPane );

	        btnBuchEdit = new JButton( "edit" );
	        btnBuchEdit.setActionCommand( "editOrt" );
	        btnBuchEdit.setBounds( 100, 200, 75, 22 );
	        btnBuchEdit.addActionListener( this );
	        hilfsComp.add( btnBuchEdit );

	        btnBuchNew = new JButton( "new" );
	        btnBuchNew.setActionCommand( "newBuch" );
	        btnBuchNew.setBounds( 200, 200, 75, 22 );
	        btnBuchNew.addActionListener( this );
		    hilfsComp.add( btnBuchNew );
	        
	        btnBuchDel = new JButton( "del" );
	        btnBuchDel.setActionCommand( "delOrt" );
	        btnBuchDel.setBounds( 300, 200, 75, 22 );
	        btnBuchDel.addActionListener( this );
	        hilfsComp.add( btnBuchDel );
			 
			JLabel lblBuchISBN = new JLabel( "ISBN:" );
			lblBuchISBN.setBounds( 400, 15, 90, 24 );
	        hilfsComp.add( lblBuchISBN );
	        txtBuchISBN = new JTextField();
	        txtBuchISBN.setBounds(520, 15, 100, 25);
	        hilfsComp.add( txtBuchISBN );       
	        
	        JLabel lblBuchTitel = new JLabel( "Titel:" );
	        lblBuchTitel.setBounds( 400, 45, 90, 24 );
	        hilfsComp.add( lblBuchTitel );
	        txtBuchTitel = new JTextField();
	        txtBuchTitel.setBounds(520, 45, 100, 25);
	        hilfsComp.add( txtBuchTitel );       
	        
	        JLabel lblBuchAutor = new JLabel( "Autor:" );
	        lblBuchAutor.setBounds( 400, 75, 90, 24 );
	        hilfsComp.add( lblBuchAutor );
	        txtBuchAutor = new JTextField();
	        txtBuchAutor.setBounds(520, 75, 100, 25);
	        hilfsComp.add( txtBuchAutor );        
	        
	        JLabel lblBuchBeschreibung = new JLabel( "Beschreibung:" );
	        lblBuchBeschreibung.setBounds( 400, 105, 90, 24 );
	        hilfsComp.add( lblBuchBeschreibung );
	        txtBuchBeschreibung = new JTextField();
	        txtBuchBeschreibung.setBounds(520, 105, 100, 25);
	        hilfsComp.add( txtBuchBeschreibung );        
	        
	        JLabel lblBuchVerlag = new JLabel( "Verlag:" );
	        lblBuchVerlag.setBounds( 400, 135, 90, 24 );
	        hilfsComp.add( lblBuchVerlag );
	        txtBuchVerlag = new JTextField();
	        txtBuchVerlag.setBounds(520, 135, 100, 25);
	        hilfsComp.add( txtBuchVerlag );

	       return hilfsComp;
			
		

	}
	
public JTabbedPane initVerleihe(){
		
	JTabbedPane tphilfs = new JTabbedPane();
	{
	Container hilfsComp = new Container();
	
    JLabel lblList = new JLabel( "Ausleihen:" );
    lblList.setBounds( 15, 15, 75, 24 );
    hilfsComp.add( lblList );

    verleihListe = new JList();
    verleihListe.addMouseListener( this );
    verleihListe.addKeyListener( this );
    verleihListe.addListSelectionListener( this );
    JScrollPane buchListeScrollPane = new JScrollPane( verleihListe );
    buchListeScrollPane.setBounds( 100, 15, 275, 150 );
    verleihListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( buchListeScrollPane );

  	 tphilfs.addTab("Buchliste", hilfsComp);
	 
	 
	}
	
	{
	Container hilfsComp = new Container();
	 
	JLabel lblAusleihenLeser = new JLabel( "Leser:" );
	lblAusleihenLeser.setBounds( 15, 15, 75, 24 );
    hilfsComp.add( lblAusleihenLeser );

    ausleihenLeserListe = new JList();
    ausleihenLeserListe.addMouseListener( this );
    ausleihenLeserListe.addKeyListener( this );
    ausleihenLeserListe.addListSelectionListener( this );
    //JScrollPane ausleihenLeserScrollPane = new JScrollPane( ausleihenLeser );
    ausleihenLeserListe.setBounds( 75, 15, 275, 150 );
    ausleihenLeserListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( ausleihenLeserListe );
    
    JLabel lblAusleihenBuch = new JLabel( "Bücher:" );
    lblAusleihenBuch.setBounds( 400, 15, 75, 24 );
    hilfsComp.add( lblAusleihenBuch );
    
    auleihenBuecherListe = new JList();
    auleihenBuecherListe.addMouseListener( this );
    auleihenBuecherListe.addKeyListener( this );
    auleihenBuecherListe.addListSelectionListener( this );
    //JScrollPane buchListeScrollPane = new JScrollPane( auleihenBuecher );
    auleihenBuecherListe.setBounds( 475, 15, 275, 150 );
    auleihenBuecherListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( auleihenBuecherListe );

    btnVerleihEdit = new JButton( "edit" );
    btnVerleihEdit.setActionCommand( "editVerleih" );
    btnVerleihEdit.setBounds( 200, 195, 75, 22 );
    btnVerleihEdit.addActionListener( this );
    hilfsComp.add( btnVerleihEdit );
    
    btnVerleihNew = new JButton( "new" );
    btnVerleihNew.setActionCommand( "newVerleih" );
    btnVerleihNew.setBounds( 120, 195, 75, 22 );
    btnVerleihNew.addActionListener( this );
    hilfsComp.add( btnVerleihNew ); 

        		 
	 tphilfs.addTab("Buch anlegen&ändern", hilfsComp);
	}
   return tphilfs;
	

	}
public void refreshLeserListe(ArrayList<Leser> les){
	this.leserListe.setListData( new String[] {} );
    // ListModel l = frame.getOrtPanelSwing().getOrtListe().getModel();
    DefaultListModel d = new DefaultListModel();
    for ( Leser leser : les )
    {
        System.out.println("objekt gefunden!");
    	d.addElement( leser.getVorname() + " " + leser.getNachname() + " " + leser.getOrt());
        
    }
    leserListe.setModel( d );
	
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		System.out.println("***actionPerformed*** " + e.toString());
		System.out.println(e.getActionCommand());
		System.out.println(txtKdName.getName());
		
		
		if("editKd".equals(cmd)){
			String kunde = "Name: " + txtKdName.getText() + "\nNachname: " + txtKdNachname.getText() + 
												"\nStraße: " + txtKdStrasse.getText() + "\nPLZ: " + 
													txtKdPLZ.getText() + "\nOrt: " + txtKdOrt.getText();
			System.out.println(kunde);
			
			txtKdNachname.setText("");
			txtKdName.setText("");
			txtKdOrt.setText("");
			txtKdPLZ.setText("");
			txtKdStrasse.setText("");
		}
		else if("newKd".equals(cmd)){
			controller.addLeser(txtKdName.getText(), txtKdNachname.getText(), 
					txtKdStrasse.getText(), txtKdPLZ.getText(), txtKdOrt.getText());
			
			txtKdNachname.setText("");
			txtKdName.setText("");
			txtKdOrt.setText("");
			txtKdPLZ.setText("");
			txtKdStrasse.setText("");
			
			this.refreshLeserListe(this.controller.getAlleLeser());
		}
		else if("editLeserListe".equals(cmd)){
			int selected = this.leserListe.getSelectedIndex();
			//System.out.println(selected);
			System.out.println(this.leserListePane.getTabRunCount());
			
		}
		else if("Speichern".equals(cmd)){
			System.out.println("Hier speichern");
		}
		else if("Oeffnen".equals(cmd)){
			System.out.println("Hier oeffnen");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***mouseClicked*** " + arg0.toString());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***mouseEntered*** " + arg0.toString());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***mouseExited*** " + arg0.toString());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***mousePressed*** " + arg0.toString());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***mouseReleased*** " + arg0.toString());
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***valueChanged*** " + arg0.toString());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***keyPressed*** " + arg0.toString());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***keyReleased*** " + arg0.toString());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("***keyTyped*** " + arg0.toString());
	}
}
