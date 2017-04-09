#Main+Citas


import java.io.*;
import java.util.Scanner;

public class main {
	
	static String pwd;
	static String valor;
	static String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
	static String menuAdmin = "\n1.Inventario\n"
			+ "2.Administrar clientes\n"
			+ "3.Citas\n"
			+ "4.Administrar usuarios\n"
			+ "5.Facturación\n" 
			+ "6.Salir\n";
	static String menuMed = "\n1.Inventario\n"
			+ "2.Administrar clientes\n"
			+ "3.Citas\n"
			+ "4.Salir\n";
	static String menuDep = "\n1.Inventario\n"
			+ "2.Administrar clientes\n"
			+ "3.Citas\n"
			+ "4.Facturación\n"
			+ "5.Salir\n"; 
	
	public static String[][][]Citas = new String [12][31][12];
	public static int Dia,Mes, Hora;
	public static String Mascota, Medico, Cliente;
	static String menuCitas = "\n1.Crear Cita\n"
			+ "2.Consultar Cita\n"
			+ "3.Eliminar Cita\n"
			+ "4.Volver al Menu Principal\n"
			+ "5.Salir\n";
	
			

	public static void main(String[] args) throws Exception {
		
		System.out.println("Bienvenido a Veterinaria VetClinic");
		System.out.println("Por favor ingrese su usuario y contraseña");
		ReadPass();
		Login();
    }  
 
	public static void Login () {
		
		
        java.io.Console console = System.console();
        String username = console.readLine("Usuario: ");
        String password = new String(console.readPassword("Contraseña: "));
        
        if (username.equalsIgnoreCase("Administrador")) {
        	
        	if (password.equals(pwd)) {
        		        	
        		System.out.println("Usuario Actual "+username);
        		AdminMenu();
        	
        	}
        	
        	else {
            	
            	System.out.println("Usuario y/o contraseña invalido \nPor favor intente de nuevo.");
            	Login();
            	
            	
            }
        }
        
        else if (username.equalsIgnoreCase("Medico")) {
        	
        	if (password.equals(pwd)) {
        		System.out.println("Usuario Actual "+username);
        		MedMenu();
        	}
        	
        	else {
            	
            	System.out.println("Usuario y/o contraseña invalido \nPor favor intente de nuevo.");
            	Login();
            	
            	
            }
        }
        
        else if (username.equalsIgnoreCase("Dependiente")) {
        	
        	if (password.equals(pwd)) {
        		System.out.println("Usuario Actual "+username);
        		DepMenu();
        	}
        	
        	else {
            	
            	System.out.println("Usuario y/o contraseña invalido \nPor favor intente de nuevo.");
            	Login();
            	
            	
            }
        }
        
        else {
        	
        	System.out.println("Usuario y/o contraseña invalido \nPor favor intente de nuevo.");
        	Login();
        	
        	
        }
		
	}
    private static String getUserName(String prompt){
        String username = null;
        System.out.print(prompt);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            username = br.readLine();
        } 
        catch (IOException e) {
            System.out.println("Por favor ingrese nuevemente su usuario");
            System.exit(1);
        }
        return username;
    }
 
 
    private static String getPassword(String prompt) {
 
        String password = "";
        ConsoleEraser consoleEraser = new ConsoleEraser();
        System.out.print(prompt);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        consoleEraser.start();
        try {
            password = in.readLine();
        }
        catch (IOException e){
            System.out.println("Por favor ingrese nuevemente su contraseña");
            System.exit(1);
        }
 
        consoleEraser.halt();
        System.out.print("\b");
 
        return password;
    }
 
 
    private static class ConsoleEraser extends Thread {
        private boolean running = true;
        public void run() {
            while (running) {
                System.out.print("\b ");
                try {
                    Thread.currentThread().sleep(1);
                }
                catch(InterruptedException e) {
                    break;
                }
            }
        }
        public synchronized void halt() {
            running = false;
        }
    }
    
    public static void AdminMenu(){
    	BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
    	int opcion;
		
		try{
			
			
			System.out.println(String.format("%30s", "\nMenu Administrador"));
			System.out.println(menuAdmin);
			valor = entrada.readLine();
			while (!validarEntrada(valor)){
				AdminMenu();
			} 
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					//this.Inventario();
					
					continuar();
					break;
				case 2:
					//this.Clientes();
					
					continuar();
					break;
				case 3:
					CitaMenu("Admin");
					
					continuar();
					break;
				case 4:
					//this.Usuarios();
					
					break;
				case 5:
					//this.Facturar();
					
					break;
									
				case 6:
					System.exit(0);
					
				}	
				
		} catch(Exception e){
			e.printStackTrace();
			
		}	
    	
    }
    
    public static void MedMenu(){
    	
    	BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
    	int opcion;
		
		try{
			
			
			System.out.println(String.format("%30s", "\nMenu Medico"));
			System.out.println(menuMed);
			valor = entrada.readLine();
			while (!validarEntrada(valor)){
				MedMenu();
			} 
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					//this.Inventario();
					
					continuar();
					break;
				case 2:
					//this.Clientes();
					
					continuar();
					break;
				case 3:
					CitaMenu("Med");
					
					continuar();
					break;
				case 5:
					System.exit(0);
					
					
										
				}	
				
		} catch(Exception e){
			e.printStackTrace();
			
		}
    	
    }
    
    public static void DepMenu(){
    	
    	BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
    	int opcion;
		
		try{
			
			
			System.out.println(String.format("%30s", "\nMenu Dependiente"));
			System.out.println(menuDep);
			valor = entrada.readLine();
			while (!validarEntrada(valor)){
				DepMenu();
			} 
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					//this.Inventario();
					
					continuar();
					break;
				case 2:
					//this.Clientes();
					
					continuar();
					break;
				case 3:
					CitaMenu("Dep");
					
					continuar();
					break;
				
				case 4:
					//this.Facturar();
					
					break;
									
				case 5:
					System.exit(0);
					
				}	
				
		} catch(Exception e){
			e.printStackTrace();
			
		}
    	
    }
    
