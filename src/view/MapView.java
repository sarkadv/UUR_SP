package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
		
    	for(int x = model.getFirstTileVisibleX().get(); x < model.getFirstTileVisibleX().get() + model.getTilesVisibleLine().get(); x++) {
    		for(int y = model.getFirstTileVisibleY().get(); y < model.getFirstTileVisibleY().get() + model.getTilesVisibleLine().get(); y++) {
    			int id = model.getTile(x, y);
    			
    			Tile tile = TilePicker.getTile(id);
    			ImageView tileImageView = new ImageView(tile.image);
        		tileImageView.setFitHeight(model.getTileSize().get());
        		tileImageView.setFitWidth(model.getTileSize().get());
        		tileImageView.setSmooth(true);
        			
        		this.add(tileImageView, x, y);
    			
    		}
    	}
    	
    	this.setGridLinesVisible(true);
	}
}
