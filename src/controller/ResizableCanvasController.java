package controller;

import java.awt.event.MouseEvent;

import model.ResizableCanvasModel;
import model.Tile;
import util.TilePicker;
import view.ResizableCanvasView;

public class ResizableCanvasController {
	
	private ResizableCanvasModel model;
	private ResizableCanvasView view;
	
	public ResizableCanvasController() {
		this.model = new ResizableCanvasModel();
		this.view = new ResizableCanvasView(650);
		this.model.getCanvasSize().addListener((obs, oldValue, newValue) -> {
			double newValueDouble = newValue.doubleValue();
			resize(newValueDouble, newValueDouble);
		});
		
	}
	
	public void resize(double width, double height) {
		view.resize(width, height);
	}

	public void putTile(MouseEvent e) {
		int x = getTileCoordinate(e.getX());
		int y = getTileCoordinate(e.getY());
	}
	
	private int getTileCoordinate(int clickedCoordinate) {
		int divisionCount = 0;
		
		while(clickedCoordinate % model.getTilesVisibleLine().get() < model.getCurrentTileSize().get()) {
			clickedCoordinate = clickedCoordinate / (int)model.getCurrentTileSize().get();
		}
		
		return divisionCount;
	}

	public ResizableCanvasModel getModel() {
		return model;
	}

	public ResizableCanvasView getView() {
		return view;
	}
	
	
}
