package view;

import java.io.File;
import java.util.ArrayList;

import javafx.geometry.Insets;
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

public class MarketWindowHerbaceous extends Window{
    Image img;
    ArrayList<MarketItem> herbaceous;
    Button nextPage;
    Button woody;
    Scene scene;
    int width, height;
    Stage stage;

	public MarketWindowHerbaceous(int width, int height, Stage stage, ArrayList<MarketItem> herbaceous, Button woody, Button nextPage) {
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.herbaceous = herbaceous;
        this.woody = woody;
        this.nextPage = nextPage;
        img = new Image(getClass().getResourceAsStream("/images/conditions-window.jpg"), width, height, false, true);
    }
    @Override
    public void draw() {
    	BackgroundImage bg = new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background background = new Background(bg);
        
        GridPane grid = new GridPane();
        Label herb = new Label("Herbaceous");
        herb.setPadding(new Insets(5,5,5,5));
        nextPage.setPadding(new Insets(5,5,5,5));
        herb.setFont(Font.font("Courier New",30));
        ListView<HBox> list = new ListView<HBox>();
        herbaceous.forEach(m -> list.getItems().add(m.getComp()));
        list.setPrefSize(width * .7, height * .7);
        grid.add(woody, 0, 0);
        grid.add(herb, 1, 0);
        grid.add(list, 1, 1);
        grid.add(nextPage, 2, 2 );
        grid.setAlignment(Pos.CENTER);
        nextPage.setPrefSize(100,50);
        
        grid.setBackground(background);
        scene = new Scene(grid, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
