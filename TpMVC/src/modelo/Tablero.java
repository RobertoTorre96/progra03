package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import observador.Observador;

public class Tablero {

    private int[][] tablero; 
    private List<Observador> observadores; 
    private int cantidadDeCasillasDelTablero, cantidadDeClicsTotal,cantidadDeClics, mejorRacha;
	

    public Tablero(int cantidadDeCasillasDelTablero) {
    	this.cantidadDeCasillasDelTablero =cantidadDeCasillasDelTablero;
    	this.tablero = new int[this.cantidadDeCasillasDelTablero]
    						  [this.cantidadDeCasillasDelTablero]; 
        this.observadores = new ArrayList<>();
     
    }

   
    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    
    public void notificar() {
        for (Observador observador : observadores) {
        	
        	if (ganaste()) {
        		observador.actualizarGano();
        	}
        	else  {
        		
        		observador.actualizar(tablero);
        	}
        }	
    }
    public void NotificarGano() {
    	notificar();
    }
    
   
 

    
    public void cambiarColor(int fila, int columna, int color) {
        tablero[fila][columna] = color;
        notificar(); 
        
        
    }

   
    public int[][] getTablero() {
        return tablero;
    }
    
    public boolean esPosicionValida(int f, int c) {
    	
    	
    	if (f>=0 && f<this.cantidadDeCasillasDelTablero && c<this.cantidadDeCasillasDelTablero && c>=0) {
    		return true;
    	}
    	return false;
    	
    	
    }
 
    public int numeroRandom (int limite) {
    	
    	Random random = new Random();
    	
    	
    	int randomNumber = random.nextInt(limite) + 1;
    	
    	return randomNumber;
    }  
    
    public ArrayList<int[]> obtenerPosicionesVecinas(int fila, int col) {
        ArrayList<int[]> posicionesVecinas = new ArrayList<>();  
	 
        if (esPosicionValida(fila, col - 1)) { 
            posicionesVecinas.add(new int[] {fila, col - 1}); 
        }
        if (esPosicionValida(fila, col + 1)) { 
            posicionesVecinas.add(new int[] {fila, col + 1});  
        }
        if (esPosicionValida(fila - 1, col)) { 
            posicionesVecinas.add(new int[] {fila - 1, col});  
        }
        if (esPosicionValida(fila + 1, col)) { 
            posicionesVecinas.add(new int[] {fila + 1, col}); 
        }

       
        return posicionesVecinas;
    }
    
    public boolean coloresIguales(int f,int c) {
    	

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
    
    public int darCantidadDeCasillasDelTablero() {
    	return this.cantidadDeCasillasDelTablero;
    }
    
    
    public void aumentarClicsTotales() {
    	++cantidadDeClicsTotal;
    }
    
    public void aumentarClicsSeguidos() {
    	++cantidadDeClics;
    	
    }
    
    public void mantenerMejorRacha() {
    	if (cantidadDeClics > mejorRacha) {
    		mejorRacha = cantidadDeClics;
    	}
    }
    
    public void volverContadorClicsSeguidosACero() {
    	cantidadDeClics = 0;
    }
    
    public int mostrarCantTotalDeClics() {
    	return cantidadDeClicsTotal;
 }
    public int mostrarMejorRacha() {
    	return mejorRacha;
    }
    
}
