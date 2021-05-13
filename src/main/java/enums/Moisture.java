package enums;

/**
 * ENUM for Moisture conditions
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */

public enum Moisture {
	MOISTURE("MOISTURE"),
    DRY("DRY"),
    MEDIUM("MEDIUM"),
    WET("WET");

    private String name = null;
    
    /**
     * moisture enum
     * @param s, name of moisture condition
     */

	private Moisture(String s){
		name = s;
	}
	
	/**
	 * gets the enum of the moisture
	 * @return name of moisture
	 */
	public String getName() {
		return name;
	}
}
