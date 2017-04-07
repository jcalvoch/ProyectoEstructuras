# Citas

import java.io.*;
import java.util.*;


public class Citas {
	
	static String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
	static String valor;
	public static String[][][]Citas = new String [12][31][12];
	public static int Dia,Mes, Hora;
	public static String Mascota, Medico;
	static String menuCitas = "\n1.Crear Cita\n"
			+ "2.Consultar Cita\n"
			+ "3.Eliminar Cita\n"
			+ "4.Salir\n";
			
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		Read(Citas);
		CitaMenu();
		
		
	}
	
	public static void CitaMenu(){
    	BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
    	int opcion;
		
		try{
			
			
			System.out.println(String.format("%30s", "Menu Citas"));
			System.out.println(menuCitas);
			valor = entrada.readLine();
			while (!validarEntrada(valor)){
				CitaMenu();
			} 
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					CrearCita ();
					
					continuar();
					break;
				case 2:
					ConsultarCita ();
					
					continuar();
					break;
				case 3:
					EliminarCita();
					
					continuar();
					break;
				case 4:
					System.exit(0);
					
				}	
				
		} catch(Exception e){
			e.printStackTrace();
			
		}	
    	
    }
	
	public static void ConsultarCita () throws IOException{
		
		BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
		System.out.println("Consultar Cita\n");
		System.out.println("Seleccione el Mes de la Cita. Por favor ingrese un valor de 1-12");
		Mes= Integer.parseInt(entrada.readLine())-1;
		
		
		while (Mes<0 || Mes>11){
			
			System.out.println("Mes Invalido. Por favor ingrese un valor de 1-12");
			Mes= Integer.parseInt(entrada.readLine())-1;
		}
		
		System.out.println("Seleccione el Dia de la Cita. Por favor ingrese un valor de 1-31");
		Dia= Integer.parseInt(entrada.readLine())-1;
		
		while (Dia<0 || Dia>30){
			
			System.out.println("Mes Invalido. Por favor ingrese un valor de 1-31");
			Dia= Integer.parseInt(entrada.readLine())-1;
		}
			
		if (Mes==1 && Dia>27) {
			
			while (Dia<0 || Dia>27){
				
				System.out.println("Febrero tiene 28 dias. Por favor ingrese un valor de 1-28");
				Dia= Integer.parseInt(entrada.readLine())-1;				
				
			}
			
		}
		
			else if (Mes % 2 != 0)	{
				
				while (Dia<0 || Dia>29){
					
					System.out.println("Este mes cuenta con 30 días. Por favor ingrese un valor de 1-30");
					Dia= Integer.parseInt(entrada.readLine())-1;
			}
			
		}
		
			for (int i = 0; i <12; i++)
				
					 {
						
						System.out.println((i+8)+":00 "+Citas[Mes][Dia][i]);
					}
		
			CitaMenu();
		
	}
	
	
	public static void CrearCita () throws IOException{
		
		String validar=null;
		BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
		
		System.out.println("Seleccione el Mes de la Cita. Por favor ingrese un valor de 1-12");
		Mes= Integer.parseInt(entrada.readLine())-1;
		System.out.println(Mes);
		
		while (Mes<0 || Mes>11){
			
			System.out.println("Mes Invalido. Por favor ingrese un valor de 1-12");
			Mes= Integer.parseInt(entrada.readLine())-1;
		}
		
		System.out.println("Seleccione el Dia de la Cita. Por favor ingrese un valor de 1-31");
		Dia= Integer.parseInt(entrada.readLine())-1;
		
		while (Dia<0 || Dia>30){
			
			System.out.println("Mes Invalido. Por favor ingrese un valor de 1-31");
			Dia= Integer.parseInt(entrada.readLine())-1;
		}
			
		if (Mes==1 && Dia>27) {
			
			while (Dia<0 || Dia>27){
				
				System.out.println("Febrero tiene 28 dias. Por favor ingrese un valor de 1-28");
				Dia= Integer.parseInt(entrada.readLine())-1;				
				
			}
			
		}
		
			else if (Mes % 2 != 0)	{
				
				while (Dia<0 || Dia>29){
					
					System.out.println("Este mes cuenta con 30 días. Por favor ingrese un valor de 1-30");
					Dia= Integer.parseInt(entrada.readLine())-1;
			}
			
		}
		
			for (int i = 0; i <12; i++)
				
					 {
						
						System.out.println((i+8)+":00 "+Citas[Mes][Dia][i]);
					}
		
		System.out.println("Digite la hora deseada de la Cita. Por favor ingrese un valor de 8-19");
		Hora= Integer.parseInt(entrada.readLine())-8;
		validar=Citas[Mes][Dia][Hora];
		
		while (!validar.contains("Disponible")){
			
			System.out.println(Citas[Mes][Dia][Hora]);
			System.out.println("Esta hora ya ha sido reservada. Por favor ingrese una hora diferente");
			Hora= Integer.parseInt(entrada.readLine())-8;
			validar=Citas[Mes][Dia][Hora];
			
		}
		
		
		 
		System.out.println("Digite el nombre de la mascota.");
		Mascota= entrada.readLine();
				
		System.out.println("Digite el nombre de la médico.");
		Medico= entrada.readLine();
		
		Citas[Mes][Dia][Hora]="Mascota:"+Mascota +"||Medico:"+Medico;
		
		System.out.println("Cita Reservada Satisfactoriamente\nHora: "+(Hora+8 +":00\n")+Citas[Mes][Dia][Hora]);

		Write (Citas);
		CitaMenu ();
		
		
		
	}
	
