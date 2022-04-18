package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.MainWindowController;
import controller.MapController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MainWindowModel;
import model.MapModel;
import util.ImageLoader;
import util.SpecialToolType;
import util.TilePicker;


public class MainWindowView extends Application {
	
	private Stage primaryStage;
	
	private MapModel mapModel = new MapModel();
	private MapView mapView;
	private MapController mapController;
	
	private MainWindowModel mainWindowModel = new MainWindowModel();
	private MainWindowController mainWindowController = new MainWindowController(mainWindowModel);
	
	private TileMenu tileMenu;
	

	public static void main(String[] args) {
		launch(args);
	}

	public void init() {
		TilePicker.init();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.primaryStage = stage;
		
		stage.setTitle("Sarka Dvorakova A21B0116P");
		stage.setScene(createScene());
		stage.setMinWidth(850);
		stage.setMinHeight(700);
		stage.show();
		
		mainWindowController.showStartAlert(stage, mapModel, mapController);
		mainWindowController.setDarkMode(mapController.getDarkMode());
		
		if(mainWindowController.getDarkMode()) {
			mainWindowController.changeModeAppearanceDuringStart(stage, mapController);
			tileMenu.setDarkMode(mainWindowController.getDarkMode());
			
		}
		
		stage.heightProperty().addListener((obs, oldValue, newValue) -> {
			mapController.changeTileSize(stage.getWidth(), newValue.doubleValue());
		});
		
		stage.widthProperty().addListener((obs, oldValue, newValue) -> {
			mapController.changeTileSize(newValue.doubleValue(), stage.getHeight());
		});
		
		stage.setOnCloseRequest(e -> {
			e.consume();
			mainWindowController.showSaveAlertClose(stage, mapModel);
		});
		
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 850, 700);
		
		scene.setOnKeyReleased(e -> {
			switch(e.getCode()) {
			case W:
				mapController.moveUp(e);
				break;
			case S:
				mapController.moveDown(e);
				break;
			case A:
				mapController.moveLeft(e);
				break;
			case D:
				mapController.moveRight(e);
				break;
			default:
				break;
			}
		});
		
