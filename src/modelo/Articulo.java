package modelo;

public class Articulo {

	private int Id;
	private String desc_articulo;
	private String desc_color;
	private String seccion;
	private double precio;
	
	public Articulo(int id, String desc, String descColor, String sec, double precio) {
		this.Id=id;
		this.desc_articulo=desc;
		this.desc_color=descColor;
		this.seccion=sec;
		this.precio=precio;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDesc_articulo() {
		return desc_articulo;
	}

	public void setDesc_articulo(String desc_articulo) {
		this.desc_articulo = desc_articulo;
	}

	public String getDesc_color() {
		return desc_color;
	}

	public void setDesc_color(String desc_color) {
		this.desc_color = desc_color;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
