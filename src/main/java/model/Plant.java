package model;

import enums.Moisture;
import enums.PlantType;
import enums.Soil;
import enums.Sun;
import javafx.scene.image.ImageView;

public class Plant implements Cloneable{
	private String comName;
	private String sciName;
	private int lepsSupported;
	private int width;
	private int height;
	private double xloc;
	private double yloc;
	private PlantType type;
	private Sun sun;
	private Soil soil;
	private Moisture moist;
	private String image;
	private ImageView iv;
	
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
	
	public Object clone(){  
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	//Getters
	public String getImageUrl(){
		return image;
	}
	
	public ImageView getImageView() {
		return iv;
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
	
	public double getX() {
		System.out.println("Trying to replace...");
		return xloc;
	}
	
	public double getY() {
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
	
	public void setImageView(ImageView i) {
		iv = i;
	}

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
	
	public void setX(double x) {
		xloc = x;
	}
	
	public void setY(double y) {
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
