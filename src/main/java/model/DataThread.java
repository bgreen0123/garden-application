package model;

import java.io.Serializable;
import java.util.ArrayList;

import enums.PlantType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DataThread extends Thread implements Serializable{
	private static final long serialVersionUID = 1L;

	Model m;
	
	transient Thread t;
	public DataThread(Model m) {
		this.m = m;
	}
	@Override
	public void run() {
		System.out.println("START THREAD");
		m.getPlants().forEach(p -> p.makeImageView());
		m.getFavorites().forEach(p -> p.makeImageView());
		System.out.println("END THREAD");
	}

}
