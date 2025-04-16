package controlador;

import java.util.ArrayList;

import modelo.Tablero;
import vista.Vista;

public class Controlador {

    private Tablero tablero; 

    public Controlador() {
    	
    }
    
    public void crearTablero(int tamanioDelTablero) {
    	Tablero tablero = new Tablero(tamanioDelTablero);
    	this.tablero = tablero;
    }

    
    public void manejarClick(int fila, int columna, int cantColores) {
    	 tablero.aumentarClicsTotales();
         tablero.aumentarClicsSeguidos();
         tablero.mantenerMejorRacha();
        
        int nuevoColor = this.tablero.numeroRandom(cantColores); 
        tablero.cambiarColor(fila, columna, nuevoColor); 
        
       
        if(tablero.coloresIguales(fila, columna)) {
        	limpiarTablero(fila,columna);
        	
        }
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

