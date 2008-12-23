import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author tbs
 * 
 */
// TODO Listener pruefen, werden wirklich alle benoetigt?
public class RouteplanerFrameneu extends JFrame
    implements ActionListener, MouseListener, ListSelectionListener, KeyListener
{

    /**
     * 
     */
    

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private JPanel OrtPanel = null;

    /**
     * 
     */
    private JPanel StrassePanel = null;

    /**
     * 
     */
    private JPanel RoutePanel = null;

    // ---------------------------------------------------------------------
    // fuers OrtPanel

    /**
     * Eingabefeld fuer Ortname
     */
    private JTextField ortName = new JTextField();

    /**
     * Liste der bestehenden Orte
     */
    private JList ortListe;

    // ---------------------------------------------------------------------
    // fuers StrassePanel

    /**
     * Liste der Strassen fuer die angegebene Stadt
     */
    private JList strasseListe = new JList();

    /**
     * Name der Stadt, fuer die die Strassen anzeigt werden
     */
    private JLabel startOrtLabel = new JLabel();

    /**
     * StadtListe bis
     */
    private JComboBox zielOrt = new JComboBox();

    /**
     * Textfeld zu Kilometerangabe
     */
    private JTextField kmTextField = new JTextField();

    // ---------------------------------------------------------------------
    // fuers RoutePanel

    /**
     * StadtListe von
     */
    private JComboBox startStadt = new JComboBox();

    /**
     * StadtListe ueber
     */
    private JComboBox ueberStadt = new JComboBox();

    /**
     * StadtListe bis
     */
    private JComboBox zielStadt = new JComboBox();

    /**
     * Label Beste Route
     */
    private JLabel besteRoute = new JLabel( "" );

    /**
     * Beschriftung des Labels beste Route
     */
    private JLabel besteRouteBeschriftung = new JLabel( "" );

    /**
     * Schieberegler zur Einstellung der Geschwindigkeit
     */
    private JSlider dieGeschwindigkeit;

    /**
     * Beschriftung zur Geschwindgikeit
     */
    private JLabel dieGeschwindigkeitLabel = new JLabel( "" );

    /**
     * Beschriftung der aktuellen Geschwindigkeit
     */
    private JLabel dieAktuelleGeschwindigkeitLabel = new JLabel( "" );

    // -------------------------------------------------------------------------------------------------
    // Konstruktor
    // -------------------------------------------------------------------------------------------------

    /**
     * This is the default constructor
     */
    public RouteplanerFrameneu()
    {
        super();

        initialize();

        // TODO Aufruf erst bei oeffnen?
        // controller.loadKarte();

        
    }

    // -------------------------------------------------------------------------------------------------
    // Initialisieren
    // -------------------------------------------------------------------------------------------------

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize()
    {
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setSize( 1400, 400 );
        this.setLayout( new GridLayout( 1, 3 ) );

        this.add( getOrtPanel(), null );
        this.add( getStrassePanel(), null );
        this.add( getRoutePanel(), null );

        this.setTitle( "Routenplaner 2.0" );
        this.setJMenuBar( initMenu() );

        
        
        this.setVisible( true );
    }

    // -------------------------------------------------------------------------------------------------
    // Menue
    // -------------------------------------------------------------------------------------------------

    /**
     * 
     * @return
     */
    public JMenuBar initMenu()
    {
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

        JMenu menuAbout = new JMenu( "Look & Feel" );
        JMenuItem menuItemJava = new JMenuItem( "L&F Java" );
        menuItemJava.addActionListener( this );
        menuAbout.add( menuItemJava );
        JMenuItem menuItemWindows = new JMenuItem( "L&F Windows" );
        menuItemWindows.addActionListener( this );
        menuAbout.add( menuItemWindows );
        JMenuItem menuItemMotif = new JMenuItem( "L&F Motif" );
        menuItemMotif.addActionListener( this );
        menuAbout.add( menuItemMotif );
        add( menuAbout );

        menuBar.add( menuDatei );
        menuBar.add( menuAbout );
        menuBar.setBounds( 0, 0, 800, 20 );

        return menuBar;
    }

    // -------------------------------------------------------------------------------------------------
    // Panels
    // -------------------------------------------------------------------------------------------------

    /**
     * This method initializes OrtPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getOrtPanel()
    {
        if ( OrtPanel == null )
        {
            OrtPanel = new JPanel();
            OrtPanel.setLayout( null );
            OrtPanel.setSize( 400, 400 );
            OrtPanel.setBackground( Color.lightGray );

            // Zeile 1
            JLabel lblList = new JLabel( "Orte:" );
            lblList.setBounds( 15, 15, 75, 24 );
            OrtPanel.add( lblList );

            ortListe = new JList();
            ortListe.addMouseListener( this );
            ortListe.addKeyListener( this );
            ortListe.addListSelectionListener( this );
            JScrollPane ortListeScrollPane = new JScrollPane( ortListe );
            ortListeScrollPane.setBounds( 100, 15, 275, 150 );
            ortListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
            OrtPanel.add( ortListeScrollPane );

            // Zeile 2
            JLabel lblTF = new JLabel( "Name:" );
            lblTF.setBounds( 15, 200, 75, 24 );
            OrtPanel.add( lblTF );

            ortName.setBounds( 100, 200, 275, 24 );
            OrtPanel.add( ortName );

            // Zeile 3
            JButton btnNew = new JButton( "new" );
            btnNew.setActionCommand( "newOrt" );
            btnNew.setBounds( 100, 250, 75, 22 );
            btnNew.addActionListener( this );
            OrtPanel.add( btnNew );

            JButton btnEdit = new JButton( "edit" );
            btnEdit.setActionCommand( "editOrt" );
            btnEdit.setBounds( 200, 250, 75, 22 );
            btnEdit.addActionListener( this );
            OrtPanel.add( btnEdit );

            JButton btnDel = new JButton( "del" );
            btnDel.setActionCommand( "delOrt" );
            btnDel.setBounds( 300, 250, 75, 22 );
            btnDel.addActionListener( this );
            OrtPanel.add( btnDel );
        }
        return OrtPanel;
    }

    /**
     * This method initializes StrassePanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getStrassePanel()
    {
        if ( StrassePanel == null )
        {
            StrassePanel = new JPanel();
            StrassePanel.setLayout( null );
            StrassePanel.setSize( 400, 400 );
            // Hintergrund-Farbe
            StrassePanel.setBackground( Color.lightGray );

            // Zeile 1
            JLabel labelStrassen = new JLabel( "Strassen:" );
            labelStrassen.setBounds( 15, 15, 75, 24 );
            StrassePanel.add( labelStrassen );
            strasseListe = new JList();
            strasseListe.addMouseListener( this );
            strasseListe.addKeyListener( this );
            strasseListe.addListSelectionListener( this );
            JScrollPane strasseListeScrollPane = new JScrollPane( strasseListe );
            strasseListeScrollPane.setBounds( 100, 15, 275, 110 );
            strasseListe.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
            StrassePanel.add( strasseListeScrollPane );

            // Zeile 2
            JLabel labelVon = new JLabel( "Von:" );
            labelVon.setBounds( 15, 130, 75, 24 );
            StrassePanel.add( labelVon );
            startOrtLabel.setBounds( 100, 130, 275, 24 );
            startOrtLabel.setForeground( Color.BLUE );
            StrassePanel.add( startOrtLabel );

            // Zeile 3
            JLabel labelBis = new JLabel( "Bis:" );
            labelBis.setBounds( 15, 160, 75, 24 );
            StrassePanel.add( labelBis );
            zielOrt.setBounds( 100, 160, 275, 24 );
            zielOrt.setVisible( true );
            StrassePanel.add( zielOrt );

            // Zeile 4
            JLabel labelKm = new JLabel( "KM:" );
            labelKm.setBounds( 15, 200, 75, 24 );
            StrassePanel.add( labelKm );
            kmTextField.setBounds( 100, 200, 150, 24 );
            StrassePanel.add( kmTextField );

            // Zeile 5
            JButton btnNew = new JButton( "new" );
            btnNew.setActionCommand( "newStrasse" );
            btnNew.setBounds( 100, 240, 75, 22 );
            btnNew.addActionListener( this );
            StrassePanel.add( btnNew );

            JButton btnEdit = new JButton( "edit" );
            btnEdit.setActionCommand( "editStrasse" );
            btnEdit.setBounds( 200, 240, 75, 22 );
            btnEdit.addActionListener( this );
            StrassePanel.add( btnEdit );

            JButton btnDel = new JButton( "del" );
            btnDel.setActionCommand( "delStrasse" );
            btnDel.setBounds( 300, 240, 75, 22 );
            btnDel.addActionListener( this );
            StrassePanel.add( btnDel );
        }

        return StrassePanel;
    }

    /**
     * This method initializes RoutePanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getRoutePanel()
    {
        if ( RoutePanel == null )
        {
            RoutePanel = new JPanel();
            RoutePanel.setLayout( null );
            RoutePanel.setSize( 600, 400 );
            // Hintergrund-Farbe
            RoutePanel.setBackground( Color.lightGray );

            // Zeile 1
            JLabel labelVon = new JLabel( "Von:" );
            labelVon.setBounds( 15, 15, 75, 24 );
            RoutePanel.add( labelVon );
            startStadt.setBounds( 120, 15, 275, 24 );
            RoutePanel.add( startStadt );

            // Zeile 2
            JLabel labelUeber = new JLabel( "Ueber:" );
            labelUeber.setBounds( 15, 40, 75, 24 );
            RoutePanel.add( labelUeber );
            ueberStadt.setBounds( 120, 40, 275, 24 );
            RoutePanel.add( ueberStadt );

            // Zeile 3
            JLabel labelBis = new JLabel( "Bis:" );
            labelBis.setBounds( 15, 65, 75, 24 );
            RoutePanel.add( labelBis );
            zielStadt.setBounds( 120, 65, 275, 24 );
            RoutePanel.add( zielStadt );

            // Zeile 4
            dieGeschwindigkeitLabel.setText( "Geschwindigkeit: " );
            dieGeschwindigkeitLabel.setBounds( 15, 90, 100, 24 );
            dieAktuelleGeschwindigkeitLabel.setText( "100" + " km/h" );
            dieAktuelleGeschwindigkeitLabel.setBounds( 400, 97, 100, 24 );
            dieGeschwindigkeit = new JSlider( JSlider.HORIZONTAL, 0, 80, 30 );
            dieGeschwindigkeit.setBounds( 120, 90, 275, 40 );
            dieGeschwindigkeit.addMouseListener( this );
            dieGeschwindigkeit.addKeyListener( this );
            dieGeschwindigkeit.setOrientation( JProgressBar.HORIZONTAL );
            dieGeschwindigkeit.setMaximum( 250 );
            dieGeschwindigkeit.setMinimum( 0 );
            dieGeschwindigkeit.setValue( 100 );
            dieGeschwindigkeit.createStandardLabels( 10 );
            dieGeschwindigkeit.setPaintLabels( true );
            dieGeschwindigkeit.setMajorTickSpacing( 50 );
            dieGeschwindigkeit.setMinorTickSpacing( 5 );
            RoutePanel.add( dieGeschwindigkeit );
            RoutePanel.add( dieGeschwindigkeitLabel );
            RoutePanel.add( dieAktuelleGeschwindigkeitLabel );

            // Zeile 4
            JButton btnSuche = new JButton( "suche" );
            btnSuche.setBounds( 320, 135, 75, 24 );
            btnSuche.addActionListener( this );
            RoutePanel.add( btnSuche );

            // Zeile 5
            besteRouteBeschriftung.setBounds( 15, 150, 300, 25 );
            besteRouteBeschriftung.setText( "" );
            besteRouteBeschriftung.setForeground( Color.red );
            RoutePanel.add( besteRouteBeschriftung );
            besteRouteBeschriftung.setVisible( true );

            // Zeile 6
            besteRoute.setBounds( 15, 160, 400, 50 );
            besteRoute.setForeground( Color.red );
            besteRoute.setText( "" );
            RoutePanel.add( besteRoute );
            besteRoute.setVisible( true );

        }
        return RoutePanel;
    }

    // ------------------------------------------------------
    // Steuerelement-Methoden

    /**
     * 
     * 
     */
    public void ortAusgewaehlt()
    {
        if ( ortListe.getSelectedValue() == null )
        {
        	startOrtLabel.setText( "" );
            ortName.setText( "" );
            
            this.strasseListe.setListData( new String[] {} );
            this.zielOrt.removeAllItems();
            this.kmTextField.setText( "" );
        }
        else
        {
        	String ort = (String) ortListe.getSelectedValue();
        	System.out.println( ort + " ausgewaehlt!" );
        	
            startOrtLabel.setText( ort );
            ortName.setText( ort );

        }
    }

    /**
     * 
     * 
     */
    

    // ----------------------------------------------------------------------------------------
    // Action Performed

    public void actionPerformed( ActionEvent e )
    {
        String cmd1 = e.getActionCommand();
        System.out.println( cmd1 + " gedrueckt!" );

        
        this.besteRouteBeschriftung.setText("");
        this.besteRoute.setText("");
        
        // ---------------------------------------------------------

        String von = "", bis = "";
        double km = 0;

        try
        {
            if ( !"".equals( kmTextField.getText() ) )
                km = Double.parseDouble( kmTextField.getText() );
        }
        catch ( Exception e1 )
        {
 //           new InfoFenster( "km ungueltig" );
        }
        
        if ( ortListe.getSelectedValue() != null )
        {
        	System.out.println("von auf " + ortListe.getSelectedValue().toString() + " gesetzt" );
            von = ortListe.getSelectedValue().toString();
        }

        // ---------------------------------------------------------

        // Neuer Ort anlegen
        if ( "newOrt".equals( cmd1 ) )
        {
            try
            {
                String name = ortName.getText();
                //controller.addStadt( name );
                //this.refreshStadtListe( controller.getStaedte() );
                System.out.println(name);
                this.ortAusgewaehlt();
                  
               /* this.refreshCombo( this.startStadt, controller.getStaedte() );
                this.refreshCombo( this.ueberStadt, controller.getStaedte() )*/
                
            }
            catch ( Exception e1 )
            {
                //new InfoFenster( e1.getMessage() );
            }
        }

        // Ort bearbeiten
        else if ( "editOrt".equals( cmd1 ) )
        {
            try
            {            	
                /*String name = ortName.getText();
                System.out.println( von + " bei Edit ausgewaehlt!" );

                controller.editStadt( von, name );
                this.refreshStadtListe( controller.getStaedte() );
                this.ortListe.setSelectedValue( name, true );
                this.ortAusgewaehlt();
                
                this.refreshCombo( this.startStadt, controller.getStaedte() );
                this.refreshCombo( this.ueberStadt, controller.getStaedte() );
                this.refreshCombo( this.zielStadt, controller.getStaedte() );*/
            }
            catch ( Exception e1 )
            {
                System.err.println( e1.getMessage() );
                //new InfoFenster( e1.getMessage() );
            }
        }

        // Ort loeschen
        else if ( "delOrt".equals( cmd1 ) )
        {
            try
            {
                /*controller.removeStadt( von );
                this.refreshStadtListe( controller.getStaedte() );
                this.ortAusgewaehlt();
                
                this.refreshCombo( this.startStadt, controller.getStaedte() );
                this.refreshCombo( this.ueberStadt, controller.getStaedte() );
                this.refreshCombo( this.zielStadt, controller.getStaedte() );*/
            }
            catch ( Exception e1 )
            {
                /*System.err.println( e1.getMessage() );
                new InfoFenster( e1.getMessage() );*/
            }
        }

        // Neue Strasse
        else if ( "newStrasse".equals( cmd1 ) )
        {
            try
            {
                /*System.out.println( kmTextField.getText() );
                if ( zielOrt.getSelectedItem() != null )
                    bis = (String) zielOrt.getSelectedItem();

                System.out.println( "von " + von + " bis " + bis + " km " + km );

                controller.addStrecke( von, bis, km );

                this.refreshStrasseListe( (ArrayList<Strasse>) controller.getStrassen( von ) );
                this.setEintraegeZielOrt( controller.getStaedteOhneVerbindung( von ) );*/
            }
            catch ( Exception e1 )
            {
                //new InfoFenster( e1.getMessage() );
            }
        }

        // Strasse bearbeiten
        else if ( "editStrasse".equals( cmd1 ) )
        {
            try
            {/*
                // Die Zielstadt der markierten Verbindung (ohne km-Angabe) als
                // 'bis'
                if ( strasseListe.getSelectedValue() != null )
                {
                    bis =
                        ( (Strasse) controller.getStadt( von ).getEdges().get( strasseListe.getSelectedIndex() ) ).getDestination().getName();
                }
                System.out.println( bis );
                System.out.println( von + " - " + bis + " - " + km );

                controller.editStrecke( von, bis, km );

                this.refreshStrasseListe( (ArrayList<Strasse>) controller.getStrassen( von ) );
                this.setEintraegeZielOrt( controller.getStaedteOhneVerbindung( von ) );*/
            }
            catch ( Exception e1 )
            {
                //new InfoFenster( e1.getMessage() );
                System.out.println( e1.getMessage() );
            }
        }

        // Strasse loeschen
        else if ( "delStrasse".equals( cmd1 ) )
        {
            try
            {
                /*// Die Zielstadt der markierten Verbindung (ohne km-Angabe) als
                // 'bis'
                if ( strasseListe.getSelectedValue() != null )
                {
                    bis =
                        ( (Strasse) controller.getStadt( von ).getEdges().get( strasseListe.getSelectedIndex() ) ).getDestination().getName();
                }
                System.out.println( von + " - " + bis + " loeschen" );
                controller.removeStrecke( von, bis );

                this.refreshStrasseListe( (ArrayList<Strasse>) controller.getStrassen( von ) );
                this.setEintraegeZielOrt( controller.getStaedteOhneVerbindung( von ) );*/
            }
            catch ( Exception e1 )
            {
                /*new InfoFenster( e1.getMessage() );
                System.out.println( e1.getMessage() );*/
            }
        }
        
        
        
        
        //Suche
        else if ( "suche".equals( cmd1 ) ){
        	
        	try{
	        	/*String startStadt = (String) this.startStadt.getSelectedItem();
	        	String ueberStadt = (String) this.ueberStadt.getSelectedItem();
	        	String zielStadt = (String) this.zielStadt.getSelectedItem();
	        	
	        	if(!"Bitte Stadt auswaehlen".equals(startStadt) && !"Bitte Stadt auswaehlen".equals(ueberStadt) && !"Bitte Stadt auswaehlen".equals(zielStadt)){
	        		this.besteRoute.setText(controller.suche(startStadt, zielStadt, ueberStadt, dieGeschwindigkeit.getValue()));
	        		this.besteRouteBeschriftung.setText("Beste Route: ");
	        	}
	        	else if(!"Bitte Stadt auswaehlen".equals(startStadt) && "Bitte Stadt auswaehlen".equals(ueberStadt) && !"Bitte Stadt auswaehlen".equals(zielStadt)){
	        		this.besteRoute.setText(controller.suche(startStadt, zielStadt, dieGeschwindigkeit.getValue()));
	        		this.besteRouteBeschriftung.setText("Beste Route: ");
	        	}
	        	else{
	        		throw new Exception("Bitte waehlen Sie zumindest (verschiedenen) Start- und Ziel-Ort aus!");
	        	}*/
        	}catch(Exception e1){
        		/*System.err.println(e1.getMessage());
        		new InfoFenster(e1.getMessage());*/
        	}
        }
        
        
        

        // ------------------------------------------------------------------------
        // Menu-Operationen
        else if ( "Speichern".equals( cmd1 ) )
        {
            //controller.storeKarte();
        }
        else if ( "Oeffnen".equals( cmd1 ) )
        {
           /* controller.loadKarte();
            this.ortListe.clearSelection();
            this.ortAusgewaehlt();
            this.refreshStadtListe( controller.getStaedte() );
            this.refreshCombo(startStadt, controller.getStaedte());
            this.refreshCombo(ueberStadt, controller.getStaedte());
            this.refreshCombo(zielStadt, controller.getStaedte());*/
        }
        else if ( "Beenden".equals( cmd1 ) )
        {
            System.out.println( "Routenplaner beendet." );
            // KarteVerwaltung.store( karte );
            this.dispose();
            System.exit( 0 );
        }
        else if ( "L&F Java".equals( cmd1 ) )
        {
            try
            {
                UIManager.setLookAndFeel( "javax.swing.plaf.metal.MetalLookAndFeel" );
            }
            catch ( ClassNotFoundException e1 )
            {
                e1.printStackTrace();
            }
            catch ( InstantiationException e1 )
            {
                e1.printStackTrace();
            }
            catch ( IllegalAccessException e1 )
            {
                e1.printStackTrace();
            }
            catch ( UnsupportedLookAndFeelException e1 )
            {
                e1.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI( this );
        }
        else if ( "L&F Windows".equals( cmd1 ) )
        {
            try
            {
                UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );
            }
            catch ( ClassNotFoundException e1 )
            {
                e1.printStackTrace();
            }
            catch ( InstantiationException e1 )
            {
                e1.printStackTrace();
            }
            catch ( IllegalAccessException e1 )
            {
                e1.printStackTrace();
            }
            catch ( UnsupportedLookAndFeelException e1 )
            {
                e1.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI( this );
        }
        else if ( "L&F Motif".equals( cmd1 ) )
        {
            try
            {
                UIManager.setLookAndFeel( "com.sun.java.swing.plaf.motif.MotifLookAndFeel" );
            }
            catch ( ClassNotFoundException e1 )
            {
                e1.printStackTrace();
            }
            catch ( InstantiationException e1 )
            {
                e1.printStackTrace();
            }
            catch ( IllegalAccessException e1 )
            {
                e1.printStackTrace();
            }
            catch ( UnsupportedLookAndFeelException e1 )
            {
                e1.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI( this );
        }
    }

    public void mouseClicked( MouseEvent e )
    {
    	if (e.getSource().equals(dieGeschwindigkeit)) {
			dieAktuelleGeschwindigkeitLabel.setText(dieGeschwindigkeit.getValue()
					+ " km/h");
		}
    }

    public void mouseEntered( MouseEvent arg0 )
    {
        // TODO Auto-generated method stub

    }

    public void mouseExited( MouseEvent arg0 )
    {
        // TODO Auto-generated method stub

    }

    public void mousePressed( MouseEvent e )
    {
        System.out.println( e.getSource() );
        if ( e.getSource().equals( ortListe ) )
        {
            this.ortAusgewaehlt();
        }
        else if ( e.getSource().equals( strasseListe ) )
        {
            //this.strasseAusgewaehlt();
        }
        else if (e.getSource().equals(dieGeschwindigkeit)) {
			dieAktuelleGeschwindigkeitLabel.setText(dieGeschwindigkeit.getValue()
					+ " km/h");
		}
    }

    public void mouseReleased( MouseEvent e )
    {
    	if (e.getSource().equals(dieGeschwindigkeit)) {
			dieAktuelleGeschwindigkeitLabel.setText(dieGeschwindigkeit.getValue()
					+ " km/h");
		}
    }

    public void keyPressed( KeyEvent e )
    {
    	if (e.getSource().equals(dieGeschwindigkeit)) {
			dieAktuelleGeschwindigkeitLabel.setText(dieGeschwindigkeit.getValue()
					+ " km/h");
		}
    }

    public void keyReleased( KeyEvent e )
    {
        if ( e.getSource().equals( ortListe ) )
        {
            this.ortAusgewaehlt();
        }
        else if ( e.getSource().equals( strasseListe ) )
        {
            //this.strasseAusgewaehlt();
        }
    }

    public void keyTyped( KeyEvent arg0 )
    {
        // TODO Auto-generated method stub
    }


    public void valueChanged( ListSelectionEvent e )
    {

    }

}