		return scene;
	}
	
	private Parent createRootPane() {
		HBox rootPane = new HBox();
		rootPane.setAlignment(Pos.CENTER);
		rootPane.setPadding(new Insets(10));
		
		rootPane.getChildren().add(createLeftContent());
		rootPane.getChildren().add(createRightContent());
		
		return rootPane;
	}
	
	private Node createLeftContent() {
		BorderPane leftContent = new BorderPane();
		leftContent.setPadding(new Insets(10, 50, 10, 10));
		
		leftContent.setTop(createTopButtons());
		
		mapView = new MapView(mapModel);
		mapController = new MapController(mapView, mapModel);
		leftContent.setCenter(mapView);
		
		mapView.setOnMouseReleased(e -> {
			if(mainWindowModel.getToolActive().get() == null) {
				mapController.putTile(e, mainWindowModel.getCurrentTile().get());
			}
			else if (mainWindowModel.getToolActive().get() == SpecialToolType.EYEDROPPER) {
				mainWindowModel.setCurrentTile(mapController.copyTile(e));
				tileMenu.updateChoice();
				mainWindowController.changeSpecialToolActive(null);
			}
		});
		mapView.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.W) {
				mapController.moveUp(e);
				System.out.println("up");
			}
		});
		
		mapView.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.S) {
				mapController.moveDown(e);
			}
		});
		
		mapView.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.D) {
				mapController.moveRight(e);
			}
		});
		
		mapView.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.A) {
				mapController.moveLeft(e);
			}
		});
		
		return leftContent;
		
	}
	
	private Node createTopButtons() {
		HBox buttonsPane = new HBox(5);
		buttonsPane.setPadding(new Insets(10, 5, 10, 5));
		
		ImageButton newFile = new ImageButton(50, 50, "Nová Mapa", ImageLoader.NEW_LIGHT);
		newFile.setOnAction(e -> mainWindowController.showSaveAlertNew(primaryStage, mapModel, mapController));
		mainWindowModel.addButton(newFile);
		
		ImageButton saveFile = new ImageButton(50, 50, "Uložit Mapu", ImageLoader.SAVE_LIGHT);
		saveFile.setOnAction(e -> mainWindowController.showFileSaveWindow(primaryStage, mapModel));
		mainWindowModel.addButton(saveFile);
		
		ImageButton back = new ImageButton(50, 50, "Zpět", ImageLoader.BACK_LIGHT);
		mainWindowModel.addButton(back);
		
		ImageButton loadFile = new ImageButton(50, 50, "Načíst Mapu", ImageLoader.LOAD_LIGHT);
		loadFile.setOnAction(e -> {
			mainWindowController.showSaveAlertLoad(primaryStage, mapModel, mapController);
			tileMenu.setDarkMode(mainWindowController.getDarkMode());
		});
		mainWindowModel.addButton(loadFile);
		
		ImageButton exportFile = new ImageButton(50, 50, "Exportovat Jako Obrázek", ImageLoader.EXPORT_LIGHT);
		exportFile.setOnAction(e -> mainWindowController.showExportWindow(primaryStage, mapModel.getAllTiles()));
		mainWindowModel.addButton(exportFile);
		
		ImageButton mode = new ImageButton(50, 50, "Změna Barevného Režimu", ImageLoader.MODE_LIGHT);
		mode.setOnAction(e -> {
			mainWindowController.changeModeAppearanceDuringRun(primaryStage, mapController);
			tileMenu.setDarkMode(mainWindowController.getDarkMode());
		});
		mainWindowModel.addButton(mode);
		
		ImageButton settings = new ImageButton(50, 50, "Nastavení", ImageLoader.SETTINGS_LIGHT);
		mainWindowModel.addButton(settings);
		
		ImageButton help = new ImageButton(50, 50, "Nápověda", ImageLoader.HELP_LIGHT);
		mainWindowModel.addButton(help);
		
		ImageButton about = new ImageButton(50, 50, "O Aplikaci", ImageLoader.ABOUT_LIGHT);
		mainWindowModel.addButton(about);
		
		buttonsPane.getChildren().addAll(newFile, saveFile, back, loadFile, exportFile, mode, settings, help, about);
		
		return buttonsPane;
	}
	
	private Node createRightContent() {
		VBox rightContent = new VBox(5);
		rightContent.setAlignment(Pos.CENTER);
		
		Button upBtn = new ImageButton(30, 30, "Nahoru", ImageLoader.TRIANGLE_UP);
		upBtn.setOnAction(e -> tileMenu.moveUp());
		
		Button downBtn = new ImageButton(30, 30, "Dolů", ImageLoader.TRIANGLE_DOWN);
		downBtn.setOnAction(e -> tileMenu.moveDown());
		
		rightContent.getChildren().add(upBtn);
		rightContent.getChildren().add(createTileMenu());
		rightContent.getChildren().add(downBtn);
		rightContent.getChildren().add(createBottomButtons());
		
		return rightContent;
		
	}
	
	private Node createTileMenu() {
		tileMenu = new TileMenu(mainWindowController);
		
		return tileMenu;
	}

	private Node createBottomButtons() {
		VBox buttons = new VBox(5);
		buttons.setPadding(new Insets(10, 0, 0, 0));
		
		buttons.getChildren().addAll(createFirstRowButtons(), createSecondRowButtons());
		
		return buttons;
	}

	private Node createFirstRowButtons() {
		HBox buttonsPane = new HBox(5);
		
		ImageButton pick = new ImageButton(50, 50, "Vybrat", ImageLoader.PICK_LIGHT);
		pick.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(null);
			mainWindowController.changeToNoTile(e);
			tileMenu.updateChoice();
		});
		mainWindowModel.addButton(pick);
		
		ImageButton erase = new ImageButton(50, 50, "Vymazat", ImageLoader.ERASE_LIGHT);
		erase.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(null);
			mainWindowController.changeToEmptyTile(e);
			tileMenu.updateChoice();
		});
		mainWindowModel.addButton(erase);
		
		ImageButton eyeDropper = new ImageButton(50, 50, "Kapátko", ImageLoader.EYEDROPPER_LIGHT);
		eyeDropper.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(SpecialToolType.EYEDROPPER);
		});
		mainWindowModel.addButton(eyeDropper);
		
		buttonsPane.getChildren().addAll(pick, erase, eyeDropper);
		
		return buttonsPane;
	}
	
	private Node createSecondRowButtons() {
		HBox buttonsPane = new HBox(5);
		
		ImageButton chooseArea = new ImageButton(50, 50, "Vybrat Oblast", ImageLoader.CHOOSEAREA_LIGHT);
		mainWindowModel.addButton(chooseArea);
		
		ImageButton fillArea = new ImageButton(50, 50, "Výplň Oblasti", ImageLoader.FILLAREA_LIGHT);
		mainWindowModel.addButton(fillArea);
		
		ImageButton fillBorders = new ImageButton(50, 50, "Výplň Hranic", ImageLoader.FILLBORDERS_LIGHT);
		mainWindowModel.addButton(fillBorders);
		
		buttonsPane.getChildren().addAll(chooseArea, fillArea, fillBorders);
		
		return buttonsPane;
	}
	
}
