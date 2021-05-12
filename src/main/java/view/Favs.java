package view;

import controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Favs extends Window{
    Image img;
    Button nextPage;
    Button backToMarket;
    Scene scene;
    int width, height;
    Stage stage;
    Controller cont;
    
	public Favs(int width, int height, Stage stage, Button backToMarket, Button nextPage, Controller cont) {
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.nextPage = nextPage;
        this.backToMarket = backToMarket;
        this.cont = cont;
        img = new Image(getClass().getResourceAsStream("/images/conditions-window.jpg"), width, height, false, true);
    }
	
    @Override
    public void draw() {
    	BackgroundImage bg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background = new Background(bg);
        
        GridPane grid = new GridPane();
        Label favs = new Label("Favorites");
        favs.setFont(Font.font("Courier New",30));
        ListView<Label> list = new ListView<Label>();
        list.setPrefSize(width * .7, height * .7);
        cont.getModel().getFavorites().forEach(p -> {
        	Label l = new Label(p.toString());
        	list.getItems().add(l);
        });
        HBox buttons = new HBox();
        buttons.getChildren().addAll(backToMarket, nextPage);
        buttons.setPrefWidth(width * .7);
        grid.add(favs, 1, 0);
        grid.add(buttons, 1, 1);
        grid.add(list, 1, 2);
        grid.setAlignment(Pos.CENTER);
        
        grid.setBackground(background);
        scene = new Scene(grid, width, height);
        stage.setScene(scene);
        stage.show();  
    }
}
