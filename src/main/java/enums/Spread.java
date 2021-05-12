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

	private Spread(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
