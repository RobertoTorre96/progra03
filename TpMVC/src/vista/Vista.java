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
    private JLabel labelIndicaQueHacer, labelTotalDeClicsAlMomento, labelMejorRacha; 
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
    
    
    /*===========METODO NUEVO PARA QUE se cree el tablero con la dificultad seleccionada========*/
    
    	private void CrearVistaDeTablero(int limite, int tamanioDelTablero) {         
        // Inicializar la interfaz gráfica
        frame = new JFrame("Juego de Tablero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); 
        panelTablero = new JPanel(new GridLayout(tamanioDelTablero, tamanioDelTablero)); // Tablero de 5x5
        
        labelIndicaQueHacer = new JLabel("Haz clic en una casilla");
        labelIndicaQueHacer.setHorizontalAlignment(SwingConstants.CENTER);
        
        labelTotalDeClicsAlMomento = new JLabel("Total de clics: " + controlador.darTotalClicsAlMomento());
        labelMejorRacha = new JLabel ("Mejor racha al momento: " + controlador.darMejorRacha());
        
        JPanel panelLabels = new JPanel(new GridLayout(1, 2)); // Un panel con 1 fila y 2 columnas
        panelLabels.add(labelTotalDeClicsAlMomento);
        panelLabels.add(labelMejorRacha);
        
        // Crear las casillas del tablero (botones o paneles)
        for (int i = 0; i < tamanioDelTablero; i++) {
            for (int j = 0; j < tamanioDelTablero; j++) {
                JButton casilla = new JButton();

                // Agregar el ActionListener para manejar los clics en la casilla
                casilla.addActionListener(CrearEventoClick(i, j, limite));

                panelTablero.add(casilla);
            }
        }
        
        

        frame.add(panelTablero);
        frame.add(labelIndicaQueHacer, BorderLayout.NORTH);
        frame.add(panelLabels, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    
    //=======metodo para que el usuario elija la dificultad===========
    public void seleccionarDificultad( int tamanioDelTablero) {
        frame = new JFrame("Seleccionar Dificultad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); 
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton btnFacil = new JButton("Fácil");
        JButton btnMedio = new JButton("Medio");
        JButton btnDificil = new JButton("Difícil");
        btnFacil.setPreferredSize(new Dimension (20,20));
        
        panel.add(btnFacil);
        panel.add(btnMedio);
        panel.add(btnDificil);

        frame.add(panel);
        frame.setVisible(true);
        
        btnFacil.addActionListener(e -> {frame.dispose();CrearVistaDeTablero( 7, tamanioDelTablero);});
        btnMedio.addActionListener(e -> {frame.dispose();CrearVistaDeTablero( 5, tamanioDelTablero); });
        btnDificil.addActionListener(e -> {frame.dispose();CrearVistaDeTablero( 4, tamanioDelTablero); });
    }
    
    
    //=========metodo para que el usuario elija el tamaño del tablero 
    
    public Vista(Controlador controlador) {
    	this.controlador = controlador;
        frame = new JFrame("Seleccionar Tamaño del tablero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null); 
        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton btn5x5 = new JButton("Tablero de 5x5");
        JButton btn7x7 = new JButton("Tablero de 7x7");
        JButton btn9x9 = new JButton("Tablero de 9x9");
        
        panel.add(btn5x5);
        panel.add(btn7x7);
        panel.add(btn9x9);

        frame.add(panel);
        frame.setVisible(true);
        
        btn5x5.addActionListener(e -> {frame.dispose();seleccionarDificultad(5);controlador.crearTablero(5); controlador.agregarObservador(this);});
        btn7x7.addActionListener(e -> {frame.dispose();seleccionarDificultad(7);controlador.crearTablero(7); controlador.agregarObservador(this); });
        btn9x9.addActionListener(e -> {frame.dispose();seleccionarDificultad(9);controlador.crearTablero(9);controlador.agregarObservador(this); });
    }
    
    
    
    

    // Método que crea el ActionListener para cada casilla
    private ActionListener CrearEventoClick(int fila, int columna, int limite) {
    	 return new ActionListener() {
             

			@Override
			public void actionPerformed(ActionEvent e) {
				// Llamar al controlador cuando se hace clic en una casilla
                controlador.manejarClick(fila, columna, limite);
                labelTotalDeClicsAlMomento.setText("Total de clics: " + controlador.darTotalClicsAlMomento());
                labelMejorRacha.setText("Mejor racha al momento: " + controlador.darMejorRacha());
				
			}
         };
    }

    
    // Método que actualiza la vista cuando el modelo cambia
    @Override
    public void actualizar(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                JButton casilla = (JButton) panelTablero.getComponent(i * tablero[0].length + j);
                int color = tablero[i][j];     
                
                Color colorAsignado;
                switch (color) {
                    case 1:
                        colorAsignado = Color.RED;
                        break;
                    case 2:
                        colorAsignado = Color.GREEN;
                        break;
                    case 3:
                        colorAsignado = Color.BLUE;
                        break;
                    case 4:
                        colorAsignado = Color.YELLOW;
                        break;
                    case 5:
                        colorAsignado = Color.MAGENTA;
                        break;
                    case 6:
                        colorAsignado = Color.ORANGE;
                        break;
                    case 7:
                        colorAsignado = Color.PINK;
                        break;
                    default:
                        colorAsignado = Color.WHITE; 
                }

                casilla.setBackground(colorAsignado);
            }
        }
        
       
    }
 
	@Override
	public void actualizarGano() {		
		/*JPanel laminaGanaste=new JPanel();
		laminaGanaste.setLayout(new BorderLayout());
    	JLabel labelGanaste=new JLabel("Ganaste");
    	laminaGanaste.add(labelGanaste,BorderLayout.CENTER);
    	frame.remove(panelTablero);
    	frame.add(laminaGanaste);
        frame.setVisible(true);*/
		
		 // Crear un nuevo panel para mostrar la imagen de "Ganaste"
	    JPanel laminaGanaste = new JPanel();
	    laminaGanaste.setLayout(new BorderLayout());

	    // Cargar la imagen desde resources (asegúrate de que la imagen esté en src/resources/)
	    ImageIcon iconoGanaste = new ImageIcon(getClass().getResource("/img/ganaste.jpg")); // Ajusta la ruta según tu proyecto
	    JLabel labelImagen = new JLabel(iconoGanaste);
	    
	    // Agregar la imagen al panel
	    laminaGanaste.add(labelImagen, BorderLayout.CENTER);

	    // Opcional: Agregar un texto adicional debajo de la imagen
	    JLabel labelTexto = new JLabel("¡Ganaste!", SwingConstants.CENTER);
	    laminaGanaste.add(labelTexto, BorderLayout.SOUTH);

	    // Reemplazar el panel actual por el nuevo
	    frame.remove(panelTablero);
	    frame.add(laminaGanaste);
	    frame.revalidate(); // Para refrescar el contenido del JFrame
	    frame.repaint(); // Para actualizar la vista
	
	}
	
	

	
}
