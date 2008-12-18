package BIB_GUI;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;



public class BIB_Haupt extends JFrame
implements ActionListener, MouseListener, ListSelectionListener, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6612455908213542855L;

	/**
     * 
     */
    private JPanel KundePanel = null;
    private JList kundeListe = null;
    private JTextField kundeName = null;
    
    

	public BIB_Haupt(){
		super();
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 800, 400 );
       
        JTabbedPane tp = new JTabbedPane();
        
       // tp.setLayout(new GridLayout( 1, 3 ));
        tp.addTab("Benutzer", initKunde());   
        tp.addTab("Medium", initBuch());
        tp.addTab("Ausleihe", initVerleihe());        
        this.add(tp);
               
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

        initKunde();
        this.setVisible(true);
	}

	 
	public JTabbedPane initKunde(){
		
		JTabbedPane tphilfs = new JTabbedPane();
		{
		Container hilfsComp = new Container();
		// Zeile 1
        JLabel lblList = new JLabel( "Kunden:" );
        lblList.setBounds( 15, 15, 75, 24 );
        hilfsComp.add( lblList );

        JList ortListe = new JList();
        ortListe.addMouseListener( this );
        ortListe.addKeyListener( this );
        ortListe.addListSelectionListener( this );
        JScrollPane ortListeScrollPane = new JScrollPane( ortListe );
        ortListeScrollPane.setBounds( 100, 15, 275, 150 );
        ortListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        hilfsComp.add( ortListeScrollPane );

        // Zeile 2
        JButton btnEdit = new JButton( "edit" );
        btnEdit.setActionCommand( "editOrt" );
        btnEdit.setBounds( 200, 200, 75, 22 );
        btnEdit.addActionListener( this );
        hilfsComp.add( btnEdit );

        JButton btnDel = new JButton( "del" );
        btnDel.setActionCommand( "delOrt" );
        btnDel.setBounds( 300, 200, 75, 22 );
        btnDel.addActionListener( this );
        hilfsComp.add( btnDel );

		 tphilfs.addTab("Kundenliste", hilfsComp);
		 
		 
		}
		
		{
		Container hilfsComp = new Container();
		 
		JLabel lblKdName = new JLabel( "Vorname:" );
		lblKdName.setBounds( 15, 15, 90, 24 );
        hilfsComp.add( lblKdName );
        JTextField txtKdName = new JTextField();
        txtKdName.setBounds(120, 15, 100, 25);
        hilfsComp.add( txtKdName );       
        
        JLabel lblKdNachname = new JLabel( "Nachname:" );
        lblKdNachname.setBounds( 15, 45, 90, 24 );
        hilfsComp.add( lblKdNachname );
        JTextField txtKdNachname = new JTextField();
        txtKdNachname.setBounds(120, 45, 100, 25);
        hilfsComp.add( txtKdNachname );       
        
        JLabel lblKdStrasse = new JLabel( "Strasse&Hn:" );
        lblKdStrasse.setBounds( 15, 75, 90, 24 );
        hilfsComp.add( lblKdStrasse );
        JTextField txtKdStrasse = new JTextField();
        txtKdStrasse.setBounds(120, 75, 100, 25);
        hilfsComp.add( txtKdStrasse );        
        
        JLabel lblKdPLZ = new JLabel( "PLZ:" );
        lblKdPLZ.setBounds( 15, 105, 90, 24 );
        hilfsComp.add( lblKdPLZ );
        JTextField txtKdPLZ = new JTextField();
        txtKdPLZ.setBounds(120, 105, 100, 25);
        hilfsComp.add( txtKdPLZ );        
        
        JLabel lblKdOrt = new JLabel( "Ort:" );
        lblKdOrt.setBounds( 15, 135, 90, 24 );
        hilfsComp.add( lblKdOrt );
        JTextField txtKdOrt = new JTextField();
        txtKdOrt.setBounds(120, 135, 100, 25);
        hilfsComp.add( txtKdOrt );

		
        JButton btnEdit = new JButton( "edit" );
        btnEdit.setActionCommand( "editOrt" );
        btnEdit.setBounds( 200, 195, 75, 22 );
        btnEdit.addActionListener( this );
	    hilfsComp.add( btnEdit );

	        		 
		 tphilfs.addTab("Kunden anlegen&ändern", hilfsComp);
		}
       return tphilfs;
		
	}
	
	public JTabbedPane initBuch(){
		
			JTabbedPane tphilfs = new JTabbedPane();
			{
			Container hilfsComp = new Container();
			// Zeile 1
	        JLabel lblList = new JLabel( "Buch:" );
	        lblList.setBounds( 15, 15, 75, 24 );
	        hilfsComp.add( lblList );

	        JList ortListe = new JList();
	        ortListe.addMouseListener( this );
	        ortListe.addKeyListener( this );
	        ortListe.addListSelectionListener( this );
	        JScrollPane ortListeScrollPane = new JScrollPane( ortListe );
	        ortListeScrollPane.setBounds( 100, 15, 275, 150 );
	        ortListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
	        hilfsComp.add( ortListeScrollPane );

	        // Zeile 2
	        JButton btnEdit = new JButton( "edit" );
	        btnEdit.setActionCommand( "editOrt" );
	        btnEdit.setBounds( 200, 200, 75, 22 );
	        btnEdit.addActionListener( this );
	        hilfsComp.add( btnEdit );

	        JButton btnDel = new JButton( "del" );
	        btnDel.setActionCommand( "delOrt" );
	        btnDel.setBounds( 300, 200, 75, 22 );
	        btnDel.addActionListener( this );
	        hilfsComp.add( btnDel );

			 tphilfs.addTab("Buchliste", hilfsComp);
			 
			 
			}
			
			{
			Container hilfsComp = new Container();
			 
			JLabel lblBuchISBN = new JLabel( "ISBN:" );
			lblBuchISBN.setBounds( 15, 15, 90, 24 );
	        hilfsComp.add( lblBuchISBN );
	        JTextField txtBuchISBN = new JTextField();
	        txtBuchISBN.setBounds(120, 15, 100, 25);
	        hilfsComp.add( txtBuchISBN );       
	        
	        JLabel lblBuchTitel = new JLabel( "Titel:" );
	        lblBuchTitel.setBounds( 15, 45, 90, 24 );
	        hilfsComp.add( lblBuchTitel );
	        JTextField txtBuchTitel = new JTextField();
	        txtBuchTitel.setBounds(120, 45, 100, 25);
	        hilfsComp.add( txtBuchTitel );       
	        
	        JLabel lblBuchAutor = new JLabel( "Autor:" );
	        lblBuchAutor.setBounds( 15, 75, 90, 24 );
	        hilfsComp.add( lblBuchAutor );
	        JTextField txtBuchAutor = new JTextField();
	        txtBuchAutor.setBounds(120, 75, 100, 25);
	        hilfsComp.add( txtBuchAutor );        
	        
	        JLabel lblBuchBeschreibung = new JLabel( "Beschreibung:" );
	        lblBuchBeschreibung.setBounds( 15, 105, 90, 24 );
	        hilfsComp.add( lblBuchBeschreibung );
	        JTextField txtBuchBeschreibung = new JTextField();
	        txtBuchBeschreibung.setBounds(120, 105, 100, 25);
	        hilfsComp.add( txtBuchBeschreibung );        
	        
	        JLabel lblBuchVerlag = new JLabel( "Verlag:" );
	        lblBuchVerlag.setBounds( 15, 135, 90, 24 );
	        hilfsComp.add( lblBuchVerlag );
	        JTextField txtBuchVerlag = new JTextField();
	        txtBuchVerlag.setBounds(120, 135, 100, 25);
	        hilfsComp.add( txtBuchVerlag );



			
	        JButton btnEdit = new JButton( "edit" );
	        btnEdit.setActionCommand( "editOrt" );
	        btnEdit.setBounds( 200, 195, 75, 22 );
	        btnEdit.addActionListener( this );
		    hilfsComp.add( btnEdit );

		        		 
			 tphilfs.addTab("Buch anlegen&ändern", hilfsComp);
			}
	       return tphilfs;
			
		

	}
	
