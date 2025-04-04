package modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TableroTest {
	
	Tablero tablero5X5 = new Tablero(5);
	int[] vecinoIzquierdo = new int[] {2, 2};
	int[] vecinoDerecho = new int[] {2, 4};
	int[] vecinoArriba = new int[] {1, 3};
	int[] vecinoAbajo = new int[] {3, 3};
	
	/*@Before
	public void inicializar() {}*/
	
	
	@Test
	public void esPosicionValidaTest() {
		assertTrue(tablero5X5.esPosicionValida(2, 4));
	}

	@Test
	public void esPosicionInvalidaTest() {
		assertFalse(tablero5X5.esPosicionValida(7, 9));
	}
	
	@Test
    public void numeroRandomDentroDelRangoTest() {
        int limite = 5;
        for (int i = 0; i < 100; i++) {
            int resultado = tablero5X5.numeroRandom(limite);
            assertTrue(resultado>=0 && resultado<=limite);
        }
    }
	
	@Test
	public void obtenerVecinosValidosTest() {
		ArrayList<int[]> posicionesVecinas = new ArrayList<>();
		
		posicionesVecinas.add(vecinoIzquierdo);
		posicionesVecinas.add(vecinoDerecho);
		posicionesVecinas.add(vecinoArriba);
		posicionesVecinas.add(vecinoAbajo);
		
		ArrayList<int[]> resultado = tablero5X5.obtenerPosicionesVecinas(2, 3);
		
		Assert.matricesIguales(posicionesVecinas, resultado);
		
	}

	@Test
	public void obtenerVecinosInvalidosTest() {
		ArrayList<int[]> posicionesVecinas = new ArrayList<>();
		
		posicionesVecinas.add(vecinoIzquierdo);
		posicionesVecinas.add(vecinoDerecho);
		posicionesVecinas.add(vecinoArriba);
		posicionesVecinas.add(vecinoAbajo);
		
		ArrayList<int[]> resultado = tablero5X5.obtenerPosicionesVecinas(1, 1);
		
		Assert.matricesDiferentes(posicionesVecinas, resultado);
		
	}
	
	@Test
	public void comprobarCoincidenciaColoresVecinosTest() {
		tablero5X5.cambiarColor(4, 0, 1); // casilla 4,0 rojo
		tablero5X5.cambiarColor(4, 1, 1); // casilla 4,1 rojo
		
		assertTrue(tablero5X5.ColoresIguales(4, 1));
		
	}
	
	@Test
	public void comprobarColoresVecinosDesigualesTest() {
		tablero5X5.cambiarColor(4, 0, 1); // casilla 4,0 rojo
		tablero5X5.cambiarColor(4, 1, 2); // casilla 4,1 verde
		
		assertFalse(tablero5X5.ColoresIguales(4, 1));
		
	}
	
	
	@Test
	public void ganasteTest() {
		completarTablero();
		assertTrue(tablero5X5.ganaste());
	}
	
	@Test
	public void noGanasteTest() {
		Tablero tableroVacio = new Tablero(5);
		assertFalse(tableroVacio.ganaste());
	}
	
	
	public void completarTablero() {
		int color = 1;
		for(int f = 0; f<5; f++) {
			for(int c = 0; c<5; c++) {
				tablero5X5.cambiarColor(f, c, color);
				color = color + 1;
			}
		}
	}
	
	
	
	
	
	
	


}
