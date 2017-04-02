
public class ColaProductos {
	
	public static int tamCola = 100;
	public int frente, fin;
	public Producto [] listaCola;
	
	public ColaProductos(){
		frente = 0;
		fin = -1;
		listaCola = new Producto[tamCola];
		
	}
	
	public void Insertar(Producto elemento) throws Exception{
		if(!ColaLlena()){
			listaCola[++fin] = elemento;
		}
		else{
			throw new Exception("Cola llena");
		}
		
	}
	
	public Producto Eliminar() throws Exception{
		Producto aux;
		if(!ColaVacia()){
			aux = listaCola[frente];
			frente++;
			return aux;
		}else{
			throw new Exception("Cola Vacia");
		}
	}
	
	public Producto FrenteCola() throws Exception{
		if (!ColaVacia()){
			return listaCola[frente];
		}else{
			throw new Exception("Cola Vacia");
		}
	}
	
	public Producto siguienteCola(int pos) throws Exception{
		if (!ColaVacia()){
			return listaCola[pos];
		}else{
			throw new Exception("Cola Vacia");
		}
	}
	
	public void BorrarCola(){
		frente = 0;
		fin = -1;
	}
	
	public boolean ColaLlena(){
		return fin == tamCola-1;
	}
	
	public boolean ColaVacia(){
		return frente > fin;
	}
	
	public int cantidadProducto(){
		int f = fin;
		if(!ColaVacia()){
			return f+1;
		
		}else{
			return 0;
		}
	}
	
	
}
