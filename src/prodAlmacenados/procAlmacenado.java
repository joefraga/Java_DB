package prodAlmacenados;
import java.sql.*;

public class procAlmacenado {

	public static void main(String[] args) {
		try {
			Connection mi_conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","fraga","adminfraga");		
			CallableStatement mi_statement=mi_conexion.prepareCall("{call SPS_ARTICULOS}");
			ResultSet rs=mi_statement.executeQuery();			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getDouble(4));
			}
			
			
		}catch(Exception e) {
			
		}
	}

}
