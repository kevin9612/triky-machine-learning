package Controlador;


import java.sql.SQLException;

import Modelo.Triky;
import Modelo.conectarBase;
import Vista.InterfazFinJuego;
import Vista.Panelbotones;
import Vista.Panelmovimiento;
import Vista.Paneltriky;

public class Controlador {

	private Panelbotones pnBotones;
	private Panelmovimiento pnMovimiento;
	private Paneltriky pnTriky;
	private Triky triky;
	private conectarBase sql;
	private InterfazFinJuego fin;
	private boolean estado=false, aprender, mov=true;
	
	public Controlador() {
		
		triky= new Triky();
		sql= new conectarBase();
		fin = new InterfazFinJuego(this);
		sql.Conectar();
		triky.accesoBaseDatos(sql.getConection());
	}

	public void conectar(Panelbotones pnBotones, Panelmovimiento pnMovimiento, Paneltriky pnTriky) {
		
		this.pnBotones=pnBotones;
		this.pnMovimiento=pnMovimiento;
		this.pnTriky=pnTriky;
		
	}
	
	
	public void jugarMaquina() {
				
		if (!estado) {
			
			if (triky.verificarMovimientos()) {
				
				pnTriky.bloquearMatriz();
				fin.setText("EMPATE");
				fin.setVisible(true);
			}else{
	
			if (aprender) {
				
				if (mov) {
					
					triky.movimientoMaquina(true, "random");
					pnMovimiento.setText(triky.getMov());
					pnTriky.pintar(triky.getMaqx(), triky.getMaqy());
					
				}else{
					
					triky.movimientoMaquina(true, "blanco");
					pnMovimiento.setText(triky.getMov());
					pnTriky.pintar(triky.getMaqx(), triky.getMaqy());
				}		
				
			}else {
				
				if (mov) {
				
					triky.movimientoMaquina(false, "random");
					pnMovimiento.setText(triky.getMov());
					pnTriky.pintar(triky.getMaqx(), triky.getMaqy());
				}else{
					
					triky.movimientoMaquina(false, "blanco");
					pnMovimiento.setText(triky.getMov());
					pnTriky.pintar(triky.getMaqx(), triky.getMaqy());
				}
				
				
				
			}	
							
		if (triky.verificarMaquina()) {			
			
			if (aprender) {
				
				try {
					triky.EncontrarEstadoaGuardar(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			pnTriky.bloquearMatriz();
			fin.setText("GANADOR MAQUINA");
			fin.setVisible(true);
			
			
		}
			}
		}
	}

	public void jugarhumano(int x, int y) {		

		triky.movimientoHumano(x, y);
		pnMovimiento.setText(triky.getMov());
				
		if (triky.verificarHumano()) {
			
			if (aprender) {
				
				try {
					triky.EncontrarEstadoaGuardar(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			pnTriky.bloquearMatriz();
			fin.setText("GANADOR HUMANO");
			fin.setVisible(true);
			estado=true;
		}
		
	}
	
	public void Aprender(Boolean estado){
		
		this.aprender=estado;
		
	}
	
	public void tipoMovimiento(Boolean estado){
		
		this.mov=estado;
		
	}
	
	
	public void borrarBaseDatos(){
		
		try {
			triky.borrarBaseDatos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void nuevoJuego(){
		
		estado=false;
		pnTriky.limpiarMatriz();
		triky.armarMatriz();
		pnMovimiento.limpiarLabel();
		
	}
	
	
	
	}
