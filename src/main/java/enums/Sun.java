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
    
    /**
     * sun type
     * @param s, sun type
     */
	private Sun(String s){
		name = s;
	}
	
	/**
     * sun type
     * @return name of sun type
     */
	public String getName() {
		return name;
	}
}
