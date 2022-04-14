package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import util.ImageLoader;
import util.TilePicker;

public class ResizableCanvasModel {
	
	private DoubleProperty canvasSize;
	private IntegerProperty allTilesWidth;
	private IntegerProperty allTilesHeight;
	private IntegerProperty tilesVisibleLine;
	private ObjectProperty<Color> planetColor;
	private BooleanProperty darkMode;
	private DoubleProperty currentTileSize;
	private ObjectProperty<Tile> chosenTile;
	
	private int[][] allTiles;
	private int[][] tilesVisible;
	
	public ResizableCanvasModel() {
		this.canvasSize = new SimpleDoubleProperty(0);
		this.allTilesWidth = new SimpleIntegerProperty(4);
		this.allTilesHeight = new SimpleIntegerProperty(4);
		this.tilesVisibleLine = new SimpleIntegerProperty(4);
		this.planetColor = new SimpleObjectProperty<Color>(ImageLoader.COLOR_PLANET_ONE);
		this.darkMode = new SimpleBooleanProperty(false);
		this.currentTileSize = new SimpleDoubleProperty(canvasSize.get() / tilesVisibleLine.get());
		this.chosenTile = new SimpleObjectProperty<Tile>(TilePicker.HADES_FACTORY_D);
		
		this.allTiles = new int[allTilesWidth.get()][allTilesHeight.get()];
		this.tilesVisible = new int[tilesVisibleLine.get()][tilesVisibleLine.get()];
	}
	
	public void setTile(int x, int y, int id) {
		this.allTiles[x][y] = id;
	}
	
	public int getTile(int x, int y) {
		return allTiles[x][y];
	}

	public DoubleProperty getCanvasSize() {
		return canvasSize;
	}

	public void setCanvasSize(double canvasSize) {
		this.canvasSize.set(canvasSize);
	}

	public IntegerProperty getAllTilesWidth() {
		return allTilesWidth;
	}

	public void setAllTilesWidth(int allTilesWidth) {
		this.allTilesWidth.set(allTilesWidth);
	}

	public IntegerProperty getAllTilesHeight() {
		return allTilesHeight;
	}

	public void setAllTilesHeight(int allTilesHeight) {
		this.allTilesHeight.set(allTilesHeight);
	}

	public IntegerProperty getTilesVisibleLine() {
		return tilesVisibleLine;
	}

	public void setTilesVisibleLine(int tilesVisibleLine) {
		this.tilesVisibleLine.set(tilesVisibleLine);
	}

	public ObjectProperty<Color> getPlanetColor() {
		return planetColor;
	}

	public void setPlanetColor(Color planetColor) {
		this.planetColor.set(planetColor);
	}

	public BooleanProperty getDarkMode() {
		return darkMode;
	}

	public void setDarkMode(boolean darkMode) {
		this.darkMode.set(darkMode);
	}

	public DoubleProperty getCurrentTileSize() {
		return currentTileSize;
	}

	public void setCurrentTileSize(double currentTileSize) {
		this.currentTileSize.set(currentTileSize);
	}

	public int[][] getAllTiles() {
		return allTiles;
	}

	public void setAllTiles(int[][] allTiles) {
		this.allTiles = allTiles;
	}

	public int[][] getTilesVisible() {
		return tilesVisible;
	}

	public void setTilesVisible(int[][] tilesVisible) {
		this.tilesVisible = tilesVisible;
	}

	public ObjectProperty<Tile> getChosenTile() {
		return chosenTile;
	}

	public void setChosenTile(Tile chosenTile) {
		this.chosenTile.set(chosenTile);
	}

}
