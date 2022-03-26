package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Button {
	private Image normalImage = new Image("file:img/hades/Hades_Industrial_Den.png");
	private Image pressedImage = new Image ("file:img/hades/Hades_Industrial_Noc.png");

    private final ImageView iv;

    public ImageButton(double width, double height, String urlNormal, String urlPressed) {
    	this.normalImage = new Image(urlNormal);
    	this.pressedImage = new Image(urlPressed);
    	
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
}
