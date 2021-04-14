package enums;
public enum Sun {
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
