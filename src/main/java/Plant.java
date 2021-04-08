
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
	
	public void move() {
		return;
	}
	
	//Getters
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
	public void setComName(String n) {
		comName = n;
	}
	
	public void setSciName(String n) {
		sciName = n;
	}

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
}
