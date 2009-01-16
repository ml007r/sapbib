package BIB_GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * 
 * @author Andreas Zoike Fenster zum anzeigen von Fehlermeldungen.
 * 
 */
public class InfoFenster extends JFrame implements ActionListener, WindowListener
{

    /**
     * 
     */
    private static final long serialVersionUID = 738710676801658892L;

    private JLabel Symbol = new JLabel( "X" );;

    private JTextArea Infotext = new JTextArea();

    private JButton buttonOk = new JButton( "Ok" );

    public InfoFenster( String text )
    {
        setTitle( "Fehler" );
        setLayout( null );
        setSize( 400, 250 );
        setBackground( Color.LIGHT_GRAY );
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation( ( d.width - this.getWidth() ) / 2, ( d.height - this.getHeight() ) / 2 );

        // Zeilenumbr�che hinzuf�gen
        int i = 45;
        while ( i < text.length() )
        {
            String temp = text.substring( i, text.length() );
            int tmp = temp.indexOf( ' ' );
            temp = text.substring( i + tmp, text.length() );
            text = text.substring( 0, i + tmp );
            text += "\n" + temp;
            i += 46;
        }

        Infotext.setBounds( 75, 50, 310, 90 );
        Infotext.setEnabled( false );
        Infotext.setText( text );
        add( Infotext );

        Symbol.setBounds( 10, 50, 60, 90 );
        Symbol.setForeground( Color.BLACK );
        Symbol.setBackground( Color.RED );
        Symbol.setFont( new Font( "Dialog", Font.BOLD, 90 ) );
        add( Symbol );

        buttonOk.setBounds( 175, 165, 50, 20 );
        buttonOk.addActionListener( this );
        add( buttonOk );

        addWindowListener( this );
        setVisible( true );
    }

    public void windowClosing( WindowEvent arg0 )
    {
        dispose();
    }

    public void actionPerformed( ActionEvent arg0 )
    {
        dispose();
    }

    public void windowActivated( WindowEvent arg0 )
    {
    }

    public void windowClosed( WindowEvent arg0 )
    {
    }

    public void windowDeactivated( WindowEvent arg0 )
    {
    }

    public void windowDeiconified( WindowEvent arg0 )
    {
    }

    public void windowIconified( WindowEvent arg0 )
    {
    }

    public void windowOpened( WindowEvent arg0 )
    {
    }

}
