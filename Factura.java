import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {

	Items [] facturables;
	double subtotal;
	double impuesto;
	double descuento;
	double total;
	LocalDate fecha;
	String nombre;
	int numero;
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Factura(){
		facturables = new Items[10];
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate localDate) {
		this.fecha = localDate;
	}

	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(double impuesto) {
		this.impuesto = impuesto;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
