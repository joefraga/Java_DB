package conectaDB;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Aplicacion_Consulta {

	public static void main(String[] args) {
		JFrame mimarco=new Marco_Aplicacion();		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mimarco.setVisible(true);
	}
}

class Marco_Aplicacion extends JFrame{
	private JComboBox secciones;
	private JComboBox<Color> colores;
	private JTextArea resultado;
	
	private Connection mi_conexion;	
	
	private PreparedStatement enviaConsultaSeccion;
	private final String query_seccion="SELECT " + 
			"DescArticulo, " +
			"DescColor, " + 
			"Seccion, " + 
			"Precio " + 
			"FROM articulos INNER JOIN colores " + 
			"ON articulos.IdColor = colores.IdColor " + 
			"WHERE Seccion= ?";
	
	private PreparedStatement enviaConsultaColor;
	private final String query_color="SELECT " + 
			"DescArticulo, " +
			"DescColor, " + 
			"Seccion, " + 
			"Precio " + 
			"FROM articulos INNER JOIN colores " + 
			"ON articulos.IdColor = colores.IdColor " + 
			"WHERE colores.IdColor= ?";
	
	private PreparedStatement enviaConsultaTodos;
	private final String query_todos="SELECT " + 
			"DescArticulo, " +
			"DescColor, " + 
			"Seccion, " + 
			"Precio " + 
			"FROM articulos INNER JOIN colores " + 
			"ON articulos.IdColor = colores.IdColor " +
			"WHERE Seccion=? AND colores.IdColor=?";  
			
	
	public Marco_Aplicacion(){
		
		setTitle ("Consulta BBDD");		
		setBounds(500,300,400,400);
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		menus.setLayout(new FlowLayout());
		
		secciones=new JComboBox();
		secciones.setEditable(false);		
		secciones.addItem("Todos");
		
		colores=new JComboBox();		
		colores.setEditable(false);
		//colores.addItem("Todos");
		colores.addItem(new Color(0, "Todos"));
		
		resultado= new JTextArea(4,50);		
		resultado.setEditable(false);		
		add(resultado);
		
		menus.add(secciones);		
		menus.add(colores);			
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");		
		botonConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ejecuta_consulta();				
			}			
		});
		add(botonConsulta, BorderLayout.SOUTH);
		
		try {
			//1.- CREAR CONEXION
			mi_conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb","fraga","adminfraga");
			
			//2.- CREAR STATEMENT PARA LLENAR LAS SECCIONES DE LOS ARTICULOS
			Statement mi_statement=mi_conexion.createStatement();
			String consulta="SELECT DISTINCTROW seccion FROM articulos";
			ResultSet rs=mi_statement.executeQuery(consulta);
			while(rs.next()) {
				secciones.addItem(rs.getString("seccion"));
			}
			rs.close();
						
			//lleno los colores
			Color c=new Color();  //creo un color vacio para mandar llamar el metodo que llena el combo
			c.llena_colores(mi_statement, colores); //mando llenar el combo
			
		}catch(Exception e) {
			System.out.println("Error de conexion");
			e.printStackTrace();
		}
	}	
	
	private void ejecuta_consulta() {
		ResultSet rs=null;
		resultado.setText(""); //limpio el textarea paa que entre limpia la informacion
		try {
			
			String seccion=(String)secciones.getSelectedItem();			 	 //obtengo la seccion seleccionada del combo
			int color=colores.getItemAt(colores.getSelectedIndex()).getId(); //obtengo el color seleccionado del combo
			System.out.println(seccion + " " + color);
			
			if(!seccion.equals("Todos") && color==0) {	//si solo escogio una seccion						
				enviaConsultaSeccion=mi_conexion.prepareStatement(query_seccion);
				enviaConsultaSeccion.setString(1, seccion);
				rs=enviaConsultaSeccion.executeQuery();
			}else if(seccion.equals("Todos") && color!=0) { //si solo escogio un color pero no una seccion
				enviaConsultaColor=mi_conexion.prepareStatement(query_color);				
				enviaConsultaColor.setInt(1, color);
				rs=enviaConsultaColor.executeQuery();
			}else if(!seccion.equals("Todos") && color!=0) {
				enviaConsultaTodos=mi_conexion.prepareStatement(query_todos);				
				enviaConsultaTodos.setString(1, seccion);
				enviaConsultaTodos.setInt(2, color);
				rs=enviaConsultaTodos.executeQuery();
			}
			
			//meto los registros al txt area del resultado
			while(rs.next()) {				
				resultado.append(rs.getString("DescArticulo") + " " + rs.getString("DescColor") + " " +
						 rs.getString("Seccion") + " " + rs.getDouble("Precio") + "\n" );
			}
			
		}catch(Exception e) {
			
		}
	}
}



class Color{
	private int id;
	private String desc_color;
	public Color() {
		
	}
	public Color(int id, String desc_color) {
		this.id=id;
		this.desc_color=desc_color;
	}
	
	public void llena_colores(Statement st, JComboBox<Color> cmb_colores) {
		try {				
			//Voy a la DB por los colores
			String consulta="SELECT IdColor, DescColor FROM colores";
			ResultSet rs=st.executeQuery(consulta);			
			while(rs.next()) {
				//Ingreso el de Todos
				cmb_colores.addItem(new Color(rs.getInt("IdColor"), rs.getString("DescColor")));
			}
			rs.close();
			 	
		}catch(Exception e) {
			System.out.println("Error al llenar los colores");
		}
		
	}
	
	public String toString() {  //por default lo que quiero mostrar en el combo
		return desc_color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc_color() {
		return desc_color;
	}

	public void setDesc_color(String desc_color) {
		this.desc_color = desc_color;
	}	
}
