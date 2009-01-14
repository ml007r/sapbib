package BIB_GUI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

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
    DefaultTableModel tmLeser;
    String[] columnNamesLeser = {"ID","Name", "Nachname","Strasse&HN","PLZ","Ort"};
    String[][] leser = {{"", "","","","",""}};
    JTable leserListe = new JTable(leser,columnNamesLeser);
    JTextField txtKdName = null;
    JTextField txtKdNachname = null;
    JTextField txtKdStrasse = null;
    JTextField txtKdPLZ = null;
    JTextField txtKdOrt = null;
    JButton btnKdEdit = null;
    JButton btnLeserEditStore = null;
    JButton btnLeserAbort = null;
    Leser hilfsLeser = null;

    /**
     * Alles für das Buchpanel - und unter Panel...
     */
    String[] columnNamesBuch = {"ID","ISBN", "Titel","Autor","Beschreibung","Verlag"};
    String[][] buch = {{"","","","","",""}};
    JTable buchListe = new JTable(buch,columnNamesBuch);
    DefaultTableModel tmBuch;
    JTextField txtBuchISBN = null;
    JTextField txtBuchTitel = null;
    JTextField txtBuchAutor = null;
    JTextField txtBuchBeschreibung = null;
    JTextField txtBuchVerlag = null;
    JButton btnBuchEdit = null;
    JButton btnBuchNew = null;
    JButton btnBuchDel = null;
    JButton btnBuchEditStore = null;
    JButton btnBuchAbort = null;
    Buch hilfsBuch = null;
    
    /**
     * Alles für das Verleihnpanel - und unter Panel...
     */
    JButton btnVerleihEdit = null;
    JButton btnVerleihNew = null;
    JButton btnVerleihDel = null;
    
    JTable buchListeVerleih = new JTable(buch,columnNamesBuch);
    
    String[] columnNamesLeserVerleih = {"ID","Name", "Nachname","Strasse&HN","PLZ","Ort"};
    String[][] leserVerleih = {{"","","","","",""}};
    JTable leserListeVerleih = new JTable(leserVerleih,columnNamesLeserVerleih);
    
    String[][] ausleiheZeile = {{"","","","","",""}};
    String[] columnNamesAusleihe = {"Vorname","Nachname","Titel","Autor","Ausleihdatum","Rueckgabedatum"};
    JTable buchListeAusleihe = new JTable(ausleiheZeile,columnNamesAusleihe);
    
    DefaultTableModel tmAusleihe;
    
	public BIB_Haupt(){
		super();
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        JTabbedPane tp = new JTabbedPane();
        
        tp.addTab("Leser", initLeser());   
        tp.addTab("Medium", initBuch());
        tp.addTab("Ausleihe", initVerleihe());      
        tp.addKeyListener(this);
        this.add(tp);
        txtKdName.setText("");
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
        this.setSize( 800, 600 );
        this.setVisible(true);
        System.out.println("");
        
	}

	 
	public Component initLeser(){
				
		Container hilfsComp = new Container();
		// Zeile 1
        JLabel lblList = new JLabel( "Leser:" );
        lblList.setBounds( 15, 15, 75, 24 );
        hilfsComp.add( lblList );

        
        //leserListe = new JTable(columnNamesLeser);
        leserListe.addMouseListener( this );
        leserListe.addKeyListener( this );
        //leserListe.addListSelectionListener( this );
        JScrollPane ortListeScrollPane = new JScrollPane( leserListe );
        ortListeScrollPane.setBounds( 100, 15, 275, 150 );
        leserListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        hilfsComp.add( ortListeScrollPane );

        // Zeile 2
        btnLeserListeEdit = new JButton( "edit" );
        btnLeserListeEdit.setActionCommand( "editLeser" );
        btnLeserListeEdit.setBounds( 100, 200, 75, 22 );
        btnLeserListeEdit.addActionListener( this );
        hilfsComp.add( btnLeserListeEdit );

        btnKdEdit = new JButton( "new" );
        btnKdEdit.setActionCommand( "newLeser" );
        btnKdEdit.setBounds( 200, 200, 75, 22 );
        btnKdEdit.addActionListener( this );
	    hilfsComp.add( btnKdEdit ); 
        
        JButton btnLeserListeDel = new JButton( "del" );
        btnLeserListeDel.setActionCommand( "delLeser" );
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
   	    
        btnLeserEditStore = new JButton( "speichern" );
        btnLeserEditStore.setActionCommand( "speichernLeser" );
        btnLeserEditStore.setBounds( 450, 200, 100, 22 );
        btnLeserEditStore.addActionListener( this );
        hilfsComp.add( btnLeserEditStore );
        btnLeserEditStore.setVisible(false);
        

        btnLeserAbort = new JButton( "abbrechen" );
        btnLeserAbort.setActionCommand( "abbrechen" );
        btnLeserAbort.setBounds( 570, 200, 100, 22 );
        btnLeserAbort.addActionListener( this );
	    hilfsComp.add( btnLeserAbort );
	    btnLeserAbort.setVisible(false);

		
       return hilfsComp;
       
		
	}
	
	public Component initBuch(){
		
			
			Container hilfsComp = new Container();
			
	        JLabel lblList = new JLabel( "Buch:" );
	        lblList.setBounds( 15, 15, 75, 24 );
	        hilfsComp.add( lblList );

	        
	        buchListe.addMouseListener( this );
	        buchListe.addKeyListener( this );
	        JScrollPane buchListeScrollPane = new JScrollPane( buchListe );
	        buchListeScrollPane.setBounds( 100, 15, 275, 150 );
	        buchListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
	        hilfsComp.add( buchListeScrollPane );

	        btnBuchEdit = new JButton( "edit" );
	        btnBuchEdit.setActionCommand( "editBuch" );
	        btnBuchEdit.setBounds( 100, 200, 75, 22 );
	        btnBuchEdit.addActionListener( this );
	        hilfsComp.add( btnBuchEdit );

	        btnBuchNew = new JButton( "new" );
	        btnBuchNew.setActionCommand( "newBuch" );
	        btnBuchNew.setBounds( 200, 200, 75, 22 );
	        btnBuchNew.addActionListener( this );
		    hilfsComp.add( btnBuchNew );
	        
	        btnBuchDel = new JButton( "del" );
	        btnBuchDel.setActionCommand( "delBuch" );
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
	        
	        btnBuchEditStore = new JButton( "speichern" );
	        btnBuchEditStore.setActionCommand( "speichernBuch" );
	        btnBuchEditStore.setBounds( 450, 200, 100, 22 );
	        btnBuchEditStore.addActionListener( this );
	        hilfsComp.add( btnBuchEditStore );
	        btnBuchEditStore.setVisible(false);
	        

	        btnBuchAbort = new JButton( "abbrechen" );
	        btnBuchAbort.setActionCommand( "abbrechenBuch" );
	        btnBuchAbort.setBounds( 570, 200, 100, 22 );
	        btnBuchAbort.addActionListener( this );
		    hilfsComp.add( btnBuchAbort );
		    btnBuchAbort.setVisible(false);
	        
	       return hilfsComp;
			
		

	}
	
