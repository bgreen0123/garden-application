package model;

import enums.Moisture;
import enums.PlantType;
import enums.Soil;
import enums.Sun;

public class Plant {
	private String comName;
	private String sciName;
	private int lepsSupported;
	private int width;
	private int height;
	private int xloc;
	private int yloc;
	private PlantType type;
	private Sun sun;
	private Soil soil;
	private Moisture moist;
	private String image;
	
	public void move() {
		return;
	}
	public Plant(String sciName, String comName, int lepsSupported, PlantType type, Soil soil, Sun sun, Moisture moist, String image){
		this.comName = comName;
		this.sciName = sciName;
		this.lepsSupported = lepsSupported;
		this.type = type;
		this.sun = sun;
		this.soil = soil;
		this.moist = moist;
		this.image = image;
	}
	//Getters
	public String getImageUrl(){
		return image;
	}
	public String getComName() {
		return comName;
	}
	
	public String getSciName() {
		return sciName;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getLepsSupported() {
		return lepsSupported;
	}

	public int getHeight() {
		return height;
	}
	
	public int getX() {
		return xloc;
	}
	
	public int getY() {
		return yloc;
	}
	
	public PlantType getType() {
		return type;
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
	
	//Setters

	//Don't need this
	public void setComName(String n) {
		comName = n;
	}
	//Don't need this
	public void setSciName(String n) {
		sciName = n;
	}
	//Don't need this
	public void setLepsSupported(int l) {
		lepsSupported = l;
	}
	
	public void setWidth(int w) {
		width = w;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public void setX(int x) {
		xloc = x;
	}
	
	public void setY(int y) {
		yloc = y;
	}
	
	public void setType(PlantType t) {
		type = t;
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

	@Override
	public String toString(){
		return "Common Name: " + comName + '\n' + "Scientific Name : " + sciName + "\n" + "Leps Supported: " + lepsSupported;
	}
}
