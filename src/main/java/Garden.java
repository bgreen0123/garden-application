
public class Garden {
	Gridspace[][] garden;
	Lep[] leps;
	Plant[] plants;
	int width;
	int height;
	Sun sun;
	Soil soil;
	Moisture moist;
	int budgetLeft;
	int lepCount;
	
	public static void updateBudget(int cost) {
		return;
	}
	
	public static void addPlant(Plant p) {
		return;
	}
	
	public static void addLeps(int numLeps) {
		return;
	}
	
	public Gridspace[][] getGarden() {
		return garden;
	}
	
	public void setGarden(Gridspace [][] g) {
		garden = g;
	}
	
	public Lep[] getLeps() {
		return leps;
	}
	
	public void setLeps(Lep[] l) {
		leps = l;
	}
	
	public Plant[] getPlants() {
		return plants;
	}
	
	public void setPlants(Plant[] p) {
		plants = p;
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
	
	public int getBudget() {
		return budgetLeft;
	}
	
	public void setBudget(int b) {
		budgetLeft = b;
	}
	
	public int getLepCount() {
		return lepCount;
	}
	
	public void setLepCount(int l) {
		lepCount = l;
	}
}
