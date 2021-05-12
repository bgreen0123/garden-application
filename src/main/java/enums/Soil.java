package enums;

/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public enum Soil {
	SOIL("SOIL"),
    ROCK("ROCK"),
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
