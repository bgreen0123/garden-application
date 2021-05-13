package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Plant;
/**
 * 
 * @author Ryan Allarey, Cole Plum, Brendan Green, Adam Kenney
 *
 */
public class MarketItem {
	boolean favorited;
	HBox comp;
	Label l;
	Button b;
	Plant p;
	ImageView iv;
	HBox imageBox;
	
	/**
	 * constructor of a market item, plants and favorite button
	 * 
	 * @param pl, plant
	 * @param but, button
	 */
	public MarketItem(Plant pl, Button but) {
		this.favorited = false;
		this.b = but;
		this.p = pl;
		this.iv = new ImageView(pl.getIm());
		this.imageBox = new HBox(iv);
		l = new Label(p.toString());
		comp = new HBox();
		comp.getChildren().addAll(imageBox, l, b);
	}
	
	/**
	 * gets if the plant is favorited or not
	 * @return boolean favorite, true = favorite/false = not
	 */
	public boolean getFavorited() {
		return favorited;
	}
	
	/**
	 * default graphic of favorite button
	 */
	public void setDefault() {
		favorited = false;
		ImageView i = new ImageView(new Image(getClass().getResourceAsStream("/images/favorite_decal.jpg")));
		i.setFitHeight(30);
		i.setFitWidth(30);
		b.setGraphic(i);
	}
	
	/**
	 * changes the button if its favorited or not
	 * check if its favorite, heart if its not
	 */
	public void setButton() {
		favorited = !favorited;
		ImageView i; 
		if(favorited) {
			i = new ImageView(new Image(getClass().getResourceAsStream("/images/check.png")));
		}else {
			i = new ImageView(new Image(getClass().getResourceAsStream("/images/favorite_decal.jpg")));
		}
		i.setFitHeight(30);
		i.setFitWidth(30);
		b.setGraphic(i);
	}
	
	/**
	 * gets the plant
	 * @return plant
	 */
	public Plant getPlant() {
		return p;
	}
	
	/**
	 * gets comp HBox of market items
	 * @return, HBox of market item
	 */
	public HBox getComp() {
		return comp;
	}
	
	/**
	 * gets label
	 * @return l plant toString information
	 */
	public Label getLabel() {
		return l;
	}
	
	/**
	 * favorite button
	 * @return button to favorite a plant
	 */
	public Button getButton() {
		return b;
	}
	
	/**
	 * image view of plant
	 * @return plant image
	 */
	public ImageView getIV() {
		return iv;
	}
	
	/**
	 * gets imageBox
	 * @return Hbox of image
	 */
	public HBox getImageBox() {
		return imageBox;
	}
}
