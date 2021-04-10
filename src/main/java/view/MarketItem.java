package view;

public class MarketItem {
	String comName;
	String sciName;
	int lepSupported;
	
	
	//Getters
	public String getComName() {
		return comName;
	}
	
	public String getSciName() {
		return sciName;
	}
	
	public int getlepSupported() {
		return lepSupported;
	}
	
	//Setters
	public void setComName(String com) {
		comName = com;
	}
	
	public void setSciName(String sci) {
		sciName = sci;
	}
	
	public void setLepSupported(int lep) {
		lepSupported = lep;
	}
}
