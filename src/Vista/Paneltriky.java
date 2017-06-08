


package Vista;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controlador.Controlador;

public class Paneltriky extends JPanel{

	private JLabel [][] triky;
	private Controlador ctrl;

	
	public Paneltriky(Controlador ctrl) {
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Triky" ) ) );
		setLayout(new GridLayout(3, 3));
		
		triky = new JLabel[3][3];
		
		this.ctrl=ctrl;
		
		for (int i = 0; i < triky.length; i++) {
			for (int j = 0; j < triky.length; j++) {
			
				triky[i][j]=new JLabel();
				triky[i][j].setEnabled(true);
				triky[i][j].setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "" ) ) );
		    	triky[i][j].setHorizontalAlignment(JLabel.CENTER);
		    	triky[i][j].setVerticalAlignment(JLabel.CENTER);
		    	triky[i][j].setFont( new Font("myFont", Font.BOLD, 13) );
		    	triky[i][j].addMouseListener(new LabelClicMouse(i, j, triky[i][j], this.ctrl));
				add(triky[i][j]);
			}
			
		}
	}
	
	public void bloquearMatriz(){
		
		for (int i = 0; i < triky.length; i++) {
			for (int j = 0; j < triky.length; j++) {
				
				triky[i][j].setEnabled(false);
			}
		}
		
	}
	
	public void limpiarMatriz(){
		
		for (int i = 0; i < triky.length; i++) {
			for (int j = 0; j < triky.length; j++) {
				
				triky[i][j].setEnabled(true);
				triky[i][j].setIcon(null);
				triky[i][j].addMouseListener(new LabelClicMouse(i, j, triky[i][j], this.ctrl));
			}
		}
	}
	
	 public void pintar(int x, int y){
	    	
	 triky[x][y].setIcon(new ImageIcon("src/imagenes/Circulo.png"));	
	    	
	 }
	
	static class LabelClicMouse extends MouseAdapter 
	{// Constantes  
	    private static final String OOOOO = "src/imagenes/Circulo.png";
	    private static final String EQUIS = "src/imagenes/equis.png";
	  
	 // Atributos de clase
	    private static boolean swClic;
	    
	 // Atributos de instancia   
	    private JLabel label;
	    private Controlador ctrl;
	    private int x, y;
	    private ImageIcon imgOoooo, imgEquis;
	    
	 // Constructor   
	    public LabelClicMouse( int x, int y, JLabel label, Controlador ctrl )
	    { this.label = label;
	      this.ctrl = ctrl;
	      this.x = x; this.y = y;
	      this.swClic = false;
	      this.imgOoooo = new ImageIcon( OOOOO );
	      this.imgEquis = new ImageIcon( EQUIS );
	    } 
	   
	   
	    public void mouseClicked(MouseEvent evento)
	    {
	         if ( evento.isMetaDown() )  // boton derecho del raton - pone X / O
		      {    if ((label.getText()).equals( "" ) && label.getIcon() == null && !swClic )
		           {    label.setIcon( imgEquis ); swClic = true;
		           }
		           else
		           if (label.getIcon() != null && label.getIcon().equals(imgEquis))
		           {   label.setIcon( null ); swClic = false;
		           }      
		      }
		      else
		      if ( evento.isAltDown() )  // boton medio del raton
		      {
		      }
		      else
		      if ( evento.isControlDown() ) // Control + boton izquierdo
		      {    
		      }
		      else	         
		      if ( (label.getText()).equals( "" ) && label.getIcon() != null && label.getIcon().equals(imgEquis) ) // boton izquierdo del raton
		      {     label.removeMouseListener( label.getMouseListeners()[0] );  
		         
		      ctrl.jugarhumano(x, y); ctrl.jugarMaquina(); swClic = false;
		      
		      }
	      
	    }
	}
}
