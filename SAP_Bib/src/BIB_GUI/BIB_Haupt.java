package BIB_GUI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.TableView.TableCell;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import BIB_Modell.Verleih;
import BIB_Modell.Buch;
import BIB_Modell.Leser;


public class BIB_Haupt extends JFrame
implements ActionListener, MouseListener, ListSelectionListener, Runnable, TableCellRenderer
{
	// Controller
	private BIB_Steuer controller = null;
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
    JTable leserListe = new JTable(leser,columnNamesLeser){
        public boolean isCellEditable(int x, int y) {
            return false;
        }
    };

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
    JTable buchListe = new JTable(buch,columnNamesBuch){
        public boolean isCellEditable(int x, int y) {
            return false;
        }
    };

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
    JScrollPane buchListeScrollPane = null;
    
    /**
     * Alles für das Verleihnpanel - und unter Panel...
     */
    JButton btnVerleihEdit = null;
    JButton btnVerleihNew = null;
    JButton btnVerleihDel = null;
    
    String[] columnNamesLeserVerleih = {"ID","Name", "Nachname","Strasse&HN","PLZ","Ort"};
    String[][] leserVerleihZeile = {{"","","","","",""}};
    JTable leserVerleih = new JTable(leserVerleihZeile,columnNamesLeserVerleih){
        public boolean isCellEditable(int x, int y) {
            return false;
        }
    };

    DefaultTableModel tmLeserVerleih;
    
    String[] columnNamesBuchVerleih = {"ID","ISBN", "Titel","Autor","Beschreibung","Verlag"};
    String[][] buchVerleihZeile = {{"","","","","",""}};
    JTable buchVerleih = new JTable(buchVerleihZeile,columnNamesBuchVerleih){
        public boolean isCellEditable(int x, int y) {
            return false;
        }
    };

    DefaultTableModel tmBuchVerleih;
    
    String[] columnNamesVerleih = {"ID" , "Buch ID", "Leser ID","Ausleihdatum","Rückgabedatum"};
    String[][] verleihZeile = {{"","","","",""}};
    JTable verleihListe = new JTable(verleihZeile,columnNamesVerleih){
        public boolean isCellEditable(int x, int y) {
            return false;
        }
    };

    DefaultTableModel tmVerleih;
   
    private static BIB_Haupt instance = null;
    
    @Override
	public void run() {
		BIB_Haupt.getInstance();
		}
    
    protected BIB_Haupt() {
    	super();
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        JTabbedPane tp = new JTabbedPane();
        
        tp.addTab("Leser", initLeser());   
        tp.addTab("Medium", initBuch());
        tp.addTab("Ausleihe", initVerleihe());      
        //tp.addKeyListener(this);
        this.add(tp);
        txtKdName.setText("");
        //this.addKeyListener(this);
        
		System.out.println();
               
        JMenuBar menuBar = new JMenuBar();

        /*JMenu menuDatei = new JMenu( "Datei" );
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

        this.setJMenuBar( menuBar );*/

        this.pack();
        this.setSize( 800, 600 );
        this.setVisible(true);
        //this.controller.oeffneDaten();
		/*if(this.controller.getAlleBuecher().size() != 0){
			this.refreshBuchTable(this.controller.getAlleBuecher());
		}
		this.refreshLeserTable(this.controller.getAlleLeser());
		this.refreshVerleihTable(this.controller.getAlleVerleihen());*/
    }
    public static BIB_Haupt getInstance() {
       if(instance == null) {
          instance = new BIB_Haupt();
       }
       return instance;
    }

    // alter Konstruktor...
	/*public BIB_Haupt(){
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
        this.controller.oeffneDaten();
		if(this.controller.getAlleBuecher().size() != 0){
			this.refreshBuchTable(this.controller.getAlleBuecher());
		}
		this.refreshLeserTable(this.controller.getAlleLeser());
		this.refreshVerleihTable(this.controller.getAlleVerleihen());
	}*/
	 
	public Component initLeser(){
				
		Container hilfsComp = new Container();
		// Zeile 1
        JLabel lblList = new JLabel( "Leser:" );
        lblList.setBounds( 15, 15, 75, 24 );
        hilfsComp.add( lblList );

        
        //leserListe = new JTable(columnNamesLeser);
        leserListe.addMouseListener( this );
        //leserListe.addKeyListener( this );
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
	        //buchListe.addKeyListener( this );
	        buchListeScrollPane = new JScrollPane( buchListe );
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
	
	JLabel lblListLeser = new JLabel( "Leser:" );
	lblListLeser.setBounds( 15, 15, 75, 24 );
    hilfsComp.add( lblListLeser );

    leserVerleih.addMouseListener( this );
	//leserVerleih.addKeyListener( this );
	JScrollPane leserListeVerleihScrollPane = new JScrollPane( leserVerleih );
    leserListeVerleihScrollPane.setBounds( 100, 15, 275, 150 );
    leserVerleih.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( leserListeVerleihScrollPane );
        
    JLabel lblListBuch = new JLabel( "Buch:" );
    lblListBuch.setBounds( 400, 15, 75, 24 );
    hilfsComp.add( lblListBuch );

    buchVerleih.addMouseListener( this );
    //buchVerleih.addKeyListener( this );
	JScrollPane buchListeVerleihScrollPane = new JScrollPane( buchVerleih );
	buchListeVerleihScrollPane.setBounds( 450, 15, 275, 150 );
    buchVerleih.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( buchListeVerleihScrollPane );
        
    JLabel lblListVerleih = new JLabel( "Verleih:" );
    lblListVerleih.setBounds( 15, 250, 75, 24 );
    hilfsComp.add( lblListVerleih );

    verleihListe.addMouseListener( this );
    //verleihListe.addKeyListener( this );
	JScrollPane listeVerleihScrollPane = new JScrollPane(  verleihListe );
	listeVerleihScrollPane.setBounds( 100 , 250, 500, 150 );
    verleihListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    hilfsComp.add( listeVerleihScrollPane );
	
    
    btnVerleihNew = new JButton( "new" );
    btnVerleihNew.setActionCommand( "newVerleih" );
    btnVerleihNew.setBounds( 100, 200, 75, 22 );
    btnVerleihNew.addActionListener( this );
    hilfsComp.add( btnVerleihNew );
    btnVerleihNew.setVisible(true);
    
    btnVerleihEdit = new JButton( "return" );
    btnVerleihEdit.setActionCommand( "returnVerleih" );
    btnVerleihEdit.setBounds( 100,430, 75, 22 );
    btnVerleihEdit.addActionListener( this );
    hilfsComp.add( btnVerleihEdit );
    btnVerleihEdit.setVisible(true);
    
    btnVerleihDel = new JButton( "del" );
    btnVerleihDel.setActionCommand( "delVerleih" );
    btnVerleihDel.setBounds( 200, 430, 75, 22 );
    btnVerleihDel.addActionListener( this );
    hilfsComp.add( btnVerleihDel );
    btnVerleihDel.setVisible(true);
    
   
        
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
	    leserListe.setModel( tmLeser );
	    leserVerleih.setModel(tmLeser);
    }
}

