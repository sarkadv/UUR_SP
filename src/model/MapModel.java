package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class MapModel {
	
	private DoubleProperty tileSize;
	private IntegerProperty allTilesWidth;
	private IntegerProperty allTilesHeight;
	private IntegerProperty tilesVisibleLine;
	private IntegerProperty firstTileVisibleX;
	private IntegerProperty firstTileVisibleY;
	private ObjectProperty<Color> planetColor;
	private BooleanProperty darkMode;
	private List<Integer[][]> tilesHistory;
	
	private Integer[][] allTiles;
	
	public MapModel() {
		this.tileSize = new SimpleDoubleProperty(145);
		this.allTilesWidth = new SimpleIntegerProperty(0);
		this.allTilesHeight = new SimpleIntegerProperty(0);
		this.tilesVisibleLine = new SimpleIntegerProperty(0);
		this.firstTileVisibleX = new SimpleIntegerProperty(0);
		this.firstTileVisibleY = new SimpleIntegerProperty(0);
		this.planetColor = new SimpleObjectProperty<Color>(ImageLoader.COLOR_PLANET_ONE);
		this.darkMode = new SimpleBooleanProperty(false);
		this.tilesHistory = new ArrayList<Integer[][]>();
	}
	
	public void initAllTilesNewMap() {
		allTiles = new Integer[allTilesWidth.get()][allTilesHeight.get()];
		
		int addition = darkMode.get()? 1 : 0;
		for(int x = 0; x < allTilesWidth.get(); x++) {
			for(int y = 0; y < allTilesHeight.get(); y++) {
				allTiles[x][y] = 00 + addition;
			}
		}
		
	}
	
	public void reInitAllTiles() {
		int addition = darkMode.get()? 1 : -1;
		for(int x = 0; x < allTilesWidth.get(); x++) {
			for(int y = 0; y < allTilesHeight.get(); y++) {
				allTiles[x][y] = allTiles[x][y] + addition;
			}
		}
	}
	
	public void addToTilesHistory() {
		Integer[][] history = copyTwoDimensionalArray(allTiles);
		this.tilesHistory.add(history);
	}
	
	public void setTile(int x, int y, int id) {
		this.allTiles[x][y] = id;
	}
	
	public int getTile(int x, int y) {
		return allTiles[x][y];
	}

	public List<Integer[][]> getTilesHistory() {
		return tilesHistory;
	}

	public DoubleProperty getTileSize() {
		return tileSize;
	}

	public void setTileSize(double canvasSize) {
		this.tileSize.set(canvasSize);
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

	public Integer[][] getAllTiles() {
		return allTiles;
	}

	public void setAllTiles(Integer[][] allTiles) {
		this.allTiles = allTiles;
	}

	public IntegerProperty getFirstTileVisibleX() {
		return firstTileVisibleX;
	}

	public void setFirstTileVisibleX(int firstTileVisibleX) {
		this.firstTileVisibleX.set(firstTileVisibleX);
	}

	public IntegerProperty getFirstTileVisibleY() {
		return firstTileVisibleY;
	}

	public void setFirstTileVisibleY(int firstTileVisibleY) {
		this.firstTileVisibleY.set(firstTileVisibleY);
	}
	
	private Integer[][] copyTwoDimensionalArray(Integer[][] old){
		Integer[][] copied = new Integer[old.length][old[0].length];
		
		for (int i = 0; i < old.length; i++) {
			for (int j = 0; j < old[0].length; j++) {
				copied[i][j] = old[i][j];
			}   
		}
		return copied;
			  
	}

}