package enums;

/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public enum Spread {
	SMALL("SMALL"),
    MEDIUM("MEDIUM"),
    LARGE("LARGE"),
    XL("XL");
    
    private String name = null;
    
    /**
     * spread of plant
     * @param s, spread of plant
     */
	private Spread(String s){
		name = s;
	}
	
	/**
     * spread size
     * @return name of spread
     */
	public String getName() {
		return name;
	}
}
