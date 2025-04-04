package modelo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class Assert {
	

	public static void matricesIguales(ArrayList<int[]> esperado, ArrayList<int[]> obtenido) {
		
		assertEquals(esperado.size(), obtenido.size());
		
		for(int i=0; i<esperado.size(); ++i) {
			// { [2, 2], [2, 4], [1, 3] [3, 3] }
			assertArrayEquals(esperado.get(i), obtenido.get(i));
				
        }
	}

	public static void matricesDiferentes(ArrayList<int[]> esperado, ArrayList<int[]> obtenido) {
		
		for(int i=0; i<esperado.size(); ++i) {
			assertFalse( Arrays.equals(esperado.get(i), obtenido.get(i)) );
        }
	}

}
