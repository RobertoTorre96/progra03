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
    /*=====METODO ORIGINAL=========
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
    }*/
    
    
    /*===========METODO NUEVO PARA QUE PRIMERO ME PIDA ELEGIR DIFICULTAD========*/
    
    	private void CrearVistaDeTablero(Tablero tablero, Controlador controlador, int limite) {
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
                casilla.addActionListener(CrearEventoClick(i, j, limite));

                panelTablero.add(casilla);
            }
        }

        frame.add(panelTablero);
        frame.setVisible(true);
    }
    
    //=======metodo para que el usuario elija la dificultad===========
    public Vista(Tablero tablero, Controlador controlador) {
        frame = new JFrame("Seleccionar Dificultad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton btnFacil = new JButton("Fácil");
        JButton btnMedio = new JButton("Medio");
        JButton btnDificil = new JButton("Difícil");
        btnFacil.setPreferredSize(new Dimension (20,20));

        btnFacil.addActionListener(e -> CrearVistaDeTablero( tablero, controlador, 7));
        btnMedio.addActionListener(e -> CrearVistaDeTablero( tablero, controlador,5 ));
        btnDificil.addActionListener(e -> CrearVistaDeTablero( tablero, controlador, 4));

        panel.add(btnFacil);
        panel.add(btnMedio);
        panel.add(btnDificil);

        frame.add(panel);
        frame.setVisible(true);
    }
    

    // Método que crea el ActionListener para cada casilla
    private ActionListener CrearEventoClick(int fila, int columna, int limite) {
    	 return new ActionListener() {
             

			@Override
			public void actionPerformed(ActionEvent e) {
				// Llamar al controlador cuando se hace clic en una casilla
                controlador.manejarClick(fila, columna, limite);
				
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
                }else if (color == 5){
                    casilla.setBackground(Color.MAGENTA);
                }else if (color == 6){
                	casilla.setBackground(Color.ORANGE);
                }else{
                	casilla.setBackground(Color.PINK);
                }
            }
        }
        
       
    }
 
	@Override
	public void actualizarGano() {
		// TODO Auto-generated method stub
		
		JPanel laminaGanaste=new JPanel();
		laminaGanaste.setLayout(new BorderLayout());
    	JLabel labelGanaste=new JLabel("Ganaste");
    	laminaGanaste.add(labelGanaste,BorderLayout.CENTER);
    	frame.remove(panelTablero);
    	frame.add(laminaGanaste);
        frame.setVisible(true);
	
	}
	
	

	
}
