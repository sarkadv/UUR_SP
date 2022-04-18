package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import util.ImageLoader;
import util.SpecialToolType;
import view.ImageButton;

public class MainWindowModel {
	private BooleanProperty darkMode;
	private ObjectProperty<Tile> currentTile;
	private ObjectProperty<SpecialToolType> specialToolActive;
	private List<Image> buttonImagesLight;
	private List<Image> buttonImagesDark;
	private List<ImageButton> buttons;
	
	public MainWindowModel() {
		this.darkMode = new SimpleBooleanProperty(false);
		this.currentTile = new SimpleObjectProperty<Tile>();
		this.specialToolActive = new SimpleObjectProperty<SpecialToolType>();
		this.buttons = new ArrayList<ImageButton>();
		this.buttonImagesLight = new ArrayList<Image>();
		this.buttonImagesDark = new ArrayList<Image>();
		
		this.buttonImagesLight.addAll(Arrays.asList(new Image[] {ImageLoader.NEW_LIGHT, ImageLoader.SAVE_LIGHT, 
				ImageLoader.BACK_LIGHT, ImageLoader.LOAD_LIGHT, ImageLoader.EXPORT_LIGHT,
				ImageLoader.MODE_LIGHT, ImageLoader.SETTINGS_LIGHT, ImageLoader.HELP_LIGHT, 
				ImageLoader.ABOUT_LIGHT, ImageLoader.PICK_LIGHT, ImageLoader.ERASE_LIGHT, ImageLoader.EYEDROPPER_LIGHT, 
				ImageLoader.CHOOSEAREA_LIGHT, ImageLoader.FILLAREA_LIGHT, ImageLoader.FILLBORDERS_LIGHT}));
		
		this.buttonImagesDark.addAll(Arrays.asList(new Image[] {ImageLoader.NEW_DARK, ImageLoader.SAVE_DARK, 
				ImageLoader.BACK_DARK, ImageLoader.LOAD_DARK, ImageLoader.EXPORT_DARK,
				ImageLoader.MODE_DARK, ImageLoader.SETTINGS_DARK, ImageLoader.HELP_DARK, 
				ImageLoader.ABOUT_DARK, ImageLoader.PICK_DARK, ImageLoader.ERASE_DARK, ImageLoader.EYEDROPPER_DARK, 
				ImageLoader.CHOOSEAREA_DARK, ImageLoader.FILLAREA_DARK, ImageLoader.FILLBORDERS_DARK}));
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

	public List<Image> getButtonImagesLight() {
		return buttonImagesLight;
	}

	public List<Image> getButtonImagesDark() {
		return buttonImagesDark;
	}
	
	public void addButton(ImageButton btn) {
		this.buttons.add(btn);
	}

	public List<ImageButton> getButtons() {
		return buttons;
	}
	
}
