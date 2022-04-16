package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import model.MapModel;

public class NewMapWindow extends Stage {
	
	private MapModel model;
	private TextField mapSizeWidthTF;
	private TextField mapSizeHeightTF;
	private ChoiceBox<Integer> tilesVisibleChoice;
	
	public NewMapWindow(MapModel model, Stage primaryStage) {
		this.model = model;
		
		this.setScene(createScene());
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("Nová Mapa");
		this.initOwner(primaryStage);
		this.setOnCloseRequest(e -> e.consume());
		this.setMinHeight(250);
		this.setMaxHeight(250);
		this.setMinWidth(400);
		this.setMaxWidth(400);
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 400, 250);
		
		return scene;
	}
	
	private Parent createRootPane() {
		VBox rootPane = new VBox(10);
		rootPane.setAlignment(Pos.CENTER);
		
		Button okBtn = new Button("OK");
		okBtn.setPrefWidth(100);
		okBtn.setOnAction(e -> commit());
		
		rootPane.getChildren().addAll(createMapSizePane(), new Separator(), createVisibleTilesPane(), new Separator(), okBtn);
		
		return rootPane;
	}

	private Node createMapSizePane() {
		VBox mapSizePane = new VBox(10);
		mapSizePane.setPadding(new Insets(0, 10, 0, 10));
		
		Label mapSizeLabel = new Label("Velikost Mapy");
		
		HBox mapSizeControls = new HBox(5);
		mapSizeControls.setAlignment(Pos.CENTER);
		mapSizeControls.setPadding(new Insets(10));
		Label mapSizeWidthLabel = new Label("Šířka: ");
		mapSizeWidthTF = new TextField("8");
		mapSizeWidthTF.setPrefColumnCount(3);
		mapSizeWidthTF.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
            	mapSizeWidthTF.setText(oldValue);
            }
		});
		
		Label mapSizeHeightLabel = new Label("Výška: ");
		mapSizeHeightTF = new TextField("8");
		mapSizeHeightTF.setPrefColumnCount(3);
		mapSizeHeightTF.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
            	mapSizeHeightTF.setText(oldValue);
            }
		});
		
		mapSizeControls.getChildren().addAll(mapSizeWidthLabel, mapSizeWidthTF, mapSizeHeightLabel, mapSizeHeightTF);
		
		mapSizePane.getChildren().addAll(mapSizeLabel, mapSizeControls);
		
		return mapSizePane;
	}
	
	private Node createVisibleTilesPane() {
		HBox visibleTilesPane = new HBox(20);
		visibleTilesPane.setPadding(new Insets(10));
		
		Label visibleTilesLabel = new Label("Počet Viditelných Dlaždic");
		tilesVisibleChoice = new ChoiceBox<Integer>();
		tilesVisibleChoice.setItems(FXCollections.observableArrayList(1, 4, 9, 16, 25, 36, 49, 64, 81, 100));
		tilesVisibleChoice.setValue(16);
		visibleTilesPane.getChildren().addAll(visibleTilesLabel, tilesVisibleChoice);
		
		return visibleTilesPane;
	}
	
	private void commit() {
		if(mapSizeHeightTF.getText() == "" || mapSizeWidthTF.getText() == "") {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nová mapa");
			alert.setHeaderText("Nepodařilo se vytvořit novou mapu");
			alert.setContentText("Musí být zadané rozměry mapy.");
			alert.showAndWait();
			
		}
		else {
			int allTilesWidth = Integer.parseInt(mapSizeWidthTF.getText());
			int allTilesHeight = Integer.parseInt(mapSizeHeightTF.getText());
			
			int tilesVisibleLine = (int)Math.sqrt(tilesVisibleChoice.getValue());
			int smallerSide = Math.min(allTilesHeight, allTilesWidth);
			if(tilesVisibleLine > smallerSide) {
				tilesVisibleLine = smallerSide;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Nová mapa");
				alert.setHeaderText("Počet viditelných dlaždic byl upraven");
				alert.setContentText("Počet viditelných dlaždic nesmí být větší než rozměry mapy.");
				alert.showAndWait();
			}
			
			model.setAllTilesWidth(allTilesWidth);
			model.setAllTilesHeight(allTilesHeight);
			model.setTilesVisibleLine(tilesVisibleLine);
			model.initAllTiles();
			this.close();
		}
		
	}
}
