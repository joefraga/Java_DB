package modelo;
import java.sql.*;
public class Conexion {
	
	Connection mi_conexion=null;
	
	public Conexion() {
		
	}
	
	public Connection dameConexion() {
		
		try {			
			mi_conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","fraga","adminfraga");						
		}catch(Exception e) {
			e.printStackTrace();
		}
		return mi_conexion;
	}
}
