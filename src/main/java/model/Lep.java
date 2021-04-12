package model;

public class Lep {
	private int xloc;
	private int yloc;
	private int xVel;
	private int yVel;
	
	public Lep(int xloc, int yloc, int xVelocity, int yVelocity) {
		this.xloc = xloc;
		this.yloc = yloc;
		this.xVel = xVelocity;
		this.yVel = yVelocity;
	}
	
	public void updateLocation() {
		/* 
		xloc += xVelocity;
		yloc += yVelocity;
		updateVelocities(); 
		*/
		return;
	}
	
	public void updateVelocities(int inc){
		return;
	}

	//Getters
	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}
	
	public int getXVelocity() {
		return xVel;
	}

	public int getYVelocity() {
		return yVel;
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
