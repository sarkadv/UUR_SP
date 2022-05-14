package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.ImageLoader;

public class AboutWindow extends Stage {
	
	public AboutWindow(Stage primaryStage) {
		
		this.setScene(createScene());
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("O Aplikaci");
		this.initOwner(primaryStage);
		this.setMinHeight(400);
		this.setMinWidth(300);
		this.show();
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 300, 400);
		
		return scene;
	}
	
	private Parent createRootPane() {
		VBox rootPane = new VBox(5);
		rootPane.setPadding(new Insets(10));
		rootPane.setAlignment(Pos.CENTER);
		
		ImageView logoImage = new ImageView(ImageLoader.ALIEN_LOGO);
		logoImage.setFitWidth(200);
		logoImage.setPreserveRatio(true);
		
		Label version = new Label("Verze 1.0");
		Label author = new Label("© Šárka Dvořáková 2022");
		
		rootPane.getChildren().addAll(logoImage, version, author);
		
		return rootPane;
	}

}
