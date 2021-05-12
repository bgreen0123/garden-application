package model;

import java.io.Serializable;

import java.util.ArrayList;

import enums.PlantType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */

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
		m.getData().getWoody().forEach(p -> {
			if(p.getImageView() == null) {
				p.makeImageView();
			}
		});
		m.getData().getHerbacious().forEach(p -> {
			if(p.getImageView() == null) {
				p.makeImageView();
			}
		});
		m.getPlants().forEach(p -> {
			if(p.getImageView() == null) {
				p.makeImageView();
			}
		});
		m.getFavorites().forEach(p -> {
			if(p.getImageView() == null) {
				p.makeImageView();
			}
		});
		System.out.println("END THREAD");
	}

}
