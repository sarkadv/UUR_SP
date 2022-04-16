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
	
	public MapController(MapView view, MapModel model) {
		this.view = view;
		this.model = model;
	}

	public MapModel getModel() {
		return model;
	}
	
	public void showNewMap(double stageWidth, double stageHeight) {
		changeTileSize(stageWidth, stageHeight);
	}
	
	public void changeTileSize(double stageWidth, double stageHeight) {
		double newTileSize = Math.min(stageWidth, stageHeight) - 150;
		newTileSize = newTileSize / model.getTilesVisibleLine().get();
		
		model.setTileSize(newTileSize);
		view.repaint();
	}
	
	public void moveUp(KeyEvent e) {
		if(model.getFirstTileVisibleY().get() > 0) {
			model.setFirstTileVisibleY(model.getFirstTileVisibleY().get() - 1);
			view.repaint();
		}
	}
	
	public void moveDown(KeyEvent e) {
		if(model.getFirstTileVisibleY().get() < model.getAllTilesHeight().get() - model.getTilesVisibleLine().get()) {
			model.setFirstTileVisibleY(model.getFirstTileVisibleY().get() + 1);
			view.repaint();
		}
	}
	
	public void moveLeft(KeyEvent e) {
		if(model.getFirstTileVisibleX().get() > 0) {
			model.setFirstTileVisibleX(model.getFirstTileVisibleX().get() - 1);
			view.repaint();
		}
	}
	
	public void moveRight(KeyEvent e) {
		if(model.getFirstTileVisibleX().get() < model.getAllTilesWidth().get() - model.getTilesVisibleLine().get()) {
			model.setFirstTileVisibleX(model.getFirstTileVisibleX().get() + 1);
			view.repaint();
		}
	}
	
	public void putTile(MouseEvent e, Tile tile) {
		if(tile != null) {
			int x = getTileCoordinate(e.getX()) + model.getFirstTileVisibleX().get();
			int y = getTileCoordinate(e.getY()) + model.getFirstTileVisibleY().get();
			
			model.setTile(x, y, tile.id);
			view.repaint();
		}

	}
	
	public Tile copyTile(MouseEvent e) {
		int x = getTileCoordinate(e.getX()) + model.getFirstTileVisibleX().get();
		int y = getTileCoordinate(e.getY()) + model.getFirstTileVisibleY().get();
		
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
		
		while(clickedCoordinate - model.getTileSize().get() > 0) {
			clickedCoordinate = clickedCoordinate - model.getTileSize().get();
			substractionCount++;
		}
		
		return substractionCount;
	}

}
