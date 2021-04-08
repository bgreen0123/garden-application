import java.util.ArrayList;

public class Model {
	private Gridspace[][] garden;
	private ArrayList<Lep> leps;
	private ArrayList<Plant> plants;
	private int width;
	private int height;
	private Sun sun;
	private Soil soil;
	private Moisture moist;
	private int budgetLeft;
	private int lepCount;

	public void updateBudget(int cost) {
		return;
	}
	
	public void addPlant(Plant p) {
		//plants.add(p);
		return;
	}
	
	public void addLeps(int numLeps) {
		return;
	}
	
	//Getters
	public Gridspace[][] getGarden() {
		return garden;
	}
	
	public void setGarden(Gridspace [][] g) {
		garden = g;
	}
	
	public Lep[] getLeps() {
		return leps;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public ArrayList<plant> getPlants() {
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

	public int getBudget() {
		return budgetLeft;
	}

	public int getLepCount() {
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