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

	private PlantType(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
