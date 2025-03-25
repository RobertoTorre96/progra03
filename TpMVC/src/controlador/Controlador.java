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
    public void manejarClick(int fila, int columna) {
        // Cambiar el color de la casilla (por ejemplo, al color rojo)
        int nuevoColor = numeroRandom(); 
        tablero.cambiarColor(fila, columna, nuevoColor); // Actualizar el modelo
        if(ColoresIguales(fila, columna)) {
        	ArrayList<int[]> vecinos=obtenerPosicionesVecinas(fila, columna);
        	reiniciarColores(vecinos);
        	
        }
        if(!ganaste()) {
        	tablero.ganaste();
        	System.out.println("aaa");

        }
    }
    
    
    public int numeroRandom () {
    	// Crear una instancia de Random
    	Random random = new Random();
    	
    	// Generar un número aleatorio entre 1 y 6
    	int randomNumber = random.nextInt(5) + 1;
    	
    	return randomNumber;
    }  
    
    public ArrayList<int[]> obtenerPosicionesVecinas(int fila, int col) {
        ArrayList<int[]> posicionesVecinas = new ArrayList<>();  // Usamos un ArrayList para almacenar las posiciones vecinas

        // Verificar cada vecino y agregarlo al ArrayList si es válido
        if (tablero.esPosicionValida(fila, col - 1)) { 
            posicionesVecinas.add(new int[] {fila, col - 1});  // Vecino a la izquierda
        }
        if (tablero.esPosicionValida(fila, col + 1)) { 
            posicionesVecinas.add(new int[] {fila, col + 1});  // Vecino a la derecha
        }
        if (tablero.esPosicionValida(fila - 1, col)) { 
            posicionesVecinas.add(new int[] {fila - 1, col});  // Vecino arriba
        }
        if (tablero.esPosicionValida(fila + 1, col)) { 
            posicionesVecinas.add(new int[] {fila + 1, col});  // Vecino abajo
        }

        // Devolvemos el ArrayList de posiciones vecinas válidas
        return posicionesVecinas;
    }
    
    public  void  reiniciarColores(ArrayList<int[]> modificar) {
    	for (int[] c: modificar ) {
    		
    		tablero.cambiarColor(c[0],c[1],0);
    		
    	}
    }
    
    public boolean ColoresIguales(int f,int c) {

    	 ArrayList<int[]> vecinos=obtenerPosicionesVecinas(f, c);
    	 
    	 int [][] tab=tablero.getTablero();
    	 int centro=tab[f][c];
    	    System.out.println("Color en la casilla central: " + centro);

    	 for( int[] casilla:vecinos) {
    		 if (tab[casilla[0]][casilla[1]] == centro) {
    			 System.out.println(tab[casilla[0]][casilla[1]]);

    			 return true;
    			 
    		 }
    		 
    		 System.out.println(tab[casilla[0]][casilla[1]]);

    	 }
    	 return false;
    	 
    	 
    	
    }
    
    public boolean ganaste() {
    	boolean ret=true;
    	int [][] tab=tablero.getTablero();
    	
    	for (int f=0;f<5;f++) {
        	for (int c=0;c<5;c++) {
        		ret=ret && tab[f][c]!=0;
        	}
    		
    	}
    	return ret;
    }


    	
    	
    	
		
    
}

