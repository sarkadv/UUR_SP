package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MapModel;

public class StarterWindow extends Stage {
	
	private Button newMapBtn;
	private Button loadMapBtn;
	private MapModel model;
	
	public StarterWindow(Stage primaryStage, MapModel model) {
		this.setScene(createScene());
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("Tiny Planet Builder");
		this.initOwner(primaryStage);
		this.setOnCloseRequest(e -> e.consume());
		this.setMinHeight(150);
		this.setMaxHeight(150);
		this.setMinWidth(225);
		this.setMaxWidth(225);
		this.model = model;
		
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 225, 150);
		createControls();
		
		return scene;
	}
	
	private Parent createRootPane() {
		VBox rootPane = new VBox(10);
		rootPane.setAlignment(Pos.CENTER);
		newMapBtn = new Button("Nová Mapa");
		newMapBtn.setPadding(new Insets(5));
		loadMapBtn = new Button("Načíst Mapu");
		loadMapBtn.setPadding(new Insets(5));
		
		rootPane.getChildren().addAll(newMapBtn, loadMapBtn);
		
		return rootPane;
	}
	
	private void createControls() {
		newMapBtn.setOnAction(e -> {
			new NewMapWindow(this, model);
		});
	}
}
