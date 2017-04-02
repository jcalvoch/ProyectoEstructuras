import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Inventario {

	List<ColaProductos> almacen = new ArrayList<>();
	ListIterator<ColaProductos> iterator = almacen.listIterator();
	ListIterator<ColaProductos> iter;
	ColaProductos estante;
	Producto producto;
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
	
	Scanner in = new Scanner(System.in);
	
	File archivo;
	FileWriter fw ;
	BufferedWriter bw;
	
	FileReader fr;
	BufferedReader br;
	
	
	public void inicio(){
		int opcion;
		
		try{
			System.out.println(String.format("%30s", "Inventario"));
			System.out.println(menuInventario);
			valor = in.nextLine();
			if (!validarEntrada(valor)){

				inicio();
				
			}else{
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					this.ingresarProducto();
					continuar();
					inicio();
					break;
				case 2:
					this.eliminarProducto();
					continuar();
					inicio();
					break;
				case 3:
					this.buscarProductos();
					continuar();
					inicio();
					break;
				case 4:
					//menu principal
					continuar();
					inicio();
					break;
				case 5:
					System.exit(0);
				}
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ingresarProducto(){
		try{
			producto = new Producto();
			estante = new ColaProductos();
			almacen = new ArrayList<ColaProductos>(); 
			
			System.out.println("Digite el tipo de articulo: ");
			producto.setTipo(in.nextLine());
			
			
			if (buscarEstante(producto.getTipo())){
				
				estante = obtenerEstante(producto.getTipo());//obtiene el estante(cola de productos) que ya existe para agregar uno mas de este tipo
				
				System.out.println("Digite la descripcion del articulo: ");
				producto.setNombre(in.nextLine());
				
				System.out.println("Digite el codigo del articulo: ");
				producto.setCodigo(in.nextLine());
					
				System.out.println("Digite el precio del articulo: ");
				producto.setPrecio(in.nextDouble());
				
				estante.Insertar(producto);
				
				guardarTxt(almacen);
				
				
			}else{
				
				System.out.println("Digite la descripcion del articulo: ");
				producto.setNombre(in.nextLine());
				
				System.out.println("Digite el codigo del articulo: ");
				producto.setCodigo(in.nextLine());
					
				System.out.println("Digite el precio del articulo: ");
				producto.setPrecio(in.nextDouble());
				
				estante.Insertar(producto); //crea un nuevo estante para este tipo de articulo y agrega el articulo
				
				almacen.add(estante); //Guarda el estante(cola) en el almacen(arraylist de colas)
				
				guardarTxt(almacen);
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
			
	}
	
	public void eliminarProducto(){
		producto = new Producto();
		
		try{
			
			System.out.println("Digite el codigo o la descripcion del articulo: ");
			datos = in.nextLine();
		
			if (buscarProducto(datos)){
				producto = this.almacen.get(obtenerNumeracionEstante(datos)).Eliminar();
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
			while(iterator.hasNext()){
				estante = iter.next();
				frente = estante.frente;
				fin = estante.fin;
				while(frente<fin){
					if((producto.getNombre().contains(datos) || producto.getCodigo().contains(datos))){
						System.out.println("Descripcion: "+producto.getNombre()+", \nCodigo: "+producto.getCodigo()+", \nPrecio:"+producto.getPrecio()+", Cantidad: "+estante.cantidadProducto()+"\n");
					}
					
					frente++;
				}
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
	
	
	public boolean buscarEstante(String data){
		 producto = new Producto();
		try{
			while(iterator.hasNext()){
				producto = iterator.next().FrenteCola();
				return (producto.getTipo().equals(data));
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return false;
		
	}

	public boolean buscarProducto(String data){
		 producto = new Producto();
		try{
			while(iterator.hasNext()){
				producto = iterator.next().FrenteCola();
				return (producto.getNombre().equals(data) || producto.getCodigo().equals(data));
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return false;
		
	}
	
	public int obtenerNumeracionEstante(String data){
		 producto = new Producto();
		 estante = new ColaProductos();
		 
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
	
	public void guardarTxt(List<ColaProductos> alm) throws Exception{
		
		try{
			
			archivo = new File(dir,nombreArchivo);
			estante = new ColaProductos();
			iter = alm.listIterator();
			
			
			if (!archivo.exists()){
				
				archivo.createNewFile();
				
				fw = new FileWriter(archivo,false);
				bw = new BufferedWriter(fw);
				
				while(iter.hasNext()){//recorre todos los estantes
					estante = iter.next();
					frente = estante.frente;
					fin = estante.fin;
					while(frente<fin){//recorre todos los productos dentro de los estantes
						linea = estante.siguienteCola(frente).getNombre()+"-"+estante.siguienteCola(frente).getCodigo()+"-"+estante.siguienteCola(frente).getTipo()+"-"+estante.siguienteCola(frente).getPrecio();
						bw.write(linea+"\n");
						bw.flush();
						frente++;
					}
					
				}
						
				System.out.println("commit bueno");
				
			}else{
				
				actualizarTxt(alm);				
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
	
	public void actualizarTxt(List<ColaProductos> alm) throws Exception{
		
		try{
			archivo = new File(dir,nombreArchivo);
			estante = new ColaProductos();
			iter = alm.listIterator();
			
			if (!archivo.exists()){
				
				guardarTxt(alm);
								
			}else{
//				almacen = new ArrayList<ColaProductos>(); 
//				almacen = cargarTxt();
				
				archivo.delete();
				archivo.createNewFile();
				fw = new FileWriter(archivo,false);
				bw = new BufferedWriter(fw);
				
				while(iter.hasNext()){
					estante = iter.next();
					frente = estante.frente;
					fin = estante.fin;
					while(frente<fin){
						linea = estante.siguienteCola(frente).getNombre()+"-"+estante.siguienteCola(frente).getCodigo()+"-"+estante.siguienteCola(frente).getTipo()+"-"+estante.siguienteCola(frente).getPrecio();
						bw.write(linea+"\n");
						bw.flush();
						frente++;
					}
				}
				
				System.out.println("commit bueno");
				
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
	
	public List<ColaProductos> cargarTxt(){
		try{
			if(archivo.exists()){
				
				producto = new Producto();
				estante = new ColaProductos();
				almacen = new ArrayList<>();
				
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				while((linea = br.readLine())!= null){
						
						String [] valores = linea.split("-");
						producto.setNombre(valores[0]);
						producto.setCodigo(valores[1]);
						producto.setTipo(valores[2]);
						producto.setPrecio(Double.parseDouble(valores[3]));
						estante.Insertar(producto);
						almacen.add(estante);
				}
								
			}
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return almacen;
		
	}

	public void continuar(){
		System.out.println("Presione Enter para continuar");
		try{System.in.read();}
		catch(Exception e){}
	}

}
