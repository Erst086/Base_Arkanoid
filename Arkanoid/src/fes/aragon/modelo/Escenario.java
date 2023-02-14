package fes.aragon.modelo;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class Escenario extends ComponentesJuego {
	public static final List<CuadroTileMap> cuadros=new ArrayList<>();
	private Image imagen;
	private int[][] mapeo= {
			{1,1,1,1,1,1,1,1,1,1,1},
			{2,2,2,2,2,2,2,2,2,2,2},
			{3,3,3,3,3,3,3,3,3,3,3},
			{4,4,4,4,4,4,4,4,4,4,4},			
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},			
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0},
	};
	

	public Escenario(int x, int y, String imagen, int velocidad) {
		super(x, y, imagen, velocidad);
		
		for (int i = 0; i < mapeo.length; i++) {
			for (int j = 0; j < mapeo.length; j++) {
				if(mapeo[i][j]!=0) {
					CuadroTileMap c=new CuadroTileMap(CargaTileMap.tileMap.get(
							mapeo[i][j]));					
					c.setxVentana(x);
					c.setyVentana(y);
					c.setRectangulo(new Rectangle(x, y, c.getAnchoVentanaPintar(), 
							c.getAltoVentanaPintar()));
					cuadros.add(c);					
				}
				x+=52;				
			}
			x=15;
			y+=52;
		}
		this.imagen=new Image(imagen);
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		for (CuadroTileMap cuadro : cuadros) {
			graficos.drawImage(imagen,cuadro.getCoorXImagenDentro(),
					cuadro.getCoorYImagenDentro(),cuadro.getAnchoImagenDentro(),
					cuadro.getAltoImagenDentro(),cuadro.getxVentana(),
					cuadro.getyVentana(),cuadro.getAnchoVentanaPintar(),
					cuadro.getAltoVentanaPintar());
			graficos.strokeRect(cuadro.getxVentana(),
					cuadro.getyVentana(),
					cuadro.getAnchoVentanaPintar(),cuadro.getAltoVentanaPintar());
			
		}
		
	}

	@Override
	public void teclado(KeyEvent evento,boolean presiona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void raton(KeyEvent evento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logicaCalculos() {
		// TODO Auto-generated method stub
		
		
	}

}
