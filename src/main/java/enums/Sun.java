package enums;

/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public enum Sun {
	SUN("SUN"),
    FULLSUN("FULLSUN"),
    PARTSUN("PARTSUN"),
    SHADE("SHADE");
    
    private String name = null;

	private Sun(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
