package model;

import java.io.Serializable;
import java.util.ArrayList;

import data.Data;
import enums.Moisture;
import enums.Soil;
import enums.Sun;

public class Model implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Data d;
	private int lepsSupported = 0;
	private ArrayList<Plant> plants;
	private ArrayList<Plant> favoritedPlants;
	private int width;
	private int height;
	//Temporary Default values to avoid "may not have been initialized error"
	private Sun sun = Sun.FULLSUN;
	private Soil soil = Soil.CLAY;
	private Moisture moist = Moisture.DRY;
	private int budgetLeft;
	private DataThread dt;

	
	public Model(){
		d = new Data();
		budgetLeft = 0;
		plants = new ArrayList<Plant>();
		favoritedPlants = new ArrayList<Plant>();
		dt = new DataThread(this);
	}
	
	public void addPlant(Plant p) {
		plants.add(p);
		return;
	}
	
	public void addFavoritePlant(Plant p) {
		favoritedPlants.add(p);
	}
	
	public void removePlant(Plant p) {
		return;
	}
	
	//Getters
	public DataThread getThread() {
		return dt;
	}
	
	public Data getData() {
		return d;
	}
	
	public int getNumLeps() {
		return lepsSupported;
	}
	
	public ArrayList<Plant> getFavorites(){
		return favoritedPlants;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public Sun getSun() {
		return sun;
	}

	public Soil getSoil() {
		return soil;
	}

	public Moisture getMoisture() {
		return moist;
	}

	public int getBudget() {
		return budgetLeft;
	}

	//Setters
	
	public void setWidth(int w) {
		width = w;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public void setSun(Sun s) {
		sun = s;
	}
	
	public void setSoil(Soil s) {
		soil = s;
	}
	
	public void setMoisture(Moisture m) {
		moist = m;
	}
	
	public void updateBudget(int b) {
		budgetLeft = b;
		System.out.println("New Budget: "+ budgetLeft);
	}
	
	public void updateLepCount(int l) {
		lepsSupported += l;
	}
}


