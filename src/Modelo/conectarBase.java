package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectarBase {

	private Connection conection;
	
	public conectarBase() {
		// TODO Auto-generated constructor stub
	}
	

	public void Conectar(){
		

		try {
			
			System.out.println("Enlazando a la base de datos.....");
			Class.forName("com.mysql.jdbc.Driver");
			
			conection= DriverManager.getConnection("jdbc:mysql://127.0.0.1/triky","root","");
			System.out.println("Conexion exitosa");
		} catch (SQLException e) {

			System.out.println("Error al conectar");
			
		}
		
		catch (Exception e) {
			System.out.println("se ha encontrado un error el cual es "+e.getMessage());
		}
		
	}


	public Connection getConection() {
		return conection;
	}


	public void setConection(Connection conection) {
		this.conection = conection;
	}
		
	
	
}
