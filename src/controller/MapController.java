package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import model.MapModel;
import model.Tile;
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
	
	public void changeTileSize(double stageWidth, double stageHeight) {
		double newTileSize = Math.min(stageWidth, stageHeight) - 150;
		newTileSize = newTileSize / model.getTilesVisibleLine().get();
		
		model.setTileSize(newTileSize);
		view.repaint();
	}
	
	public void putTile(MouseEvent e, Tile tile) {
		int x = getTileCoordinate(e.getX());
		int y = getTileCoordinate(e.getY());
		
		model.setTile(x, y, tile.id);
		view.repaint();
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
