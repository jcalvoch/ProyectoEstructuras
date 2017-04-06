

#Hide password

import java.io.*;
import java.util.Scanner;

public class MenuInicial {
	
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
			

	public static void main(String[] args) throws Exception {
		
		System.out.println("Bienvenido a Veterinaria VetClinic");
		System.out.println("Por favor ingrese su usuario y contraseña");
		Login();
    }  
 
	public static void Login () {
		
		
        java.io.Console console = System.console();
        String username = console.readLine("Usuario: ");
        String password = new String(console.readPassword("Contraseña: "));
        //System.out.println(username+"/"+password);
        
        if (username.equalsIgnoreCase("Administrador")) {
        	
        	if (password.equals("password")) {
        		        	
        		System.out.println("Usuario Actual "+username);
        		AdminMenu();
        	
        	}
        	
        	else {
            	
            	System.out.println("Usuario y/o contraseña invalido \nPor favor intente de nuevo.");
            	Login();
            	
            	
            }
        }
        
        else if (username.equalsIgnoreCase("Medico")) {
        	
        	if (password.equals("password")) {
        		System.out.println("Usuario Actual "+username);
        		MedMenu();
        	}
        	
        	else {
            	
            	System.out.println("Usuario y/o contraseña invalido \nPor favor intente de nuevo.");
            	Login();
            	
            	
            }
        }
        
        else if (username.equalsIgnoreCase("Dependiente")) {
        	
        	if (password.equals("password")) {
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
			
			
			System.out.println(String.format("%30s", "Menu Administrador"));
			System.out.println(menuAdmin);
			valor = entrada.readLine();
			while (!validarEntrada(valor)){
				AdminMenu();
			} 
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					this.Inventario();
					
					continuar();
					break;
				case 2:
					this.Clientes();
					
					continuar();
					break;
				case 3:
					this.Citas();
					
					continuar();
					break;
				case 4:
					//this.Usuarios();
					
					break;
				case 5:
					this.Facturar();
					
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
			
			
			System.out.println(String.format("%30s", "Menu Medico"));
			System.out.println(menuMed);
			valor = entrada.readLine();
			while (!validarEntrada(valor)){
				MedMenu();
			} 
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					this.Inventario();
					
					continuar();
					break;
				case 2:
					this.Clientes();
					
					continuar();
					break;
				case 3:
					this.Citas();
					
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
			
			
			System.out.println(String.format("%30s", "Menu Dependiente"));
			System.out.println(menuDep);
			valor = entrada.readLine();
			while (!validarEntrada(valor)){
				DepMenu();
			} 
		
						
				opcion = Integer.parseInt(valor);
				
				switch(opcion){
				
				case 1:
					this.Inventario();
					
					continuar();
					break;
				case 2:
					this.Clientes();
					
					continuar();
					break;
				case 3:
					//this.Citas();
					
					continuar();
					break;
				
				case 4:
					this.Facturar();
					
					break;
									
				case 5:
					System.exit(0);
					
				}	
				
		} catch(Exception e){
			e.printStackTrace();
			
		}
    	
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

 

