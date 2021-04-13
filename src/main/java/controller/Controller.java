package controller;

import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;
import javafx.scene.control.Button;

import model.Model;
import view.View;
import enums.CurrentScreen;

public class Controller{
	Model model;
	View view;
	Font font = Font.font("Courier New", FontWeight.BOLD, 24);
	//Buttons
	Button welcomeNext;

	public Controller(View view){
		this.view = view;
		model = new Model();

		welcomeNext = new Button("Start");
		welcomeNext.setMaxSize(200, 50);
		welcomeNext.setFont(font);
		welcomeNext.setOnAction(e -> {
			view.changeScreen(CurrentScreen.CONDITIONS);
			System.out.println("Button Pressed");
		});
	}

	//Getters
	public Button getWelcomeButton(){
		return welcomeNext;
	}

	//Actions
	public EventHandler dragPlant() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	/*
	 * Change Listeners for Sliders can be added after those sliders are created
	 * Need them for: 
	 *     Choosing width
	 *     Choosing height
	 */
	
	public EventHandler enterBudget() {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {}
		};
	}
	
	public EventHandler toOtherPlantType() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
	
	public EventHandler favoritePlant() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {}
		};
	}
}