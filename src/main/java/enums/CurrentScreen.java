package enums;

/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */

/**
 * 
 * Strings that identify what the current few is exm: "WELCOME"
 * We will have a switch in the drawScreen method in View that will have all this enum as options and we will have a variable in 
 * view that represents the current screen --Adam
 *
 */

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
	LOAD("LOAD"),
	LOADINGSCREEN("LOADINGSCREEN");
	
	private String name = null;
	
	/**
	 * CurrentScreen of the project
	 * @param s, name of screen
	 */
	
	private CurrentScreen(String s){
		name = s;
	}
	
	/**
	 * gets the enum of the screen
	 * @return name of screen
	 */
	public String getName() {
		return name;
	}
}
