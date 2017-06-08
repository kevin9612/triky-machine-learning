package Vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Controlador.Controlador;

public class Panelmovimiento extends JPanel implements ActionListener{

	private JRadioButton random, firstblank;
	private JLabel titulo, movimientos;
	private ButtonGroup radiogroup;
	private String mov="";
	private Controlador ctrl;
	
	public Panelmovimiento(Controlador ctrl) {
		
		setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "Movimientos" ) ) );
	    setLayout(null);
	    
	    titulo = new JLabel("Hello, triky world..");
	    titulo.setBounds(40, 30, 130, 30);
	    add(titulo);
	    
	    movimientos = new JLabel("");
	    movimientos.setBounds(60, 210, 180, 30);
	    add(movimientos);
		
	    random = new JRadioButton("random", true);
	    random.addActionListener(this);
	    random.setActionCommand("random");
	    random.setBackground(new Color(174, 215, 216));
	    random.setBounds(40, 80, 130, 30);
	    add(random);
	    
	    this.ctrl=ctrl;
	    
	    firstblank = new JRadioButton("firstblank");
	    firstblank.addActionListener(this);
	    firstblank.setActionCommand("firstblank");
	    firstblank.setBackground(new Color(174, 215, 216));
	    firstblank.setBounds(40, 110, 130, 30);
	    add(firstblank);
	    
	    radiogroup = new ButtonGroup();
	    radiogroup.add(random);
	    radiogroup.add(firstblank);
	}

	public void setText(String text){
		
		mov+=""+text;
		movimientos.setText(mov);
	}
	
	public void limpiarLabel() {
	
		mov="";
		movimientos.setText("");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (random.isSelected()==true) {
			
			firstblank.setSelected(false);
			ctrl.tipoMovimiento(true);
		}
		
		if (firstblank.isSelected()==true) {
			
			random.setSelected(false);
			ctrl.tipoMovimiento(false);
			System.out.println("primer blanco");
			
		}
		
		
	}

	
	
}
