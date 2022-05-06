package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.MapModel;
import model.Tile;
import util.ImageLoader;
import util.TilePicker;

public class MapView extends GridPane {
	
	private MapModel model;
	private ColorAdjust colorAdjustHighlight = new ColorAdjust(0, 0, 0.5, 0); 
	
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
		
    	for(int x = model.getFirstTileVisibleX(); x < model.getFirstTileVisibleX() + model.getTilesVisibleLine(); x++) {
    		for(int y = model.getFirstTileVisibleY(); y < model.getFirstTileVisibleY() + model.getTilesVisibleLine(); y++) {
    			int id = model.getTile(x, y);
    			
    			Tile tile = TilePicker.getTile(id);
    			ImageView tileImageView = new ImageView(tile.image);
        		tileImageView.setFitHeight(model.getTileSize());
        		tileImageView.setFitWidth(model.getTileSize());
        		tileImageView.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[x][y]) {
        				tileImageView.setEffect(colorAdjustHighlight);
        			}
        		}
        			
        		this.add(tileImageView, x, y);
    			
    		}
    	}
    	
    	drawArrows();
    	this.setGridLinesVisible(true);
	}
	
	public void changeButtonAvailability(Button btn, boolean available) {
		btn.setDisable(!available);
	}
	
	private void drawArrows() {
		double fitBigger = Math.min(35, model.getTileSize());
		double fitSmaller = 0.5 * fitBigger;
		
		if(model.getTilesVisibleLine() % 2 == 0) { // je videt sudy pocet dlazdic
    		int arrowIndex1 = (int)(model.getTilesVisibleLine()/2.0) - 1;
    		int arrowIndex2 = arrowIndex1 + 1;
    		
        	if(model.getFirstTileVisibleX() > 0) {	// mozne hybat doleva 
        		// prvni cast sipky
        		StackPane arrowPane1 = new StackPane();
        		
        		int id1 = model.getTile(model.getFirstTileVisibleX(), model.getFirstTileVisibleY() + arrowIndex1);
        		
        		Tile tile1 = TilePicker.getTile(id1);
    			ImageView tileImageView1 = new ImageView(tile1.image);
        		tileImageView1.setFitHeight(model.getTileSize());
        		tileImageView1.setFitWidth(model.getTileSize());
        		tileImageView1.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX()][model.getFirstTileVisibleY() + arrowIndex1]) {
        				tileImageView1.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane1.getChildren().add(tileImageView1);
        		
        		ImageView arrowTop = new ImageView(ImageLoader.TRIANGLE_LEFT_TOP_HALF);
        		
        		arrowTop.setFitHeight(fitSmaller);
        		arrowTop.setFitWidth(fitBigger);
        		arrowTop.setOpacity(0.75);
        		
        		arrowPane1.getChildren().add(arrowTop);
        		arrowPane1.setAlignment(Pos.BOTTOM_LEFT);
        		
        		this.add(arrowPane1, model.getFirstTileVisibleX(), model.getFirstTileVisibleY() + arrowIndex1);
        		
        		// druha cast sipky
        		StackPane arrowPane2 = new StackPane();
        		
        		int id2 = model.getTile(model.getFirstTileVisibleX(), model.getFirstTileVisibleY() + arrowIndex2);
        		
        		Tile tile2 = TilePicker.getTile(id2);
    			ImageView tileImageView2 = new ImageView(tile2.image);
    			tileImageView2.setFitHeight(model.getTileSize());
    			tileImageView2.setFitWidth(model.getTileSize());
    			tileImageView2.setSmooth(true);
    			
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX()][model.getFirstTileVisibleY() + arrowIndex2]) {
        				tileImageView2.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane2.getChildren().add(tileImageView2);
        		
        		ImageView arrowBottom = new ImageView(ImageLoader.TRIANGLE_LEFT_BOTTOM_HALF);
        		arrowBottom.setFitHeight(fitSmaller);
        		arrowBottom.setFitWidth(fitBigger);
        		arrowBottom.setOpacity(0.75);
        		
        		arrowPane2.getChildren().add(arrowBottom);
        		arrowPane2.setAlignment(Pos.TOP_LEFT);
        		
        		this.add(arrowPane2, model.getFirstTileVisibleX(), model.getFirstTileVisibleY() + arrowIndex2);
        	}
        	
        	
        	if(model.getFirstTileVisibleY() > 0) {	// mozne hybat nahoru 
        		// prvni cast sipky
        		StackPane arrowPane1 = new StackPane();
        		
        		int id1 = model.getTile(model.getFirstTileVisibleX() + arrowIndex1, model.getFirstTileVisibleY());
        		
        		Tile tile1 = TilePicker.getTile(id1);
    			ImageView tileImageView1 = new ImageView(tile1.image);
        		tileImageView1.setFitHeight(model.getTileSize());
        		tileImageView1.setFitWidth(model.getTileSize());
        		tileImageView1.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + arrowIndex1][model.getFirstTileVisibleY()]) {
        				tileImageView1.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane1.getChildren().add(tileImageView1);
        		
        		ImageView arrowLeft = new ImageView(ImageLoader.TRIANGLE_UP_LEFT_HALF);
        		arrowLeft.setFitHeight(fitBigger);
        		arrowLeft.setFitWidth(fitSmaller);
        		arrowLeft.setOpacity(0.75);
        		
        		arrowPane1.getChildren().add(arrowLeft);
        		arrowPane1.setAlignment(Pos.TOP_RIGHT);
        		
        		this.add(arrowPane1, model.getFirstTileVisibleX() + arrowIndex1, model.getFirstTileVisibleY());
        		
        		// druha cast sipky
        		StackPane arrowPane2 = new StackPane();
        		
        		int id2 = model.getTile(model.getFirstTileVisibleX() + arrowIndex2, model.getFirstTileVisibleY());
        		
        		Tile tile2 = TilePicker.getTile(id2);
    			ImageView tileImageView2 = new ImageView(tile2.image);
        		tileImageView2.setFitHeight(model.getTileSize());
        		tileImageView2.setFitWidth(model.getTileSize());
        		tileImageView2.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + arrowIndex2][model.getFirstTileVisibleY()]) {
        				tileImageView2.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane2.getChildren().add(tileImageView2);
        		
        		ImageView arrowRight = new ImageView(ImageLoader.TRIANGLE_UP_RIGHT_HALF);
        		arrowRight.setFitHeight(fitBigger);
        		arrowRight.setFitWidth(fitSmaller);
        		arrowRight.setOpacity(0.75);
        		
        		arrowPane2.getChildren().add(arrowRight);
        		arrowPane2.setAlignment(Pos.TOP_LEFT);
        		
        		this.add(arrowPane2, model.getFirstTileVisibleX() + arrowIndex2, model.getFirstTileVisibleY());
        	}
        	
        	if(model.getFirstTileVisibleY() < model.getAllTilesHeight() - model.getTilesVisibleLine()) { // mozne hybat dolu
        		// prvni cast sipky
        		StackPane arrowPane1 = new StackPane();
        		
        		int id1 = model.getTile(model.getFirstTileVisibleX() + arrowIndex1, model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1);
        		
        		Tile tile1 = TilePicker.getTile(id1);
    			ImageView tileImageView1 = new ImageView(tile1.image);
        		tileImageView1.setFitHeight(model.getTileSize());
        		tileImageView1.setFitWidth(model.getTileSize());
        		tileImageView1.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + arrowIndex1][model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1]) {
        				tileImageView1.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane1.getChildren().add(tileImageView1);
        		
        		ImageView arrowLeft = new ImageView(ImageLoader.TRIANGLE_DOWN_LEFT_HALF);
        		arrowLeft.setFitHeight(fitBigger);
        		arrowLeft.setFitWidth(fitSmaller);
        		arrowLeft.setOpacity(0.75);
        		
        		arrowPane1.getChildren().add(arrowLeft);
        		arrowPane1.setAlignment(Pos.BOTTOM_RIGHT);
        		
        		this.add(arrowPane1, model.getFirstTileVisibleX() + arrowIndex1, model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1);
        		
        		// druha cast sipky
        		StackPane arrowPane2 = new StackPane();
        		
        		int id2 = model.getTile(model.getFirstTileVisibleX() + arrowIndex2, model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1);
        		
        		Tile tile2 = TilePicker.getTile(id2);
    			ImageView tileImageView2 = new ImageView(tile2.image);
        		tileImageView2.setFitHeight(model.getTileSize());
        		tileImageView2.setFitWidth(model.getTileSize());
        		tileImageView2.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + arrowIndex2][model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1]) {
        				tileImageView2.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane2.getChildren().add(tileImageView2);
        		
        		ImageView arrowRight = new ImageView(ImageLoader.TRIANGLE_DOWN_RIGHT_HALF);
        		arrowRight.setFitHeight(fitBigger);
        		arrowRight.setFitWidth(fitSmaller);
        		arrowRight.setOpacity(0.75);
        		
        		arrowPane2.getChildren().add(arrowRight);
        		arrowPane2.setAlignment(Pos.BOTTOM_LEFT);
        		
        		this.add(arrowPane2, model.getFirstTileVisibleX() + arrowIndex2, model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1);
        	}
        	
        	if(model.getFirstTileVisibleX() < model.getAllTilesWidth() - model.getTilesVisibleLine()) { // mozne hybat doprava
        		// prvni cast sipky
        		StackPane arrowPane1 = new StackPane();
        		
        		int id1 = model.getTile(model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1, model.getFirstTileVisibleY() + arrowIndex1);
        		
        		Tile tile1 = TilePicker.getTile(id1);
    			ImageView tileImageView1 = new ImageView(tile1.image);
        		tileImageView1.setFitHeight(model.getTileSize());
        		tileImageView1.setFitWidth(model.getTileSize());
        		tileImageView1.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1][model.getFirstTileVisibleY() + arrowIndex1]) {
        				tileImageView1.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane1.getChildren().add(tileImageView1);
        		
        		ImageView arrowTop = new ImageView(ImageLoader.TRIANGLE_RIGHT_TOP_HALF);
        		arrowTop.setFitHeight(fitSmaller);
        		arrowTop.setFitWidth(fitBigger);
        		arrowTop.setOpacity(0.75);
        		
        		arrowPane1.getChildren().add(arrowTop);
        		arrowPane1.setAlignment(Pos.BOTTOM_RIGHT);
        		
        		this.add(arrowPane1, model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1, model.getFirstTileVisibleY() + arrowIndex1);
        		
        		// druha cast sipky
        		StackPane arrowPane2 = new StackPane();
        		
        		int id2 = model.getTile(model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1, model.getFirstTileVisibleY() + arrowIndex2);
        		
        		Tile tile2 = TilePicker.getTile(id2);
    			ImageView tileImageView2 = new ImageView(tile2.image);
        		tileImageView2.setFitHeight(model.getTileSize());
        		tileImageView2.setFitWidth(model.getTileSize());
        		tileImageView2.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1][model.getFirstTileVisibleY() + arrowIndex2]) {
        				tileImageView2.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane2.getChildren().add(tileImageView2);
        		
        		ImageView arrowBottom = new ImageView(ImageLoader.TRIANGLE_RIGHT_BOTTOM_HALF);
        		arrowBottom.setFitHeight(fitSmaller);
        		arrowBottom.setFitWidth(fitBigger);
        		arrowBottom.setOpacity(0.75);
        		
        		arrowPane2.getChildren().add(arrowBottom);
        		arrowPane2.setAlignment(Pos.TOP_RIGHT);
        		
        		this.add(arrowPane2, model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1, model.getFirstTileVisibleY() + arrowIndex2);
        	}

    	}
    	else {  // je videt lichy pocet dlazdic
        	int arrowIndex = (int)Math.ceil(model.getTilesVisibleLine()/2.0) - 1;
        	
        	if(model.getFirstTileVisibleX() > 0) {	// mozne hybat doleva 	
        		StackPane arrowPane = new StackPane();
        		
        		int id = model.getTile(model.getFirstTileVisibleX(), model.getFirstTileVisibleY() + arrowIndex);
        		
        		Tile tile = TilePicker.getTile(id);
    			ImageView tileImageView = new ImageView(tile.image);
        		tileImageView.setFitHeight(model.getTileSize());
        		tileImageView.setFitWidth(model.getTileSize());
        		tileImageView.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX()][model.getFirstTileVisibleY() + arrowIndex]) {
        				tileImageView.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane.getChildren().add(tileImageView);
        		
        		ImageView arrow = new ImageView(ImageLoader.TRIANGLE_LEFT);
        		arrow.setFitHeight(fitBigger);
        		arrow.setFitWidth(fitBigger);
        		arrow.setOpacity(0.75);
        		
        		arrowPane.getChildren().add(arrow);
        		arrowPane.setAlignment(Pos.CENTER_LEFT);
        		
        		this.add(arrowPane, model.getFirstTileVisibleX(), model.getFirstTileVisibleY() + arrowIndex);
        	}
        	
        	if(model.getFirstTileVisibleY() > 0) {	// mozne hybat nahoru 
        		StackPane arrowPane = new StackPane();
        		
        		int id = model.getTile(model.getFirstTileVisibleX() + arrowIndex, model.getFirstTileVisibleY());
        		
        		Tile tile = TilePicker.getTile(id);
    			ImageView tileImageView = new ImageView(tile.image);
        		tileImageView.setFitHeight(model.getTileSize());
        		tileImageView.setFitWidth(model.getTileSize());
        		tileImageView.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + arrowIndex][model.getFirstTileVisibleY()]) {
        				tileImageView.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane.getChildren().add(tileImageView);
        		
        		ImageView arrow = new ImageView(ImageLoader.TRIANGLE_UP);
        		arrow.setFitHeight(fitBigger);
        		arrow.setFitWidth(fitBigger);
        		arrow.setOpacity(0.75);
        		
        		arrowPane.getChildren().add(arrow);
        		arrowPane.setAlignment(Pos.TOP_CENTER);
        		
        		this.add(arrowPane, model.getFirstTileVisibleX() + arrowIndex, model.getFirstTileVisibleY());
        	}
        	
        	if(model.getFirstTileVisibleY() < model.getAllTilesHeight() - model.getTilesVisibleLine()) { // mozne hybat dolu
        		StackPane arrowPane = new StackPane();
        		
        		int id = model.getTile(model.getFirstTileVisibleX() + arrowIndex, model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1);
        		
        		Tile tile = TilePicker.getTile(id);
    			ImageView tileImageView = new ImageView(tile.image);
        		tileImageView.setFitHeight(model.getTileSize());
        		tileImageView.setFitWidth(model.getTileSize());
        		tileImageView.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + arrowIndex][model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1]) {
        				tileImageView.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane.getChildren().add(tileImageView);
        		
        		ImageView arrow = new ImageView(ImageLoader.TRIANGLE_DOWN);
        		arrow.setFitHeight(fitBigger);
        		arrow.setFitWidth(fitBigger);
        		arrow.setOpacity(0.75);
        		
        		arrowPane.getChildren().add(arrow);
        		arrowPane.setAlignment(Pos.BOTTOM_CENTER);
        		
        		this.add(arrowPane, model.getFirstTileVisibleX() + arrowIndex, model.getFirstTileVisibleY() + model.getTilesVisibleLine() - 1);
        	}
        	
        	if(model.getFirstTileVisibleX() < model.getAllTilesWidth() - model.getTilesVisibleLine()) { // mozne hybat doprava
        		StackPane arrowPane = new StackPane();
        		
        		int id = model.getTile(model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1, model.getFirstTileVisibleY() + arrowIndex);
        		
        		Tile tile = TilePicker.getTile(id);
    			ImageView tileImageView = new ImageView(tile.image);
        		tileImageView.setFitHeight(model.getTileSize());
        		tileImageView.setFitWidth(model.getTileSize());
        		tileImageView.setSmooth(true);
        		
        		if(model.getChosenTiles() != null) {
        			if(model.getChosenTiles()[model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1][model.getFirstTileVisibleY() + arrowIndex]) {
        				tileImageView.setEffect(colorAdjustHighlight);
        			}
        		}
        		
        		arrowPane.getChildren().add(tileImageView);
        		
        		ImageView arrow = new ImageView(ImageLoader.TRIANGLE_RIGHT);
        		arrow.setFitHeight(fitBigger);
        		arrow.setFitWidth(fitBigger);
        		arrow.setOpacity(0.75);
        		
        		arrowPane.getChildren().add(arrow);
        		arrowPane.setAlignment(Pos.CENTER_RIGHT);
        		
        		this.add(arrowPane, model.getFirstTileVisibleX() + model.getTilesVisibleLine() - 1, model.getFirstTileVisibleY() + arrowIndex);
        	}
    	}
	}
	
}
