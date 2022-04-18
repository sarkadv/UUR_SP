package controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.MainWindowModel;
import model.MapModel;
import model.Tile;
import util.FileLoader;
import util.FileSaver;
import util.SpecialToolType;
import util.TilePicker;
import view.ImageButton;
import view.NewMapWindow;
import view.PictureExportWindow;

public class MainWindowController {
	
	private MainWindowModel mainWindowModel;
	
	public MainWindowController (MainWindowModel mainWindowModel) {
		this.mainWindowModel = mainWindowModel;
	}
	
	public Tile getCurrentTile() {
		return this.mainWindowModel.getCurrentTile().get();
	}
	
	public void setCurrentTile(Tile tile) {
		this.mainWindowModel.setCurrentTile(tile);
	}
	
	public boolean getDarkMode() {
		return this.mainWindowModel.getDarkMode().get();
	}
	
	public void setDarkMode(boolean darkMode) {
		this.mainWindowModel.setDarkMode(darkMode);
	}
	
	public void changeCurrentTile(ActionEvent e, Tile newTile) {
		this.mainWindowModel.setCurrentTile(newTile);
	}
	
	public void changeToEmptyTile(ActionEvent e) {
		if(getDarkMode()) {
			this.mainWindowModel.setCurrentTile(TilePicker.getTile(01));
		}
		else {
			this.mainWindowModel.setCurrentTile(TilePicker.getTile(00));
		}
		
	}
	
	public void changeToNoTile(ActionEvent e) {
		this.mainWindowModel.setCurrentTile(null);
	}
	
	public void changeSpecialToolActive(SpecialToolType tool) {
		mainWindowModel.setToolActive(tool);
	}
	
	public void removeSpecialToolActive() {
		mainWindowModel.setToolActive(null);
	}
	
