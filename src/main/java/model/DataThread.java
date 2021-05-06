package model;

import java.io.Serializable;
import java.util.ArrayList;

import enums.PlantType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DataThread extends Thread implements Serializable{
	private static final long serialVersionUID = 1L;

	Model m;
	double centerWidth;
	double centerHeight;
	double gardenWidth;
	double gardenHeight;
	int width;
	int height;
	double imDiameter;
	
	transient Thread t;
	public DataThread(Model m) {
		this.m = m;
	}
	@Override
	public void run() {
		System.out.println("START THREAD");
		
		for(Plant p : m.getPlants()) {
			if(p.getType() == PlantType.HERBACIOUS) {
				p.setImageView(new ImageView(new Image(getClass().getResourceAsStream("/images/plant.png"))));
			}else {
				p.setImageView(new ImageView(new Image(getClass().getResourceAsStream("/images/tree.png"))));
			}
		}
		m.getFavorites().forEach(p -> p.makeImageView());
		System.out.println("END THREAD");
	}

}
