package controlador;
import java.sql.*;
import modelo.*;
import java.util.ArrayList;

import controlador.*;
public class CargaSecciones {
	
	Conexion mi_conexion;
	ResultSet rs;
	public CargaSecciones() {
		mi_conexion=new Conexion();
	}
	
	public ArrayList<String> llenaSecciones() {
		ArrayList<String> lista_secciones=new ArrayList<String>();
		Connection accesoDB=mi_conexion.dameConexion();		
		try {
			Statement st_secciones=accesoDB.createStatement();
			rs=st_secciones.executeQuery("SELECT DISTINCTROW Seccion FROM articulos");
			while(rs.next()) {											
				lista_secciones.add(rs.getString("Seccion"));
			}
			rs.close();
			st_secciones.close();
			accesoDB.close();
		}catch(Exception e) {
			System.out.println("Error al buscar las secciones");
		}	
		return lista_secciones;
	}
}
