package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import observador.Observador;

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
    public void notificar(String estado) {
        for (Observador observador : observadores) {
        	
        	if (estado.equals("gano")) {
        		observador.actualizarGano();
        	}
        	else if (estado.equals("actualizar")) {
        		observador.actualizar(tablero); // Notificamos el cambio
        	}
        }	
    }
   
 

    // Método para cambiar el color de una casilla en la posición (fila, columna)
    public void cambiarColor(int fila, int columna, int color) {
        tablero[fila][columna] = color;
        notificar("actualizar"); // Notificamos a la vista que el modelo ha cambiado
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
    public void notificarGano() {
    	notificar("gano");
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
        if (esPosicionValida(fila, col - 1)) { 
            posicionesVecinas.add(new int[] {fila, col - 1});  // Vecino a la izquierda
        }
        if (esPosicionValida(fila, col + 1)) { 
            posicionesVecinas.add(new int[] {fila, col + 1});  // Vecino a la derecha
        }
        if (esPosicionValida(fila - 1, col)) { 
            posicionesVecinas.add(new int[] {fila - 1, col});  // Vecino arriba
        }
        if (esPosicionValida(fila + 1, col)) { 
            posicionesVecinas.add(new int[] {fila + 1, col});  // Vecino abajo
        }

        // Devolvemos el ArrayList de posiciones vecinas válidas
        return posicionesVecinas;
    }
    
    public boolean ColoresIguales(int f,int c) {
    	

    	ArrayList<int[]> vecinos=obtenerPosicionesVecinas(f, c);
   	 
    	int [][] tab=getTablero();
    	int centro=tab[f][c];
   	    System.out.println("Color en la casilla central: " + centro);

   	    for( int[] casilla:vecinos) {
   	    	if (tab[casilla[0]][casilla[1]] == centro) {
   	    		System.out.println(tab[casilla[0]][casilla[1]]);

   	    		return true;
   			 
   	    	}
    
    
   	    }
   	    return false;
   }
    
    public  void  reiniciarColores(ArrayList<int[]> modificar, int fila, int columna ) {
    	for (int[] c: modificar ) {
    		
    		cambiarColor(c[0],c[1],0);
    		cambiarColor(fila,columna,0);
    		
    	}
    }
    
    
    public boolean ganaste() {
    	boolean ret=true;
    	int [][] tab=getTablero();
    	
    	for (int f=0;f<5;f++) {
        	for (int c=0;c<5;c++) {
        		ret=ret && tab[f][c]!=0;
        	}
    		
    	}
    	return ret;
    }

    
 }
