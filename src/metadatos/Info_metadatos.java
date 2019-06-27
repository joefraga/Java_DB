package metadatos;
import java.sql.*;


public class Info_metadatos {

	public static void main(String[] args) {
		//mostrar_info_DB();
		mostrar_info_Tablas();
		
	}
	
	public static void mostrar_info_DB() {
		Connection miConexion=null; 	
		
		try{					
			
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "fraga", "adminfraga");
			
			//obtener metadatos
			DatabaseMetaData db=miConexion.getMetaData();
			
			System.out.println("Administrador de DB: " + db.getDatabaseProductName());
			System.out.println("Version de DB: " + db.getDatabaseProductVersion());
			System.out.println("Controlador de DB: " + db.getDriverName());
			System.out.println("Version de controlador: " + db.getDriverVersion());
			
			
		}catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}finally {
			try {
				miConexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void mostrar_info_Tablas() {
		Connection miConexion=null; 	
		ResultSet rs=null;
		try{					
			
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "fraga", "adminfraga");
			
			//obtener metadatos
			DatabaseMetaData db=miConexion.getMetaData();
			rs=db.getTables("pruebas", null, null, null);
			
			//listar las tablas de la db pruebas
			System.out.println("Lista de Tablas");
			while(rs.next()) {
				System.out.println( rs.getString("TABLE_CAT") + " " + rs.getString("TABLE_NAME"));
			}
			
			//listar las columnas de la tabla productos de la db pruebas			
			System.out.println("\nLista de columnas de Productos");
			rs=db.getColumns(null, null, "clientes", null);
			while(rs.next()) {
				System.out.println(rs.getString("COLUMN_NAME"));
			}
			
			
		}catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}finally {
			try {
				miConexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
