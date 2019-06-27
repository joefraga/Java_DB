package transacciones;
import java.sql.*;

public class Inserta_Clientes_Pedidos {
	
	
	
	public static void main(String[] args) {
		
		Connection miConexion=null; 	
		
		try{					
			
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "fraga", "adminfraga");				
			miConexion.setAutoCommit(false);	//aqui es donde le determino que sera un bloque
			
			Statement miStatement =miConexion.createStatement();
			
		    String instruccionSql_1="INSERT INTO CLIENTES (CODIGOCLIENTE, EMPRESA, DIRECCION) VALUES ('CT84', 'EJEMPLO', 'P BOTANICO')";
		    miStatement.executeUpdate(instruccionSql_1);
		    String instruccionSql_2="INSERT INTO PEDIDOS (NUMERODEPEDIDO, CODIGOCLIENTE,FECHADEPEDIDO) VALUES('82', 'CT84', '11/03/2000')";			    
		    miStatement.executeUpdate(instruccionSql_2);
		    miConexion.commit();    //hasta aqui hago el commit de ambas sentencias
		    
		    System.out.println("Datos INSERTADOS correctamente");
				
		}catch(Exception e){
			System.out.println("ERROR EN LA INSERCIÓN DE DATOS!!");
			try {
				miConexion.rollback();   //hago el rollback si algo sale mal
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}

	}

}
