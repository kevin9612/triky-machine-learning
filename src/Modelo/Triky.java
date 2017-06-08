
package Modelo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Statement;

public class Triky implements Serializable{

	private int [][] matriztriky;
	private int maqx=0, maqy=0,cont, movimientos, prueba;
	private String lugar="", coordenadas="", estadoencontrado, mov;
	private Boolean estadohori;
	private String estadoprueba, tipo, estado;
	private java.sql.PreparedStatement preInsertar;
	private java.sql.Statement consultar;
	private HashMap<Integer, ArrayList<String>> guardarestados;
	private ArrayList<String> estados, estadosbasep, estadobaseg;
	private Connection connection;
	
	public Triky() {
		
		
		matriztriky = new int[3][3];		
		estado="";
		armarMatriz();
	}
	
	public void armarMatriz(){
		
		guardarestados = new HashMap<>();		
		movimientos=0;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				matriztriky[i][j]=0;
				
			}
		}
		
	}
	
	public void movimientoHumano(int x, int y){			
		
		
		matriztriky[x][y]=1;
		tipo="humano";	
		mov="H";
		coordenadas=""+x+","+y;
		concatenarEstado();
	}
	
	public boolean verificarAtaqueVertical(){		
		
		cont=0;
		estadohori=false;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {				
						
					if (matriztriky[j][i]==2) {
						
						cont++;
					
					}
					
					if (cont==2) {						
						
						maqy=i;
						maqx=j;
						
						if (maqx==2) {
							
							if (matriztriky[j-1][i]==0) {
								
								cont=0;
								lugar="mitad";
								estadohori=true;
								break;
								
							}else{
								
								if (matriztriky[j-2][i]==0) {
									
									cont=0;
									lugar="atras";
									estadohori=true;
									break;
									
									
								}
								
							}
							
						}else{
							
							if (maqx==1) {
								
								if (matriztriky[j+1][i]==0) {
									
									cont=0;
									lugar="adelante";
									estadohori=true;
									break;
									
								}
								
							}
							
						}
						
						break;
					}
					
					
						
					}
			
				if (estadohori) {
					
					break;
					
				}
				
				cont=0;
				}
				
		
			if (estadohori) {
				
				return true;
				
			}
			
		return false;
		
		
	}
	
	public boolean verificarAtaqueHorizontal(){
		
		cont=0;
		estadohori=false;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
			
				if (matriztriky[i][j]==2) {
					
					cont++;
					
				}			
				
				if (cont==2) {
					
					maqx=i;
					maqy=j;
					
					if (maqy==2) {
					
						if (matriztriky[i][j-1]==0) {
							
							cont=0;
							lugar="mitad";
							estadohori=true;
							break;
							
						}else{
							
							if (matriztriky[i][j-2]==0) {
								
								cont=0;
								lugar="atras";
								estadohori=true;
								break;
								
								
							}
							
						}
						
					}else{
						if (maqy==1) {
							
							if (matriztriky[i][j+1]==0) {

								
								cont=0;
								lugar="adelante";
								estadohori=true;
								break;
								
							}
							
						}
						
					}
					
					break;
				}
				
			}
			
			if (estadohori) {
				
				break;
				
			}
			
			cont=0;
		}
		
		if (estadohori) {
			
			return true;
			
		}
		
		return false;
	}
	
	public boolean verificarAtaqueDiagonales(){
		
		cont=0;
		int iterador=0;
		estadohori=false;
		
		
		for (int i = 0; i < matriztriky.length; i++) {
			
			if (matriztriky[i][i]==2) {
			
				cont++;
				
			}
			
			if (cont==2) {
				
				maqx=i;
				maqy=i;
				
				if (maqy==2) {
					
					if (matriztriky[i-1][i-1]==0) {
						
						cont=0;
						lugar="mitad";
						estadohori=true;
						break;
						
					}
					
					if (matriztriky[i-2][i-2]==0) {
						
						cont=0;
						lugar="atras";
						estadohori=true;
						break;
						
					}
					
				}else{
					
					if (maqy==1) {
						
						if (matriztriky[i+1][i+1]==0) {
							
							cont=0;
							lugar="adelante";
							estadohori=true;
							break;
							
							
						}
						
					}
					
				}
				
			}
			
		}
		
		if (estadohori) {
			
			return true;			
			
		}
		
		cont=0;
		
			for (int j2 = matriztriky.length-1; j2 >=0; j2--) {
				
				if (matriztriky[iterador][j2]==2) {
					
					cont++;
					
				}
				
				if (cont==2) {
					
					maqx=iterador;
					maqy=j2;
					
					if (maqy==0) {
						
						if (matriztriky[iterador-1][j2+1]==0) {
							
							cont=0;
							lugar="mitad2";
							estadohori=true;
							break;
							
						}
						
						if (matriztriky[iterador-2][j2+2]==0) {
							
							cont=0;
							lugar="atras2";
							estadohori=true;
							break;
							
						}
						
					}else{
						
						if (maqy==1) {
							
							if (matriztriky[iterador+1][maqy-1]==0) {
								
								cont=0;
								lugar="adelante2";
								estadohori=true;
								break;
								
							}
							
						}
					}
					
				}
				
				iterador++;
			}
		
			if (estadohori) {
				
				return true;			
				
			}
		
		return false;
		
	}
	
	public boolean verificarDiagonales(){
		
		cont=0;
		int iterador=0;
		estadohori=false;
		
		
		for (int i = 0; i < matriztriky.length; i++) {
			
			if (matriztriky[i][i]==1) {
			
				cont++;
				
			}
			
			if (cont==2) {
				
				maqx=i;
				maqy=i;
				
				if (maqy==2) {
					
					if (matriztriky[i-1][i-1]==0) {
						
						cont=0;
						lugar="mitad";
						estadohori=true;
						break;
						
					}
					
					if (matriztriky[i-2][i-2]==0) {
						
						cont=0;
						lugar="atras";
						estadohori=true;
						break;
						
					}
					
				}else{
					
					if (maqy==1) {
						
						if (matriztriky[i+1][i+1]==0) {
							
							cont=0;
							lugar="adelante";
							estadohori=true;
							break;
							
							
						}
						
					}
					
				}
				
			}
			
		}
		
		if (estadohori) {
			
			return true;			
			
		}
		
		cont=0;
		
			for (int j2 = matriztriky.length-1; j2 >=0; j2--) {
				
				if (matriztriky[iterador][j2]==1) {
					
					cont++;
					
				}
				
				if (cont==2) {
					
					maqx=iterador;
					maqy=j2;
					
					if (maqy==0) {
						
						if (matriztriky[iterador-1][j2+1]==0) {
							
							cont=0;
							lugar="mitad2";
							estadohori=true;
							break;
							
						}
						
						if (matriztriky[iterador-2][j2+2]==0) {
							
							cont=0;
							lugar="atras2";
							estadohori=true;
							break;
							
						}
						
					}else{
						
						if (maqy==1) {
							
							if (matriztriky[iterador+1][maqy-1]==0) {
								
								cont=0;
								lugar="adelante2";
								estadohori=true;
								break;
								
							}
							
						}
					}
					
				}
				
				iterador++;
			}
		
			if (estadohori) {
				
				return true;			
				
			}
		
		return false;
		
	}
	
	public boolean verificarVertical(){
		
		cont=0;
		estadohori=false;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
						
					if (matriztriky[j][i]==1) {
						
						cont++;
						
					}
					
					if (cont==2) {						
						
						maqy=i;
						maqx=j;
						
						if (maqx==2) {
							
							if (matriztriky[j-1][i]==0) {
								
								cont=0;
								lugar="mitad";
								estadohori=true;
								break;
								
							}else{
								
								if (matriztriky[j-2][i]==0) {
									
									cont=0;
									lugar="atras";
									estadohori=true;
									break;
									
									
								}
								
							}
							
						}else{
							
							if (maqx==1) {
								
								if (matriztriky[j+1][i]==0) {
									
									cont=0;
									lugar="adelante";
									estadohori=true;
									break;
									
								}
								
							}
							
						}
						
						break;
					}
					
					
						
					}
			
				if (estadohori) {
					
					break;
					
				}
				
				cont=0;
				}
				
		
			if (estadohori) {
				
				return true;
				
			}
			
		return false;
		
	}
	
	public boolean verificarHorizontal(){
		
		cont=0;
		estadohori=false;
		
		//Recorre la matriz horizontalmente
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				//Si encuentra un uno en la matriz que es la jugada del humano entonces aumenta el contador, si encuentra dos significa que tenemos xx- lo que es
				//la maquina
				
				if (matriztriky[i][j]==1) {
					
					cont++;
					
				}			
				
				if (cont==2) {
					
					//guardo las coordenadas del ultimo es decir x(x)-->este 
					
					maqx=i;
					maqy=j;
					
					if (maqy==2) {
						//Si es dos significa que esta en la ultima posicion de la matriz asi que el espacio donde debe jugar la maquina puede ser en la mitad x_x
						//o atras _xx
						if (matriztriky[i][j-1]==0) {
							
							cont=0;
							lugar="mitad";
							estadohori=true;
							break;
							
						}else{
							
							if (matriztriky[i][j-2]==0) {
								
								cont=0;
								lugar="atras";
								estadohori=true;
								break;
								
								
							}
							
						}
						
					}else{
						
						//Si esta en 1 esta en la mitad entonces hay que jugar adelante xx_
						
						if (maqy==1) {
							
							if (matriztriky[i][j+1]==0) {
								
								cont=0;
								lugar="adelante";
								estadohori=true;
								break;
								
							}
							
						}
						
					}
					
					break;
				}
				
			}
			
			if (estadohori) {
				
				break;
				
			}
			
			cont=0;
		}
		
		if (estadohori) {
			
			return true;
			
		}
		
		return false;
	}
	
	public void movimientoMaquina(boolean consultar, String movimiento){
		
		tipo="";
		mov="";
					
		if (verificarAtaqueVertical()) {		
			
			tipo="ataque";
			mov="A";
			System.out.println("ataque vertical");
			
			if (lugar.equals("atras")) {
				
				maqx=maqx-2;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("mitad")) {
				
				maqx--;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("adelante")) {
			
				maqx++;
				movimiento(maqx, maqy);
			}		
			
		}else{
		
		if (verificarAtaqueHorizontal()) {
			
			tipo="ataque";
			mov="A";
			
			System.out.println("horizontal ataque");
			
			if (lugar.equals("atras")) {
				
				maqy=maqy-2;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("mitad")) {
				
				maqy--;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("adelante")) {
			
				maqy++;
				movimiento(maqx, maqy);
			}
			
		}	
		else{
			
		if (verificarAtaqueDiagonales()) {
			
			tipo="ataque";
			mov="A";
			
			System.out.println("Diagonal ataque");
			
			if (lugar.equals("atras")) {
				
				maqx=maqx-2;
				maqy=maqy-2;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("mitad")) {
				
				maqx--;
				maqy--;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("adelante")) {
			
				maqx++;
				maqy++;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("atras2")) {
			
				maqx=maqx-2;
				maqy=maqy+2;
				movimiento(maqx, maqy);
				
			}
			
			if (lugar.equals("mitad2")) {
				
				maqx--;
				maqy++;
				movimiento(maqx, maqy);
				
			}
			
			if (lugar.equals("adelante2")) {
				
				maqx++;
				maqy--;
				movimiento(maqx, maqy);
				
			}
			
		
			
		}else{
		
		if (verificarVertical()) {
			
			tipo="defensa";
			mov="D";
			
			System.out.println("vertical");
			
			if (lugar.equals("atras")) {
				
				maqx=maqx-2;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("mitad")) {
				
				maqx--;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("adelante")) {
			
				maqx++;
				movimiento(maqx, maqy);
			}
			
			
		}else{
		
		if (verificarHorizontal()) {
			
			tipo="defensa";
			mov="D";
			
			System.out.println("horizontal");
			
			if (lugar.equals("atras")) {
				
				maqy=maqy-2;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("mitad")) {
				
				maqy--;
				movimiento(maqx, maqy);
			}
			
			if (lugar.equals("adelante")) {
			
				maqy++;
				movimiento(maqx, maqy);
			}
			
		}else {
			
			if (verificarDiagonales()) {
				
				tipo="defensa";
				mov="D";
				System.out.println("Diagonal");
				
				if (lugar.equals("atras")) {
					
					maqx=maqx-2;
					maqy=maqy-2;
					movimiento(maqx, maqy);
				}
				
				if (lugar.equals("mitad")) {
					
					maqx--;
					maqy--;
					movimiento(maqx, maqy);
				}
				
				if (lugar.equals("adelante")) {
				
					maqx++;
					maqy++;
					movimiento(maqx, maqy);
				}
				
				if (lugar.equals("atras2")) {
				
					maqx=maqx-2;
					maqy=maqy+2;
					movimiento(maqx, maqy);
					
				}
				
				if (lugar.equals("mitad2")) {
					
					maqx--;
					maqy++;
					movimiento(maqx, maqy);
					
				}
				
				if (lugar.equals("adelante2")) {
					
					maqx++;
					maqy--;
					movimiento(maqx, maqy);
					
				}
				
			}else{
			
			
			
			if (consultar) {
				
				if (movimiento.equalsIgnoreCase("random")) {					

					mov="K";
					System.out.println("random base");
					movimientoRandomBase();
				}
				
				if (movimiento.equalsIgnoreCase("blanco")) {
				
					mov="K";
					System.out.println("random base");
					movimientoPrimerBlancoBase();
				}
				
			}else{
				
				if (movimiento.equalsIgnoreCase("random")) {					

					mov="R";
					System.out.println("random");
					movimientoRandom();
				}
				
				if (movimiento.equalsIgnoreCase("blanco")) {
					
					mov="B";
					System.out.println("primer blanco");
					movimientoPrimerBlanco();
				}
				
			}
		
			}
		}
		
		}
		}
		
		}
		
		}
		
		
	}
	
	public boolean verificarHumano(){
		
		int cont=0;
		int iterador=0;	
		boolean estado=false;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				if (matriztriky[i][j]==1) {
					
					cont++;
					
				}
				
				if (cont==3) {
					
					break;
				}
				
				if (j==2) {
					
					cont=0;
				}
				
			}
			
			if (cont==3) {
				
				estado=true;
				break;
			}
		}
		
		if (estado) {
		
			return true;
			
			
		}
		
		cont=0;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				if (matriztriky[j][i]==1) {
					
					cont++;
					
				}
				
				if (cont==3) {
					
					break;
					
				}
				
				if (j==2) {
					
					cont=0;
				}
			}
			
			if (cont==3) {
				
				estado=true;
				break;
				
			}
		}
			
		if (estado) {
			
			return true;
			
			
		}	
		
		cont=0;
		
		for (int i = 0; i < matriztriky.length; i++) {
			
			if (matriztriky[i][i]==1) {
				
				cont++;
			}
			
			if (cont==3) {
				estado=true;
				break;
			}
			
		}
		
		if (estado) {
			
			return true;
		}
		
		cont=0;
		
		for (int i = matriztriky.length-1; i>=0 ; i--) {
			
			if (matriztriky[iterador][i]==1) {
				
				cont++;
				
			}
			
			iterador++;
			
			if (cont==3) {
				
				estado=true;
				break;
			}
		}
		
		if (estado) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public boolean verificarMaquina(){
		

		int cont=0;
		int iterador=0;	
		boolean estado=false;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				if (matriztriky[i][j]==2) {
					
					cont++;					
				}
				
				if (cont==3) {
					
					break;
				}
				
				if (j==2) {
					
					cont=0;
				}
				
			}
			
			if (cont==3) {
				
				estado=true;
				break;
			}
		}
		
		if (estado) {
		
			return true;
			
			
		}
		
		cont=0;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				if (matriztriky[j][i]==2) {
					
					cont++;
					
				}
				
				if (cont==3) {
					
					break;
					
				}
				
				if (j==2) {
					
					cont=0;
				}
			}
			
			if (cont==3) {
				
				estado=true;
				break;
				
			}
		}
			
		if (estado) {
			
			return true;
			
			
		}	
		
		cont=0;
		
		for (int i = 0; i < matriztriky.length; i++) {
			
			if (matriztriky[i][i]==2) {
				
				cont++;
			}
			
			if (cont==3) {
				estado=true;
				break;
			}
			
		}
		
		if (estado) {
			
			return true;
		}
		
		cont=0;
		
		for (int i = matriztriky.length-1; i>=0 ; i--) {
			
			if (matriztriky[iterador][i]==2) {
				
				cont++;
				
			}
			
			iterador++;
			
			if (cont==3) {
				
				estado=true;
				break;
			}
		}
		
		if (estado) {
			
			return true;
			
		}
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				System.out.println(matriztriky[i][j]);
			}
		}
		
		return false;
		
	}
	
	
	public void movimiento(int i, int j){
		
		
		this.coordenadas=""+i+","+j;
		matriztriky[i][j]=2;
		concatenarEstado();
		
	}
	
	public Boolean verificarGanadorMaquina(){
		
		String temp="";
		Boolean comp=false;	
		
		temp=obtenerEstado(matriztriky);
		
		for (int i = 0; i < estadobaseg.size(); i++) {
			
				if (temp.equalsIgnoreCase(estadobaseg.get(i))) {
				estadoencontrado=estadobaseg.get(i);	
				comp=true;
				break;
			}
		}
		
		if (comp) {
			
			return true;
		}
		
		return false;
	}
	
	public void movimientoPrimerBlancoBase(){		
		
			
		boolean estado=false;
		ResultSet resultados;
		String coordenadas="";
		String[] coordenadasSeparadas;
		consultarEstados();
	
		
		if (verificarGanadorMaquina()) {			
			
			try {
				consultar=connection.createStatement();
				resultados=consultar.executeQuery("SELECT Posicion FROM estados WHERE Estados ='"+estadoencontrado+"'");	
				
				while (resultados.next()) {
					
					coordenadas=resultados.getString(1);
				}
				
				coordenadasSeparadas = coordenadas.split(",");
					maqx = Integer.parseInt(coordenadasSeparadas[0]);
					maqy = Integer.parseInt(coordenadasSeparadas[1]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else{
		
		while (!estado) {
			
			boolean comp=true, comp2=false;
			String temp;
			
			
			int[][] aux = Serialization.copy(matriztriky);
	
			for (int i2 = 0; i2 < aux.length; i2++) {
				for (int j = 0; j < aux.length; j++) {
					
					aux=Serialization.copy(matriztriky);
					
					if (matriztriky[i2][j]==0) {
						
						comp2=true;
						
						maqx=i2;
						maqy=j;
						
						aux[maqx][maqy]=2;
						temp=obtenerEstado(aux);
						consultarEstados();
						
						for (int i = 0; i < estadosbasep.size(); i++) {
							
							if (temp.equalsIgnoreCase(estadosbasep.get(i))) {
								
								System.out.println("repetido");
								comp=false;
								break;
							}else{
								
								comp=true;
							}
						}
						
						if (comp) {
							
							estado=true;
							break;
							
						}					
					}	
					
				}
				
				if (comp2 && comp) {
					
					break;
				}
				
			}
			
			}}
		
		tipo="azar";
		matriztriky[maqx][maqy]=2;
		this.coordenadas=""+maqx+","+maqy;	
		concatenarEstado();
	
	}
	
	public void movimientoRandomBase(){		

		boolean estado=false;
		ResultSet resultados;
		String coordenadas="";
		String[] coordenadasSeparadas;
		consultarEstados();
		
		if (verificarGanadorMaquina()) {			
			
			try {
				consultar=connection.createStatement();
				resultados=consultar.executeQuery("SELECT Posicion FROM estados WHERE Estados ='"+estadoencontrado+"'");	
				
				while (resultados.next()) {
					
					coordenadas=resultados.getString(1);
				}
				
				coordenadasSeparadas = coordenadas.split(",");
					maqx = Integer.parseInt(coordenadasSeparadas[0]);
					maqy = Integer.parseInt(coordenadasSeparadas[1]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else{
		
		while (!estado) {
			
			boolean comp=true;
			String temp;
			
			
			int[][] aux = Serialization.copy(matriztriky);
			
				maqx=(int) (Math.random()*3);
				maqy=(int) (Math.random()*3);	
			
				
			
			if (matriztriky[maqx][maqy]==0) {
				
				aux[maqx][maqy]=2;
				temp=obtenerEstado(aux);
				consultarEstados();
				
				for (int i = 0; i < estadosbasep.size(); i++) {
					
					if (temp.equalsIgnoreCase(estadosbasep.get(i))) {
						
						System.out.println("repetido");
						comp=false;
					}
				}
				
				if (comp) {
					
					estado=true;
				}
				
				
			}	}}
		
		tipo="azar";
		matriztriky[maqx][maqy]=2;
		this.coordenadas=""+maqx+","+maqy;	
		concatenarEstado();
	
	}
	
	public String getMov() {
		return mov;
	}

	public void setMov(String mov) {
		this.mov = mov;
	}

	public void movimientoRandom(){
		
		boolean estado=false;
		
		while (!estado) {
			
			tipo="azar";
			
			maqx=(int) (Math.random()*3);
			maqy=(int) (Math.random()*3);		
			
			if (matriztriky[maqx][maqy]==0) {
				
				estado=true;
							
			}else{
				System.out.println("recursividad");
				
			}
			
		}
		
		matriztriky[maqx][maqy]=2;
		coordenadas=""+maqx+","+maqy;
		concatenarEstado();
		
	}
	
	public void movimientoPrimerBlanco(){		
		
		boolean estado=false;
		
		while (!estado) {
			
			
			
			for (int i = 0; i < matriztriky.length; i++) {
				for (int j = 0; j < matriztriky.length; j++) {
					
					if (matriztriky[i][j]==0) {
						
						maqx=i;
						maqy=j;
						estado=true;
						break;
					}
					
				}
				
				if (estado) {
					
					break;
				}
			}
			
			
		}
		
		tipo="azar";
		matriztriky[maqx][maqy]=2;
		coordenadas=""+maqx+","+maqy;
		concatenarEstado();
		
		
	}
	
	public void accesoBaseDatos(Connection connection){
		
		this.connection=connection;
	}
	
	public void borrarBaseDatos() throws SQLException{
	
		preInsertar = connection.prepareStatement("DELETE FROM estados");
		
		preInsertar.executeUpdate();
	
	}
	
	public void consultarEstados(){
		
		estadosbasep = new ArrayList<>();
		estadobaseg = new ArrayList<>();
		
		ResultSet resultados;
		
		try {
			consultar=connection.createStatement();
			resultados=consultar.executeQuery("SELECT Estados from estados WHERE Evaluacion=2");
			
			while (resultados.next()) {
				
				estadosbasep.add(resultados.getString(1));
			}
			
			resultados=consultar.executeQuery("SELECT Estados from estados WHERE Evaluacion=1");
			
			while (resultados.next()) {
				
				estadobaseg.add(resultados.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
	public String obtenerEstado(int [][] matriz){
		
		estadoprueba="";
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				
				estadoprueba+=""+matriz[i][j];
				
			}
		}
		
		return estadoprueba;
	}
	
	public void concatenarEstado(){
		
		estado="";
		estados = new ArrayList<>();
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				estado+=""+matriztriky[i][j];
				
			}
		}
		
		estados.add(estado);
		estados.add(tipo);
		estados.add(coordenadas);
		guardarestados.put(guardarestados.size()+1, estados);
		
		
	}
	
	public boolean verificarMovimientos(){
		
		int cont=0;
		
		for (int i = 0; i < matriztriky.length; i++) {
			for (int j = 0; j < matriztriky.length; j++) {
				
				if (matriztriky[i][j]!=0) {
					
					cont++;
				}
			}
		}
		
		System.out.println(cont);
		
		if (cont>=9) {
			
			return true;
		}
		
		return false;
	}
	
	
	public void EncontrarEstadoaGuardar(Boolean estado) throws SQLException{

		int cont=0;
		ArrayList<String> temphumano, tempmaquina;
		
		if (estado) {
		
				for (int i = guardarestados.size(); i >=1; i--) {
					
					tempmaquina=guardarestados.get(i);
					
					if (verificarGanador(tempmaquina.get(0))) {
						
						if (tempmaquina.get(1).equalsIgnoreCase("azar")) {				
							temphumano=guardarestados.get(i-1);
							guardarEstado(tempmaquina, temphumano, estado);
							break;
							
						}
						
					}
					
					
				}		
						
		}else{
			

			ArrayList<String> temp;
			
			for (int i = guardarestados.size(); i >=1; i--) {
				
				temp=guardarestados.get(i);
				
				if (temp.get(1).equalsIgnoreCase("azar")) {				
					
					guardarEstado(temp, null, estado);
					break;
					
				}
				
			}
			
			
		}
		
	}
	
	public void guardarEstado(ArrayList<String> tempmaquina, ArrayList<String> temphumano, Boolean estado) throws SQLException{
		
		boolean estadogana=false, estadopierde=false;
		
		if (estado) {
			//gana
			
			for (int i = 0; i < estadobaseg.size(); i++) {
				
				if (estadobaseg.get(i).equals(temphumano.get(0))) {
					
					estadogana=true;
				}
				
			}			
			
			if (!estadogana) {				
			

				preInsertar = connection.prepareStatement("INSERT INTO estados (Estados, Posicion, Evaluacion)"+" VALUES(?,?,?)");
				preInsertar.setString(1,temphumano.get(0));
				preInsertar.setString(2,tempmaquina.get(2));			
				preInsertar.setString(3, "1");
				
				preInsertar.executeUpdate();
				
			}
			
		}else{
			//pierde
			
			for (int i = 0; i < estadosbasep.size(); i++) {
				
				if (estadosbasep.get(i).equals(tempmaquina.get(0))) {
						
					estadopierde=true;
				}
					
			}	
			
			if (!estadopierde) {
				

				preInsertar = connection.prepareStatement("INSERT INTO estados (Estados, Posicion, Evaluacion)"+" VALUES(?,?,?)");
				preInsertar.setString(1,tempmaquina.get(0));
				preInsertar.setString(2,tempmaquina.get(2));
				preInsertar.setString(3, "2");
				
				preInsertar.executeUpdate();
			}
			
		}
				
		
	}
	
	private Boolean verificarGanador(String estado) {
		
		int iterator=0, iterator2=0, cont=0, oportunidades=0;
		String var = "";
		int [][] aux = new int[3][3];
		estadohori=false;
		
		for (int i = 0; i < estado.length(); i++) {
			
			var=""+estado.charAt(i);
			aux[iterator2][iterator]=Integer.parseInt(var);
					
			iterator++;
			
			if (iterator==3){
				iterator2++;
				iterator=0;
			}
			
			
		}
		
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux.length; j++) {
				
				if (aux[i][j]==2) {
					
					cont++;
					
				}
				
				if (cont==2) {
				
					if (j==2) {
						
					
					if (aux[i][j-1]==0) {
						
						cont=0;
						estadohori=true;
						break;
						
					}else{
						
						if (aux[i][j-2]==0) {
							
							cont=0;
							estadohori=true;
							break;
							
							
						}
					
				}
				
					}else{
						
						if (j==1) {
							
							if (matriztriky[i][j+1]==0) {
								
								cont=0;						
								estadohori=true;
								break;
								
							}
						}
						
						
					}
					
					break;
				}
			}
			
			if (estadohori) {
				
				break;
			}			
			
			cont=0;
		}
		
		if (estadohori) {
			
			oportunidades++;
		}
		
		estadohori=false;
		cont=0;
		
		for (int i2 = 0; i2 < aux.length; i2++) {
			for (int j2 = 0; j2 < aux.length; j2++) {
				
				
				if (aux[j2][i2]==2) {
					
					cont++;
				
				}
				
				if (cont==2) {				
					
					if (j2==2) {
						
						if (aux[j2-1][i2]==0) {
							
							cont=0;
							estadohori=true;
							break;
							
						}else{
							
							if (aux[j2-2][i2]==0) {
								
								cont=0;
								estadohori=true;
								break;
								
								
							}
							
						}
						
					}else{
						
						if (j2==1) {
							
							if (aux[j2+1][i2]==0) {
								
								cont=0;
								estadohori=true;
								break;
								
							}
							
						}
						
					}
					
					break;
				}
				
				
			} 
			
			if (estadohori) {
				
				break;
			}
			
			cont=0;
			
		}
		
		if (estadohori) {
			
			oportunidades++;
		}
		
		estadohori=false;
		cont=0;
		iterator=0;
		iterator2=0;
		
		for (int i = 0; i < aux.length; i++) {
			
			if (aux[i][i]==2) {
			
				cont++;
				
			}
			
			if (cont==2) {
							
				if (i==2) {
					
					if (aux[i-1][i-1]==0) {
						
						cont=0;						
						estadohori=true;
						break;
						
					}
					
					if (aux[i-2][i-2]==0) {
						
						cont=0;						
						estadohori=true;
						break;
						
					}
					
				}else{
					
					if (i==1) {
						
						if (aux[i+1][i+1]==0) {
							
							cont=0;						
							estadohori=true;
							break;
							
							
						}
						
					}
					
				}
				
			}
			
		}
		
		if (estadohori==true) {
			
			oportunidades++;			
		}
		
		cont=0;
		
		for (int j2 = aux.length-1; j2 >=0; j2--) {
			
			if (aux[iterator][j2]==2) {
				
				cont++;
				
			}
			
			if (cont==2) {		
				
				if (j2==0) {
					
					if (aux[iterator-1][j2+1]==0) {
						
						cont=0;
						estadohori=true;
						break;
						
					}
					
					if (aux[iterator-2][j2+2]==0) {
						
						cont=0;
						estadohori=true;
						break;
						
					}
					
				}else{
					
					if (j2==1) {
						
						if (aux[iterator+1][j2-1]==0) {
							
							cont=0;					
							estadohori=true;
							break;
							
						}
						
					}
				}
				
			}
			
			iterator++;
		}
		
		if (estadohori) {
			
			oportunidades++;
		}
		
		if (oportunidades==2) {
			
			return true;
		}
		
		return false;
	
	}
	
	
	public int getMaqx() {
		return maqx;
	}

	public void setMaqx(int maqx) {
		this.maqx = maqx;
	}

	public int getMaqy() {
		return maqy;
	}

	public void setMaqy(int maqy) {
		this.maqy = maqy;
	}

	
	
}


