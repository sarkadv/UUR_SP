package controller;

import javafx.event.ActionEvent;
import model.MainWindowModel;
import model.Tile;
import util.SpecialToolType;
import util.TilePicker;

public class MainWindowController {
	
	private MainWindowModel model;
	
	public MainWindowController (MainWindowModel model) {
		this.model = model;
	}
	
	public Tile getCurrentTile() {
		return this.model.getCurrentTile().get();
	}
	
	public void changeCurrentTile(ActionEvent e, Tile newTile) {
		this.model.setCurrentTile(newTile);
	}
	
	public void changeToEmptyTile(ActionEvent e) {
		if(model.getDarkMode().get()) {
			this.model.setCurrentTile(TilePicker.getTile(01));
		}
		else {
			this.model.setCurrentTile(TilePicker.getTile(00));
		}
		
	}
	
	public void changeToNoTile(ActionEvent e) {
		this.model.setCurrentTile(null);
	}
	
	public void changeSpecialToolActive(SpecialToolType tool) {
		model.setToolActive(tool);
	}
	
	public void removeSpecialToolActive() {
		model.setToolActive(null);
	}
	
	public void setDarkMode(boolean darkMode) {
		this.model.setDarkMode(darkMode);
	}

}