public JTabbedPane initVerleihe(){
		
	JTabbedPane tphilfs = new JTabbedPane();
	{
	Container hilfsComp = new Container();
	// Zeile 1
    JLabel lblList = new JLabel( "Ausleihen:" );
    lblList.setBounds( 15, 15, 75, 24 );
    hilfsComp.add( lblList );

    JList ortListe = new JList();
    ortListe.addMouseListener( this );
    ortListe.addKeyListener( this );
    ortListe.addListSelectionListener( this );
    JScrollPane ortListeScrollPane = new JScrollPane( ortListe );
    ortListeScrollPane.setBounds( 100, 15, 275, 150 );
    ortListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( ortListeScrollPane );

  	 tphilfs.addTab("Buchliste", hilfsComp);
	 
	 
	}
	
	{
	Container hilfsComp = new Container();
	 
	JLabel lblAusleihenLeser = new JLabel( "Leser:" );
	lblAusleihenLeser.setBounds( 15, 15, 75, 24 );
    hilfsComp.add( lblAusleihenLeser );

    JList ausleihenLeserListe = new JList();
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
    
    JList auleihenBuecherListe = new JList();
    auleihenBuecherListe.addMouseListener( this );
    auleihenBuecherListe.addKeyListener( this );
    auleihenBuecherListe.addListSelectionListener( this );
    //JScrollPane ortListeScrollPane = new JScrollPane( auleihenBuecher );
    auleihenBuecherListe.setBounds( 475, 15, 275, 150 );
    auleihenBuecherListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( auleihenBuecherListe );

    JButton btnEdit = new JButton( "edit" );
    btnEdit.setActionCommand( "editOrt" );
    btnEdit.setBounds( 200, 195, 75, 22 );
    btnEdit.addActionListener( this );
    hilfsComp.add( btnEdit );

        		 
	 tphilfs.addTab("Buch anlegen&ändern", hilfsComp);
	}
   return tphilfs;
	

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***actionPerformed*** " + arg0.toString());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***mouseClicked*** " + arg0.toString());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***mouseEntered*** " + arg0.toString());
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***mouseExited*** " + arg0.toString());
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***mousePressed*** " + arg0.toString());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***mouseReleased*** " + arg0.toString());
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***valueChanged*** " + arg0.toString());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***keyPressed*** " + arg0.toString());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***keyReleased*** " + arg0.toString());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("***keyTyped*** " + arg0.toString());
	}
}
