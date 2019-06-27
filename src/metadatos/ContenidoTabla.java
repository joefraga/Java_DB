package metadatos;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ContenidoTabla {

	public static void main(String[] args) {
		MarcoBBDD mimarco=new MarcoBBDD();	
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		mimarco.setVisible(true);
	}

}


class MarcoBBDD extends JFrame{

	public MarcoBBDD(){
		
		setBounds(300,300,700,700);	
		LaminaBBDD milamina=new LaminaBBDD();		
		add(milamina);
				
	}	
	
}

class LaminaBBDD extends JPanel{
	private JComboBox comboTablas;
	private JTextArea areaInformacion;
	Connection miConexion; 	
	ResultSet rs;
	private FileReader archivo_config;
	
	public LaminaBBDD(){
		
		setLayout(new BorderLayout());	
		comboTablas=new JComboBox();		
		areaInformacion=new JTextArea();
		add(areaInformacion,BorderLayout.CENTER);		
		add(comboTablas, BorderLayout.NORTH);
		ConectaDB();
		llena_combo_tablas();
		comboTablas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				llena_contenido_tabla((String)comboTablas.getSelectedItem());							
			}	});
	}
	
	public void ConectaDB() {
		miConexion=null;
		String basedatos, usuario, password;
		
		try {
			//LEO EL ARCHIVO TEXTO
			archivo_config=new FileReader("src/metadatos/db_config.txt");
		}catch(IOException e1) {
			
			JFileChooser seleccionador=new JFileChooser();  //Creo un sleector de archivos
			FileNameExtensionFilter filtro=new FileNameExtensionFilter("Archivos Texto", "txt");
			seleccionador.setFileFilter(filtro);
			int valor=seleccionador.showOpenDialog(this);
			if(valor==JFileChooser.APPROVE_OPTION) {
				try {
					archivo_config=new FileReader(seleccionador.getSelectedFile().getAbsolutePath());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
			
		try {
			BufferedReader buffer=new BufferedReader(archivo_config);
			basedatos=buffer.readLine();
			usuario=buffer.readLine();
			password=buffer.readLine();

			//HAGO LA CONEXION CON LOS DATOS LEIDOS DEL ARCHIVO DE CONFIGURACION
			miConexion=DriverManager.getConnection(basedatos, usuario, password);
			
			buffer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void llena_combo_tablas() {
		rs=null;
		try {
			DatabaseMetaData db=miConexion.getMetaData();
			rs=db.getTables("pruebas", null, null, null);
			while(rs.next()) {
				comboTablas.addItem(rs.getString("TABLE_NAME"));
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al llenar combo de tablas");
			e.printStackTrace();			
		}
	}
	
	public void llena_contenido_tabla(String tabla) {
		rs=null;
		ArrayList<String> lista_campos=new ArrayList<String>();
		String consulta="SELECT * FROM " + tabla;
		Statement st=null;
		areaInformacion.setText("");
		try {
			st=miConexion.createStatement();
			rs=st.executeQuery(consulta);
			
			//saco los nombres de las columnas de la tabla
			ResultSetMetaData rsmt=rs.getMetaData();
			for(int i=1; i<rsmt.getColumnCount(); i++) {  //aqui me devuelve los campos de la tabla
				lista_campos.add(rsmt.getColumnLabel(i));
			}
			
			//recorro el Resultset de los registros para irlos ingresando
			while(rs.next()) {
				for(String campo:lista_campos) {
					areaInformacion.append(rs.getString(campo) + " ");
				}
				areaInformacion.append("\n");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al llenar contenido de tabla");
			e.printStackTrace();
		}
		
	}
		
}

