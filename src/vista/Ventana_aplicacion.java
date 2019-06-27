package vista;

import java.awt.*;
import javax.swing.*;

import controlador.*;
import modelo.Color;

public class Ventana_aplicacion extends JFrame{
	public JComboBox cmb_secciones;
	public JComboBox<Color> cmb_colores;
	public JTextArea resultado;
	
	public Ventana_aplicacion() {
		setTitle ("Consulta BBDD");		
		setBounds(500,300,400,400);
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		menus.setLayout(new FlowLayout());
		
		cmb_secciones=new JComboBox<String>();
		cmb_secciones.setEditable(false);		
		
		
		cmb_colores=new JComboBox<Color>();		
		cmb_colores.setEditable(false);
		
		
		resultado= new JTextArea(4,50);		
		resultado.setEditable(false);		
		add(resultado);
		
		menus.add(cmb_secciones);		
		menus.add(cmb_colores);			
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		add(botonConsulta, BorderLayout.SOUTH);
		botonConsulta.addActionListener(new ControladorBotonConsulta(this));
		
		addWindowListener(new CargaCombos(this));  //me pongo en escucha para lanzar el llenado de combos
	}	
}
