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

    /**
     * soil type
     * @param s, soil type
     */
	private Soil(String s){
		name = s;
	}
	
	/**
     * soil type
     * @return name of soil type
     */
	public String getName() {
		return name;
	}
}
