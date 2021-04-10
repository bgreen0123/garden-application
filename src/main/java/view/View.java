package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import view.WelcomeScreen;
import view.EndWindow;
import view.GardenWindow;
import view.MarketEntryWindow;
import view.MarketItem;
import view.MarketWindow;

import controller.Controller;

import enums.CurrentScreen;

public class View extends Application {
	Controller cont;
	CurrentScreen currentScreen;
	WelcomeScreen welcome;
	EndWindow endWindow;
	GardenWindow garden;
	MarketItem marketItem;
	MarketWindow marketWindow;
	MarketEntryWindow marketEntry;
	
	@Override
    public void start(Stage stage) {

	}
	
	public void update() {
		return;
	}
	
	public void drawScreen() {
		switch(currentScreen){
			case WELCOME:
				welcome.draw();
			case GARDEN:
				garden.draw();
		}
		return;
	}

	public static void main(String[] args) {
		launch();
	}
}