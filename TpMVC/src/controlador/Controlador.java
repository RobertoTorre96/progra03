package controlador;

import java.util.ArrayList;

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
    public void manejarClick(int fila, int columna, int cantColores) {
    	 tablero.aumentarClicsTotales();
         tablero.aumentarClicsSeguidos();
         tablero.mantenerMejorRacha();
        // Cambiar el color de la casilla (por ejemplo, al color rojo)
        int nuevoColor = this.tablero.numeroRandom(cantColores); 
        tablero.cambiarColor(fila, columna, nuevoColor); // Actualizar el modelo
        
        //reiniciar colores
        if(tablero.coloresIguales(fila, columna)) {
        	limpiarTablero(fila,columna);
        	
        }//gano
        else if(tablero.ganaste()) {
        	tablero.NotificarGano();;

        }
    }

	public void agregarObservador(Vista vista) {
		this.tablero.agregarObservador(vista);	
	}

	public String darTotalClicsAlMomento() {
		
		return Integer.toString(tablero.mostrarCantTotalDeClics());
	}

	public String DarMejorRacha() {
		return Integer.toString(tablero.mostrarMejorRacha());
	}

	
	
	private void limpiarTablero(int fila, int columna) {
		ArrayList<int[]> vecinos=tablero.obtenerPosicionesVecinas(fila, columna);
    	tablero.reiniciarColores(vecinos, fila, columna);
    	tablero.volverContadorClicsSeguidosACero();
		
	}
	
    
}

