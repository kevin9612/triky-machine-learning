package Vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controlador.Controlador;

public class Panelbotones extends JPanel implements ActionListener{

	private JButton newgame, resetdb;
	private JRadioButton learn;
	private Controlador ctrl;
	
	public Panelbotones(Controlador ctrl) {
		
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Opciones" ) ) );
	    setLayout(null);
		
	    newgame = new JButton("New game");
	    newgame.setBounds(50, 40, 130, 30);
	    newgame.addActionListener(this);
	    add(newgame);
	    
	    this.ctrl=ctrl;
	    
	    resetdb = new JButton("Reset D.B");
	    resetdb.setBounds(215, 40, 130, 30);
	    resetdb.addActionListener(this);
	    add(resetdb);
	    
	    learn = new JRadioButton("I want to learn", false);	 
	    learn.setBackground(new Color(51, 153, 255));
	    learn.addActionListener(this);
	    learn.setActionCommand("learn");
	    learn.setBounds(515, 40, 130, 30);
	    add(learn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String accion=e.getActionCommand();
		
		if (accion.equals("New game")) {
			
			ctrl.nuevoJuego();
			
		}
		
		if (accion.equals("Reset D.B")) {
			
			ctrl.borrarBaseDatos();
			
		}
		
		if (learn.isSelected()==true) {
			
			ctrl.Aprender(true);
			
			
		}
		
		if (learn.isSelected()==false) {
			
			ctrl.Aprender(false);
			
		}
		
	}
	
}
