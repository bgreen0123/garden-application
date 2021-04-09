package model;

public class Lep {
	private int xloc;
	private int yloc;
	private int xVelocity;
	private int yVelocity;
	
	public void updateLocation() {
		/* 
		xloc += xVelocity;
		yloc += yVelocity;
		updateVelocities(); 
		*/
		return;
	}
	
	private void updateVelocities(){
		return;
	}

	//Getters
	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}
	
	//Setters
	//I don't think we need these because they should start at random locations and they are updated automatically in the updateLocation method --Adam

	/* public void setX(int x) {
		xloc = x;
	}
	
	public void setY(int y) {
		yloc = y;
	} */
}
