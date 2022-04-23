package controller;

import java.io.File;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.MainWindowModel;
import model.Tile;
import util.FileLoader;
import util.FileSaver;
import util.SpecialToolType;
import util.TilePicker;
import view.ImageButton;
import view.MapSettingsWindow;
import view.PictureExportWindow;

public class MainWindowController {
	
	private MainWindowModel mainWindowModel;
	
	public MainWindowController () {
		this.mainWindowModel = new MainWindowModel();
	}
	
	public void addButton(ImageButton button) {
		this.mainWindowModel.addButton(button);
	}
	
	public Tile getCurrentTile() {
		return this.mainWindowModel.getCurrentTile();
	}
	
	public void setCurrentTile(Tile tile) {
		this.mainWindowModel.setCurrentTile(tile);
	}
	
	public boolean getDarkMode() {
		return this.mainWindowModel.getDarkMode();
	}
	
	public void setDarkMode(boolean darkMode) {
		if(mainWindowModel.isWindowModeChanges()) {
			this.mainWindowModel.setDarkMode(darkMode);
		}
	}
	
	public void setDarkModeCSS(boolean darkMode, Stage stage) {
		if(darkMode) {
			stage.getScene().getStylesheets().add("file:resources/darkmode.css");
		}
		else {
			stage.getScene().getStylesheets().clear();
		}
	}
	
	public SpecialToolType getToolActive() {
		return this.mainWindowModel.getToolActive();
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
	
	public boolean isWindowModeChanges() {
		return mainWindowModel.isWindowModeChanges();
	}
	
	public void setWindowModeChanges(boolean windowModeChanges) {
		mainWindowModel.setWindowModeChanges(windowModeChanges);
	}
	
	public void showStartAlert(Stage stage, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Vyberte způsob zahájení stavby");
		
		if(getDarkMode()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType newMapBtn = new ButtonType("Nová Mapa");
		ButtonType loadMapBtn = new ButtonType("Načíst Mapu");

		alert.getButtonTypes().setAll(newMapBtn, loadMapBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == newMapBtn){
			showNewFileWindow(stage, mapController);
		    
		    
		} else if (result.get() == loadMapBtn) {
		    showLoadFileWindowStart(stage, mapController);
		}
	}
	
	public void showNewFileWindow(Stage stage, MapController mapController) {
		MapSettingsWindow newMap = new MapSettingsWindow(mapController, this, stage, getDarkMode(), false);
	    newMap.showAndWait();
	    mapController.showNewMap(stage.getWidth(), stage.getHeight());
	    mapController.clearMapHistory();
	}
	
	public void showLoadFileWindowStart(Stage stage, MapController mapController) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Načtení Souboru");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile == null) {
			showStartAlert(stage, mapController);
		}
		else {
			FileLoader.loadFile(selectedFile);
			
			if(!FileLoader.isSuccessful()) {
				showStartAlert(stage, mapController);
			}
			else {
				FileLoader.setMapModel(mapController, this, stage);
				mapController.showNewMap(stage.getWidth(), stage.getHeight());
			}
		}
		
	}
	
	public void showLoadFileWindowContinue(Stage stage, MapController mapController) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Načtení Souboru");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		if(selectedFile != null) {
			FileLoader.loadFile(selectedFile);
			
