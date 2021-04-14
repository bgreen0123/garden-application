package enums;
public enum Soil {
	SAND("SAND"),
	CLAY("CLAY"),
	DIRT("DIRT");
	
	private String name = null;
	
	private Soil(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
