package principal;

import javax.swing.JFrame;
import vista.*;

public class Ejecuta_Modelo_Vista_Controlador {

	public static void main(String[] args) {
		Ventana_aplicacion miventana=new Ventana_aplicacion();		
		miventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		miventana.setVisible(true);
	}

}