			if(FileLoader.isSuccessful()) {
				FileLoader.setMapModel(mapController, this, stage);
				mapController.showNewMap(stage.getWidth(), stage.getHeight());
				mapController.clearMapHistory();
				
				if(isWindowModeChanges()) {
					setDarkMode(mapController.getDarkMode());
					setDarkModeCSS(getDarkMode(), stage);
				}
				
			}
		}
		
	}
	
	public void showSaveAlertClose(Stage stage, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Přejete si před zavřením aplikace uložit soubor?");
		
		if(mainWindowModel.getDarkMode()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType saveBtn = new ButtonType("Ano");
		ButtonType dontSaveBtn = new ButtonType("Ne");
		ButtonType cancelBtn = new ButtonType("Zpět", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == saveBtn){
			showFileSaveWindow(stage, mapController); 
			stage.close();
		}
		else if (result.get() == dontSaveBtn) {
		    stage.close();
		}
	}
	
	public void showSaveAlertNew(Stage stage, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Přejete si uložit aktuální soubor?");
		
		if(mainWindowModel.getDarkMode()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType saveBtn = new ButtonType("Ano");
		ButtonType dontSaveBtn = new ButtonType("Ne");
		ButtonType cancelBtn = new ButtonType("Zpět", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == saveBtn){
			showFileSaveWindow(stage, mapController); 
			showNewFileWindow(stage, mapController);
		}
		else if (result.get() == dontSaveBtn) {
			showNewFileWindow(stage, mapController);
		}
	}
	
	
	
	public void showSaveAlertLoad(Stage stage, MapController mapController) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tiny Planet Builder");
		alert.setHeaderText("Přejete si uložit aktuální soubor?");
		
		if(mainWindowModel.getDarkMode()) {
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add("file:resources/darkmode.css");
		}

		ButtonType saveBtn = new ButtonType("Ano");
		ButtonType dontSaveBtn = new ButtonType("Ne");
		ButtonType cancelBtn = new ButtonType("Zpět", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(saveBtn, dontSaveBtn, cancelBtn);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == saveBtn){
			showFileSaveWindow(stage, mapController); 
			showLoadFileWindowContinue(stage, mapController);
		}
		else if (result.get() == dontSaveBtn) {
			showLoadFileWindowContinue(stage, mapController);
		}
	}
	
	public void showFileSaveWindow(Stage stage, MapController mapController) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Uložení souboru");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File file = fileChooser.showSaveDialog(stage);
		
		if(file != null) {
			FileSaver.saveFile(file, mapController.getModel());
		}
	}
	
	public void showExportWindow(Stage stage, MapController mapController) {
		PictureExportWindow exportWindow = new PictureExportWindow(stage, mapController);
		exportWindow.showAndWait();
	}
	
	public void changeModeAppearanceDuringRun(Stage stage, MapController mapController) {
		if(mainWindowModel.isWindowModeChanges()) {
			setDarkMode(!getDarkMode());
			setDarkModeCSS(getDarkMode(), stage);
			changeButtonsMode(getDarkMode());
		}

		mapController.setDarkMode(!mapController.getDarkMode());
		mapController.reInitAllTiles();
		mapController.repaint();
		
		int addition = getDarkMode()? 1 : -1;
		Tile currentTile = getCurrentTile();
		
		if(currentTile != null) {
			int currentTileId = currentTile.id;
			setCurrentTile(TilePicker.getTile(currentTileId + addition));
		}
		
	}
	
	public void changeModeAppearanceDuringStart(Stage stage, MapController mapController) {
		boolean darkModeActive = mainWindowModel.getDarkMode();
		
		setDarkModeCSS(darkModeActive, stage);
		
		int addition = getDarkMode()? 1 : -1;
		Tile currentTile = getCurrentTile();
		
		if(currentTile != null) {
			int currentTileId = currentTile.id;
			setCurrentTile(TilePicker.getTile(currentTileId + addition));
		}
		
		changeButtonsMode(getDarkMode());
		
	}
	
	public void changeButtonsMode(boolean darkMode) {
		if(mainWindowModel.isWindowModeChanges()) {
			if(darkMode) {
				for(ImageButton btn : mainWindowModel.getButtons()) {
					Image btnImage = btn.getImage();
					int imgIndex = mainWindowModel.getButtonImagesLight().indexOf(btnImage);
					
					if(imgIndex != -1) {
						Image newImage = mainWindowModel.getButtonImagesDark().get(imgIndex);
						btn.setImage(newImage);
					}
					
				}
			}
			else {
				for(ImageButton btn : mainWindowModel.getButtons()) {
					Image btnImage = btn.getImage();
					int imgIndex = mainWindowModel.getButtonImagesDark().indexOf(btnImage);
					
					if(imgIndex != -1) {
						Image newImage = mainWindowModel.getButtonImagesLight().get(imgIndex);
						btn.setImage(newImage);
					}
					
				}
			}
		}

	}
	
	public void showSettingsWindow(Stage stage, MapController mapController) {
		MapSettingsWindow newMap = new MapSettingsWindow(mapController, this, stage, getDarkMode(), true);
	    newMap.showAndWait();
	}

}
