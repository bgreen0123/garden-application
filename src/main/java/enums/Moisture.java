package enums;
public enum Moisture {
	MOISTURE("MOISTURE"),
    DRY("DRY"),
    MEDIUM("MEDIUM"),
    WET("WET");

    private String name = null;

	private Moisture(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
