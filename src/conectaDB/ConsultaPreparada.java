package conectaDB;

import java.sql.*;

public class ConsultaPreparada {

	public static void main(String[] args) {
		try {
			//1.- CREAR CONEXION
			Connection mi_conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","fraga","adminfraga");
			
			//2.- CREAR STATEMENT
			PreparedStatement miquery=mi_conexion.prepareStatement("select IdArticulo, DescArticulo, Precio from articulos " +
							"where IdArticulo< ?  and IdColor= ?");
			
			//3.-ESTABLECER PARAMETROS DE CONSULTA
			miquery.setInt(1, 10);  //establezco valor de primer parametro articulo<10
			miquery.setInt(2, 3);  //establezco valor de primer parametro color=3
						
			//4.- RECORRER EL RESULTSET
			ResultSet mi_resultset=miquery.executeQuery();
			while(mi_resultset.next()) {
				//System.out.println(mi_resultset.getString("IdArticulo") + ", " + mi_resultset.getString("DescArticulo") + ", " + mi_resultset.getString("Precio"));
				System.out.println(mi_resultset.getInt(1) + "|" + mi_resultset.getString(2) + "|" + mi_resultset.getDouble(3));
			}
			mi_resultset.close();
			
		}catch(Exception e) {
			System.out.println("Error de conexion");
			e.printStackTrace();
		}
	}

}
