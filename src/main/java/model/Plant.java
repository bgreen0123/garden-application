package model;

import enums.Moisture;
import enums.PlantType;
import enums.Soil;
import enums.Spread;
import enums.Sun;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Plant implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;
	
	private String comName;
	private String sciName;
	private int lepsSupported;
	private double diameter;
	private double xloc;
	private double yloc;
	private PlantType type;
	private Sun sun;
	private Soil soil;
	private Moisture moist;
	private Spread spread;
	private String image;
	private String details;
	transient Circle circ;
	transient ImageView iv;
	
	public void move() {
		return;
	}
	public Plant(String sciName, String comName, int lepsSupported, PlantType type, Soil soil, Sun sun, Moisture moist, Spread spread, String image, String details){
		this.comName = comName;
		this.sciName = sciName;
		this.lepsSupported = lepsSupported;
		this.type = type;
		this.sun = sun;
		this.soil = soil;
		this.moist = moist;
		this.spread = spread;
		this.image = image;
		this.details = details;
	}
	
	public void makeImageView() {
		try {
			System.out.println(image);
			iv = new ImageView(new Image(image));
			if(circ == null) {
				circ = new Circle();
				circ.setFill(new ImagePattern(new Image(image)));
			}
		} catch(Exception e){
			if(type == PlantType.HERBACIOUS) {
				image = "/images/plant.png";
				iv = new ImageView(new Image(image));
        		circ = new Circle();
        		circ.setFill(new ImagePattern(new Image(getClass().getResourceAsStream(image))));
        	} else  {
        		image = "/images/tree.png";
        		iv = new ImageView(new Image(image));
        		circ = new Circle();
        		circ.setFill(new ImagePattern(new Image(getClass().getResourceAsStream((image)))));
        	}    		
		}
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
	
	public Circle getCircle() {
		return circ;
	}
	public String getComName() {
		return comName;
	}
	
	public String getSciName() {
		return sciName;
	}
	
	public double getDiameter() {
		return diameter;
	}
	
	public int getLepsSupported() {
		return lepsSupported;
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
	
	public Spread getSpread() {
		return spread;
	}
	
	//Setters
	
	public void setCircle(Circle c) {
		circ = c;
	}
	
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

	
	public void setDiameter(double d) {
		diameter = d;
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
