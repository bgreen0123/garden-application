package enums;
public enum CurrentScreen {
    //Strings that identify what the current few is exm: "WELCOME"
    //We will have a switch in the drawScreen method in View that will have all this enum as options and we will have a variable in 
    //view that represents the current screen --Adam

    WELCOME("WELCOME"),
	MARKET_H("MARKET_H"),
	MARKET_W("MARKET_W"),
	CONDITIONS("CONDITIONS"),
	END("END"),
    GARDEN("GARDEN"),
	FAVS("FAVS"),
	LOAD("LOAD");
	
	private String name = null;
	
	private CurrentScreen(String s){
		name = s;
	}
	public String getName() {
		return name;
	}
}