public void refreshBuchTable(ArrayList<Buch> buch){
    if(!buch.isEmpty()){
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
	    tmBuchVerleih = new DefaultTableModel(columnNamesBuchVerleih,0);
	    int j = 0;
	    for ( Buch bu : buch )
	    {
	        if(bu.getVerleihStatus()){
		    	System.out.println("Buch objekt gefunden mit verleihStatus true!");
		        String[] hilfsString = {bu.getId()+"", bu.getIsbn(), bu.getTitel(),bu.getAutor(), bu.getBeschreibung(), bu.getVerlag()};
		        tmBuchVerleih.insertRow(j, hilfsString);
		    	j++;
	        }        
	    }
	    buchVerleih.setModel(tmBuchVerleih);
    }
}

public void refreshVerleihTable(ArrayList<Verleih> verleih){
    if(!verleih.isEmpty()){
		tmVerleih = new DefaultTableModel(columnNamesVerleih,0);
	    int i = 0;
	    for ( Verleih ver : verleih )
	    {
	        if(ver.getId()!= 0 ){
	        String[] hilfsString = {ver.getId()+"",ver.getDasBuch()+"",ver.getDerLeser()+"", ver.getAusleihdatum(), ver.getRueckgabedatum()};
	        tmVerleih.insertRow(i, hilfsString);
	    	i++;
	        }
	    }
	    verleihListe.setModel( tmVerleih );
    }	
}
public BIB_Steuer getController() {
	return controller;
}
@Override
public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int column) {
	 // die normalen Farben

	setForeground( Color.BLACK );
   
    
  
    return this;
 } 


