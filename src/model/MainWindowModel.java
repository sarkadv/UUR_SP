package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.TilePicker;

public class MainWindowModel {
	private BooleanProperty darkMode;
	private ObjectProperty<Tile> currentTile;
	
	public MainWindowModel() {
		this.darkMode = new SimpleBooleanProperty(false);
		this.currentTile = new SimpleObjectProperty<Tile>(TilePicker.HADES_SHOP_D);
	}
	
	public BooleanProperty getDarkMode() {
		return darkMode;
	}
	
	public void setDarkMode(boolean darkMode) {
		this.darkMode.set(darkMode);
	}

	public ObjectProperty<Tile> getCurrentTile() {
		return currentTile;
	}

	public void setCurrentTile(Tile currentTile) {
		this.currentTile.set(currentTile);
	}

	
}
