package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Button {
	private Image image;
    private ImageView imageView;
    private ColorAdjust colorAdjustHighlight = new ColorAdjust(0, 0, 0.5, 0); 
    private ColorAdjust colorAdjustBack = new ColorAdjust(0, 0, 0, 0); 
    private String name;
    private double width;
    private double height;
    public int imageId;

    public ImageButton(double width, double height, String name, Image normal) {
    	super.setHeight(height);
    	super.setWidth(width);
    	this.name = name;
    	this.width = width;
    	this.height = height;
    	super.setTooltip(new Tooltip(name));
    	super.setMinSize(width, height);
    	super.setMaxSize(width, height);
    	
    	this.image = normal;
    	
        this.imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);
        
        this.setGraphic(this.imageView);

    } 
    
    public void highlightImage() {
    	this.imageView.setEffect(colorAdjustHighlight);
    }
    
    public void imageBackToNormal() {
    	this.imageView.setEffect(colorAdjustBack);
    }
    
    public String getName() {
    	return this.name;
    }

	public void setImage(Image image) {
		this.image = image;
		
		 this.imageView = new ImageView(image);
	     imageView.setFitHeight(height);
	     imageView.setFitWidth(width);
	     imageView.setPreserveRatio(true);
	        
	     this.setGraphic(this.imageView);
	}

	public Image getImage() {
		return image;
	}
}
