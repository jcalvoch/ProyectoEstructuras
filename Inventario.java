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
			+ "4.Menu principal\n" 
			+ "5.Salir\n";
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
			almacen = cargarTxt();
			
			System.out.println(String.format("%30s", "Inventario"));
			System.out.println(menuInventario);
			valor = in.nextLine();
			while (!validarEntrada(valor)){
				inicio();
			} 
			
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					this.ingresarProducto();
					
					inicio();
					break;
				case 2:
					this.eliminarProducto();
					
					inicio();
					break;
				case 3:
					this.buscarProductos();
					
					inicio();
					break;
				case 4:
					//menu principal
					
					break;
				case 5:
					System.exit(0);
				
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ingresarProducto(){
		try{
			almacen = new ArrayList<ColaProductos>(); 
			producto = new Producto();
			estante = new ColaProductos();
			
			
			System.out.println("Digite el tipo de articulo: ");
			valor = in.nextLine();
			
			
			
			if (buscarEstante(valor)){
				
				producto.setTipo(valor);
				
				estante = obtenerEstante(producto.getTipo());//obtiene el estante(cola de productos) que ya existe para agregar uno mas de este tipo
				
				System.out.println("Digite la descripcion del articulo: ");
				producto.setNombre(in.nextLine());
				
				System.out.println("Digite el codigo del articulo: ");
				producto.setCodigo(in.nextLine());
					
				System.out.println("Digite el precio del articulo: ");
				producto.setPrecio(in.nextDouble());
				
				estante.Insertar(producto);
				this.almacen.add(estante);
				guardarTxt();
				
				
			}else{
				
				producto.setTipo(valor);
				
				System.out.println("Digite la descripcion del articulo: ");
				producto.setNombre(in.nextLine());
				
				System.out.println("Digite el codigo del articulo: ");
				producto.setCodigo(in.nextLine());
					
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
			System.out.println("Digite el codigo o la descripcion del articulo: ");
			datos = in.nextLine();
		
			if (buscarProducto(datos)){
				producto = this.almacen.get(obtenerNumeracionEstante(datos)).Eliminar();
				actualizarTxt();
				System.out.println(producto.getNombre()+" eliminado exitosamente");
				
			}else{
				System.out.println("El articulo no existe");
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
				frente = estante.frente;
				fin = estante.fin;
				while(frente<=fin){
					if((producto.getNombre().contains(datos) || producto.getCodigo().contains(datos))){
						System.out.println("Descripcion: "+producto.getNombre()+", Codigo: "+producto.getCodigo()+", Precio:"+producto.getPrecio()+", Cantidad: "+estante.cantidadProducto()+"\n");
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
				return (aux.getTipo().equals(data));
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
				return (aux.getNombre().equals(data) || aux.getCodigo().equals(data));
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
				 
				if (estante.FrenteCola().getNombre().equals(data) || estante.FrenteCola().getCodigo().equals(data)){
					return this.almacen.indexOf(estante);
				}
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return -1;
		
	}
	
	public ColaProductos obtenerEstante(String data){
		 producto = new Producto();
		 estante = new ColaProductos();
		 iterator = this.almacen.listIterator();
		 
		try{
			while(iterator.hasNext()){
				estante = iterator.next();
				 
				if (estante.FrenteCola().getTipo().equals(data)){
					return estante;
				}
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return null;
		
	}
	
	public boolean validarEntrada(String in){
		return (!specialChars.contains(in) || in.matches("[0-9]+"));
	}
	
	public List<ColaProductos>actualizarAlmacen(List<ColaProductos> almT, List<ColaProductos> almP){
		
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
			
			almacenTemp = cargarTxt();
			this.almacen = actualizarAlmacen(almacenTemp,this.almacen);
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
		try{
			if(archivo.exists()){
				
				producto = new Producto();
				estante = new ColaProductos();
				
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				if ((linea = br.readLine())!= null){
					
					while(linea!= null){
						
						String [] valores = linea.split("-");
						producto.setNombre(valores[0]);
						producto.setCodigo(valores[1]);
						producto.setTipo(valores[2]);
						producto.setPrecio(Double.parseDouble(valores[3]));
						estante.Insertar(producto);
						almacen.add(estante);
						linea = br.readLine();
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
		return almacen;
		
	}



}

