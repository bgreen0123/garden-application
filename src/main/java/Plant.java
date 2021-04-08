
public class Plant {
	String comName;
	String sciName;
	int lepsSupported;
	int width;
	int height;
	int xloc;
	int yloc;
	PlantType type;
	Sun sun;
	Soil soil;
	Moisture moist;
	
	public void move() {
		return;
	}
	
	public String getComName() {
		return comName;
	}
	
	public void setComName(String n) {
		comName = n;
	}
	
	public String getSciName() {
		return sciName;
	}
	
	public void setSciName(String n) {
		sciName = n;
	}
	
	public int getLepsSupported() {
		return lepsSupported;
	}
	
	public void setLepsSupported(int l) {
		lepsSupported = l;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int w) {
		width = w;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public int getX() {
		return xloc;
	}
	
	public void setX(int x) {
		xloc = x;
	}
	
	public int getY() {
		return yloc;
	}
	
	public void setY(int y) {
		yloc = y;
	}
	
	public PlantType getType() {
		return type;
	}
	
	public void setType(PlantType t) {
		type = t;
	}
	
	public Sun getSun() {
		return sun;
	}
	
	public void setSun(Sun s) {
		sun = s;
	}
	
	public Soil getSoil() {
		return soil;
	}
	
	public void setSoil(Soil s) {
		soil = s;
	}
	
	public Moisture getMoisture() {
		return moist;
	}
	
	public void setMoisture(Moisture m) {
		moist = m;
	}
}