public static void ReadPass() throws IOException {
		
		
		Scanner s2 = new Scanner (new File ("C:/Users/luissilv/Desktop/password.txt"));
		
		
  					  					
              pwd= s2.next();
  				
			s2.close();
			
        }

	
public static void CitaMenu(String user) throws IOException{
	ReadCita(Citas);
	BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
	int opcion;
	
	try{
		
		
		System.out.println(String.format("%30s", "Menu Citas"));
		System.out.println(menuCitas);
		valor = entrada.readLine();
		while (!validarEntrada(valor)){
			CitaMenu(user);
		} 
	
					
			opcion = Integer.parseInt(valor);
			
			switch(opcion){
			
			case 1:
				CrearCita (user);
				
				continuar();
				break;
			case 2:
				ConsultarCita (user);
				
				continuar();
				break;
			case 3:
				EliminarCita(user);
				
				continuar();
				break;
			case 4:
				VolverMenuPrincipal(user);
				
				continuar();
				break;	
			case 5:
				System.exit(0);
				
			}	
			
	} catch(Exception e){
		e.printStackTrace();
		
	}	
	
}

public static void VolverMenuPrincipal (String user) throws IOException{
	
	if (user.equals("Admin")){
		
		AdminMenu();
		
	}
	
	else if (user.equals("Med")){
		
		MedMenu();
		
	}
	
	else if (user.equals("Dep")){
		
		DepMenu();
		
	}
	
	
}

public static void ConsultarCita (String user) throws IOException{
	
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
	
		CitaMenu(user);
	
}

public static void CrearCita (String user) throws IOException{
	
	String validar=null;
	BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
	
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
	
	System.out.println("Digite la hora deseada de la Cita. Por favor ingrese un valor de 8-19");
	Hora= Integer.parseInt(entrada.readLine())-8;
	validar=Citas[Mes][Dia][Hora];
	
	while (!validar.contains("Disponible")){
		
		System.out.println(Citas[Mes][Dia][Hora]);
		System.out.println("Esta hora ya ha sido reservada. Por favor ingrese una hora diferente");
		Hora= Integer.parseInt(entrada.readLine())-8;
		validar=Citas[Mes][Dia][Hora];
		
	}
	
	System.out.println("Digite el nombre del Cliente.");
	Cliente= entrada.readLine();
	 
	System.out.println("Digite el nombre de la mascota.");
	Mascota= entrada.readLine();
			
	System.out.println("Digite el nombre de la médico.");
	Medico= entrada.readLine();
	
	Citas[Mes][Dia][Hora]="Cliente:"+Cliente+"||Mascota:"+Mascota +"||Medico:"+Medico;
	
	System.out.println("Cita Reservada Satisfactoriamente\nHora: "+(Hora+8 +":00\n")+Citas[Mes][Dia][Hora]);

	WriteCita (Citas);
	CitaMenu (user);
	
	
	
}
public static void EliminarCita (String user) throws IOException{
	
	String validar=null;
	BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
	
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
	
	System.out.println("Digite la hora de la Cita que desea eliminar. Por favor ingrese un valor de 8-19");
	Hora= Integer.parseInt(entrada.readLine())-8;
			
	Citas[Mes][Dia][Hora]="Disponible";
	
	System.out.println("Cita Eliminada Satisfactoriamente\n");

	WriteCita (Citas);
	CitaMenu (user);
	
	
	
}

public static void WriteCita(String string[][][]){
       
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
  	
public static void ReadCita(String string[][][]) throws IOException {
	
	
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
				Login();
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
			Login();
		}
		
	}
}

 

