package controlador;
import java.awt.event.*;
import java.util.*;
import modelo.*;
import vista.Ventana_aplicacion;

public class CargaCombos extends WindowAdapter {
	private Ventana_aplicacion ventana;
	
	
	public CargaCombos(Ventana_aplicacion marco) {   //constructor
		ventana=marco;
	}
	
	public void windowOpened(WindowEvent e) { //sobreescribo el metodo al abrir la ventana
		ventana.cmb_secciones.addItem("Todos");		
		ArrayList<String> lista_secciones=new CargaSecciones().llenaSecciones();		
		for(String s:lista_secciones) {
			ventana.cmb_secciones.addItem(s);
		}
		
		Color c_todos=new Color(0,"Todos");
		ventana.cmb_colores.addItem(c_todos);
		HashMap<Integer,String> lista_colores=new CargaColores().llena_colores();
		for(Map.Entry<Integer, String>entrada:lista_colores.entrySet()) { //defino recorrer la coleccion como entry set
			Color c=new Color((int)entrada.getKey(),entrada.getValue());
			ventana.cmb_colores.addItem(c);
		}		
	}
}
