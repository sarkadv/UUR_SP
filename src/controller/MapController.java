package controller;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.MapModel;
import model.Tile;
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
	
	public Integer[][] getAllTiles(){
		return model.getAllTiles();
	}
	
	public void showNewMap(double stageWidth, double stageHeight) {
		changeTileSize(stageWidth, stageHeight);
	}
	
	public void changeTileSize(double stageWidth, double stageHeight) {
		double newTileSize = Math.min(stageWidth, stageHeight) - 150;
		newTileSize = newTileSize / model.getTilesVisibleLine();
		
		model.setTileSize(newTileSize);
		repaint();
	}
	
	public void initAllTilesNewMap() {
		model.initAllTilesNewMap();
	}
	
	public void reInitAllTiles() {
		model.reInitAllTiles();
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
		
		Tile copied = TilePicker.getTile(model.getTile(x, y));
		if(copied.id == 00 || copied.id == 01) {
			return null;
		}
		else {
			return copied;
		}
	}
	
	private int getTileCoordinate(double clickedCoordinate) {
		int substractionCount = 0;
		
		while(clickedCoordinate - model.getTileSize() > 0) {
			clickedCoordinate = clickedCoordinate - model.getTileSize();
			substractionCount++;
		}
		
		return substractionCount;
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
	
	public void setFirstTileVisibleX(int firstTileVisibleX) {
		model.setFirstTileVisibleX(firstTileVisibleX);
	}

	public void setFirstTileVisibleY(int firstTileVisibleY) {
		model.setFirstTileVisibleY(firstTileVisibleY);
	}
	
	public void setAllTiles(Integer[][] tiles) {
		model.setAllTiles(tiles);
	}
}
