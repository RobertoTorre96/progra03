package controlador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import modelo.Tablero;
import vista.Vista;

public class Controlador {

    private Tablero tablero; // El modelo

    public Controlador() {
    	
    }
    
    public void crearTablero(int tamanioDelTablero) {
    	Tablero tablero = new Tablero(tamanioDelTablero);
    	this.tablero = tablero;
    }

    // Método que maneja el evento de clic sobre una casilla
    public void manejarClick(int fila, int columna, int limite) {
    	 tablero.aumentarClicsTotales();
         tablero.aumentarClicsSeguidos();
         tablero.mantenerMejorRacha();
        // Cambiar el color de la casilla (por ejemplo, al color rojo)
        int nuevoColor = this.tablero.numeroRandom(limite); 
        tablero.cambiarColor(fila, columna, nuevoColor); // Actualizar el modelo
        if(tablero.ColoresIguales(fila, columna)) {
        	ArrayList<int[]> vecinos=tablero.obtenerPosicionesVecinas(fila, columna);
        	tablero.reiniciarColores(vecinos, fila, columna);
        	tablero.volverContadorClicsSeguidosACero();
        	
        }
        if(tablero.ganaste()) {
        	tablero.notificarGano();;
        	System.out.println("aaa");

        }
    }

	public void agregarObservador(Vista vista) {
		this.tablero.agregarObservador(vista);	
	}

	public String darTotalClicsAlMomento() {
		
		return Integer.toString(tablero.mostrarCantTotalDeClics());
	}

	public String darMejorRacha() {
		return Integer.toString(tablero.mostrarMejorRacha());
	}

	
	
	
	
    
}

