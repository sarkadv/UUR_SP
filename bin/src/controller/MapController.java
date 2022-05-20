package controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.MapModel;
import model.Tile;
import util.DragCoordinates;
import util.TilePicker;
import view.MapView;

public class MapController {
	
	private MapView view;
	private MapModel model;
	
	public MapController() {
		this.model = new MapModel();
		this.view = new MapView(model);
		
	}

	public MapModel getModel() {
		return model;
	}
	
	public MapView getView() {
		return view;
	}
	
	public void setAllTilesWidth(int allTilesWidth) {
		model.setAllTilesWidth(allTilesWidth);
	}
	
	public void setAllTilesHeight(int allTilesHeight) {
		model.setAllTilesHeight(allTilesHeight);
	}
	
	public void setTilesVisibleLine(int tilesVisibleLine) {
		model.setTilesVisibleLine(tilesVisibleLine);
	}
	
	public int getTilesVisibleLine() {
		return model.getTilesVisibleLine();
	}
	
	public Integer[][] getAllTiles(){
		return model.getAllTiles();
	}
	
	public int getActivePlanet() {
		return model.getActivePlanet();
	}
	
	public void setActivePlanet(int activePlanet) {
		model.setActivePlanet(activePlanet);
	}
	
	public void showNewMap(double stageWidth, double stageHeight) {
		changeTileSize(stageWidth, stageHeight);
	}
	
	public void changeTileSize(double stageWidth, double stageHeight) {
		double newTileSize = Math.min(stageWidth, stageHeight) - 175;
		newTileSize = newTileSize / model.getTilesVisibleLine();
		
		model.setTileSize(newTileSize);
		repaint();
	}
	
	public void resizeMap(double stageWidth, double stageHeight, int allTilesWidth, int allTilesHeight, int tilesVisibleLine) {
		if(tilesVisibleLine >= 1 && tilesVisibleLine <= allTilesWidth && tilesVisibleLine <= allTilesHeight) {
			setAllTilesWidth(allTilesWidth);
			setAllTilesHeight(allTilesHeight);
			setTilesVisibleLine(tilesVisibleLine);
			initAllTilesResizedMap();
			
			if(getFirstTileVisibleX() + tilesVisibleLine > getAllTilesWidth()) {
				int subtraction = getAllTilesWidth() - (getFirstTileVisibleX() + tilesVisibleLine);
				setFirstTileVisibleX(getFirstTileVisibleX() + subtraction);
			}
			
			if(getFirstTileVisibleY() + tilesVisibleLine > getAllTilesHeight()) {
				int subtraction = getAllTilesHeight() - (getFirstTileVisibleY() + tilesVisibleLine);
				setFirstTileVisibleY(getFirstTileVisibleY() + subtraction);
			}
			
			setDragCoordinates(null);
			changeChosenTiles();
			changeChosenBorders();
			
			changeTileSize(stageWidth, stageHeight);
			repaint();
		}
	}
	
