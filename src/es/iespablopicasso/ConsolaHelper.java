package es.iespablopicasso;

import java.util.Scanner;

public class ConsolaHelper {
	
	static String numeroP;
	//listar interfaces ipconfig -all
	//mostar la ip ipconfig
	
	/**
	 * Método para leer la entrada del teclado
	 */
	public static String  leerEntradaTeclado() {
	
		//No cerrar nunca el System.in si no queremos cargarnos el flujo estandar (teclado)
			
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
		
	}
	/**
	 * Comportamiento que pinta la pantalla del menú
	 */
	public static void iniciaMenu() {
	
		do {
			
		System.out.println("Ponga el número que corresponde a cada función");
		System.out.println();
		System.out.println("1.- Crear directorio");
		System.out.println("2.- Crear archivo");
		System.out.println("3.- Listar interfaces de red");
		System.out.println("4.- Mostrar la IP del ordenador");
		System.out.println("5.- Mostrar la direccion MAC");
		System.out.println("6.- Comprobar conectividad");
		System.out.println("7.- Salir ");
		System.out.println("///////////////////////////////////////////////");
		numeroP = ConsolaHelper.leerEntradaTeclado();
		
		}while (!checkNumero(numeroP));
		System.out.println("\n\n");
		
	}
	

	/**
	 * Método para checkear el número que hemos introducido al principio 
	 * @param numeroP
	 * @return
	 */
	private static boolean checkNumero(String numero) {
		boolean resultado = true;
		
		try {
			int numeroComprobar = Integer.parseInt(numero);
			if(!(numeroComprobar <=7&&numeroComprobar>0)) {
				System.out.println("Por favor introduce un numero de la lista");
				System.out.println();
				resultado = false;
			}
		}catch(NumberFormatException e) {
			System.out.println("Por favor introduce un número");
			resultado=false;
		}
		return resultado;
	}
}
