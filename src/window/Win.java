package window;



import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JApplet;

public class Win
{
    /**
     * Centra una ventana en la pantalla
     * @param ventana La ventana que se va a centrar
     */
    public static void centerWindow( JFrame ventana )
    {
    	//System.out.println("Centrar una ventana en la pantalla.");
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }
   
    public static void centerWindow( JDialog ventana )
    {
    	//System.out.println("Centrar una ventana en la pantalla.");
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }

    public static void centerWindow( JApplet ventana )
    {
    	//System.out.println("Centrar una ventana en la pantalla.");
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }
    
}    