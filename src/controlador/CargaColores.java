package controlador;
import modelo.*;
import java.sql.*;
import java.util.HashMap;

public class CargaColores {
	Conexion mi_conexion;
	Statement st;
	public CargaColores() {
		mi_conexion=new Conexion();
	}
	
	public HashMap<Integer,String> llena_colores() {
		HashMap<Integer,String> lista_colores=new HashMap<Integer,String>();
		Connection con_db=mi_conexion.dameConexion();
		try {				
			//Voy a la DB por los colores		
			st=con_db.createStatement();
			ResultSet rs=st.executeQuery("SELECT IdColor, DescColor FROM colores");			
			while(rs.next()) {
				lista_colores.put(rs.getInt("IdColor"), rs.getString("DescColor"));
			}
			rs.close();
			 	
		}catch(Exception e) {
			System.out.println("Error al llenar los colores");
		}
		return lista_colores;
	}
}
