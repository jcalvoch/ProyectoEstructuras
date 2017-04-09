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
	ColaProductos estante, estanteTemp, auxEstante;
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
			this.almacen = new ArrayList<>();
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
					this.imprimirProductos();
					continuar();
					break;
				case 4:
					this.verInventario();
					continuar();
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
			
			auxEstante = new ColaProductos();
			
						
			System.out.println("Digite el codigo del articulo: ");
			valor = in.nextLine();
			
			if (buscarEstante(valor)){
				
				producto = new Producto();
				
				System.out.println("Codigo encontrado.....\n");
				
				producto = obtenerEstante(valor).FrenteCola();
				
				auxEstante = this.almacen.get(obtenerNumeracionEstante(valor));
				
				System.out.println("Digite la cantidad de articulos: ");
				int cantidad = in.nextInt();
				
				for(int i=cantidad; i>0;i--){
					auxEstante.Insertar(producto);  
				}
				
				this.almacen.set(obtenerNumeracionEstante(valor),auxEstante);
				guardarTxt();
				
			}else{
				
				producto = new Producto();
				
				auxEstante = new ColaProductos();
				producto.setCodigo(valor);
				System.out.println("Nuevo codigo ingresado, creando nuevo codigo en la base de datos.....\n");
				
				System.out.println("Digite el nombre del articulo: ");
				producto.setNombre(in.nextLine());
				
				System.out.println("Digite el tipo de articulo: ");
				producto.setTipo(in.nextLine());
					
				System.out.println("Digite el precio del articulo en $: ");
				producto.setPrecio(in.nextDouble());
				
				System.out.println("Digite el peso del articulo en gramos: ");
				producto.setPeso(in.nextDouble());
				
				System.out.println("Digite la cantidad de articulos: ");
				int cantidad = in.nextInt();
				
				for(int i=cantidad; i>0;i--){
					auxEstante.Insertar(producto); //crea un nuevo estante para este tipo de articulo y agrega la cantidad de articulos
				}
				
				this.almacen.add(auxEstante); //Guarda el estante(cola) en el almacen(arraylist de colas)
				
				guardarTxt();
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
			
	}
	
	public void eliminarProducto(){
		producto = new Producto();
		
		try{
			System.out.println("Digite el codigo o el nombre del articulo: ");
			datos = in.nextLine();
		
			if (buscarProducto(datos)){
				producto = this.almacen.get(obtenerNumeracionEstante(datos)).Eliminar();
				actualizarTxt();
				System.out.println("Articulo: "+producto.getNombre()+", eliminado exitosamente"+". Cantidad restante: "+(obtenerEstante(datos).cantidadProducto()-1)+"\n");
				
			}else{
				System.out.println("El articulo no existe, o tiene un codigo/nombre diferente");
			}
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void imprimirProductos(){
		
		estante = new ColaProductos();
		
		try{
			System.out.println("Ingrese el nombre, codigo o el tipo de producto: ");
			datos = in.nextLine();
			this.almacen = new ArrayList<>();
			this.almacen = cargarTxt();
			iterator = this.almacen.listIterator();
			
			while(iterator.hasNext()){
				estante = iterator.next();
				producto = estante.FrenteCola();
				
				if((producto.getNombre().toLowerCase().contains(datos.toLowerCase()) || producto.getTipo().toLowerCase().contains(datos.toLowerCase())|| producto.getCodigo().toLowerCase().contains(datos.toLowerCase()))){
						System.out.println("Nombre: "+producto.getNombre()+", Codigo: "+producto.getCodigo()+", Precio: $"+producto.getPrecio()+", Peso: "+producto.getPeso()+"kg"+", Cantidad: "+estante.cantidadProducto());
				}
					
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
	}
		
	public boolean buscarEstante(String data){
		 aux = new Producto();
		 iterator = this.almacen.listIterator();
		 boolean encontrado = false;
		try{
			while(iterator.hasNext() && !encontrado){
				aux = iterator.next().FrenteCola();
				encontrado = (aux.getCodigo().toLowerCase().equals(data.toLowerCase()));
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return encontrado;
		
	}

	public boolean buscarProducto(String data){
		 aux = new Producto();
		 auxEstante = new ColaProductos();
		 iterator = this.almacen.listIterator();
		 boolean encontrado = false;
		try{
			
			while(iterator.hasNext()){
				auxEstante = iterator.next();
				frente = auxEstante.frente;
				fin = auxEstante.fin;
				aux = auxEstante.siguienteCola(frente);
				
				while(frente<=fin && !encontrado){
					frente++;
					if (aux.getNombre().toLowerCase().equals(data.toLowerCase()) || aux.getCodigo().toLowerCase().equals(data.toLowerCase())){
						encontrado = true;
						
					}
					aux = auxEstante.siguienteCola(frente);
					
				}
				
			}
			
		
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return encontrado;
		
	}
	
	public int obtenerNumeracionEstante(String data){
		 
		 auxEstante = new ColaProductos();
		 iterator = this.almacen.listIterator();
		 
		try{
			while(iterator.hasNext()){
				auxEstante = iterator.next();
				 
				if (auxEstante.FrenteCola().getNombre().toLowerCase().equals(data.toLowerCase()) || auxEstante.FrenteCola().getCodigo().toLowerCase().equals(data.toLowerCase())){
					return this.almacen.indexOf(auxEstante);
				}
			}
			
		}catch (Exception e){
			System.out.println(e.toString());
		}
		return this.almacen.indexOf(auxEstante);
		
	}
	
	public ColaProductos obtenerEstante(String data){
		 
		auxEstante = new ColaProductos();
		 iterator = this.almacen.listIterator();
		 
		try{
			while(iterator.hasNext()){
				auxEstante = iterator.next();
				 
				if (auxEstante.FrenteCola().getCodigo().toLowerCase().equals(data.toLowerCase())){
					return auxEstante;
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
						linea = estante.siguienteCola(frente).getNombre()+"-"+estante.siguienteCola(frente).getCodigo()+"-"+estante.siguienteCola(frente).getTipo()+"-"+estante.siguienteCola(frente).getPrecio()+"-"+estante.siguienteCola(frente).getPeso()+separadorLineas;
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
					linea = estante.siguienteCola(frente).getNombre()+"-"+estante.siguienteCola(frente).getCodigo()+"-"+estante.siguienteCola(frente).getTipo()+"-"+estante.siguienteCola(frente).getPrecio()+"-"+estante.siguienteCola(frente).getPeso()+separadorLineas;
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
		estanteTemp = new ColaProductos();
		String codigo = "";
		try{
			if(archivo.exists()){
				
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				if ((linea = br.readLine())!= null && (!linea.isEmpty())){
				
					while(linea!= null){
						
						producto = new Producto();
						estante = new ColaProductos();
						String [] valores = linea.split("-");
						producto.setNombre(valores[0]);
						producto.setCodigo(valores[1]);
						producto.setTipo(valores[2]);
						producto.setPrecio(Double.parseDouble(valores[3]));
						producto.setPeso(Double.parseDouble(valores[4]));
						estante.Insertar(producto);
						
						linea = br.readLine();
	
						if(producto.getCodigo().equals(codigo)){
							estanteTemp = this.almacen.get(obtenerNumeracionEstante(codigo));
							estanteTemp.Insertar(producto);
							this.almacen.set(obtenerNumeracionEstante(codigo), estanteTemp); 
							
						}else{
							codigo = producto.getCodigo();
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


	
	public void verInventario(){
		auxEstante = new ColaProductos();
		aux = new Producto();
		
		
		try {
			this.almacen = new ArrayList<>();
			this.almacen = cargarTxt();
			iterator = this.almacen.listIterator();
			
			while(iterator.hasNext()){
				auxEstante = iterator.next();
				aux = auxEstante.FrenteCola();
				System.out.println("Nombre: "+aux.getNombre()+", Codigo: "+aux.getCodigo()+", Precio: $"+aux.getPrecio()+", Peso: "+aux.getPeso()+"kg"+", Cantidad: "+auxEstante.cantidadProducto());

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
		System.out.println("\n\nDigite 1 para volver al menu anterior o 0 para salir");
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
