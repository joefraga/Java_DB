package controlador;
import java.awt.event.*;
import vista.*;
import modelo.*;
import java.sql.*;

public class ControladorBotonConsulta implements ActionListener {
	private Ventana_aplicacion ventana;
	private ConsultaDB consulta=new ConsultaDB();
	private ResultSet rs=null;
	public ControladorBotonConsulta(Ventana_aplicacion marco) {
		ventana=marco;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String seccion=(String)ventana.cmb_secciones.getSelectedItem();
		int	color=ventana.cmb_colores.getItemAt(ventana.cmb_colores.getSelectedIndex()).getId();
		ventana.resultado.setText("");
		try {		
			rs=consulta.aplica_filtro(seccion, color);
			while(rs.next()) {
				ventana.resultado.append(rs.getString("DescArticulo") + " " + rs.getString("DescColor") + " " + 
						rs.getString("Seccion") + " " + rs.getDouble("Precio") + "\n");
			}	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

}
