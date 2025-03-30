package main;
import controlador.Controlador;
import modelo.Tablero;
import vista.Vista;

public class Juego {
	
    public static void main(String[] args) {
    	
    	//Tablero tablero = new Tablero(); // Crear el modelo
        Controlador controlador = new Controlador(); // Crear el controlador
        Vista vista = new Vista(controlador); // Crear la vista
    	 
        
    	
        }
}