public Component initVerleihe(){
		
	
	Container hilfsComp = new Container();
		
    buchListeAusleihe.addMouseListener( this );
    buchListeAusleihe.addKeyListener( this );
    JScrollPane ortListeScrollPane = new JScrollPane( buchListeAusleihe );
    ortListeScrollPane.setBounds( 100, 15, 275, 150 );
    buchListeAusleihe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( ortListeScrollPane );
    
   /*buchListeAusleihe.addMouseListener( this );
    buchListeAusleihe.addKeyListener( this );
    JScrollPane ortListeScrollPane = new JScrollPane( buchListeAusleihe );
    ortListeScrollPane.setBounds( 100, 15, 275, 150 );
    buchListeAusleihe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( ortListeScrollPane );*/
    
    buchListeVerleih.addMouseListener( this );
    buchListeVerleih.addKeyListener( this );
    JScrollPane ortListePane = new JScrollPane( buchListeVerleih );
    ortListeScrollPane.setBounds( 100, 300, 275, 150 );
    buchListeAusleihe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( ortListePane );
    
    

     
   return hilfsComp;
	

	

	}
public void refreshLeserTable(ArrayList<Leser> les){
	tmLeser = new DefaultTableModel(columnNamesLeser,0);
	if(!les.isEmpty()){		
	    int i = 0;
	    for ( Leser leser : les )
	    {
	    	if(leser.getId() != 0){
		        System.out.println("objekt gefunden!" + leser.getNachname());
		        String[] hilfsString = {leser.getId()+"",leser.getVorname(), leser.getNachname() ,leser.getStrasse(), leser.getPlz(), leser.getOrt()};
		        tmLeser.insertRow(i, hilfsString);
		    	i++;
	    	}
	        
	    }
    }
    leserListe.setModel( tmLeser );
	
}

