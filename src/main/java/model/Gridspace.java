package model;

public class Gridspace {
	private Plant plant;
	private Boolean hasPlant;
	private Boolean canPlace;
	
	public Gridspace() {
		
	}
	
	
	//Getters
	public Plant getPlant() {
		return plant;
	}
	
	public Boolean getHasPlant() {
		return hasPlant;
	}

	public Boolean getCanPlace() {
		return canPlace;
	}

	//Setters
	public void setPlant(Plant p) {
		plant = p;
	}
	
	public void setHasPlant(Boolean b) {
		hasPlant = b;
	}
	
	public void setCanPlace(Boolean b) {
		canPlace = b;
	}

	public void removePlant() {
		plant = null;
	}
}
