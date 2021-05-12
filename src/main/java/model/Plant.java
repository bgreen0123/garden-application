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
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
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
	private String details;
	private int spreadInt;
	transient Image im;
	transient Circle circ;
	transient ImageView iv;
	
	
	/**
	 * plant is moving
	 */	
	public void move() {
		return;
	}
	/**
	 * constructor for the Plant class
	 * 
	 * @param sciName, scientific name of plant
	 * @param comName, common name of plant
	 * @param lepsSupported, amount of leps plant supports
	 * @param type, type of plant (woody or herbaceous)
	 * @param soil, soil condition of plant
	 * @param sun, sun condition of plant
	 * @param moist, moist condition of plant
	 * @param spread, spread of plant
	 * @param details, plant details
	 * @param spreadInt, spread of plant in integer
	 */	
	public Plant(String sciName, String comName, int lepsSupported, PlantType type, Soil soil, Sun sun, Moisture moist, Spread spread, String details, int spreadInt){
		this.comName = comName;
		this.sciName = sciName;
		this.lepsSupported = lepsSupported;
		this.type = type;
		this.sun = sun;
		this.soil = soil;
		this.moist = moist;
		this.spread = spread;
		this.details = details;
		this.spreadInt = spreadInt;
	}
	
	/**
	 * gives each plant an image, grabs the images from resource folder.
	 */	
	
	public void makeImageView() {
		try{
			im = new Image(getClass().getResourceAsStream("/plants/" + comName + ".jpg"));
			iv = new ImageView(im);
			if(circ == null) {
				circ = new Circle(); 
				circ.setFill(new ImagePattern(im));
			}
		}catch(Exception e){
			try {
				im = new Image(getClass().getResourceAsStream("/plants/" + comName + ".jfif"));
				iv = new ImageView(im);
				if(circ == null) {
					circ = new Circle();
					circ.setFill(new ImagePattern(im));
				}
			}catch(Exception ex) {
				try {
					im = new Image(getClass().getResourceAsStream("/plants/" + comName + ".jpeg"));
					iv = new ImageView(im);
					if(circ == null) {
						circ = new Circle();
						circ.setFill(new ImagePattern(im));
					}
				}catch (Exception exc) {
					if(type == PlantType.HERBACIOUS) {
						im = new Image(getClass().getResourceAsStream("/images/plant.png"));
						iv = new ImageView(new Image(getClass().getResourceAsStream("/images/plant.png")));
		        		circ = new Circle();
		        		circ.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/images/plant.png"))));
		        	} else  {
						im = new Image(getClass().getResourceAsStream("/images/tree.png"));
		        		iv = new ImageView(new Image(getClass().getResourceAsStream("/images/tree.png")));
		        		circ = new Circle();
		        		circ.setFill(new ImagePattern(new Image(getClass().getResourceAsStream("/images/tree.png"))));
		        	}
				}
			}
		}
	}
	
	/**
	 * tries to clone plant
	 * @return plant 
	 */	
	public Plant clone(){  
	    try{  
	    	Plant p = (Plant) super.clone();
	    	p.makeImageView();
	        return p;  
	    }catch(Exception e){ 
	        return null; 
	    }
	}
	
	//Getters
	
	/**
	 * gets image of plant
	 * 
	 * @return im, plant image
	 */	
	public Image getIm() {
		return im;
	}
	
	/**
	 * gets plant details
	 * 
	 * @return details of jplant
	 */	
	public String getDetails() {
		return details;
	}
	
	/**
	 * gets imageview of plant
	 * 
	 * @return iv, plant imageview
	 */	
	public ImageView getImageView() {
		return iv;
	}
	
	/**
	 * gets circle that goes around plant image
	 * 
	 * @return circle
	 */	
	public Circle getCircle() {
		return circ;
	}
	
	/**
	 * gets image plant common name
	 * 
	 * @return common name of plant
	 */	
	public String getComName() {
		return comName;
	}
	
	/**
	 * gets scientific name of plant
	 * 
	 * @return scientific name of plant
	 */	
	public String getSciName() {
		return sciName;
	}
	
	/**
	 * gets diameter of plants
	 * 
	 * @return diameter
	 */	
	public double getDiameter() {
		return diameter;
	}
	
	/**
	 * gets amount of leps supported for plant
	 * 
	 * @return amount of leps that the plant supports
	 */	
	public int getLepsSupported() {
		return lepsSupported;
	}
	
	/**
	 * gets the X location of plant
	 * 
	 * @return xloc, location of plant (horizontal)
	 */	
	public double getX() {
		System.out.println("Trying to replace...");
		return xloc;
	}
	
	/**
	 * gets the Y location of plant
	 * 
	 * @return yloc, location of plant (vertical)
	 */	
	public double getY() {
		return yloc;
	}
	
	/**
	 * gets the plant type (woody or herbaceous)
	 * 
	 * @return type of the plant
	 */	
	public PlantType getType() {
		return type;
	}
	
	/**
	 * gets the sun condition of the plant
	 * 
	 * @return sun condition
	 */	
	public Sun getSun() {
		return sun;
	}
	
	/**
	 * gets the soil condition of the plant
	 * 
	 * @return soil condition
	 */	
	public Soil getSoil() {
		return soil;
	}
	
	/**
	 * gets the moisture condition of the plant
	 * 
	 * @return moisture conditoin
	 */	
	public Moisture getMoisture() {
		return moist;
	}
	
	/**
	 * gets the spread of the plant
	 * 
	 * @return spread
	 */	
	
	public Spread getSpread() {
		return spread;
	}
	
	//Setters
	
	/**
	 * sets the circle of the plant imageview
	 * 
	 * @param c, circle
	 */	
	public void setCircle(Circle c) {
		circ = c;
	}
	
	/**
	 * sets the image view of the plant
	 * 
	 * @param i, imageview that is set
	 */	
	public void setImageView(ImageView i) {
		iv = i;
	}
	
	/**
	 * sets the common name
	 * 
	 * @param n, common name of plant
	 */	
	
	//Don't need this
	public void setComName(String n) {
		comName = n;
	}
	
	/**
	 * sets the scientific name
	 * 
	 * @param n, scientific name of plant
	 */	
	
	//Don't need this
	public void setSciName(String n) {
		sciName = n;
	}
	
	/**
	 * sets the leps supported of each plant
	 * 
	 * @param l, amount of leps supported
	 */	
	
	//Don't need this
	public void setLepsSupported(int l) {
		lepsSupported = l;
	}

	/**
	 * sets the diameter of plant
	 * 
	 * @param d, diameter of plant
	 */	
	public void setDiameter(double d) {
		diameter = d;
	}
	
	/**
	 * sets the x location of plant
	 * 
	 * @param x, x location of plant (horizontal)
	 */	
	public void setX(double x) {
		xloc = x;
	}
	
	/**
	 * sets the y location of plant
	 * 
	 * @param y, y location of plant (vertical)
	 */	
	public void setY(double y) {
		yloc = y;
	}
	
	/**
	 * sets the plant type (woody or herbaceous)
	 * 
	 * @param t, plant type
	 */	
	public void setType(PlantType t) {
		type = t;
	}
	
	/**
	 * sets the plant sun condition
	 * 
	 * @param s, sun condition
	 */	
	public void setSun(Sun s) {
		sun = s;
	}
	
	/**
	 * sets the plant soil condition
	 * 
	 * @param s, soil condition
	 */	
	public void setSoil(Soil s) {
		soil = s;
	}
	
	/**
	 * sets the plant moisture condition
	 * 
	 * @param m, moisture condition
	 */	
	public void setMoisture(Moisture m) {
		moist = m;
	}
	
	/**
	 * toString of plant that prints all information of each plant
	 * 
	 */	

	@Override
	public String toString(){
		return "Common Name: " + comName + '\n' + "Scientific Name : " + sciName + "\n" + "Leps Supported: " + lepsSupported + "\nSpread: " + spreadInt + " (" + spread + ")";
	}
}