public void setController(BIB_Steuer controller) {
	this.controller = controller;
	this.refreshBuchTable(BIB_Steuer.getInstance().getAlleBuecher());
	this.refreshLeserTable(BIB_Steuer.getInstance().getAlleLeser());
	this.refreshVerleihTable(BIB_Steuer.getInstance().getAlleVerleihen());
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
		else if("newVerleih".equals(cmd)){
			Calendar myCal2 = new GregorianCalendar();
			String aktDatum;
			String rueckDatum;
			int aktTag = myCal2.get(Calendar.DAY_OF_MONTH);
			int aktMonat = myCal2.get(Calendar.MONTH)+1;
			int aktJahr = myCal2.get(Calendar.YEAR);
			System.out.println(aktJahr);
			if (aktMonat<= 9){
				aktDatum = aktTag + ".0" + aktMonat + "." + aktJahr;
			}
			else{
				aktDatum = aktTag + "." + aktMonat + "." + aktJahr;
			}
			
			if (aktMonat == 12){
				aktMonat = 0;
				aktJahr += 1;
			}
			if (aktMonat == 12){
				aktMonat = 0;
				aktJahr += 1;
			}
			int rueckMonat = aktMonat + 1;
			
			if (aktMonat<= 9){
				rueckDatum = aktTag + ".0" + rueckMonat + "." + aktJahr;
			}
			else{
				rueckDatum = aktTag + ".0" + rueckMonat + "." + aktJahr;
			}
			
			int b = (Integer.parseInt(tmBuchVerleih.getValueAt(buchVerleih.getSelectedRow(), 0) + ""));
			Buch hilfsBuch = this.controller.getBuchByID(b);
			int leser = (Integer.parseInt(tmLeser.getValueAt(leserVerleih.getSelectedRow(), 0) + ""));
			this.controller.addVerleih(Verleih.getAnzahlVerleihen(),aktDatum, rueckDatum, leser, hilfsBuch.getId());
			this.refreshVerleihTable(this.controller.getAlleVerleihen());
			this.controller.aendernBuchStatus(hilfsBuch);
			this.refreshBuchTable(this.controller.getAlleBuecher());
			btnVerleihDel.setVisible(true);
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
		else if("returnVerleih".equals(cmd)){
			Calendar myCal2 = new GregorianCalendar();
			String rueckDatum;
			int aktTag = myCal2.get(Calendar.DAY_OF_MONTH);
			int aktMonat = myCal2.get(Calendar.MONTH)+1;
			int aktJahr = myCal2.get(Calendar.YEAR);
			rueckDatum = aktTag + ".0" + aktMonat + "." + aktJahr;
			int i = Integer.parseInt(tmVerleih.getValueAt(verleihListe.getSelectedRow(), 0) + "");
			Verleih ver = controller.getVerleihByID(i);
			ver.setRueckgabedatum(rueckDatum);
			this.controller.aendernRueckgabeDatum(ver);
		
			Buch hilfsBuch = this.controller.getBuchByID(ver.getDasBuch());
			System.out.println("status von buch + " + buch + " geändert...");
			this.controller.aendernBuchStatus(hilfsBuch);
			
			this.refreshBuchTable(this.controller.getAlleBuecher());
			this.refreshVerleihTable(this.controller.getAlleVerleihen());
		}
		else if("delVerleih".equals(cmd)){
			if(!this.controller.getAlleVerleihen().isEmpty()){
			int i = Integer.parseInt(tmVerleih.getValueAt(verleihListe.getSelectedRow(), 0) + "");
			Verleih ver = controller.getVerleihByID(i);
			int buch = ver.getDasBuch();
			System.out.println("status von buch + " + buch + " geändert...");
			this.controller.removeVerleih(ver);
			this.controller.getBuchByID(buch).setLeihe(true);
			this.refreshBuchTable(this.controller.getAlleBuecher());
			this.refreshVerleihTable(this.controller.getAlleVerleihen());
			}
			if(this.controller.getAlleVerleihen().isEmpty()){
				btnVerleihDel.setVisible(false);
			}
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
			/*this.controller.oeffneDaten();
			if(this.controller.getAlleBuecher().size() != 0){
				this.refreshBuchTable(this.controller.getAlleBuecher());
			}
			this.refreshLeserTable(this.controller.getAlleLeser());
			this.refreshVerleihTable(this.controller.getAlleVerleihen());*/
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
