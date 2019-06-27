package conectaDB;
import java.sql.*;

public class ConectaPrueba {

	public static void main(String[] args) {
		
		try {
			//1.- CREAR CONEXION
			Connection mi_conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","fraga","adminfraga");
			
			//2.- CREAR STATEMENT
			Statement mi_statement=mi_conexion.createStatement();
			
			//3.- CREAR EL RESULTSET
			//ResultSet mi_resultset=mi_statement.executeQuery("SELECT * FROM ARTICULOS");
			ResultSet mi_resultset=mi_statement.executeQuery("select " + 
					"			IdArticulo, " + 
					"		    DescArticulo," + 
					"		    DescColor," + 
					"		    Precio," +
					"		    a.Fecha" +
					"		from articulos a inner join colores c" + 
					"		on a.IdColor=c.IdColor where idArticulo<10");
			
			
			//4.- RECORRER EL RESULTSET
			while(mi_resultset.next()) {
				//System.out.println(mi_resultset.getString("IdArticulo") + ", " + mi_resultset.getString("DescArticulo") + ", " + mi_resultset.getString("Precio"));
				System.out.println(mi_resultset.getInt(1) + "|" + mi_resultset.getString(2) + "|" + mi_resultset.getString(3) +"|" + mi_resultset.getDouble(4) + "|" + mi_resultset.getDate(5));
			}
			
		}catch(Exception e) {
			System.out.println("Error de conexion");
			e.printStackTrace();
		}

	}

}
