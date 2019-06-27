package modelo;

public class Color {	
	private int id;
	private String desc_color;
	
	public Color() {
			
	}
	public Color(int id, String desc_color) {
		this.id=id;
		this.desc_color=desc_color;
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
