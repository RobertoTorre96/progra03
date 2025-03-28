package controlador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import modelo.Tablero;

public class Controlador {

    private Tablero tablero; // El modelo

    public Controlador(Tablero tablero) {
        this.tablero = tablero;
    }

    // Método que maneja el evento de clic sobre una casilla
    public void manejarClick(int fila, int columna, int limite) {
        // Cambiar el color de la casilla (por ejemplo, al color rojo)
        int nuevoColor = tablero.numeroRandom(limite); 
        tablero.cambiarColor(fila, columna, nuevoColor); // Actualizar el modelo
        if(tablero.ColoresIguales(fila, columna)) {
        	ArrayList<int[]> vecinos=tablero.obtenerPosicionesVecinas(fila, columna);
        	tablero.reiniciarColores(vecinos, fila, columna);
        	
        }
        if(tablero.ganaste()) {
        	tablero.notificarGano();;
        	System.out.println("aaa");

        }
    }

	
	
	
	
    
}

