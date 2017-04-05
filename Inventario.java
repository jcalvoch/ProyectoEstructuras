import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Inventario {

	List<ColaProductos> almacen = new ArrayList<>();
	List<ColaProductos> almacenTemp = new ArrayList<>();
	ListIterator<ColaProductos> iterator;
	ListIterator<ColaProductos> iter;
	ColaProductos estante;
	Producto producto, aux;
	int frente,fin;
	
	String dir = System.getProperty("user.dir");
	String nombreArchivo = "Inventario.txt";
	String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
	String menuInventario = "\n1.Ingresar producto\n"
			+ "2.Eliminar productos\n"
			+ "3.Buscar productos\n"
			+ "4.Ver todo el inventario\n"
			+ "5.Menu principal\n" 
			+ "6.Salir\n";
	String valor;
	String linea;
	String datos;
	String separadorLineas = System.getProperty("line.separator");
	
	Scanner in = new Scanner(System.in);
	
	File archivo;
	FileWriter fw ;
	BufferedWriter bw;
	
	FileReader fr;
	BufferedReader br;
	
	
	public void inicio() {
		
		int opcion;
		
		try{
			this.almacen = cargarTxt();
			
			System.out.println(String.format("%30s", "Inventario"));
			System.out.println(menuInventario);
			valor = in.nextLine();
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					this.ingresarProducto();
					
					continuar();
					break;
				case 2:
					this.eliminarProducto();
					
					continuar();
					break;
				case 3:
					this.buscarProductos();
					
					continuar();
					break;
				case 4:
					this.verInventario();
					
					break;
				case 5:
					break;
									
				case 6:
					System.exit(0);
					
				
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ingresarProducto(){
		try{
			
			estante = new ColaProductos();
			producto = new Producto();
						
			
			System.out.println("Digite el codigo del articulo: ");
			valor = in.nextLine();
			
			
			
			if (buscarEstante(valor)){
				
				System.out.println("Codigo encontrado.....\n");
				
				estante = this.almacen.get(obtenerNumeracionEstante(valor));//obtiene el estante(cola de productos) que ya existe para agregar uno mas de este tipo
				
				producto.setCodigo(valor);
				
				System.out.println("Digite el nombre del articulo: ");
				producto.setNombre(in.nextLine());
				
				System.out.println("Digite el tipo del articulo: ");
				producto.setTipo(in.nextLine());
					
				System.out.println("Digite el precio del articulo: ");
				producto.setPrecio(in.nextDouble());
				
				estante.Insertar(producto);
				
				this.almacen.set(obtenerNumeracionEstante(valor),estante);
				guardarTxt();
				
				
			}else{
				
				producto.setCodigo(valor);
				System.out.println("Nuevo codigo ingresado, creando nuevo codigo en la base de datos.....\n");
				
				System.out.println("Digite el nombre del articulo: ");
				producto.setNombre(in.nextLine());
				
				System.out.println("Digite el tipo de articulo: ");
				producto.setTipo(in.nextLine());
					
				System.out.println("Digite el precio del articulo: ");
				producto.setPrecio(in.nextDouble());
				
				estante.Insertar(producto); //crea un nuevo estante para este tipo de articulo y agrega el articulo
				
				this.almacen.add(estante); //Guarda el estante(cola) en el almacen(arraylist de colas)
				
				guardarTxt();
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
			
	}
	
	public void eliminarProducto(){
		producto = new Producto();
		
		try{
			this.almacen = cargarTxt();
			System.out.println("Digite el codigo o el nombre del articulo: ");
			datos = in.nextLine();
		
			if (buscarProducto(datos)){
				producto = this.almacen.get(obtenerNumeracionEstante(datos)).Eliminar();
				actualizarTxt();
				System.out.println(producto.getNombre()+" eliminado exitosamente"+". Cantidad total del articulos: "+obtenerEstante(datos).cantidadProducto());
				
			}else{
				System.out.println("El articulo no existe, o tiene un codigo/nombre diferente");
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void buscarProductos(){
		
		estante = new ColaProductos();
		
		try{
			System.out.println("Ingrese el codigo o la descripcion del producto: ");
			datos = in.nextLine();
			iterator = this.almacen.listIterator();
			while(iterator.hasNext()){
				estante = iterator.next();
				producto = estante.FrenteCola();
				frente = estante.frente;
				fin = estante.fin;
				while(frente<=fin){
					if((producto.getNombre().toLowerCase().contains(datos.toLowerCase()) || producto.getCodigo().toLowerCase().contains(datos.toLowerCase()))){
						System.out.println("Nombre: "+producto.getNombre()+", Codigo: "+producto.getCodigo()+", Precio:"+producto.getPrecio()+", Cantidad: "+estante.cantidadProducto());
					}
					
					frente++;
				}
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
		
	public boolean buscarEstante(String data){
		 aux = new Producto();
		 iterator = this.almacen.listIterator();
		try{
			while(iterator.hasNext()){
				aux = iterator.next().FrenteCola();
				return (aux.getCodigo().toLowerCase().equals(data.toLowerCase()));
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return false;
		
	}

	public boolean buscarProducto(String data){
		 aux = new Producto();
		 iterator = this.almacen.listIterator();
		try{
			while(iterator.hasNext()){
				aux = iterator.next().FrenteCola();
				return (aux.getNombre().toLowerCase().equals(data.toLowerCase()) || aux.getCodigo().toLowerCase().equals(data.toLowerCase()));
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return false;
		
	}
	
	public int obtenerNumeracionEstante(String data){
		 producto = new Producto();
		 estante = new ColaProductos();
		 iterator = this.almacen.listIterator();
		 
		try{
			while(iterator.hasNext()){
				estante = iterator.next();
				 
				if (estante.FrenteCola().getNombre().toLowerCase().equals(data.toLowerCase()) || estante.FrenteCola().getCodigo().toLowerCase().equals(data.toLowerCase())){
					return this.almacen.indexOf(estante);
				}
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return this.almacen.indexOf(estante);
		
	}
	
	public ColaProductos obtenerEstante(String data){
		 producto = new Producto();
		 estante = new ColaProductos();
		 iterator = this.almacen.listIterator();
		 
		try{
			while(iterator.hasNext()){
				estante = iterator.next();
				 
				if (estante.FrenteCola().getCodigo().toLowerCase().equals(data.toLowerCase())){
					return estante;
				}
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	
	public void guardarTxt() throws Exception{
		
		try{
			
			archivo = new File(dir,nombreArchivo);
			estante = new ColaProductos();
			iter = this.almacen.listIterator();
			
			if (!archivo.exists()){
				
				archivo.createNewFile();
				
				fw = new FileWriter(archivo,false);
				bw = new BufferedWriter(fw);
				
				while(iter.hasNext()){//recorre todos los estantes
					estante = iter.next();
					frente = estante.frente;
					fin = estante.fin;
					while(frente<=fin){//recorre todos los productos dentro de los estantes
						linea = estante.siguienteCola(frente).getNombre()+"-"+estante.siguienteCola(frente).getCodigo()+"-"+estante.siguienteCola(frente).getTipo()+"-"+estante.siguienteCola(frente).getPrecio()+separadorLineas;
						bw.write(linea);
						bw.flush();
						frente++;
					}
					
				}
						
				System.out.println("commit bueno");
				
			}else{
				
				actualizarTxt();				
			}
			
			
		}catch(IOException e){
			
			System.out.println(e.toString());
			
		
		}finally{ 
			   
			try{
			
				if(bw!=null)
				bw.close();
			
			}catch(Exception ex){
			
				System.out.println("Error cerrando el BufferedWriter - "+ex.toString());
			    
			}
		}
		
	}
	
	public void actualizarTxt() throws Exception{
		
		try{
			archivo = new File(dir,nombreArchivo);
			estante = new ColaProductos();
			
			iter = this.almacen.listIterator();
				
			archivo.delete();
			archivo.createNewFile();
			fw = new FileWriter(archivo,false);
			bw = new BufferedWriter(fw);
				
			while(iter.hasNext()){
				estante = iter.next();
				frente = estante.frente;
				fin = estante.fin;
				while(frente<=fin){
					linea = estante.siguienteCola(frente).getNombre()+"-"+estante.siguienteCola(frente).getCodigo()+"-"+estante.siguienteCola(frente).getTipo()+"-"+estante.siguienteCola(frente).getPrecio()+separadorLineas;
					bw.write(linea);
					bw.flush();
					frente++;
				}
			}
				
			System.out.println("commit bueno");
				
			
			
			
		}catch(IOException e){
			
			System.out.println(e.toString());
			
		
		}finally{ 
			   
			try{
			
				if(bw!=null)
				bw.close();
			
			}catch(Exception ex){
			
				System.out.println("Error cerrando el BufferedWriter - "+ex.toString());
			    
			}
		}
		
	}
		
	
	public List<ColaProductos> cargarTxt() throws Exception{
		archivo = new File(dir,nombreArchivo);
		almacenTemp = new ArrayList<>();
		String codigo;
		try{
			if(archivo.exists()){
				
				
				
				
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				if ((linea = br.readLine())!= null && (!linea.isEmpty())){
				
					
					while(linea!= null){
						
						producto = new Producto();
						codigo = producto.getCodigo();
						String [] valores = linea.split("-");
						producto.setNombre(valores[0]);
						producto.setCodigo(valores[1]);
						producto.setTipo(valores[2]);
						producto.setPrecio(Double.parseDouble(valores[3]));
						estante = new ColaProductos();
						estante.Insertar(producto);
						
						linea = br.readLine();
						
						if(producto.getCodigo().equals(codigo)){
							this.almacen.get(obtenerNumeracionEstante(codigo)).Insertar(producto);
							
						}else{
							this.almacen.add(estante);
						}
					}
				}else{
					return almacenTemp;
				}
				
								
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally{ 
			   
			try{
			
				if(br!=null)
				br.close();
			
			}catch(Exception ex){
			
				System.out.println("Error cerrando el BufferedReader - "+ex.toString());
			    
			}
		}
		return this.almacen;
		
	}

	public List<ColaProductos> actualizarAlmacen(List<ColaProductos> almT, List<ColaProductos> almP){
		
		iter = almP.listIterator();
		try{
			while(iter.hasNext()){
				estante = iter.next();
				almT.add(estante);
				}
		}catch(Exception e){
			
			System.out.println(e.toString());
			
		}
		return almT;
	}
	
	public void verInventario(){
		estante = new ColaProductos();
		iterator = this.almacen.listIterator();
		
		try {
			this.almacen = cargarTxt();
			
			while(iterator.hasNext()){
				estante = iterator.next();
				producto = estante.FrenteCola();
				
				System.out.println("Nombre: "+producto.getNombre()+", Codigo: "+producto.getCodigo()+", Precio:"+producto.getPrecio()+", Cantidad: "+estante.cantidadProducto());
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	

	public boolean opcionValida(String in){
		return (!specialChars.contains(in) || in.matches("[0-9]+"));
	}

	public boolean validarLetra(String in){
		return (in.matches(".*[a-zA-Z]+.*"));
	}
	
	public boolean validarNumero(String in){
		return (in.matches("[0-9]+"));
	}
	
	public void continuar() throws IOException {
		String op;
		in = new Scanner(System.in);
		System.out.println("Digite 1 para continuar o 0 para salir");
		op = in.nextLine();
		if(validarNumero(op)){
			if(op.equals(0)){
				System.exit(0);
			}else{
				inicio();
			}
		}else{
			while(!op.equals(0) || !op.equals(1)){
				System.out.println("Valor invalido. Porfavor digite el  1 o 0");
				op = in.nextLine();
			}
		}
		
		op = in.nextLine();
		if(System.in.available()==0){
			System.exit(0);
		}else{
			inicio();
		}
		
	}

}
