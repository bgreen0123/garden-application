package model;

import java.util.ArrayList;

import enums.Moisture;
import enums.Soil;
import enums.Sun;

public class Model {
	private Gridspace[][] garden;
	private static ArrayList<Lep> leps;
	private static ArrayList<Plant> plants;
	private int width;
	private int height;
	private Sun sun;
	private Soil soil;
	private Moisture moist;
	private static int budgetLeft;
	private static int lepCount;

	public Model(Plant p, Lep l) {
		leps = new ArrayList<Lep>();
		plants = new ArrayList<Plant>();
	}
	
	public static void updateBudget(int cost) {
		budgetLeft = cost;
		return;
	}
	
	public static void addPlant(Plant p) {
		plants.add(p);
		return;
	}
	
	public static void addLeps(Lep lep) {
		
		//leps.add(lep);
		return;
	}
	
	public static void removePlant(Plant p) {
		return;
	}
	
	public static void removeLep(Lep l) {
		return;
	}
	
	//Getters
	public Gridspace[][] getGarden() {
		return garden;
	}
	
	public void setGarden(Gridspace [][] g) {
		garden = g;
	}
	
	public static ArrayList<Lep> getLeps() {
		return leps;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static ArrayList<Plant> getPlants() {
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

	public static int getBudget() {
		return budgetLeft;
	}

	public static int getLepCount() {
		return lepCount;
	}

	//Setters
	public void setLeps(ArrayList<Lep> l) {
		leps = l;
	}
	
	public void setPlants(ArrayList<Plant> p) {
		plants = p;
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
	
	public void setBudget(int b) {
		budgetLeft = b;
	}
	
	public void setLepCount(int l) {
		lepCount = l;
	}
}