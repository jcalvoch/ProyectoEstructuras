import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Facturacion {

	Inventario inventario = new Inventario();
	Items artFactura, tempArtFactura;
	Producto product;
	ColaProductos estante = new ColaProductos();
	ColaProductos estanteFactura = new ColaProductos();
	Factura factura = new Factura(), facturaTemp;
	Factura [] factureroDiario = new Factura[100];
	Factura [] factureroTotal = new Factura[100];
	
	int contItems = 0, contFacturas = 0, descuento;
	boolean masItems;
	
	Scanner in = new Scanner(System.in);
	String datos;
	int cantidad, opMas, opcionPrincipal;
	
	String dir = System.getProperty("user.dir");
	String nombreArchivo = "Facturero.txt";
	String linea;
	String separadorLineas = System.getProperty("line.separator");
	File archivo;
	FileWriter fw ;
	BufferedWriter bw;
	
	FileReader fr;
	BufferedReader br;
	
	
	public void menuFacturacion(){
		
		factureroDiario = new Factura[100];
		factureroTotal = new Factura[100];
		
		factureroDiario = cargarFactureroHoy();
		in = new  Scanner(System.in);
		try{
			
			do{
			
				System.out.println(String.format("%30s", "Menu de facturacion:"));
				System.out.println("\nDigite 1 para facturar productos\n"
					+ "Digite 2 para facturar servicios de estetica\n"
					+ "Digite 3 para facturar servicios medicos\n"
					+ "Digite 4 para volver al menu principal\n"
					+ "Digite 5 para salir...");
			
				opcionPrincipal = in.nextInt();
			
			}while(!opcionValida(opcionPrincipal));
				
			if(opcionPrincipal == 1){
				this.ingresarProductos();
				masItems = masItemsFactura();
				if(masItems){
					menuFacturacion();
				}else{
					this.facturar();
				}
								
			}else if (opcionPrincipal == 2){
								
				this.ingresarServicioEstetica();
				masItems = masItemsFactura();
				if(masItems){
					menuFacturacion();
				}else{
					this.facturar();
				}
				
			}else if (opcionPrincipal == 3){
				this.ingresarServicioMedicos();
				masItems = masItemsFactura();
				if(masItems){
					menuFacturacion();
				}else{
					this.facturar();
					}
			}else if (opcionPrincipal == 4){
				//return menu pincipal
			}else{
				System.exit(0);
			}
		}catch(Exception e){
				e.printStackTrace();
		}
		
	}
	
	
	public void ingresarProductos(){
		
		in = new Scanner(System.in);
		try{
			
			System.out.println("Por favor digite el codigo o el nombre del articulo ");
			datos = in.nextLine();
			
			if(inventario.buscarProducto(datos)){
				
				this.estante = inventario.obtenerEstante(inventario.obtenerProducto(datos).getCodigo());
				System.out.println("Por favor ingrese la cantidad, total disponible: "+this.estante.cantidadProducto());
				cantidad = in.nextInt();
				
				while(!inventario.validarNumero(String.valueOf(cantidad))){
					System.out.println("Por favor ingrese la cantidad numeral");
					cantidad = in.nextInt();
				}
				
				while(cantidad > this.estante.cantidadProducto()){
					System.out.println("Por favor ingrese una cantidad menor");
					System.out.println("\ntotal disponible: "+estante.cantidadProducto());
					cantidad = in.nextInt();
				}
				
				product = new Producto();
				product = this.estante.FrenteCola();
				artFactura = new Items();
				artFactura.descripcion = product.getNombre();
				artFactura.setPrecio(product.getPrecio());
				artFactura.setCantidad(cantidad);
				artFactura.setCodigo(product.getCodigo());
				inventario.facturarProducto(product.getCodigo(),artFactura.getCantidad());
				this.factura.facturables[contItems] = artFactura;
				contItems++;
				
				
				
			}else{
				artFactura = new Items();
				System.out.println("El articulo no se encuentra en inventario....");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void ingresarServicioEstetica(){
		
		artFactura = new Items();
		in = new Scanner(System.in);
		try{
		
			System.out.println("Por favor ingrese la descripcion del servicio de estetica: ");
			artFactura.setDescripcion(in.nextLine());
			
			System.out.println("Por favor ingrese la cantidad de servicios de estetica realizados: ");
			artFactura.setCantidad(in.nextInt());
			
			System.out.println("Por favor ingrese el precio de cada servicio: ");
			artFactura.setPrecio(in.nextDouble());
			
			artFactura.setCodigo("Estetica");
			this.factura.facturables[contItems] = artFactura;
			contItems++;

			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void ingresarServicioMedicos(){
		
		in = new Scanner(System.in);
		
		try{
			artFactura = new Items();
			
			System.out.println("Por favor ingrese la descripcion del servicio medico: ");
			artFactura.setDescripcion(in.nextLine());
			
			System.out.println("Por favor ingrese la cantidad de servicios de estetica: ");
			artFactura.setCantidad(in.nextInt());
			
			System.out.println("Por favor ingrese el precio de cada servicio: ");
			artFactura.setPrecio(in.nextDouble());
			
			artFactura.setCodigo("Medicos");
			this.factura.facturables[contItems] = artFactura;
			contItems++;

			
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}
	
		
	public void facturar(){
		in = new Scanner(System.in);
		try{
			this.factura.setNumero(contFacturas+1);		
			
			System.out.println("Por favor ingrese el nombre del cliente: ");
			this.factura.setNombre(in.nextLine());
			
			this.factura.setSubtotal(calcularSubtotal(this.factura.facturables));
				
			System.out.println("Por favor ingrese el descuento en valor de porcentaje: ");
			descuento = in.nextInt();
				
			this.factura.setDescuento(this.factura.getSubtotal() * (descuento *0.001));
			this.factura.setFecha(LocalDateTime.now().toLocalDate());
			this.factura.setImpuesto(this.factura.getSubtotal() * 0.13);
			this.factura.setTotal(this.factura.getSubtotal() - this.factura.getDescuento()+this.factura.getImpuesto());
				
			this.factureroDiario[contFacturas] = this.factura;
			contFacturas++;
			guardarFactureroHoy();
			
			
			System.out.println("\n\nCliente: "+this.factura.getNombre()+"    Factura #"+this.contFacturas+"    Fecha: "+this.factura.getFecha().toString()+"\n");
			
			String format = "|%1$-50s|%2$-20s|%3$-20s|%4$-20s|\n";
			System.out.format(format, "Articulo","Cantidad","Precio","Subtotal");
			
			for(Items articulo:factura.facturables){
				if (articulo != null){
					System.out.format(format, articulo.getDescripcion(),articulo.getCantidad(),("¢"+articulo.getPrecio()),("¢"+articulo.calcularSubtotal()));
				}				
			}
				System.out.println("\n\n");
				System.out.format("%1$-15s|¢%2$-10s|\n", "Subtotal:",this.factura.getSubtotal());
				System.out.format("%1$-15s|¢%2$-10s|\n", "Descuento",this.factura.getDescuento());
				System.out.format("%1$-15s|¢%2$-10s|\n", "Impuesto 13%",this.factura.getImpuesto());
				System.out.format("%1$-15s|¢%2$-10s|\n", "Total",this.factura.getTotal());
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				continuar();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
		
	public boolean masItemsFactura(){
		
		do{
			System.out.println("\n\nDesea añadir mas productos o servicios a esta factura?\n"
					+ "ingrese 1 para añadir mas o 2 para finalizar la factura");
			opMas = in.nextInt();
		}while(opMas != 1 && opMas != 2);
		
		if (opMas ==1){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	public double calcularSubtotal(Items [] articulos){
		double subtotal = 0;
		try{
			
			for(Items articulo : articulos){
				if(articulo!= null){
					subtotal += articulo.calcularSubtotal();
				}
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return subtotal;
		
		
	}
	

	public boolean opcionValida(int opc){
		return (opc == 1 || opc == 2 || opc == 3 || opc == 3 || opc == 4 || opc == 5);
	}

	
	public void continuar() throws IOException {
		in = new Scanner(System.in);
		System.out.println("\n\nDigite 1 para volver al menu anterior o 0 para salir");
		int conti = in.nextInt();
		if(conti==1 || conti==0){
			if(conti == 0){
				System.exit(0);
			}else{
				this.menuFacturacion();
			}
		}else{
			while(conti !=1 || conti !=0){
				System.out.println("Valor invalido. Porfavor digite el  1 para el menu de facturacion o 0 para salir");
				conti = in.nextInt();
			}
		}
		
	}
	
	
	public Factura []  cargarFactureroTodosLosTiempos(){
		archivo = new File(dir,nombreArchivo);
		factureroTotal = new Factura[100];
		int posFactura=0, posItems=0;
		LocalDate fechaFactura = null;
		String codigo = "";
		int numeroFactura = -1;
		facturaTemp = new Factura();
		linea = new String();
		try{
			if(archivo.exists()){
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				if ((linea = br.readLine())!= null && (!linea.isEmpty())){
					
					while(linea!= null){
						
						factura = new Factura();
						String [] valores = linea.split("-");
						
						
						factureroTotal[posFactura].facturables[posItems].setCodigo(valores[0]);
						factureroTotal[posFactura].facturables[posItems].setDescripcion(valores[1]);
						factureroTotal[posFactura].facturables[posItems].setCantidad(Integer.parseInt(valores[2]));
						factureroTotal[posFactura].facturables[posItems].setPrecio(Double.parseDouble(valores[3]));
						factureroTotal[posFactura].setNumero(Integer.parseInt(valores[4]));
						factureroTotal[posFactura].setNombre(valores[5]);
						factureroTotal[posFactura].setDescuento(Double.parseDouble(valores[6]));
						factureroTotal[posFactura].setImpuesto(Double.parseDouble(valores[7]));
						factureroTotal[posFactura].setSubtotal(Double.parseDouble(valores[8]));
						factureroTotal[posFactura].setTotal(Double.parseDouble(valores[9]));
						factureroTotal[posFactura].setFecha(LocalDate.parse(valores[10],DateTimeFormatter.ofPattern("yyyy-MM-dd")));
						factura = factureroTotal[posFactura]; 
												
						linea = br.readLine();
						
						if (codigo.equals(valores[0])){
							posItems++;
							
						}else{
							codigo = valores[0];
						}
						
						if (numeroFactura == Integer.parseInt(valores[4])){
							factureroTotal[posFactura++] = factura;
							
						}else{
							numeroFactura = Integer.parseInt(valores[4]);
						}
						
					}
				}else{
					return factureroTotal;
				}
				
			}else{
				return factureroTotal;
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
		return factureroTotal;
		
		
	}
	
	
	public Factura []  cargarFactureroHoy(){
		
		archivo = new File(dir,nombreArchivo);
		factureroDiario = new Factura[100];
		int posFactura=0, posItems=0;
		LocalDate fechaFactura = LocalDate.now();
		String codigo = "";
		int numeroFactura = -1;
		facturaTemp = new Factura();
		linea = new String();
		try{
			if(archivo.exists()){
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				
				if ((linea = br.readLine())!= null && (!linea.isEmpty())){
					
					while(linea!= null){
						
						factura = new Factura();
						String [] valores = linea.split("-");
						
						if (fechaFactura == (LocalDate.parse((valores[10]+"-"+valores[11]+"-"+valores[12]),DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
							
						factureroDiario[posFactura] = new Factura();
						factureroDiario[posFactura].facturables[posItems] = new Items();
						factureroDiario[posFactura].facturables[posItems].setCodigo(valores[0]);
						factureroDiario[posFactura].facturables[posItems].setDescripcion(valores[1]);
						factureroDiario[posFactura].facturables[posItems].setCantidad(Integer.parseInt(valores[2]));
						factureroDiario[posFactura].facturables[posItems].setPrecio(Double.parseDouble(valores[3]));
						factureroDiario[posFactura].setNumero(Integer.parseInt(valores[4]));
						factureroDiario[posFactura].setNombre(valores[5]);
						factureroDiario[posFactura].setDescuento(Double.parseDouble(valores[6]));
						factureroDiario[posFactura].setImpuesto(Double.parseDouble(valores[7]));
						factureroDiario[posFactura].setSubtotal(Double.parseDouble(valores[8]));
						factureroDiario[posFactura].setTotal(Double.parseDouble(valores[9]));
						factureroDiario[posFactura].setFecha(LocalDate.parse((valores[10]+"-"+valores[11]+"-"+valores[12]),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
						factura = factureroDiario[posFactura]; 
												
						linea = br.readLine();
						
						if (codigo.equals(valores[0])){
							posItems++;
							
						}else{
							codigo = valores[0];
						}
						
						}if (numeroFactura == Integer.parseInt(valores[4])){
							factureroDiario[posFactura++] = factura;
						}else{
							numeroFactura = Integer.parseInt(valores[4]);
						}
						
					}
				}else{
					return factureroDiario;
				}
				
			}else{
				return factureroDiario;
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
		return factureroDiario;
		
	}
	
	
	public void guardarFactureroHoy() throws Exception{
		
		try{
			archivo = new File(dir,nombreArchivo);
									
			if (!archivo.exists()){
				
				archivo.createNewFile();
				
				fw = new FileWriter(archivo,false);
				bw = new BufferedWriter(fw);
				
				for (int i=0; i <= contFacturas-1; i++){
					
					for(int e = 0; e <= contItems-1; e++){
						linea = this.factureroDiario[i].facturables[e].getCodigo() +"-"+ this.factureroDiario[i].facturables[e].getDescripcion() +"-"+ 
								this.factureroDiario[i].facturables[e].getCantidad() +"-"+ this.factureroDiario[i].facturables[e].getPrecio() +"-"+ 
								this.factureroDiario[i].getNumero()+"-"+this.factureroDiario[i].getNombre() +"-"+ this.factureroDiario[i].getDescuento() +"-"+
								this.factureroDiario[i].getImpuesto() +"-"+this.factureroDiario[i].getSubtotal() +"-"+ this.factureroDiario[i].getTotal() +"-"+
								this.factureroDiario[i].getFecha() + separadorLineas;
						bw.write(linea);
						bw.flush();
					}
					
				}
				
				System.out.println("Factura guardada a la base de datos TXT...");
				
			}else{
				
				try{
					archivo = new File(dir,nombreArchivo);
												
					fw = new FileWriter(archivo,true);
					bw = new BufferedWriter(fw);
						
					for (int i=0; i <= contFacturas-1; i++){
						
						for(int e = 0; e <= contItems-1;e++){
							linea = this.factureroDiario[i].facturables[e].getCodigo() +"-"+ this.factureroDiario[i].facturables[e].getDescripcion() +"-"+ 
									this.factureroDiario[i].facturables[e].getCantidad() +"-"+ this.factureroDiario[i].facturables[e].getPrecio() +"-"+ 
									this.factureroDiario[i].getNumero()+"-"+this.factureroDiario[i].getNombre() +"-"+ this.factureroDiario[i].getDescuento() +"-"+
									this.factureroDiario[i].getImpuesto() +"-"+this.factureroDiario[i].getSubtotal() +"-"+ this.factureroDiario[i].getTotal() +"-"+
									this.factureroDiario[i].getFecha() + separadorLineas;
							
							if (linea != null){
								bw.write(linea);
								bw.flush();
							}
							
						}
						
					}
					
					System.out.println("Factura actualizada en la base de datos TXT...");
						
					
				}catch(IOException e){
					
					System.out.println(e.toString());
					
				
				}finally{ 
					   
					try{
						this.contFacturas = 0;
						for(int i = 0; i < this.factureroDiario.length; i++){
							this.contFacturas++;	
						}
						if(bw!=null)
						bw.close();
					
					}catch(Exception ex){
					
						System.out.println("Error cerrando el BufferedWriter - "+ex.toString());
					    
					}
				}			
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
	
	
	public Items reporteServicioEstetica(){
		
		try{
			for(int i=0; i<factureroDiario.lenght; i++){
			
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	
}
