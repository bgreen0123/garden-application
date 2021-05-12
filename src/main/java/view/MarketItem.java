package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Plant;

public class MarketItem {
	boolean favorited;
	HBox comp;
	Label l;
	Button b;
	Plant p;
	ImageView iv;
	HBox imageBox;
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
	public boolean getFavorited() {
		return favorited;
	}
	
	public void setDefault() {
		favorited = false;
		ImageView i = new ImageView(new Image(getClass().getResourceAsStream("/images/favorite_decal.jpg")));
		i.setFitHeight(30);
		i.setFitWidth(30);
		b.setGraphic(i);
	}
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
	public Plant getPlant() {
		return p;
	}
	public HBox getComp() {
		return comp;
	}
	public Label getLabel() {
		return l;
	}
	public Button getButton() {
		return b;
	}
	public ImageView getIV() {
		return iv;
	}
	public HBox getImageBox() {
		return imageBox;
	}
}
