
public class Producto {

	public String nombre;
	public String codigo;
	public String tipo;
	public double precio;
	public double peso;

	
	public Producto(){}
	
	public Producto(String nomb, String cod, String tpo, double pre, double pes){

		nombre = nomb;
		codigo = cod;
		tipo = tpo;
		precio = pre;
		peso= pes;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
