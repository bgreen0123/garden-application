package enums;

/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public enum PlantType {
    WOODY("Woody"),
    HERBACIOUS("Herbacious");
    
    private String name = null;

    /**
     * plant type
     * @param s, plant type (woody or herbaceous)
     */
	private PlantType(String s){
		name = s;
	}
	
	/**
     * plant type
     * @return name of plant type
     */
	public String getName() {
		return name;
	}
}
