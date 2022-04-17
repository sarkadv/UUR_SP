package controller;

import java.io.File;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
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
import view.NewMapWindow;
import view.PictureExportWindow;

public class MainWindowController {
	
	private MainWindowModel model;
	
	public MainWindowController (MainWindowModel model) {
		this.model = model;
	}
	
	public Tile getCurrentTile() {
		return this.model.getCurrentTile().get();
	}
	
	public void changeCurrentTile(ActionEvent e, Tile newTile) {
		this.model.setCurrentTile(newTile);
	}
	
	public void changeToEmptyTile(ActionEvent e) {
		if(model.getDarkMode().get()) {
			this.model.setCurrentTile(TilePicker.getTile(01));
		}
		else {
			this.model.setCurrentTile(TilePicker.getTile(00));
		}
		
	}
	
	public void changeToNoTile(ActionEvent e) {
		this.model.setCurrentTile(null);
	}
	
	public void changeSpecialToolActive(SpecialToolType tool) {
		model.setToolActive(tool);
	}
	
	public void removeSpecialToolActive() {
		model.setToolActive(null);
	}
	
	public void showStartAlert(Stage stage, MapModel mapModel, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Vyberte způsob zahájení stavby");

		ButtonType newMapBtn = new ButtonType("Nová Mapa");
		ButtonType loadMapBtn = new ButtonType("Načíst Mapu");

		alert.getButtonTypes().setAll(newMapBtn, loadMapBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == newMapBtn){
			showNewFileWindow(stage, mapModel, mapController);
		    
		    
		} else if (result.get() == loadMapBtn) {
		    showLoadFileWindow(stage, mapModel, mapController);
		}
	}
	
	public void showNewFileWindow(Stage stage, MapModel mapModel, MapController mapController) {
		NewMapWindow newMap = new NewMapWindow(mapModel, stage);
	    newMap.showAndWait();
	    mapController.showNewMap(stage.getWidth(), stage.getHeight());
	}
	
	public void showLoadFileWindow(Stage stage, MapModel mapModel, MapController mapController) {
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
	
	public void showSaveAlertClose(Stage stage, MapModel mapModel) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Přejete si před zavřením aplikace uložit soubor?");

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

		ButtonType saveBtn = new ButtonType("Ano");
		ButtonType dontSaveBtn = new ButtonType("Ne");
		ButtonType cancelBtn = new ButtonType("Zpět", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == saveBtn){
			showFileSaveWindow(stage, mapModel); 
			showLoadFileWindow(stage, mapModel, mapController);
		}
		else if (result.get() == dontSaveBtn) {
			showLoadFileWindow(stage, mapModel, mapController);
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
	
	public void showExportWindow(Stage stage, int[][] tiles) {
		PictureExportWindow exportWindow = new PictureExportWindow(tiles, stage);
		exportWindow.showAndWait();
	}
	
	public void setDarkMode(boolean darkMode) {
		this.model.setDarkMode(darkMode);
	}
	
	

}
