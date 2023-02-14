package fes.aragon.modelo;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pelota extends ComponentesJuego {
	private int gravedad = 2;
	private boolean desciende = false;
	private boolean salto = false;
	public static Rectangle rectangulo;
	private Image imagen;
	private boolean derecha = false;
	private boolean izquierda = false;
	private boolean arriba = false;
	private boolean abajo = false;
	private double radianes;

	public Pelota(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		this.imagen = new Image(imagen);
		rectangulo = new Rectangle(x, y, this.imagen.getWidth(), this.imagen.getHeight());
		this.abajo=true;		
		radianes=Math.toRadians(270);

	}

	@Override
	public void pintar(GraphicsContext graficos) {
		// TODO Auto-generated method stub
		graficos.drawImage(imagen, x, y);
		graficos.setStroke(Color.RED);
		graficos.strokeRect(rectangulo.getX(), rectangulo.getY(), rectangulo.getWidth(), rectangulo.getHeight());
		graficos.setStroke(Color.BLACK);

	}

	@Override
	public void teclado(KeyEvent evento, boolean presiona) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub

	}

	@Override
	public void logicaCalculos() {
		// TODO Auto-generated method stub
		if (arriba && (x+imagen.getWidth()>=600)) {
			int grados=(int)(Math.random()*(135-90)+90);	
			System.out.println(grados+" limite derecha");
			radianes=Math.toRadians(grados);
			this.derecha=false;
			this.izquierda=true;
		}
		if (arriba && (x<=0)) {
			int grados=((int)(Math.random()*(180-90)+90));	
			System.out.println(grados+" limite izquierda");
			radianes=Math.toRadians(grados);
			this.derecha=true;
			this.izquierda=false;
		}
		if (arriba && (y<=0)) {
			int grados=((int)(Math.random()*(225-180)+180));	
			System.out.println(grados+" limite arriba");
			radianes=Math.toRadians(grados);
			this.derecha=false;
			this.izquierda=true;
			this.arriba=false;
			this.abajo=true;
		}
		
		
		if(abajo) {			
			
			x=x+(int)(Math.cos(radianes)+velocidad);
			y=y-(int)(Math.sin(radianes)+velocidad)+velocidad;	
		}
		
		if(arriba && derecha) {
			x=x+(int)(Math.cos(radianes)+velocidad);
			y=y-(int)(Math.sin(radianes)+velocidad);
		}
		if(arriba && izquierda) {
			x=x-(int)(Math.cos(radianes)+velocidad)-1;
			y=y-(int)(Math.sin(radianes)+velocidad)-1;
		}
		
		this.rectangulo.setX(x);
		this.rectangulo.setY(y);

		// colision general con barra
		if(rectangulo.getBoundsInLocal().intersects(Personaje.rectangulo.getBoundsInLocal())) {
			if(abajo) {		
				int grados=(int)(Math.random()*(135-45)+45);
				if(grados>=45 && grados<90) {
					abajo=false;
					arriba=true;
					izquierda=false;
					derecha=true;
				}
				if(grados>=90 && grados<=135) {
					abajo=false;
					arriba=true;
					izquierda=true;
					derecha=false;					
				}				
				System.out.println(grados);
				radianes=Math.toRadians(grados);
			}
		}
		
		
		
	}

}
