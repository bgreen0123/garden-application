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
	private boolean warned;

	
	public Model(){
		d = new Data();
		budgetLeft = 0;
		plants = new ArrayList<Plant>();
		favoritedPlants = new ArrayList<Plant>();
		warned = false;
		
	}
	
	public Model(int width, int height, Data d, int lepSupported, ArrayList<Plant> plants, ArrayList<Plant>favoritedPlants, Sun sun, Soil soil, Moisture moist,
			int budgetLeft, boolean warned) {
		this.width = width;
		this.height = height;
		this.d = d;
		this.lepsSupported = lepSupported;
		this.plants = plants;
		this.favoritedPlants = favoritedPlants;
		this.sun = sun;
		this.soil = soil;
		this.moist = moist;
		this.budgetLeft = budgetLeft;
		this.warned = warned;
	}
	
	public void addPlant(Plant p) {
		plants.add(p);
		return;
	}
	
	public void removePlant(Plant p) {
		plants.remove(p);
	}
	
	public void addFavoritePlant(Plant p) {
		favoritedPlants.add(p);
	}
	
	//Getters
	
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
	
	public boolean getWarned() {
		return warned;
	}

	//Setters
	public void setWarned() {
		warned = !warned;
	}
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


