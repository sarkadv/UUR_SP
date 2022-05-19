package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import util.DragCoordinates;
import util.ImageLoader;

public class MapModel {
	
	private double tileSize;
	private int allTilesWidth;
	private int allTilesHeight;
	private int tilesVisibleLine;
	private int firstTileVisibleX;
	private int firstTileVisibleY;
	private boolean darkMode;
	private List<Integer[][]> tilesHistory;
	private Integer[][] allTiles;
	private boolean[][] chosenTiles;
	private boolean[][] chosenBorders;
	private DragCoordinates dragCoordinates;
	private int activePlanet;
	
	public MapModel() {
		this.tileSize = 145;
		this.allTilesWidth = 0;
		this.allTilesHeight = 0;
		this.tilesVisibleLine = 0;
		this.firstTileVisibleX = 0;
		this.firstTileVisibleY = 0;
		this.darkMode = false;
		this.tilesHistory = new ArrayList<Integer[][]>();
		this.activePlanet = 0;
	}
	
	public void initAllTilesResizedMap() {
		Integer[][] allTilesNew = copyTwoDimensionalMap(allTiles, allTilesWidth, allTilesHeight);
		this.allTiles = allTilesNew;
	}
	
	public void initAllTilesNewMap() {
		allTiles = new Integer[allTilesWidth][allTilesHeight];
		
		int addition = darkMode? 1 : 0;
		for(int x = 0; x < allTilesWidth; x++) {
			for(int y = 0; y < allTilesHeight; y++) {
				allTiles[x][y] = activePlanet * 80 + addition;
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
		Integer[][] history = copyTwoDimensionalMap(allTiles, allTilesWidth, allTilesHeight);
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
	
	public int getActivePlanet() {
		return activePlanet;
	}
	
	public void setActivePlanet(int activePlanet) {
		this.activePlanet = activePlanet;
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
	
	private Integer[][] copyTwoDimensionalMap(Integer[][] old, int dimensionOneLength, int dimensionTwoLength){
		Integer[][] copied = new Integer[dimensionOneLength][dimensionTwoLength];

		int addition = darkMode? 1 : 0;
		
		for (int i = 0; i < dimensionOneLength; i++) {
			for (int j = 0; j < dimensionTwoLength; j++) {
				if(i >= old.length || j >= old[0].length) {
					copied[i][j] = activePlanet*80 + addition;
				}
				else {
					copied[i][j] = old[i][j];
				}
				
			}   
		}
		return copied;
			  
	}

	public DragCoordinates getDragCoordinates() {
		return this.dragCoordinates;
	}

	public void setDragCoordinates(DragCoordinates dragCoordinates) {
		this.dragCoordinates = dragCoordinates;
	}

	public boolean[][] getChosenTiles() {
		return chosenTiles;
	}

	public void setChosenTiles(boolean[][] chosenTiles) {
		this.chosenTiles = chosenTiles;
	}
	
	public boolean[][] getChosenBorders() {
		return chosenBorders;
	}

	public void setChosenBorders(boolean[][] chosenBorders) {
		this.chosenBorders = chosenBorders;
	}

}