	public void fillArea(Tile tile) {
		for(int x = 0; x < model.getAllTilesWidth(); x++) {
			for(int y = 0; y < model.getAllTilesHeight(); y++) {
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[x][y]) {
        				if(tile != null) {
        					model.setTile(x, y, tile.id);
        				}
        			}
        		}
			}
		}
		
		model.addToTilesHistory();
		repaint();
	}
	
	public void fillBorders(Tile tile) {
		for(int x = 0; x < model.getAllTilesWidth(); x++) {
			for(int y = 0; y < model.getAllTilesHeight(); y++) {
        		if(model.getChosenBorders() != null) {
        			if(model.getChosenBorders()[x][y]) {
        				if(tile != null) {
        					model.setTile(x, y, tile.id);
        				}
        			}
        		}
			}
		}
		
		model.addToTilesHistory();
		repaint();
	}
	
	public void initAllTilesNewMap() {
		model.initAllTilesNewMap();
	}
	
	public void reInitAllTiles() {
		model.reInitAllTiles();
	}
	
	public void initAllTilesResizedMap() {
		model.initAllTilesResizedMap();
	}
	
	public void repaint() {
		view.repaint();
	}
	
	public void moveUp(KeyEvent e) {
		if(model.getFirstTileVisibleY() > 0) {
			model.setFirstTileVisibleY(model.getFirstTileVisibleY() - 1);
			repaint();
		}
		
	}
	
	public void moveDown(KeyEvent e) {
		if(model.getFirstTileVisibleY() < model.getAllTilesHeight() - model.getTilesVisibleLine()) {
			model.setFirstTileVisibleY(model.getFirstTileVisibleY() + 1);
			repaint();
		}
	}
	
	public void moveLeft(KeyEvent e) {
		if(model.getFirstTileVisibleX() > 0) {
			model.setFirstTileVisibleX(model.getFirstTileVisibleX() - 1);
			repaint();
		}
	}
	
	public void moveRight(KeyEvent e) {
		if(model.getFirstTileVisibleX() < model.getAllTilesWidth() - model.getTilesVisibleLine()) {
			model.setFirstTileVisibleX(model.getFirstTileVisibleX() + 1);
			repaint();
		}
	}
	
	public void putTile(MouseEvent e, Tile tile) {
		if(tile != null) {
			int x = getTileCoordinate(e.getX()) + model.getFirstTileVisibleX();
			int y = getTileCoordinate(e.getY()) + model.getFirstTileVisibleY();
			
			model.setTile(x, y, tile.id);
			repaint();
			model.addToTilesHistory();
		}
	}
	
	public void oneStepBack() {
		if(model.getTilesHistory().size() >= 2) {
			int oneStepBeforeIndex = model.getTilesHistory().size() - 2;
			Integer[][] oneStepBefore = model.getTilesHistory().get(oneStepBeforeIndex);
			changeTilesToMode(getDarkMode(), oneStepBefore);
			model.setAllTiles(oneStepBefore);
			model.getTilesHistory().remove(oneStepBeforeIndex + 1);
			view.repaint();
		}
		else if (model.getTilesHistory().size() == 1) {
			model.getTilesHistory().remove(model.getTilesHistory().size() - 1);
			model.initAllTilesNewMap();
			view.repaint();
		}
	}
	
	public void clearMapHistory() {
		model.clearMapHistory();
	}
	
	public Tile copyTile(MouseEvent e) {
		int x = getTileCoordinate(e.getX()) + model.getFirstTileVisibleX();
		int y = getTileCoordinate(e.getY()) + model.getFirstTileVisibleY();
		
		Tile copied = TilePicker.getTile(model.getTile(x, y), getActivePlanet());
		if(copied.id == getActivePlanet()*80 || copied.id == getActivePlanet()*80 + 1) {
			return null;
		}
		else {
			return copied;
		}
	}
	
	public void changeChosenTiles() {
		DragCoordinates dragCoordinates = getDragCoordinates();
		boolean[][] chosenTiles = new boolean[model.getAllTilesWidth()][model.getAllTilesHeight()];
		
		if(dragCoordinates != null) {
			int tileCoordinateStartX = model	.getFirstTileVisibleX() + getTileCoordinate(dragCoordinates.getStartX());
			int tileCoordinateStartY = model	.getFirstTileVisibleY() + getTileCoordinate(dragCoordinates.getStartY());
			
			int tileCoordinateEndX = model.getFirstTileVisibleX() + getTileCoordinate(dragCoordinates.getEndX());
			int tileCoordinateEndY = model.getFirstTileVisibleY() + getTileCoordinate(dragCoordinates.getEndY());
			
			for(int x = 0; x < chosenTiles.length; x++) {
				for(int y = 0; y < chosenTiles[x].length; y++) {
					if((x >= tileCoordinateStartX && x <= tileCoordinateEndX)||(x >= tileCoordinateEndX && x <= tileCoordinateStartX)) {
						if((y >= tileCoordinateStartY && y <= tileCoordinateEndY)||(y >= tileCoordinateEndY && y <= tileCoordinateStartY)) {
							chosenTiles[x][y] = true;
						}
					}
					else {
						chosenTiles[x][y] = false;
					}
				}
			}
			
			model.setChosenTiles(chosenTiles);
		}
		
		else {
			model.setChosenTiles(null);
		}

	}
	
	public void changeChosenBorders() {
		DragCoordinates dragCoordinates = getDragCoordinates();
		boolean[][] chosenBorders = new boolean[model.getAllTilesWidth()][model.getAllTilesHeight()];
		
		if(dragCoordinates != null) {
			int tileCoordinateStartX = model	.getFirstTileVisibleX() + getTileCoordinate(dragCoordinates.getStartX());
			int tileCoordinateStartY = model	.getFirstTileVisibleY() + getTileCoordinate(dragCoordinates.getStartY());
			
			int tileCoordinateEndX = model.getFirstTileVisibleX() + getTileCoordinate(dragCoordinates.getEndX());
			int tileCoordinateEndY = model.getFirstTileVisibleY() + getTileCoordinate(dragCoordinates.getEndY());
			
			for(int x = 0; x < chosenBorders.length; x++) {
				for(int y = 0; y < chosenBorders[x].length; y++) {
					if(x == tileCoordinateStartX || x == tileCoordinateEndX
        					|| y == tileCoordinateStartY || y == tileCoordinateEndY) {
						if((x >= tileCoordinateStartX && x <= tileCoordinateEndX)||(x >= tileCoordinateEndX && x <= tileCoordinateStartX)) {
							if((y >= tileCoordinateStartY && y <= tileCoordinateEndY)||(y >= tileCoordinateEndY && y <= tileCoordinateStartY)) {
								chosenBorders[x][y] = true;
							}
						}
					}
					else {
						chosenBorders[x][y] = false;
					}
				}
			}
			
			model.setChosenBorders(chosenBorders);
		}
		
		else {
			model.setChosenBorders(null);
		}

	}
	
	private int getTileCoordinate(double clickedCoordinate) {
		return (int)((clickedCoordinate) / model.getTileSize());
	}
	
	public void setDarkMode(boolean darkMode) {
		model.setDarkMode(darkMode);
	}
	
	public boolean getDarkMode() {
		return model.getDarkMode();
	}
	
	private Integer[][] changeTilesToMode(boolean darkMode, Integer[][] tiles) {
		if(getDarkMode() && tiles[0][0] % 10 == 0) {
			for(int i = 0; i < tiles.length; i++) {
				for(int j = 0; j < tiles[0].length; j++) {
					tiles[i][j] = tiles[i][j] + 1;
				}
			}
		}
		else if(!getDarkMode() && tiles[0][0] % 10 == 1) {
			for(int i = 0; i < tiles.length; i++) {
				for(int j = 0; j < tiles[0].length; j++) {
					tiles[i][j] = tiles[i][j] - 1;
				}
			}
		}
		
		return tiles;
	}
	
	public void changePlanet(int newPlanet) {
		if(newPlanet != this.getActivePlanet()) {
			
			for(int x = 0; x < model.getAllTilesWidth(); x++) {
				for(int y = 0; y < model.getAllTilesHeight(); y++) {
					model.getAllTiles()[x][y] = model.getAllTiles()[x][y] + (newPlanet - model.getActivePlanet())*80;
				}
			}
			
			for(int i = 0; i < model.getTilesHistory().size(); i++) {
				for(int x = 0; x < model.getAllTilesWidth(); x++) {
					for(int y = 0; y < model.getAllTilesHeight(); y++) {
						model.getTilesHistory().get(i)[x][y] = model.getTilesHistory().get(i)[x][y] + (newPlanet - model.getActivePlanet())*80;
					}
				}
			}
			
			model.setActivePlanet(newPlanet);
		}
	}
	
	public void setFirstTileVisibleX(int firstTileVisibleX) {
		model.setFirstTileVisibleX(firstTileVisibleX);
	}

	public void setFirstTileVisibleY(int firstTileVisibleY) {
		model.setFirstTileVisibleY(firstTileVisibleY);
	}
	
	public void setAllTiles(Integer[][] tiles) {
		model.setAllTiles(tiles);
	}
	
	public int getAllTilesWidth () {
		return model.getAllTilesWidth();
	}
	
	public int getAllTilesHeight () {
		return model.getAllTilesHeight();
	}
	
	public int getFirstTileVisibleX() {
		return model.getFirstTileVisibleX();
	}
	
	public int getFirstTileVisibleY() {
		return model.getFirstTileVisibleY();
	}
	
	public DragCoordinates getDragCoordinates() {
		return model.getDragCoordinates();
	}

	public void setDragCoordinates(DragCoordinates dragCoordinates) {
		model.setDragCoordinates(dragCoordinates);
	}
	
}
