package controller;

import javafx.event.ActionEvent;
import model.MainWindowModel;
import model.Tile;

public class MainWindowController {
	
	private MainWindowModel model;
	
	public MainWindowController (MainWindowModel model) {
		this.model = model;
	}
	
	public void changeCurrentTile(ActionEvent e, Tile newTile) {
		this.model.setCurrentTile(newTile);
	}
	
	public void setDarkMode(boolean darkMode) {
		this.model.setDarkMode(darkMode);
	}

}