public void refreshBuchTable(ArrayList<Buch> buch){
    tmBuch = new DefaultTableModel(columnNamesBuch,0);
    int i = 0;
    for ( Buch bu : buch )
    {
        System.out.println("Buch objekt gefunden!");
        String[] hilfsString = {bu.getId()+"", bu.getIsbn(), bu.getTitel(),bu.getAutor(), bu.getBeschreibung(), bu.getVerlag()};
    	tmBuch.insertRow(i, hilfsString);
    	i++;
        
    }
    buchListe.setModel( tmBuch );
	
}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println("***actionPerformed*** " + e.toString());
		System.out.println(e.getActionCommand());
		
		
		int selectedRow = 0;
		
		if("speichernBuch".equals(cmd)){
			this.controller.setBuch((Integer.parseInt(tmBuch.getValueAt(buchListe.getSelectedRow(), 0) + "")),txtBuchISBN.getText(),txtBuchTitel.getText(),txtBuchAutor.getText(),
					txtBuchBeschreibung.getText(), txtBuchVerlag.getText());
			this.refreshBuchTable(this.controller.getAlleBuecher());
			txtBuchISBN.setText("");
			txtBuchAutor.setText("");
			txtBuchBeschreibung.setText("");
			txtBuchTitel.setText("");
			txtBuchVerlag.setText("");
			btnBuchAbort.setVisible(false);
			btnBuchEditStore.setVisible(false);			
		}
		else if("newBuch".equals(cmd)){
			System.out.println("Buch.getAnzahlBuecher()"+ Buch.getAnzahlBuecher());
			this.controller.addBuch(Buch.getAnzahlBuecher(),txtBuchISBN.getText(),txtBuchAutor.getText(),
					txtBuchBeschreibung.getText(), txtBuchTitel.getText(),txtBuchVerlag.getText());
			txtBuchISBN.setText("");
			txtBuchAutor.setText("");
			txtBuchBeschreibung.setText("");
			txtBuchTitel.setText("");
			txtBuchVerlag.setText("");
			this.refreshBuchTable(this.controller.getAlleBuecher());
		}
		else if("speichernLeser".equals(cmd)){
			
			this.controller.setLeser((Integer.parseInt(tmLeser.getValueAt(leserListe.getSelectedRow(), 0) + "")),txtKdName.getText(),txtKdNachname.getText(),
					txtKdStrasse.getText(),txtKdPLZ.getText(),txtKdOrt.getText());
			txtKdNachname.setText("");
			txtKdName.setText("");
			txtKdOrt.setText("");
			txtKdPLZ.setText("");
			txtKdStrasse.setText("");			
			this.refreshLeserTable(this.controller.getAlleLeser());
			btnLeserEditStore.setVisible(false);
			btnLeserAbort.setVisible(false);			
		}
		else if ("abbrechenBuch".equals(cmd)){
			txtBuchISBN.setText("");
			txtBuchAutor.setText("");
			txtBuchBeschreibung.setText("");
			txtBuchTitel.setText("");
			txtBuchVerlag.setText("");
			btnBuchAbort.setVisible(false);
			btnBuchEditStore.setVisible(false);
		}
		else if("delBuch".equals(cmd)){
			int i = Integer.parseInt(tmBuch.getValueAt(buchListe.getSelectedRow(), 0) + "");
			this.controller.removeBuch(controller.getBuchByID(i));
			this.refreshBuchTable(this.controller.getAlleBuecher());
				
		}
		else if("delLeser".equals(cmd)){
			int i = Integer.parseInt(tmLeser.getValueAt(leserListe.getSelectedRow(), 0) + "");
			this.controller.removeLeser(controller.getLeserByID(i));
			this.refreshLeserTable(this.controller.getAlleLeser());
				
		}
		else if("editBuch".equals(cmd)){
			hilfsBuch = this.controller.getBuchByID((Integer.parseInt(tmBuch.getValueAt(buchListe.getSelectedRow(), 0) + "")));
			System.out.println(buchListe.getSelectedRow());
			txtBuchISBN.setText(hilfsBuch.getIsbn());
			txtBuchAutor.setText(hilfsBuch.getAutor());
			txtBuchBeschreibung.setText(hilfsBuch.getBeschreibung());
			txtBuchTitel.setText(hilfsBuch.getTitel());
			txtBuchVerlag.setText(hilfsBuch.getVerlag());
			btnBuchAbort.setVisible(true);
			btnBuchEditStore.setVisible(true);
		}
		else if("editLeser".equals(cmd)){
			System.out.println(tmLeser.getValueAt(leserListe.getSelectedRow(), 0) + "");
			hilfsLeser = this.controller.getLeserByID((Integer.parseInt(tmLeser.getValueAt(leserListe.getSelectedRow(), 0) + "")));
			txtKdName.setText(hilfsLeser.getVorname());
			txtKdNachname.setText(hilfsLeser.getNachname());
			txtKdStrasse.setText(hilfsLeser.getStrasse());
			txtKdPLZ.setText(hilfsLeser.getPlz());
			txtKdOrt.setText(hilfsLeser.getOrt());
			btnLeserEditStore.setVisible(true);
			btnLeserAbort.setVisible(true);
		}
		else if("newLeser".equals(cmd)){
					this.controller.addLeser(Leser.getAnzahlLeser(),txtKdName.getText(), txtKdNachname.getText(), 
								txtKdStrasse.getText(), txtKdPLZ.getText(), txtKdOrt.getText());
					txtKdNachname.setText("");
					txtKdName.setText("");
					txtKdOrt.setText("");
					txtKdPLZ.setText("");
					txtKdStrasse.setText("");
			
			this.refreshLeserTable(this.controller.getAlleLeser());
		}
		else if("Oeffnen".equals(cmd)){
			System.out.println("Hier oeffnen");
			this.controller.oeffneDaten();
			if(this.controller.getAlleBuecher().size() != 0){
				this.refreshBuchTable(this.controller.getAlleBuecher());
			}
			this.refreshLeserTable(this.controller.getAlleLeser());
		}
		else if("newBuch".equals(cmd)){					
				controller.addBuch(Buch.getAnzahlBuecher(),txtBuchISBN.getText(), txtBuchAutor.getText(), 
						txtBuchTitel.getText(), txtBuchBeschreibung.getText(), txtBuchVerlag.getText());
					
					txtBuchAutor.setText("");
					txtBuchBeschreibung.setText("");
					txtBuchISBN.setText("");
					txtBuchTitel.setText("");
					txtBuchVerlag.setText("");
			
			this.refreshBuchTable(this.controller.getAlleBuecher());
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
