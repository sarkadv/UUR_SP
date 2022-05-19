package view;

import java.util.List;
import java.util.Optional;
import controller.MainWindowController;
import controller.MapController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MapSettingsWindow extends Stage {
	
	private MapController mapController;
	private MainWindowController mainWindowController;
	private TextField mapSizeWidthTF;
	private TextField mapSizeHeightTF;
	private ComboBox<Integer> tilesVisibleCombo;
	private List<Integer> tilesVisibleChoiceList;
	private Label mapSizeComputedNumber;
	private final int MAX_TILE_NUMBER = 1600;
	private boolean darkMode;
	private ToggleGroup mapColorRBGroup;
	private RadioButton purpleRB;
	private RadioButton greenRB;
	private RadioButton orangeRB;
	private CheckBox modeCheckBox;
	private ToggleGroup modeRBGroup;
	private RadioButton lightRB;
	private RadioButton darkRB;
	private boolean settings;
	
	public MapSettingsWindow(MapController mapController, MainWindowController mainWindowController, Stage primaryStage, boolean darkMode, boolean settings) {
		this.mapController = mapController;
		this.mainWindowController = mainWindowController;
		this.settings = settings;
		this.tilesVisibleChoiceList = FXCollections.observableArrayList();
		
		if(settings) {
			this.mapSizeWidthTF = new TextField(String.valueOf(mapController.getAllTilesWidth()));
			this.mapSizeHeightTF = new TextField(String.valueOf(mapController.getAllTilesHeight()));
			this.mapSizeComputedNumber = new Label(String.valueOf(mapController.getAllTilesWidth()*mapController.getAllTilesHeight()));
			this.darkMode = mapController.getDarkMode();
		}
		else {
			this.mapSizeWidthTF = new TextField("8");
			this.mapSizeHeightTF = new TextField("8");
			this.mapSizeComputedNumber = new Label("64");
			this.darkMode = darkMode;
		}
		
		this.setScene(createScene());
		computeTilesVisibleChoiceList();
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("Nastavení mapy");
		this.initOwner(primaryStage);
		
		if(!settings) {
			this.setOnCloseRequest(e -> e.consume());
		}
		
		this.setMinHeight(500);
		this.setMaxHeight(500);
		this.setMinWidth(400);
		this.setMaxWidth(400);
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 400, 500);
		
		if(this.darkMode) {
			scene.getStylesheets().add("file:resources/darkmode.css");
		}
		
		return scene;
	}
	
	private Parent createRootPane() {
		VBox rootPane = new VBox(10);
		rootPane.setAlignment(Pos.CENTER);
		
		Button okBtn = new Button("OK");
		okBtn.setPrefWidth(100);
		okBtn.setOnAction(e -> commit());
		
		rootPane.getChildren().addAll(createMapSizePane(), new Separator(), createVisibleTilesPane(), 
				new Separator(), createMapColorPane(), new Separator(), createModePane(), 
				new Separator(), okBtn);
		
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
		Label mapSizeComputedLabel = new Label("Počet Dlaždic: ");
		
		mapSizeComputedPane.getChildren().addAll(mapSizeComputedLabel, mapSizeComputedNumber);
		
		mapSizePane.getChildren().addAll(mapSizeLabel, mapSizeControls, mapSizeComputedPane);
		
		return mapSizePane;
	}
	
	private Node createMapColorPane() {
		VBox mapColorPane = new VBox(10);
		mapColorPane.setPadding(new Insets(0, 10, 0, 10));
		
		Label mapColorLabel = new Label("Barva Mapy");
		
		VBox mapColorControls = new VBox(5);
		mapColorControls.setPadding(new Insets(5));
		
		mapColorRBGroup = new ToggleGroup();

		purpleRB = new RadioButton("Fialová");
		purpleRB.setToggleGroup(mapColorRBGroup);

		greenRB = new RadioButton("Zelená");
		greenRB.setToggleGroup(mapColorRBGroup);
		 
		orangeRB = new RadioButton("Oranžová");
		orangeRB.setToggleGroup(mapColorRBGroup);
		
		if(mapController.getActivePlanet() == 0) {
			purpleRB.setSelected(true);
		}
		else if(mapController.getActivePlanet() == 1) {
			greenRB.setSelected(true);
		}
		else if(mapController.getActivePlanet() == 2) {
			orangeRB.setSelected(true);
		}
		
		mapColorControls.getChildren().addAll(purpleRB, greenRB, orangeRB);
		
		mapColorPane.getChildren().addAll(mapColorLabel, mapColorControls);
		
		return mapColorPane;
	}
	
	private Node createModePane() {
		VBox modePane = new VBox(10);
		modePane.setPadding(new Insets(0, 10, 0, 10));
		
		HBox modeChangeControls = new HBox(5);
		
		Label modeLabel = new Label("Denní / Noční režim ovlivňuje vzhled okna");
		
		modeCheckBox = new CheckBox();
		modeCheckBox.selectedProperty().setValue(mainWindowController.isWindowModeChanges());
		
		modeChangeControls.getChildren().addAll(modeLabel, modeCheckBox);
		
		VBox modeColorControls = new VBox(5);
		modeColorControls.setPadding(new Insets(5));
		
		modeRBGroup = new ToggleGroup();

		lightRB = new RadioButton("Světlý mód");
		lightRB.setToggleGroup(modeRBGroup);
		lightRB.setSelected(!mainWindowController.getDarkMode());
		lightRB.setDisable(mainWindowController.isWindowModeChanges());

		darkRB = new RadioButton("Tmavý mód");
		darkRB.setToggleGroup(modeRBGroup);
		darkRB.setSelected(mainWindowController.getDarkMode());
		darkRB.setDisable(mainWindowController.isWindowModeChanges());
		
		modeColorControls.getChildren().addAll(lightRB, darkRB);
		
		modePane.getChildren().addAll(modeChangeControls, modeColorControls);
		
		modeCheckBox.selectedProperty().addListener(e -> {
			if(modeCheckBox.isSelected()) {
				lightRB.setDisable(true);
				darkRB.setDisable(true);
			}
			else {
				lightRB.setDisable(false);
				darkRB.setDisable(false);
			}
		});
		
		return modePane;
	}
	
	private Node createVisibleTilesPane() {
		HBox visibleTilesPane = new HBox(20);
		visibleTilesPane.setPadding(new Insets(10));
		
		Label visibleTilesLabel = new Label("Počet Viditelných Dlaždic");
		tilesVisibleCombo = new ComboBox<Integer>();
		tilesVisibleCombo.setItems(FXCollections.observableArrayList(tilesVisibleChoiceList));
		
		if(settings) {
			tilesVisibleCombo.setValue(mapController.getTilesVisibleLine() * mapController.getTilesVisibleLine());
		}
		else {
			tilesVisibleCombo.setValue(16);
		}
		
		
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
		else if(Integer.parseInt(mapSizeHeightTF.getText()) == 0 || Integer.parseInt(mapSizeWidthTF.getText()) == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Nová mapa");
			alert.setHeaderText("Nepodařilo se vytvořit novou mapu");
			alert.setContentText("Ani jeden z rozměrů mapy nesmí být 0.");
			alert.showAndWait();
		}
		else if (!settings) {
			int allTilesWidth = Integer.parseInt(mapSizeWidthTF.getText());
			int allTilesHeight = Integer.parseInt(mapSizeHeightTF.getText());
			int tilesVisibleLine = (int)Math.sqrt(tilesVisibleCombo.getValue());
			
			mapController.setAllTilesWidth(allTilesWidth);
			mapController.setAllTilesHeight(allTilesHeight);
			mapController.setTilesVisibleLine(tilesVisibleLine);
			
			if(purpleRB.isSelected()) {
				mapController.setActivePlanet(0);
			}
			else if(greenRB.isSelected()) {
				mapController.setActivePlanet(1);
			}
			else if(orangeRB.isSelected()) {
				mapController.setActivePlanet(2);
			}
			
			mapController.initAllTilesNewMap();
			mapController.changePlanet(mapController.getActivePlanet());
			mapController.setFirstTileVisibleX(0);
			mapController.setFirstTileVisibleY(0);
			
			setMode();
			
			this.close();
		}
		else if(settings) {
			int allTilesWidth = Integer.parseInt(mapSizeWidthTF.getText());
			int allTilesHeight = Integer.parseInt(mapSizeHeightTF.getText());
			int tilesVisibleLine = (int)Math.sqrt(tilesVisibleCombo.getValue());
			
			if(purpleRB.isSelected()) {
				mapController.changePlanet(0);
			}
			else if(greenRB.isSelected()) {
				mapController.changePlanet(1);
			}
			else if(orangeRB.isSelected()) {
				mapController.changePlanet(2);
			}
			
			if (allTilesWidth < mapController.getAllTilesWidth() || allTilesHeight < mapController.getAllTilesHeight()) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Změna velikosti mapy");
				alert.setHeaderText("Opravdu chcete mapu zmenšit?");
				alert.setContentText("Některé části mapy budou muset být oříznuty.");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					mapController.resizeMap(this.getOwner().getWidth(), this.getOwner().getHeight(), allTilesWidth, allTilesHeight, tilesVisibleLine);
					this.close();
				} else {
				    alert.close();
				}
			}
			
			else {
				mapController.resizeMap(this.getOwner().getWidth(), this.getOwner().getHeight(), allTilesWidth, allTilesHeight, tilesVisibleLine);
				this.close();
			}
			
			setMode();
			
		}
		
	}
	
	private void setMode() {
		mainWindowController.setWindowModeChanges(true);
		
		if(!modeCheckBox.isSelected()) {
			RadioButton selectedRB = (RadioButton) modeRBGroup.getSelectedToggle();
	
			if(selectedRB == lightRB) {
				mainWindowController.setDarkMode(false);
				mainWindowController.setDarkModeCSS(false, (Stage)this.getOwner());
				mainWindowController.changeButtonsMode(false);
			}
			else {
				mainWindowController.setDarkMode(true);
				mainWindowController.setDarkModeCSS(true, (Stage)this.getOwner());
				mainWindowController.changeButtonsMode(true);
			}
			
			mainWindowController.setWindowModeChanges(false);
		}
		else {
			mainWindowController.setDarkMode(mapController.getDarkMode());
			mainWindowController.setDarkModeCSS(mapController.getDarkMode(), (Stage)this.getOwner());
			mainWindowController.changeButtonsMode(mapController.getDarkMode());
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