	public void showStartAlert(Stage stage, MapModel mapModel, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Vyberte způsob zahájení stavby");
		
		if(mainWindowModel.getDarkMode().get()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType newMapBtn = new ButtonType("Nová Mapa");
		ButtonType loadMapBtn = new ButtonType("Načíst Mapu");

		alert.getButtonTypes().setAll(newMapBtn, loadMapBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == newMapBtn){
			showNewFileWindow(stage, mapModel, mapController);
		    
		    
		} else if (result.get() == loadMapBtn) {
		    showLoadFileWindowStart(stage, mapModel, mapController);
		}
	}
	
	public void showNewFileWindow(Stage stage, MapModel mapModel, MapController mapController) {
		NewMapWindow newMap = new NewMapWindow(mapModel, stage, mainWindowModel.getDarkMode().get());
	    newMap.showAndWait();
	    mapController.showNewMap(stage.getWidth(), stage.getHeight());
	}
	
	public void showLoadFileWindowStart(Stage stage, MapModel mapModel, MapController mapController) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Načtení Souboru");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile == null) {
			showStartAlert(stage, mapModel, mapController);
		}
		else {
			FileLoader.loadFile(selectedFile);
			
			if(!FileLoader.isSuccessful()) {
				showStartAlert(stage, mapModel, mapController);
			}
			else {
				FileLoader.setMapModel(mapModel);
				mapController.showNewMap(stage.getWidth(), stage.getHeight());
			}
		}
		
	}
	
	public void showLoadFileWindowContinue(Stage stage, MapModel mapModel, MapController mapController) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Načtení Souboru");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile != null) {
			FileLoader.loadFile(selectedFile);
			
			if(!FileLoader.isSuccessful()) {
				showStartAlert(stage, mapModel, mapController);
			}
			else {
				FileLoader.setMapModel(mapModel);
				mapController.showNewMap(stage.getWidth(), stage.getHeight());
				
				setDarkMode(mapController.getDarkMode());
				
				if(getDarkMode()) {
					stage.getScene().getStylesheets().add("file:resources/darkmode.css");
				}
				else {
					stage.getScene().getStylesheets().remove("file:resources/darkmode.css");
				}
			}
		}
		
	}
	
	public void showSaveAlertClose(Stage stage, MapModel mapModel) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Přejete si před zavřením aplikace uložit soubor?");
		
		if(mainWindowModel.getDarkMode().get()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType saveBtn = new ButtonType("Ano");
		ButtonType dontSaveBtn = new ButtonType("Ne");
		ButtonType cancelBtn = new ButtonType("Zpět", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == saveBtn){
			showFileSaveWindow(stage, mapModel); 
			stage.close();
		}
		else if (result.get() == dontSaveBtn) {
		    stage.close();
		}
	}
	
	public void showSaveAlertNew(Stage stage, MapModel mapModel, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Přejete si uložit aktuální soubor?");
		
		if(mainWindowModel.getDarkMode().get()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType saveBtn = new ButtonType("Ano");
		ButtonType dontSaveBtn = new ButtonType("Ne");
		ButtonType cancelBtn = new ButtonType("Zpět", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == saveBtn){
			showFileSaveWindow(stage, mapModel); 
			showNewFileWindow(stage, mapModel, mapController);
		}
		else if (result.get() == dontSaveBtn) {
			showNewFileWindow(stage, mapModel, mapController);
		}
	}
	
	
	
	public void showSaveAlertLoad(Stage stage, MapModel mapModel, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Přejete si uložit aktuální soubor?");
		
		if(mainWindowModel.getDarkMode().get()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType saveBtn = new ButtonType("Ano");
		ButtonType dontSaveBtn = new ButtonType("Ne");
		ButtonType cancelBtn = new ButtonType("Zpět", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == saveBtn){
			showFileSaveWindow(stage, mapModel); 
			showLoadFileWindowContinue(stage, mapModel, mapController);
		}
		else if (result.get() == dontSaveBtn) {
			showLoadFileWindowContinue(stage, mapModel, mapController);
		}
	}
	
	public void showFileSaveWindow(Stage stage, MapModel mapModel) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Uložení souboru");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File file = fileChooser.showSaveDialog(stage);
		
		if(file != null) {
			FileSaver.saveFile(file, mapModel);
		}
	}
	
	public void showExportWindow(Stage stage, Integer[][] tiles) {
		PictureExportWindow exportWindow = new PictureExportWindow(tiles, stage, this.mainWindowModel.getDarkMode().get());
		exportWindow.showAndWait();
	}
	
	public void changeModeAppearanceDuringRun(Stage stage, MapController mapController) {
		boolean darkModeActive = getDarkMode();
		
		if(darkModeActive) {
			stage.getScene().getStylesheets().remove("file:resources/darkmode.css");
		}
		else {
			stage.getScene().getStylesheets().add("file:resources/darkmode.css");

		}
		setDarkMode(!getDarkMode());
		mapController.setDarkMode(!mapController.getDarkMode());
		mapController.reInitAllTiles();
		mapController.repaint();
		
		int addition = getDarkMode()? 1 : -1;
		Tile currentTile = getCurrentTile();
		
		if(currentTile != null) {
			int currentTileId = currentTile.id;
			setCurrentTile(TilePicker.getTile(currentTileId + addition));
		}
		
		changeButtonsMode(getDarkMode());
		
	}
	
	public void changeModeAppearanceDuringStart(Stage stage, MapController mapController) {
		boolean darkModeActive = mainWindowModel.getDarkMode().get();
		
		if(darkModeActive) {
			stage.getScene().getStylesheets().add("file:resources/darkmode.css");
		}
		else {
			stage.getScene().getStylesheets().remove("file:resources/darkmode.css");

		}
		
		int addition = getDarkMode()? 1 : -1;
		Tile currentTile = getCurrentTile();
		
		if(currentTile != null) {
			int currentTileId = currentTile.id;
			setCurrentTile(TilePicker.getTile(currentTileId + addition));
		}
		
		changeButtonsMode(getDarkMode());
		
	}
	
	public void changeButtonsMode(boolean darkMode) {
		if(darkMode) {
			for(ImageButton btn : mainWindowModel.getButtons()) {
				Image btnImage = btn.getImage();
				int imgIndex = mainWindowModel.getButtonImagesLight().indexOf(btnImage);
				Image newImage = mainWindowModel.getButtonImagesDark().get(imgIndex);
				btn.setImage(newImage);
			}
		}
		else {
			for(ImageButton btn : mainWindowModel.getButtons()) {
				Image btnImage = btn.getImage();
				int imgIndex = mainWindowModel.getButtonImagesDark().indexOf(btnImage);
				Image newImage = mainWindowModel.getButtonImagesLight().get(imgIndex);
				btn.setImage(newImage);
			}
		}
	}

}
