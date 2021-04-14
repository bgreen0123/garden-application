package view;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import static javafx.geometry.Pos.CENTER;

public class WelcomeScreen extends Window{
    Button nextPage;
	Scene scene;
    int width, height;
    Stage stage;

    public WelcomeScreen(int width, int height, Stage stage, Button nextPage){
        this.width = width;
        this.height = height;
        this.stage = stage;
        this.nextPage = nextPage;
    }
    
    @Override
    public void draw() {

        //Background Image
        Image img = new Image(new File("src/main/java/images/welcome-background.jpg").toURI().toString(), width, height, false, true);
        BackgroundImage bg = new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background= new Background(bg);
 
        //Border layout with Button in the center, and label on top
        Label title = new Label("Welcome To The Garden Builder");
        title.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.ITALIC, 36));

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(150, 0, 100, 0));
        
        border.setCenter(nextPage);
        border.setTop(title);
        BorderPane.setAlignment(title, CENTER);
        border.setBackground(background);

        scene = new Scene(border, width, height);
        stage.setScene(scene);
        stage.show(); 
    }
}
