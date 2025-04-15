package vista;

import controlador.Controlador;
import observador.Observador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vista implements Observador {

    private Controlador controlador; 
    private JFrame ventana;
    private JPanel panelTablero;
    private JLabel labelTotalDeClicsAlMomento, labelMejorRacha; 
    JLabel panelVentanaPrincipal;
    JPanel panelMenuTamtablero = new JPanel();
    
    
    
    public Vista(Controlador controlador) {
        this.controlador = controlador;
        
        
        ventana = new JFrame("Histeria");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 500);
        ventana.setLocationRelativeTo(null);
        
        
        panelVentanaPrincipal = new JLabel(new ImageIcon("src/img/fondoDeJuego.jpg"));
        panelVentanaPrincipal.setLayout(new GridBagLayout());
        ventana.setContentPane(panelVentanaPrincipal);
        
        crearMenuTamTablero();
    }
      
    public void crearMenuTamTablero() {
		
        panelMenuTamtablero.setLayout(new GridLayout(3, 1, 10, 10)); 
        panelMenuTamtablero.setOpaque(false); 
        panelMenuTamtablero.setBounds(150, 150, 200, 180);
        
        
        JButton btn5x5 = crearBotonMenu("Tablero de 5x5", new Color(0, 153, 255), Color.WHITE);
        JButton btn7x7 = crearBotonMenu("Tablero de 7x7", new Color(0, 204, 102), Color.BLACK);
        JButton btn9x9 = crearBotonMenu("Tablero de 9x9", new Color(255, 102, 102), Color.WHITE);
        
        btn5x5.addActionListener(evenetoMenuTamtablero(5, this));
        btn7x7.addActionListener(evenetoMenuTamtablero(7, this));
        btn9x9.addActionListener(evenetoMenuTamtablero(9, this));
        
        	
        panelMenuTamtablero.add(btn5x5);
        panelMenuTamtablero.add(btn7x7);
        panelMenuTamtablero.add(btn9x9);    
        
        
        
        panelVentanaPrincipal.add(panelMenuTamtablero);
        
        ventana.setVisible(true);
     
	}
    public void seleccionarDificultad( int tamanioDelTablero) {
        
        
        JPanel panelSeleccionDificultad = new JPanel(new GridLayout(3, 1));
       
        JButton btnFacil = crearBotonMenu("Fácil", new Color(144, 238, 144), Color.BLACK);
        JButton btnMedio = crearBotonMenu("Medio", new Color(255, 215, 0), Color.BLACK);
        JButton btnDificil = crearBotonMenu("Difícil", new Color(255, 69, 0), Color.WHITE);

        panelSeleccionDificultad.add(btnFacil);
        panelSeleccionDificultad.add(btnMedio);
        panelSeleccionDificultad.add(btnDificil);
        

        panelSeleccionDificultad.setBounds(150, 150, 200, 180);  
        panelVentanaPrincipal.add(panelSeleccionDificultad);
       

        panelVentanaPrincipal.revalidate();
        panelVentanaPrincipal.repaint(); 


        btnFacil.addActionListener(e -> {
        	panelVentanaPrincipal.remove(panelSeleccionDificultad);
        	crearVistaDeTablero(7, tamanioDelTablero);
        	crearFooter();


        });
        
        btnMedio.addActionListener(e -> {
        	
        	panelVentanaPrincipal.remove(panelSeleccionDificultad);
        	crearVistaDeTablero(5, tamanioDelTablero);
        	crearFooter();

        });
        
        btnDificil.addActionListener(e -> {
        	panelVentanaPrincipal.remove(panelSeleccionDificultad);
        	crearVistaDeTablero(4, tamanioDelTablero);
        	crearFooter();

        });
    } 
    private void crearVistaDeTablero(int limite, int tamanioDelTablero) {         
    
    	 panelTablero=new JPanel(new GridLayout(tamanioDelTablero,tamanioDelTablero));
    	
    	
        for (int i = 0; i < tamanioDelTablero; i++) {
            for (int j = 0; j < tamanioDelTablero; j++) {
                JButton casilla = new JButton();

                casilla.addActionListener(evenetoClickCasilla(i, j, limite));

                panelTablero.add(casilla);
            }
            panelVentanaPrincipal.setLayout(new BorderLayout());
            panelVentanaPrincipal.add(panelTablero,BorderLayout.CENTER);

            panelVentanaPrincipal.validate();
            panelVentanaPrincipal.repaint();
           
        }
      
    }
    
    public void crearFooter() {
    	
    	labelTotalDeClicsAlMomento = new JLabel("Total de clics: " + controlador.darTotalClicsAlMomento());
        labelMejorRacha = new JLabel ("Mejor racha al momento: " + controlador.DarMejorRacha());
        
        JPanel panelLabels = new JPanel(new GridLayout(1, 2)); 
        panelLabels.add(labelTotalDeClicsAlMomento);
        panelLabels.add(labelMejorRacha);
        
        panelVentanaPrincipal.setLayout(new BorderLayout());
        panelVentanaPrincipal.add(panelTablero,BorderLayout.CENTER);
        panelVentanaPrincipal.add(panelLabels, BorderLayout.SOUTH);

    	

        panelVentanaPrincipal.validate();
        panelVentanaPrincipal.repaint();
    	
    }
      
    private JButton crearBotonMenu(String texto, Color colorFondo, Color colorTexto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(180, 60)); 
        boton.setBackground(colorFondo); 
        boton.setForeground(colorTexto); 
        boton.setFont(new Font("Arial", Font.BOLD, 16)); 
        boton.setFocusPainted(false); 
        boton.setBorder(BorderFactory.createRaisedBevelBorder()); 
        return boton;
    }
    
    

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
		
		panelVentanaPrincipal.remove(panelTablero);
		 
	    JPanel laminaGanaste = new JPanel();
	    laminaGanaste.setLayout(new BorderLayout());
		
	    ImageIcon iconoGanaste = new ImageIcon(getClass().getResource("/img/ganaste.jpg")); 
	    JLabel labelImagen = new JLabel(iconoGanaste);
	    
	    laminaGanaste.add(labelImagen, BorderLayout.CENTER);

	    panelVentanaPrincipal.add(laminaGanaste,BorderLayout.CENTER);
	
	
	}
	
	private ActionListener evenetoClickCasilla(int fila, int columna, int cantColores) {
	   	 return new ActionListener() {
	            

				@Override
				public void actionPerformed(ActionEvent e) {
				
	               controlador.manejarClick(fila, columna, cantColores);
	               labelTotalDeClicsAlMomento.setText("Total de clics: " + controlador.darTotalClicsAlMomento());
	               labelMejorRacha.setText("Mejor racha al momento: " + controlador.DarMejorRacha());
					
				}
	        };
	        
	        
   }
	private ActionListener evenetoMenuTamtablero(int tam,Vista vista) {
	   	 return new ActionListener() {
	            

				@Override
				public void actionPerformed(ActionEvent e) {
			
					panelVentanaPrincipal.remove(panelMenuTamtablero);
		            seleccionarDificultad(tam);
		            controlador.crearTablero(tam);
		            controlador.agregarObservador(vista);
				}
	        };

	}
	
	
	
   
  
	

	
}