public static void EliminarCita () throws IOException{
		
		String validar=null;
		BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
		
		System.out.println("Seleccione el Mes de la Cita. Por favor ingrese un valor de 1-12");
		Mes= Integer.parseInt(entrada.readLine())-1;
		System.out.println(Mes);
		
		while (Mes<0 || Mes>11){
			
			System.out.println("Mes Invalido. Por favor ingrese un valor de 1-12");
			Mes= Integer.parseInt(entrada.readLine())-1;
		}
		
		System.out.println("Seleccione el Dia de la Cita. Por favor ingrese un valor de 1-31");
		Dia= Integer.parseInt(entrada.readLine())-1;
		
		while (Dia<0 || Dia>30){
			
			System.out.println("Mes Invalido. Por favor ingrese un valor de 1-31");
			Dia= Integer.parseInt(entrada.readLine())-1;
		}
			
		if (Mes==1 && Dia>27) {
			
			while (Dia<0 || Dia>27){
				
				System.out.println("Febrero tiene 28 dias. Por favor ingrese un valor de 1-28");
				Dia= Integer.parseInt(entrada.readLine())-1;				
				
			}
			
		}
		
			else if (Mes % 2 != 0)	{
				
				while (Dia<0 || Dia>29){
					
					System.out.println("Este mes cuenta con 30 días. Por favor ingrese un valor de 1-30");
					Dia= Integer.parseInt(entrada.readLine())-1;
			}
			
		}
		
			for (int i = 0; i <12; i++)
				
					 {
						
						System.out.println((i+8)+":00 "+Citas[Mes][Dia][i]);
					}
		
		System.out.println("Digite la hora de la Cita que desea eliminar. Por favor ingrese un valor de 8-19");
		Hora= Integer.parseInt(entrada.readLine())-8;
				
		Citas[Mes][Dia][Hora]="Disponible";
		
		System.out.println("Cita Eliminada Satisfactoriamente\n");

		Write (Citas);
		CitaMenu ();
		
		
		
	}
	
	public static void Write(String string[][][]){
	       
	      try{
	          FileWriter fr = new FileWriter("C:/Users/luissilv/Desktop/Citas.txt");
	          BufferedWriter br = new BufferedWriter(fr);
	          PrintWriter out = new PrintWriter(br);
	          for (int j = 0; j <12; j++)
	  			for (int k = 0; k <31; k++)
	  				for (int l = 0; l <12; l++){
	  					  					
	              if(string[j][k][l] != null)
	                   
	            out.write(string[j][k][l]);
	                out.write("\n");       
	          }
	          out.close();
	           
	           
	      }
	      
	      	      
	      catch(IOException e){
	       System.out.println(e);   
	      }
	      
	      
	  }
	
	public static void Read(String string[][][]) throws IOException {
		
		
		Scanner s2 = new Scanner (new File ("C:/Users/luissilv/Desktop/Citas.txt"));
		
		for (int j = 0; j <12; j++)
  			for (int k = 0; k <31; k++)
  				for (int l = 0; l <12; l++){
  					  					
              string[j][k][l]= s2.next();
  				}
			s2.close();
        }
	
	public static boolean opcionValida(String in){
		return (!specialChars.contains(in) || in.matches("[0-9]+"));
	}

	public static boolean validarLetra(String in){
		return (in.matches(".*[a-zA-Z]+.*"));
	}
    
    public static boolean validarNumero(String in){
		return (in.matches("[0-9]+"));
		
    }
    
    public static boolean validarEntrada(String in){
		return (!specialChars.contains(in) || in.matches("[0-9]+"));
	}
    
    public static void continuar() throws IOException {
		String op;
		BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
		System.out.println("Digite 1 para continuar o 0 para salir");
		op = entrada.readLine();
		if(validarNumero(op)){
			if(op.equals(0)){
				System.exit(0);
			}else{
				CitaMenu();
			}
		}else{
			while(!op.equals(0) || !op.equals(1)){
				System.out.println("Valor invalido. Porfavor digite el  1 o 0");
				op = entrada.readLine();
			}
		}
		
		op = entrada.readLine();
		if(System.in.available()==0){
			System.exit(0);
		}else{
			CitaMenu();
		}
		
	}
    
	
	
}


