package model;

import java.util.ArrayList;

import enums.Moisture;
import enums.Soil;
import enums.Sun;

public class Model {
	private Gridspace[][] garden;
	private ArrayList<Lep> leps;
	private int lepsSupported = 0;
	private ArrayList<Plant> plants;
	private ArrayList<Plant> favoritedPlants;
	private int width;
	private int height;
	private Sun sun;
	private Soil soil;
	private Moisture moist;
	private int budgetLeft;

	
	public Model(){
		leps = new ArrayList<Lep>();
		plants = new ArrayList<Plant>();
		favoritedPlants = new ArrayList<Plant>();
	}
	
	public void addPlant(Plant p) {
		plants.add(p);
		return;
	}
	
	public void addFavoritePlant(Plant p) {
		favoritedPlants.add(p);
	}
	
	public void addLeps(Lep lep) {
		leps.add(lep);
		return;
	}
	
	public void removePlant(Plant p) {
		return;
	}
	
	public void removeLep(Lep l) {
		return;
	}
	
	//Getters
	public int getNumLeps() {
		return lepsSupported;
	}
	public ArrayList<Plant> getFavorites(){
		return favoritedPlants;
	}
	
	public Gridspace[][] getGarden() {
		return garden;
	}
	
	public void setGarden(Gridspace [][] g) {
		garden = g;
	}
	
	public ArrayList<Lep> getLeps() {
		return leps;
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
	}
	
	public void updateLepCount(int l) {
		lepsSupported += l;
	}
}