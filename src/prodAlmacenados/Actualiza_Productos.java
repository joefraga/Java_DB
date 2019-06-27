package prodAlmacenados;
import java.sql.*;

import javax.swing.JOptionPane;

public class Actualiza_Productos {

	public static void main(String[] args) {
		
		int art=Integer.parseInt(JOptionPane.showInputDialog("Dame articulo: "));
		double precio=Double.parseDouble(JOptionPane.showInputDialog("Dame el precio: "));
		
		try {
			
			Connection mi_conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","fraga","adminfraga");		
			CallableStatement mi_statement=mi_conexion.prepareCall("{call SPU_ART_PRECIO(?,?)}");
			mi_statement.setInt(1, art);
			mi_statement.setDouble(2, precio);
			mi_statement.executeQuery();
			System.out.println("Actualizacion OK");
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
