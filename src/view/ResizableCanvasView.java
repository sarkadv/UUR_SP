package view;

import controller.MainWindowController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.ResizableCanvasModel;
import model.Tile;
import util.TilePicker;

public class ResizableCanvasView extends Canvas {
	
	private ResizableCanvasModel model = new ResizableCanvasModel();
	
	public ResizableCanvasView(int sideSize) {
		super(sideSize, sideSize);
	}

    @Override
    public double minHeight(double width)
    {
        return 500;
    }

    @Override
    public double minWidth(double height)
    {
        return 500;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height)
    {
    	super.setWidth(height);
    	super.setHeight(height);

    	paint(); 
    }
    
    private void paint() {
    	GraphicsContext gc = this.getGraphicsContext2D();
    	gc.setFill(model.getPlanetColor().get());
    	gc.fillRect(0, 0, model.getCanvasSize().get(), model.getCanvasSize().get());
    	model.setTile(0, 0, 10);
    	model.setTile(0, 1, 20);
    	model.setTile(1, 0, 30);
    	model.setTile(1, 1, 40);
    	paintTiles();
    	paintBorder();
    	paintGrid();
    }
    
    private void paintBorder() {
    	GraphicsContext gc = this.getGraphicsContext2D();
    	Color fill = model.getDarkMode().get()? Color.WHITE : Color.BLACK;
    	
    	gc.setFill(fill);
    	gc.setLineWidth(10);
    	gc.strokeLine(0, 0, 0, model.getCanvasSize().get());
    	gc.strokeLine(0, model.getCanvasSize().get(), model.getCanvasSize().get(), model.getCanvasSize().get());
    	gc.strokeLine(model.getCanvasSize().get(), model.getCanvasSize().get(), model.getCanvasSize().get(), 0);
    	gc.strokeLine(model.getCanvasSize().get(), 0, 0, 0);
    }
    
    private void paintGrid() {
    	GraphicsContext gc = this.getGraphicsContext2D();
    	Color fill = model.getDarkMode().get()? Color.WHITE : Color.BLACK;
    	
    	gc.setFill(fill);
    	gc.setLineWidth(5);
    	
    	for(int i = 0; i < model.getTilesVisibleLine().get(); i++) {		// vodorovne cary
    		gc.strokeLine(0, model.getCanvasSize().get()/model.getTilesVisibleLine().get()*(i+1), model.getCanvasSize().get(), model.getCanvasSize().get()/model.getTilesVisibleLine().get()*(i+1));
    	}
    	
    	for(int i = 0; i < model.getTilesVisibleLine().get(); i++) {		// svisle cary
    		gc.strokeLine(model.getCanvasSize().get()/model.getTilesVisibleLine().get()*(i+1), 0, model.getCanvasSize().get()/model.getTilesVisibleLine().get()*(i+1), model.getCanvasSize().get());
    	}
    	
    }
    
    private void paintTiles() {
    	GraphicsContext gc = this.getGraphicsContext2D();
    	
    	for(int x = 0; x < model.getTilesVisibleLine().get(); x++) {
    		for(int y = 0; y < model.getTilesVisibleLine().get(); y++) {
    			int id = model.getTile(x, y);
    			
    			Tile tile = TilePicker.getTile(id);
    			if(tile != null) {
    				ImageView tileImageView = new ImageView(tile.image);
        			tileImageView.setFitHeight(model.getCanvasSize().get()/model.getTilesVisibleLine().get());
        			tileImageView.setFitWidth(model.getCanvasSize().get()/model.getTilesVisibleLine().get());
        			tileImageView.setPreserveRatio(true);
        			tileImageView.setSmooth(true);
        			Image tileImage = tileImageView.snapshot(null, null);
        			gc.drawImage(tileImage, x*(model.getCanvasSize().get()/model.getTilesVisibleLine().get()), y*(model.getCanvasSize().get()/model.getTilesVisibleLine().get()));
    			}
    			
    		}
    	}
    }
}
