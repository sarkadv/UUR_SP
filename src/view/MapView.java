package view;

import controller.MapController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.MapModel;
import model.Tile;
import util.TilePicker;

public class MapView extends GridPane {
	
	private MapModel model;
	
	public MapView(MapModel model) {
		this.model = model;
		this.gridLinesVisibleProperty().set(true);
		repaint();
	}
	
	public void repaint() {
		this.setGridLinesVisible(false);
		this.getColumnConstraints().clear();
		this.getRowConstraints().clear();
		this.getChildren().clear();
		
    	for(int x = 0; x < model.getTilesVisibleLine().get(); x++) {
    		for(int y = 0; y < model.getTilesVisibleLine().get(); y++) {
    			int id = model.getTile(x, y);
    			
    			Tile tile = TilePicker.getTile(id);
    			if(tile != null) {
    				ImageView tileImageView = new ImageView(tile.image);
        			tileImageView.setFitHeight(model.getTileSize().get());
        			tileImageView.setFitWidth(model.getTileSize().get());
        			tileImageView.setSmooth(true);
        			
        			this.add(tileImageView, x, y);
       
    			}
    			
    		}
    	}
    	
    	this.setGridLinesVisible(true);
	}
}
