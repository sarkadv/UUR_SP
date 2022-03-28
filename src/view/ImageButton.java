package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Button {
	private Image normalImage;
	private Image pressedImage;

    private final ImageView iv;

    public ImageButton(double width, double height, String name, Image normal, Image pressed) {
    	super.setHeight(height);
    	super.setWidth(width);
    	super.setTooltip(new Tooltip(name));
    	super.setMinSize(width, height);
    	super.setMaxSize(width, height);
    	
    	this.normalImage = normal;
    	this.pressedImage = pressed;
    	
        this.iv = new ImageView(normalImage);
        iv.setFitHeight(height);
        iv.setFitWidth(width);
        iv.setPreserveRatio(true);
        
        this.setGraphic(this.iv);

        this.setOnMousePressed(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent evt) {
                iv.setImage(pressedImage);
            }

        });

        this.setOnMouseReleased(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent evt) {
                iv.setImage(normalImage);
            }

        });

    } 
    
    public ImageButton(double width, double height, String name, Image normal) {
    		this(width, height, name, normal, normal);
    }
}
