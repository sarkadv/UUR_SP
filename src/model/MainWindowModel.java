package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import util.TilePicker;
import util.SpecialToolType;

public class MainWindowModel {
	private BooleanProperty darkMode;
	private ObjectProperty<Tile> currentTile;
	private ObjectProperty<SpecialToolType> specialToolActive;
	
	public MainWindowModel() {
		this.darkMode = new SimpleBooleanProperty(false);
		this.currentTile = new SimpleObjectProperty<Tile>();
		this.specialToolActive = new SimpleObjectProperty<SpecialToolType>();
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

	public ObjectProperty<SpecialToolType> getToolActive() {
		return specialToolActive;
	}

	public void setToolActive(SpecialToolType toolActive) {
		this.specialToolActive.set(toolActive);
	}
	
}
