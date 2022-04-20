package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import util.ImageLoader;

public class MapModel {
	
	private double tileSize;
	private int allTilesWidth;
	private int allTilesHeight;
	private int tilesVisibleLine;
	private int firstTileVisibleX;
	private int firstTileVisibleY;
	private Color planetColor;
	private boolean darkMode;
	private List<Integer[][]> tilesHistory;
	
	private Integer[][] allTiles;
	
	public MapModel() {
		this.tileSize = 145;
		this.allTilesWidth = 0;
		this.allTilesHeight = 0;
		this.tilesVisibleLine = 0;
		this.firstTileVisibleX = 0;
		this.firstTileVisibleY = 0;
		this.planetColor = ImageLoader.COLOR_PLANET_ONE_LIGHT;
		this.darkMode = false;
		this.tilesHistory = new ArrayList<Integer[][]>();
	}
	
	public void initAllTilesNewMap() {
		allTiles = new Integer[allTilesWidth][allTilesHeight];
		
		int addition = darkMode? 1 : 0;
		for(int x = 0; x < allTilesWidth; x++) {
			for(int y = 0; y < allTilesHeight; y++) {
				allTiles[x][y] = 00 + addition;
			}
		}
		
	}
	
	public void reInitAllTiles() {
		int addition = darkMode? 1 : -1;
		for(int x = 0; x < allTilesWidth; x++) {
			for(int y = 0; y < allTilesHeight; y++) {
				allTiles[x][y] = allTiles[x][y] + addition;
			}
		}
	}
	
	public void addToTilesHistory() {
		Integer[][] history = copyTwoDimensionalArray(allTiles);
		this.tilesHistory.add(history);
	}
	
	public void clearMapHistory() {
		this.tilesHistory = new ArrayList<Integer[][]>();
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

	public double getTileSize() {
		return tileSize;
	}

	public void setTileSize(double canvasSize) {
		this.tileSize = canvasSize;
	}

	public int getAllTilesWidth() {
		return allTilesWidth;
	}

	public void setAllTilesWidth(int allTilesWidth) {
		this.allTilesWidth = allTilesWidth;
	}

	public int getAllTilesHeight() {
		return allTilesHeight;
	}

	public void setAllTilesHeight(int allTilesHeight) {
		this.allTilesHeight = allTilesHeight;
	}

	public int getTilesVisibleLine() {
		return tilesVisibleLine;
	}

	public void setTilesVisibleLine(int tilesVisibleLine) {
		this.tilesVisibleLine = tilesVisibleLine;
	}

	public Color getPlanetColor() {
		return planetColor;
	}

	public void setPlanetColor(Color planetColor) {
		this.planetColor = planetColor;
	}

	public boolean getDarkMode() {
		return darkMode;
	}

	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
	}

	public Integer[][] getAllTiles() {
		return allTiles;
	}

	public void setAllTiles(Integer[][] allTiles) {
		this.allTiles = allTiles;
	}

	public int getFirstTileVisibleX() {
		return firstTileVisibleX;
	}

	public void setFirstTileVisibleX(int firstTileVisibleX) {
		this.firstTileVisibleX = firstTileVisibleX;
	}

	public int getFirstTileVisibleY() {
		return firstTileVisibleY;
	}

	public void setFirstTileVisibleY(int firstTileVisibleY) {
		this.firstTileVisibleY = firstTileVisibleY;
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