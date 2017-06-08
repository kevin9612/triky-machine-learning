package Vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controlador.Controlador;
import window.Win;

public class InterfazFinJuego extends JFrame implements ActionListener{

	private JLabel ganador;
	private JButton newgame;
	private Controlador ctrl;
	
	public InterfazFinJuego(Controlador controlador) {
		
		setTitle("Ganador");
		setSize(260, 180);
		setResizable(false);
		setLayout(null);

		ctrl = controlador;
		
		ganador = new JLabel("");
		ganador.setBounds(70,10,200,100);
		getContentPane().add(ganador);
		
		newgame = new JButton("NUEVO JUEGO");
		newgame.addActionListener(this);
		newgame.setBounds(57, 80, 150, 30);
		getContentPane().add(newgame);
		
		getContentPane().setBackground(new Color(164, 14, 14));
		
		Win.centerWindow(this);
	
	}
	
	public void setText(String text){
		
		ganador.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String accion=e.getActionCommand();
		
		if (accion.equals("NUEVO JUEGO")) {
			
			System.out.println("entre");
			ctrl.nuevoJuego();
			this.dispose();
		}
		
	}
	
}
