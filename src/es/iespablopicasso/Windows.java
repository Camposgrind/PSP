package es.iespablopicasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Windows {
	
	/**
	 * ESTADO
	 */
	ProcessBuilder processBuilder;
	String ruta;
	
	/**
	 * Constructor
	 */
	public Windows () {
		processBuilder = new ProcessBuilder();
		ruta = "";
	
	}
	
	/**
	 * Método que escoge lo que hay que hacer, depende del numero que hayamos puesto 
	 * en nuestro menú
	 * @param numeroElegido
	 */
	public void escogeAccion(String numeroElegido) {
		int miNumero = Integer.parseInt(numeroElegido);
		switch(miNumero) {
		case 1: 
			crearDirectorio();
			break;
		case 2: 
			crearArchivo();
			break;
		case 3: 
			listaInterfacesRed();
			break;
		case 4: 
			mostrarIP();
			break;
		case 5:
			mostrarMAC();
			break;
		case 6: 
			comprobarConectividad();
			break;
		case 7: 
			System.out.println("Hasta luego Lucas");
			break;

		}
	}
	/**
	 * Método que crea el directorio 
	 */
	public void crearDirectorio() {
		
		do {
			System.out.println("Introduce la ruta donde crear el directorio");
			System.out.println();
			ruta = ConsolaHelper.leerEntradaTeclado();

		}while(!checkRuta(ruta));
			
		do {
			System.out.println("Introduce el nombre de un directorio que no exista");
			System.out.println();
			ruta += "\\"+ ConsolaHelper.leerEntradaTeclado();

		}while(checkRuta(ruta));
			
		processBuilder.command("cmd.exe","/c","MD "+ruta);
			try {
				
				processBuilder.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Carpeta creada con éxito");
			reiniciar();
	}
	
	/**
	 * Método para crear el archivo
	 */
	public void crearArchivo() {
		
		do {
			System.out.println("Introduce la ruta donde crear el txt");
			System.out.println();
			ruta = ConsolaHelper.leerEntradaTeclado();

		}while(!checkRuta(ruta));
			
		do {
			System.out.println("Introduce el nombre del txt a crear");
			System.out.println();
			ruta += "\\"+ ConsolaHelper.leerEntradaTeclado();

		}while(checkRuta(ruta));
			
		processBuilder.command("powerShell","/c","ni "+ruta+".txt");
			
			try {
				processBuilder.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("TXT creado con éxito");
			reiniciar();
	}
	
	/**
	 * Método para listar las interfaces
	 */
	public void listaInterfacesRed() {
		
		processBuilder.command("powerShell","ipconfig -all");
		
		try {
			Process process = processBuilder.start();
			
			StringBuilder buffer = new StringBuilder();
			//El cp850 es para que lea bien las tildes
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(),"Cp850"));
			
		// Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
					buffer.append(line + "\n");
				}
			
				if (process.waitFor() == 0) {
					System.out.println(buffer);
					
				} else {
					System.out.println("No se ha podido listar la interfaz de red");
				}	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		reiniciar();
	}
	/**
	 * Método para mostrar la IP
	 */
	public void mostrarIP() {
		
		processBuilder.command("powerShell",
				"Get-NetAdapter -Name  Wi-Fi  | Get-NetIPAddress -AddressFamily IPv4");
		
		
		try {
			Process process = processBuilder.start();
			
			StringBuilder buffer = new StringBuilder();
			//El cp850 es para que lea bien las tildes
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(),"Cp850"));
			
			// Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
				
				//Le decimos que si la linea contiene IPAdress y que con el substring pille solo la IP 
				if (line.contains("IPAddress")) {
					line = line.substring(line.indexOf(":")+1,line.length());
					buffer.append(line + "\n");
				}
			}		
				if (process.waitFor() == 0) {
					System.out.println(buffer);
					
				} else {
					System.out.println("No se ha podido mostrar la IP");
				}	
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		reiniciar();
	}
	/**
	 * Método para mostrar la MAC
	 */
	public void mostrarMAC() {
		
		processBuilder.command("powerShell","(Get-NetAdapter -Name Wi-Fi).MacAddress");
		
		try {
			Process process = processBuilder.start();
			
			StringBuilder buffer = new StringBuilder();
			//El cp850 es para que lea bien las tildes
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(),"Cp850"));
			
		// Guardamos en un buffer la salida del proceso
			String line;
			while ((line = reader.readLine()) != null) {
					buffer.append(line + "\n");
				}
			
				if (process.waitFor() == 0) {
					System.out.println(buffer);
					
				} else {
					System.out.println("No se ha podido proporcionar la MAC");
				}	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		reiniciar();
	}
	/**
	 * Método que comprueba la conectividad 
	 */
	public void comprobarConectividad() {
		
		System.out.println("Introduce la dirección para comprobar");
		
		String line;
		String IP = ConsolaHelper.leerEntradaTeclado();
		
		processBuilder.command("powerShell","ping "+IP);
		
		
		try {
			Process process = processBuilder.start();
			
			StringBuilder buffer = new StringBuilder();
			//El cp850 es para que lea bien las tildes
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream(),"Cp850"));
			
			while ((line = reader.readLine()) != null) {
				
				//le decimos que si contiene la palabra paquetes imprima esa linea por pantalla 
				if (line.contains("Paquetes")) {
					buffer.append(line + "\n");
				}
			}
			if (process.waitFor() == 0) {
				System.out.println("La conexión es buena "+"\n");
				System.out.println(buffer);
				
			} else {
				System.out.println("No se ha podido mostrar la IP");
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		reiniciar();
	}
	

	/**
	 * Método que comprueba si la ruta/carpeta/archivo existe
	 * @param miRuta
	 * @return
	 */
	private boolean checkRuta(String miRuta) {
		
		return new File(miRuta).exists();
	}
	
	/**
	 * Método que se encarga de preguntar si necesitamos hacer algo más
	 * Vuelve a poner el menú si le decimos que sí, si decimos que no, cierra
	 * el programa
	 */
	private void reiniciar() {
		String respuesta;
		
		System.out.println("Desea hacer algo más? Si/No");
		respuesta = ConsolaHelper.leerEntradaTeclado();
		
		if(respuesta.equalsIgnoreCase("Si")){
			ConsolaHelper.iniciaMenu();
			escogeAccion(ConsolaHelper.numeroP);
		}
		else {
			System.out.println("Hasta luego Lucas");
		}
	}
	
}
