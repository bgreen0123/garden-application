package model;

import java.io.Serializable;
import java.util.ArrayList;

import data.Data;
import enums.Moisture;
import enums.Soil;
import enums.Sun;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */

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

	/**
	 * constructor for the model class
	 */	
	public Model(){
		d = new Data();
		budgetLeft = 0;
		plants = new ArrayList<Plant>();
		favoritedPlants = new ArrayList<Plant>();
		warned = false;
		
	}
	
	/**
	 * constructor for the model class
	 * 
	 * @param width, width of garden
	 * @param height, height of garden
	 * @param d, plant data
	 * @param lepSupported, amount of leps supported
	 * @param plants, arrayList of plants
	 * @param favoritedPlants, arrayList of favorited plants
	 * @param sun, sun enum
	 * @param soil, soil enum
	 * @param moist, moisture enum
	 * @param budgetLeft, budget remaining
	 * @param, warned, warning boolean
	 * 
	 */
	
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
	
	/**
	 * adds plant to the list
	 * 
	 * @param p, plant in the garden
	 */
	public void addPlant(Plant p) {
		plants.add(p);
		return;
	}
	
	/**
	 * removes plant from the list
	 * 
	 * @param p, plant deleted form garden
	 */
	public void removePlant(Plant p) {
		plants.remove(p);
	}
	
	/**
	 * adds plant to the favorite list
	 * 
	 * @param p, plant user has favorited
	 */
	public void addFavoritePlant(Plant p) {
		favoritedPlants.add(p);
	}
	
	//Getters
	
	/**
	 * gets the plant data
	 * 
	 * @return d, plant data
	 */
	public Data getData() {
		return d;
	}
	
	/**
	 * gets the amount of leps supported
	 * 
	 * @return lepsSupported, amount of leps supported by each plant
	 */
	public int getNumLeps() {
		return lepsSupported;
	}
	
	/**
	 * gets the favorites list
	 * 
	 * @return list of user favorited plants
	 */
	public ArrayList<Plant> getFavorites(){
		return favoritedPlants;
	}
	
	/**
	 * gets the width of the garden
	 * 
	 * @return width of garden
	 */
	public int getWidth() {
		return width;
	}

	
	/**
	 * gets the height of the garden
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * gets the arrayList of the plants in the Garden
	 * 
	 * @return plants, array list of garden plants
	 */
	public ArrayList<Plant> getPlants() {
		return plants;
	}
	
	/**
	 * gets the Sun condition
	 * 
	 * @return sun, sun condition of plants
	 */
	public Sun getSun() {
		return sun;
	}

	/**
	 * gets the soil condition
	 * 
	 * @return soil condition of plant
	 */
	public Soil getSoil() {
		return soil;
	}

	/**
	 * gets the moisture condition
	 * 
	 * @return moist, moisture condition of plant
	 */
	public Moisture getMoisture() {
		return moist;
	}

	/**
	 * gets the budget after updating
	 * 
	 * @return budget remaining
	 */
	public int getBudget() {
		return budgetLeft;
	}
	
	/**
	 * gets the warning boolean
	 * 
	 * @return warning 
	 */
	public boolean getWarned() {
		return warned;
	}

	//Setters
	
	/**
	 * sets warning
	 * 
	 */
	public void setWarned() {
		warned = !warned;
	}
	
	/**
	 * sets the garden width
	 * 
	 * @param w, width of garden
	 */
	public void setWidth(int w) {
		width = w;
	}
	
	/**
	 * sets the garden height
	 * 
	 * @param h, height of garden
	 */
	public void setHeight(int h) {
		height = h;
	}
	
	/**
	 * sets the sun condition
	 * 
	 * @param s, sun condition
	 */
	public void setSun(Sun s) {
		sun = s;
	}
	
	/**
	 * sets the soil condition
	 * 
	 * @param s, soil condition
	 */
	public void setSoil(Soil s) {
		soil = s;
	}
	
	/**
	 * sets the moisture conditions
	 * 
	 * @param m, moisture condition
	 */
	public void setMoisture(Moisture m) {
		moist = m;
	}
	
	
	/**
	 * updates the budget according to the plant price
	 * 
	 * @param b, budget remaining
	 */
	public void updateBudget(int b) {
		budgetLeft = b;
		System.out.println("New Budget: "+ budgetLeft);
	}
	
	/**
	 * updates the lep count according to the amount of leps supported by each plant
	 * 
	 * @param l, amount of leps supported by each plant
	 */
	public void updateLepCount(int l) {
		lepsSupported += l;
	}

}


