package Vista;

import java.awt.Color;

import javax.swing.JFrame;

import Controlador.Controlador;
import window.Win;

public class InterfazApp extends JFrame{

	Panelbotones pnBotones;
	Panelmovimiento pnMovimiento;
	Paneltriky pnTriky;
	
	public InterfazApp(Controlador ctrl) {		
		
		setTitle("Triky");
		setSize(750, 550);
		setResizable(false);
		setLayout(null);
		
		this.getContentPane().setBackground(new Color(239, 147, 27));
		
		pnMovimiento = new Panelmovimiento(ctrl);
		pnMovimiento.setBounds(480, 70, 220, 320);
		pnMovimiento.setBackground(new Color(174, 215, 216));
		getContentPane().add(pnMovimiento);
		
		pnTriky = new Paneltriky(ctrl);
		pnTriky.setBounds(30, 70, 400, 320);
		pnTriky.setBackground(new Color(255, 255, 255));
		getContentPane().add(pnTriky);
		
		pnBotones = new Panelbotones(ctrl);
		pnBotones.setBounds(30, 400, 670, 100);
		pnBotones.setBackground(new Color(51, 153, 255));
		getContentPane().add(pnBotones);
		
		ctrl.conectar(pnBotones, pnMovimiento, pnTriky);
		
		Win.centerWindow(this);
	}
	
	public static void main(String[] args) {
		
		InterfazApp inte= new InterfazApp(new Controlador());
		inte.setVisible(true);
	}
	
}
