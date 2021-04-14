package model;

import java.util.ArrayList;

import enums.Moisture;
import enums.Soil;
import enums.Sun;

public class Model {
	private static Gridspace[][] garden;
	private static ArrayList<Lep> leps;
	private static ArrayList<Plant> plants;
	private static int width;
	private static int height;
	private static Sun sun;
	private static Soil soil;
	private static Moisture moist;
	private static int budgetLeft;
	private static int lepCount;

	
	public Model(){
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
		leps.add(lep);
		return;
	}
	
	public static void removePlant(Plant p) {
		return;
	}
	
	public static void removeLep(Lep l) {
		return;
	}
	
	//Getters
	public static Gridspace[][] getGarden() {
		return garden;
	}
	
	public static void setGarden(Gridspace [][] g) {
		garden = g;
	}
	
	public static ArrayList<Lep> getLeps() {
		return leps;
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static ArrayList<Plant> getPlants() {
		return plants;
	}

	public static Sun getSun() {
		return sun;
	}

	public static Soil getSoil() {
		return soil;
	}

	public static Moisture getMoisture() {
		return moist;
	}

	public static int getBudget() {
		return budgetLeft;
	}

	public static int getLepCount() {
		return lepCount;
	}

	//Setters
	
	public static void setWidth(int w) {
		width = w;
	}
	
	public static void setHeight(int h) {
		height = h;
	}
	
	public static void setSun(Sun s) {
		sun = s;
	}
	
	public static void setSoil(Soil s) {
		soil = s;
	}
	
	public static void setMoisture(Moisture m) {
		moist = m;
	}
	
	public static void setBudget(int b) {
		budgetLeft = b;
	}
	
	public static void setLepCount(int l) {
		lepCount = l;
	}
}