package es.iespablopicasso;

public class Main {
		static Windows myWindows = new Windows();
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Primero llamamos a iniciar el menú
		ConsolaHelper.iniciaMenu();
		
		myWindows.escogeAccion(ConsolaHelper.numeroP);
		
		
	
	}

}
