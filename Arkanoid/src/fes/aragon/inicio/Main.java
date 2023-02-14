package fes.aragon.inicio;

import fes.aragon.extras.MusicaCiclica;
import fes.aragon.modelo.CargaTileMap;
import fes.aragon.modelo.Escenario;
import fes.aragon.modelo.Fondo;
import fes.aragon.modelo.Pelota;
import fes.aragon.modelo.Personaje;
import fes.aragon.modelo.PersonajeAnimacion;
import fes.aragon.modelo.Piedra;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas hoja;
	private Thread hiloFondo;
	private Fondo fondo;
	private Personaje personaje;
	private PersonajeAnimacion personajeA;
	private Escenario mapa;
	private Pelota pelota;
	
	private CargaTileMap carga=null;

	@Override
	public void start(Stage ventana) {
		componentesIniciar();
		pintar();
		eventosTeclado();		
		ventana.setScene(escena);
		ventana.setTitle("Videojuego Demo");
		ventana.setX(Screen.getPrimary().getVisualBounds().getMaxX()+100);
		ventana.setY(40);
		ventana.show();
		ciclo();
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void stop() throws Exception {
		hiloFondo.stop();		
	}


	public static void main(String[] args) {
		launch(args);
	}

	private void componentesIniciar() {
		root = new Group();
		escena = new Scene(root, 600, 600);
		hoja = new Canvas(600, 600);
		root.getChildren().add(hoja);
		graficos = hoja.getGraphicsContext2D();
		MusicaCiclica entrada = new MusicaCiclica("musica_entrada");
		hiloFondo = new Thread(entrada);
		//hiloFondo.start();
		carga=new CargaTileMap();
		fondo=new Fondo(0, 0,"/fes/aragon/recursos/patron.png" , 1);		
		personaje=new Personaje(300, 550,"/fes/aragon/recursos/bloque.png" , 5);
		mapa=new Escenario(15, 0, "/fes/aragon/recursos/Bloques.png", 0);
		pelota=new Pelota(320,400,"/fes/aragon/recursos/bola.png",1);
		//personajeA=new PersonajeAnimacion(10, 200,"/fes/aragon/recursos/mario_caminar.png" , 2);		
		
		//piedra=new Piedra(50, 100, "/fes/aragon/recursos/piedra.png", 3);
	}
	public void ciclo() {
		long tiempoInicio=System.nanoTime();
		AnimationTimer tiempo=new AnimationTimer() {			
			@Override
			public void handle(long tiempoActual) {
				double t=(tiempoActual-tiempoInicio)/1000000000.0;
				//personajeA.setTiempo(t);
				calculosLogica();
				pintar();				
				
			}
		};
		tiempo.start();
	}
	private void pintar() {
		this.fondo.pintar(graficos);
		this.mapa.pintar(graficos);
		this.personaje.pintar(graficos);
		this.pelota.pintar(graficos);
		
	}
	private void calculosLogica() {
		this.fondo.logicaCalculos();
		this.personaje.logicaCalculos();
		this.pelota.logicaCalculos();
	}
	private void eventosTeclado() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent arg0) {				
				// TODO Auto-generated method stub
				fondo.teclado(arg0,true);
				personaje.teclado(arg0,true);
				//personajeA.teclado(arg0,true);
			}			
		});
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				personaje.teclado(arg0,false);
				//personajeA.teclado(arg0,false);
			}
			
		});
		
	}
}
