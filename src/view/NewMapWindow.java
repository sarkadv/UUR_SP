package view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
	private ComboBox<Integer> tilesVisibleCombo;
	private List<Integer> tilesVisibleChoiceList;
	private Label mapSizeComputedNumber;
	private final int MAX_TILE_NUMBER = 1600;
	private int chosenTilesVisible;
	
	public NewMapWindow(MapModel model, Stage primaryStage) {
		this.model = model;
		this.mapSizeWidthTF = new TextField("8");
		this.mapSizeHeightTF = new TextField("8");
		this.mapSizeComputedNumber = new Label("64");
		this.tilesVisibleChoiceList = FXCollections.observableArrayList(1, 4, 9, 16, 25, 36, 49, 64);
		
		this.setScene(createScene());
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("Nová Mapa");
		this.initOwner(primaryStage);
		this.setOnCloseRequest(e -> e.consume());
		this.setMinHeight(275);
		this.setMaxHeight(275);
		this.setMinWidth(400);
		this.setMaxWidth(400);
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 400, 300);
		
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
		mapSizeWidthTF.setPrefColumnCount(3);
		mapSizeWidthTF.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
            	mapSizeWidthTF.setText(oldValue);
            }
            computeMapSize();
            computeTilesVisibleChoiceList();
		});
		
		Label mapSizeHeightLabel = new Label("Výška: ");
		mapSizeHeightTF.setPrefColumnCount(3);
		mapSizeHeightTF.textProperty().addListener((obs, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
            	mapSizeHeightTF.setText(oldValue);
            }
            computeMapSize();
            computeTilesVisibleChoiceList();
		});
		
		mapSizeControls.getChildren().addAll(mapSizeWidthLabel, mapSizeWidthTF, mapSizeHeightLabel, mapSizeHeightTF);
		
		HBox mapSizeComputedPane = new HBox(5);
		mapSizeComputedPane.setAlignment(Pos.CENTER);
		Label mapSizeComputedLabel = new Label("Počet dlaždic: ");
		
		mapSizeComputedPane.getChildren().addAll(mapSizeComputedLabel, mapSizeComputedNumber);
		
		mapSizePane.getChildren().addAll(mapSizeLabel, mapSizeControls, mapSizeComputedPane);
		
		return mapSizePane;
	}
	
	private Node createVisibleTilesPane() {
		HBox visibleTilesPane = new HBox(20);
		visibleTilesPane.setPadding(new Insets(10));
		
		Label visibleTilesLabel = new Label("Počet Viditelných Dlaždic");
		tilesVisibleCombo = new ComboBox<Integer>();
		tilesVisibleCombo.setItems(FXCollections.observableArrayList(tilesVisibleChoiceList));
		tilesVisibleCombo.setValue(16);
		
		visibleTilesPane.getChildren().addAll(visibleTilesLabel, tilesVisibleCombo);
		
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
		else if(Integer.parseInt(mapSizeComputedNumber.getText()) > MAX_TILE_NUMBER){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nová mapa");
			alert.setHeaderText("Nepodařilo se vytvořit novou mapu");
			alert.setContentText("Celkový počet dlaždic nesmí být větší než " + MAX_TILE_NUMBER + " .");
			alert.showAndWait();
		}
		else {
			int allTilesWidth = Integer.parseInt(mapSizeWidthTF.getText());
			int allTilesHeight = Integer.parseInt(mapSizeHeightTF.getText());
			
			int tilesVisibleLine = (int)Math.sqrt(tilesVisibleCombo.getValue());
			
			model.setAllTilesWidth(allTilesWidth);
			model.setAllTilesHeight(allTilesHeight);
			model.setTilesVisibleLine(tilesVisibleLine);
			model.initAllTiles();
			this.close();
		}
		
	}
	
	private void computeMapSize() {
		if(!mapSizeHeightTF.getText().equals("") && !mapSizeWidthTF.getText().equals("")) {
			int value = Integer.parseInt(mapSizeHeightTF.getText()) * Integer.parseInt(mapSizeWidthTF.getText());
			mapSizeComputedNumber.setText(String.valueOf(value));
		}
		else {
			mapSizeComputedNumber.setText("");
		}
		
	}
	
	private void computeTilesVisibleChoiceList() {
		int chosenTilesVisible = tilesVisibleCombo.getValue();
		
		if(!mapSizeHeightTF.getText().equals("") && !mapSizeWidthTF.getText().equals("")) {
			int smallerSide = Math.min(Integer.parseInt(mapSizeWidthTF.getText()), Integer.parseInt(mapSizeHeightTF.getText()));
			
			this.tilesVisibleChoiceList = FXCollections.observableArrayList();
			
			for(int i = 1; i <= smallerSide; i++) {
				tilesVisibleChoiceList.add(i*i);
			}
			
			this.tilesVisibleCombo.setItems(FXCollections.observableArrayList(tilesVisibleChoiceList));
		
			if(Math.sqrt(chosenTilesVisible) > smallerSide) {
				this.tilesVisibleCombo.setValue(smallerSide*smallerSide);
			}
		}	
	}
}
