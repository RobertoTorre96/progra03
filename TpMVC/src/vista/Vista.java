package vista;

import controlador.Controlador;
import modelo.Tablero;
import observador.Observador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

public class Vista implements Observador {

    private Tablero tablero; // El modelo
    private Controlador controlador; // El controlador
    private JFrame frame;
    private JPanel panelTablero;

    public Vista(Tablero tablero, Controlador controlador) {
        this.tablero = tablero;
        this.controlador = controlador;
        tablero.agregarObservador(this); // Nos registramos como observador del modelo

        // Inicializar la interfaz gráfica
        frame = new JFrame("Juego de Tablero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        panelTablero = new JPanel(new GridLayout(5, 5)); // Tablero de 5x5

        // Crear las casillas del tablero (botones o paneles)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JButton casilla = new JButton();
                casilla.setPreferredSize(new Dimension(60, 60));

                // Agregar el ActionListener para manejar los clics en la casilla
                casilla.addActionListener(CrearEventoClick(i, j));

                panelTablero.add(casilla);
            }
        }

        frame.add(panelTablero);
        frame.setVisible(true);
    }

    // Método que crea el ActionListener para cada casilla
    private ActionListener CrearEventoClick(int fila, int columna) {
    	 return new ActionListener() {
             

			@Override
			public void actionPerformed(ActionEvent e) {
				// Llamar al controlador cuando se hace clic en una casilla
                controlador.manejarClick(fila, columna);
				
			}
         };
    }

    // Método que actualiza la vista cuando el modelo cambia
    @Override
    public void actualizar(int[][] tablero) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JButton casilla = (JButton) panelTablero.getComponent(i * 5 + j);
                int color = tablero[i][j];

                // Cambiar el color del botón basado en el valor de la matriz
                if (color == 0) {
                    casilla.setBackground(Color.WHITE);
                } else if (color == 1) {
                    casilla.setBackground(Color.RED);
                } else if (color == 2) {
                    casilla.setBackground(Color.GREEN);
                } else if (color == 3) {
                    casilla.setBackground(Color.BLUE);
                } else if (color == 4) {
                    casilla.setBackground(Color.YELLOW);
                } else {
                    casilla.setBackground(Color.MAGENTA);
                }
            }
        }
        
       
    }
 
	@Override
	public void actualizarGano() {
		// TODO Auto-generated method stub
		
		JPanel laminaGanaste=new JPanel();
		laminaGanaste.setLayout(new BorderLayout());
    	JLabel labelGanaste=new JLabel("ganate");
    	laminaGanaste.add(labelGanaste,BorderLayout.CENTER);
    	frame.remove(panelTablero);
    	frame.add(laminaGanaste);
        frame.setVisible(true);
		
	}

	
}
