package conectaDB;
import java.sql.*;
public class ModificaDB {

	public static void main(String[] args) {
		try {
			//1.- CREAR CONEXION
			Connection mi_conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","fraga","adminfraga");
			
			//2.- CREAR STATEMENT PARA NGRESAR ARTICULO
			Statement mi_statement=mi_conexion.createStatement();
			/******iNSERTO **************/
			//String miquery="insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Abrigo',4,9000,0,STR_TO_DATE(REPLACE('20/01/2019','/','.'), GET_FORMAT(date,'EUR')))";
			
			/******ACTUALIZO **************/
			//String miquery="UPDATE ARTICULOS SET PRECIO=1800 WHERE IDARTICULO=13";
			
			/******ELIMINO **************/
			String miquery="DELETE FROM ARTICULOS WHERE IDARTICULO=13";
			mi_statement.executeUpdate(miquery);		
			System.out.println("Movimiento exitoso");
			
		}catch(Exception e) {
			System.out.println("Error de conexion");
			e.printStackTrace();
		}

	}

}
