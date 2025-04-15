package modelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

public class Assert {
	

	public static void matricesIguales(ArrayList<int[]> esperado, ArrayList<int[]> obtenido) {
		
		assertEquals(esperado.size(), obtenido.size());
		
		for(int i=0; i<esperado.size(); ++i) {
			assertArrayEquals(esperado.get(i), obtenido.get(i));
				
        }
	}

	public static void matricesDiferentes(ArrayList<int[]> esperado, ArrayList<int[]> obtenido) {
		
		for(int i=0; i<esperado.size(); ++i) {
			assertFalse( Arrays.equals(esperado.get(i), obtenido.get(i)) );
        }
	}

}
