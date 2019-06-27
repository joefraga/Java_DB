package modelo;
import java.sql.*;
public class ConsultaDB {
	private Conexion mi_conexion;
	private Statement st;
	private ResultSet rs;	
	
	public ConsultaDB(){
		mi_conexion=new Conexion();
	}
	
	public ResultSet aplica_filtro(String seccion, int color) {
		Connection con=mi_conexion.dameConexion();  //1.- creo conexion
		String query="";
		try {
			st=con.createStatement();					//2.- creo statement

			
			if(!seccion.equals("Todos") && color==0) {	//si solo escogio una seccion						
				query="SELECT DescArticulo, DescColor, Seccion, Precio " +  
							"FROM articulos INNER JOIN colores ON articulos.IdColor = colores.IdColor " + 
							"WHERE Seccion='" + seccion + "'";
			}else if(seccion.equals("Todos") && color!=0) { //si solo escogio un color pero no una seccion
				query="SELECT DescArticulo, DescColor, Seccion, Precio " +  
						"FROM articulos INNER JOIN colores ON articulos.IdColor = colores.IdColor " + 
						"WHERE colores.IdColor=" + color;		
			}else if(!seccion.equals("Todos") && color!=0) {
				query="SELECT DescArticulo, DescColor, Seccion, Precio " +  
						"FROM articulos INNER JOIN colores ON articulos.IdColor = colores.IdColor " + 
						"WHERE Seccion='" + seccion + "' AND colores.IdColor=" + color;  			
			}
			System.out.println(query);
			rs=st.executeQuery(query);			
			return rs;
						
		}catch(Exception e) {
			System.out.println("Error en la consulta");
		}		
		return null;
	}
}
