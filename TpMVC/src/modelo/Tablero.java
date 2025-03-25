package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

    private int[][] tablero; // Matriz 5x5 para representar los colores
    private List<Observador> observadores; // Lista de observadores

    public Tablero() {
        this.tablero = new int[5][5]; // Inicializamos un tablero 5x5 en 0 (sin color)
        this.observadores = new ArrayList<>();
    }

    // Método para agregar un observador
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    // Método para notificar a todos los observadores
    public void notificar() {
        for (Observador observador : observadores) {
            observador.actualizar(tablero); // Notificamos el cambio
        }
    }
    public void notificar(boolean b) {
        for (Observador observador : observadores) {
            observador.actualizar(true); // Notificamos el cambio
        }
    }

    // Método para cambiar el color de una casilla en la posición (fila, columna)
    public void cambiarColor(int fila, int columna, int color) {
        tablero[fila][columna] = color;
        notificar(); // Notificamos a la vista que el modelo ha cambiado
    }

    // Método para obtener el tablero
    public int[][] getTablero() {
        return tablero;
    }
    
    public boolean esPosicionValida(int f, int c) {
    	
    	
    	if (f>=0 && f<5 && c<5 && c>=0) {
    		return true;
    	}
    	return false;
    	
    	
    }
    public void ganaste() {
    	notificar(true);
    }
}
