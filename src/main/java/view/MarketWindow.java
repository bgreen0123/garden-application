package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class MarketWindow extends Window{
	Image background;
    Button nextPage;
    Button favorited;
    Button woody;
    Button herb;
    MarketItem[] items;
    
    //Getters
    public Image getBackground() {
    	return background;
    }
    
    public Button getNextPage() {
    	return nextPage;
    }
    
    public Button getFavorited() {
    	return favorited;
    }
    
    public Button getWoody() {
    	return woody;
    }
    
    public Button getHerb() {
    	return herb;
    }
    
    public MarketItem[] getItems() {
    	return items;
    }
    
    //Setters
    
    public void setBackground(Image b) {
    	background = b;
    }
    
    public void setNextPage(Button b) {
    	nextPage = b;
    }
    
    public void setFavorited(Button favorite) {
    	favorited = favorite;
    }
    
    public void setWoody(Button wood) {
    	woody = wood;
    }
    
    public void setHerb(Button h) {
    	herb = h;
    }
    
    public void setItems(MarketItem[] item) {
    	items = item;
    }
}

