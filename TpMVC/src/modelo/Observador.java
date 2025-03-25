package modelo;

public interface Observador {
    // Método que es llamado cuando el modelo ha cambiado
	void actualizar(int[][] tablero);
	void actualizar(boolean b) ;
    
    
}
