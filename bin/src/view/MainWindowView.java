package view;

import java.io.IOException;

import controller.MainWindowController;
import controller.MapController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.DragCoordinates;
import util.ImageLoader;
import util.SpecialToolType;
import util.TilePicker;

public class MainWindowView extends Application {
	
	private Stage primaryStage;
	private Scene primaryScene;
	
	private ImageButton newFile;
	private ImageButton saveFile;
	private ImageButton back;
	private ImageButton loadFile;
	private ImageButton exportFile;
	private ImageButton mode;
	private ImageButton options;
	private ImageButton help;
	private ImageButton about;
	private ImageButton pick;
	private ImageButton erase;
	private ImageButton eyeDropper;
	private ImageButton chooseArea;
	private ImageButton fillArea;
	private ImageButton fillBorders;
	private ImageButton upBtn;
	private ImageButton downBtn;
	
	private MapController mapController = new MapController();
	private MainWindowController mainWindowController = new MainWindowController();
	
	private TileMenu tileMenu;
	

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.primaryStage = stage;
		
		try {
			ImageLoader.init();
			TilePicker.init();
			
			this.primaryScene = createScene();
			
			stage.setTitle("Tiny Planet Builder - Sarka Dvorakova A21B0116P");
			stage.setScene(primaryScene);
			stage.setMinWidth(850);
			stage.setMinHeight(700);
			stage.show();
			
			mainWindowController.showStartAlert(stage, mapController);
			
			tileMenu.changePlanet(mapController.getActivePlanet());
			
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
				mainWindowController.showSaveAlertClose(stage, mapController);
			});
			
			KeyCombination kcNew = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
			Runnable rnNew = () -> newFile.fire();
			primaryScene.getAccelerators().put(kcNew, rnNew);
			
			KeyCombination kcSave = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
			Runnable rnSave = () -> saveFile.fire();
			primaryScene.getAccelerators().put(kcSave, rnSave);
			
			KeyCombination kcBack1 = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
			Runnable rnBack = () -> back.fire();
			primaryScene.getAccelerators().put(kcBack1, rnBack);
			KeyCombination kcBack2 = new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN);
			primaryScene.getAccelerators().put(kcBack2, rnBack);
			
			KeyCombination kcLoad = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN);
			Runnable rnLoad = () -> loadFile.fire();
			primaryScene.getAccelerators().put(kcLoad, rnLoad);
			
			KeyCombination kcExport = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
			Runnable rnExport = () -> exportFile.fire();
			primaryScene.getAccelerators().put(kcExport, rnExport);
			
			KeyCombination kcMode = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
			Runnable rnMode = () -> mode.fire();
			primaryScene.getAccelerators().put(kcMode, rnMode);
			
			KeyCombination kcOptions = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN);
			Runnable rnOptions = () -> options.fire();
			primaryScene.getAccelerators().put(kcOptions, rnOptions);
			
			KeyCombination kcHelp = new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN);
			Runnable rnHelp = () -> help.fire();
			primaryScene.getAccelerators().put(kcHelp, rnHelp);
			
			KeyCombination kcAbout = new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN);
			Runnable rnAbout = () -> about.fire();
			primaryScene.getAccelerators().put(kcAbout, rnAbout);
			
			KeyCombination kcPick = new KeyCodeCombination(KeyCode.ESCAPE);
			Runnable rnPick = () -> pick.fire();
			primaryScene.getAccelerators().put(kcPick, rnPick);
			
			KeyCombination kcErase = new KeyCodeCombination(KeyCode.BACK_SPACE);
			Runnable rnErase = () -> erase.fire();
			primaryScene.getAccelerators().put(kcErase, rnErase);
			
			KeyCombination kcEyeDropper = new KeyCodeCombination(KeyCode.E, KeyCombination.SHIFT_DOWN);
			Runnable rnEyeDropper = () -> eyeDropper.fire();
			primaryScene.getAccelerators().put(kcEyeDropper, rnEyeDropper);
			
			KeyCombination kcChoose = new KeyCodeCombination(KeyCode.C, KeyCombination.SHIFT_DOWN);
			Runnable rnChoose = () -> chooseArea.fire();
			primaryScene.getAccelerators().put(kcChoose, rnChoose);
			
			KeyCombination kcFillArea = new KeyCodeCombination(KeyCode.F, KeyCombination.SHIFT_DOWN);
			Runnable rnFillArea = () -> fillArea.fire();
			primaryScene.getAccelerators().put(kcFillArea, rnFillArea);
			
			KeyCombination kcFillBorders = new KeyCodeCombination(KeyCode.B, KeyCombination.SHIFT_DOWN);
			Runnable rnFillBorders = () -> fillBorders.fire();
			primaryScene.getAccelerators().put(kcFillBorders, rnFillBorders);
		}
		catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Načítání Zdrojů");
			alert.setHeaderText("Chyba při načítání obrázkových zdrojů");
			alert.setContentText("Aplikaci se nepodařilo najít a načíst obrázkové zdroje. Bez nich nemůže pracovat, a proto bude nyní ukočena.");
			alert.showAndWait();
			Platform.exit();
		}
		
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
			case UP:
				upBtn.fire();
				break;
			case DOWN:
				downBtn.fire();
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
		leftContent.setCenter(mapController.getView());
		
		mapController.getView().setOnMouseReleased(e -> {
			if(mainWindowController.getToolActive() == null) {
				mapController.putTile(e, mainWindowController.getCurrentTile());
			}
			else if (mainWindowController.getToolActive() == SpecialToolType.EYEDROPPER) {
				mainWindowController.setCurrentTile(mapController.copyTile(e));
				tileMenu.updateChoice();
				mainWindowController.changeSpecialToolActive(null);
			}
		});
		
		mapController.getView().setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
				mapController.moveUp(e);
			}
		});
		
		mapController.getView().setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
				mapController.moveDown(e);
			}
		});
		
		mapController.getView().setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
				mapController.moveRight(e);
			}
		});
		
		mapController.getView().setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
				mapController.moveLeft(e);
			}
		});
		
		mapController.getView().setOnZoom(e -> {
			if (e.getTotalZoomFactor() > 1) {
				mapController.resizeMap(primaryStage.getWidth(), primaryStage.getHeight(), 
						mapController.getAllTilesWidth(), mapController.getAllTilesHeight(), mapController.getTilesVisibleLine() - 1);
			}
			else {
				mapController.resizeMap(primaryStage.getWidth(), primaryStage.getHeight(), 
						mapController.getAllTilesWidth(), mapController.getAllTilesHeight(), mapController.getTilesVisibleLine() + 1);
			}
		});
		
		mapController.getView().setOnScroll(e -> {
			if (e.getDeltaY() > 1) {
				mapController.resizeMap(primaryStage.getWidth(), primaryStage.getHeight(), 
						mapController.getAllTilesWidth(), mapController.getAllTilesHeight(), mapController.getTilesVisibleLine() - 1);
			}
			else {
				mapController.resizeMap(primaryStage.getWidth(), primaryStage.getHeight(), 
						mapController.getAllTilesWidth(), mapController.getAllTilesHeight(), mapController.getTilesVisibleLine() + 1);
			}
		});
		
		mapController.getView().setOnMouseDragged(e -> {
			if(mainWindowController.getToolActive() == SpecialToolType.CHOOSEAREA) {
				if(mapController.getDragCoordinates() != null) {
					double startX = mapController.getDragCoordinates().getStartX();
					double startY = mapController.getDragCoordinates().getStartY();
					mapController.setDragCoordinates(new DragCoordinates(startX, startY, e.getX(), e.getY()));
					mapController.changeChosenTiles();
					mapController.changeChosenBorders();
					mapController.repaint();
				}
				else {
					mapController.setDragCoordinates(new DragCoordinates(e.getX(), e.getY(), e.getX(), e.getY()));
					mapController.changeChosenTiles();
					mapController.changeChosenBorders();
					mapController.repaint();
				}
			}
		});
		
		mapController.getView().setOnDragDetected(e -> {
			if(mainWindowController.getToolActive() == SpecialToolType.CHOOSEAREA) {
				mapController.setDragCoordinates(null);
				mapController.changeChosenTiles();
				mapController.changeChosenBorders();
				mapController.repaint();
			}
		});
		
		return leftContent;
		
	}
	
	private Node createTopButtons() {
		HBox buttonsPane = new HBox(5);
		buttonsPane.setPadding(new Insets(10, 5, 10, 5));
		
		newFile = new ImageButton(50, 50, "Nová Mapa", ImageLoader.NEW_LIGHT);
		newFile.setOnAction(e -> {
			mainWindowController.showSaveAlertNew(primaryStage, mapController);
			tileMenu.changePlanet(mapController.getActivePlanet());
		});
		mainWindowController.addButton(newFile);
		newFile.setMnemonicParsing(true);
		
		saveFile = new ImageButton(50, 50, "Uložit Mapu", ImageLoader.SAVE_LIGHT);
		saveFile.setOnAction(e -> mainWindowController.showFileSaveWindow(primaryStage, mapController));
		mainWindowController.addButton(saveFile);
		
		back = new ImageButton(50, 50, "Zpět", ImageLoader.BACK_LIGHT);
		back.setOnAction(e -> mapController.oneStepBack());
		mainWindowController.addButton(back);
		
		loadFile = new ImageButton(50, 50, "Načíst Mapu", ImageLoader.LOAD_LIGHT);
		loadFile.setOnAction(e -> {
			mainWindowController.showSaveAlertLoad(primaryStage, mapController);
			tileMenu.setDarkMode(mapController.getDarkMode());
			tileMenu.changePlanet(mapController.getActivePlanet());
		});
		mainWindowController.addButton(loadFile);
		
		exportFile = new ImageButton(50, 50, "Exportovat Jako Obrázek", ImageLoader.EXPORT_LIGHT);
		exportFile.setOnAction(e -> mainWindowController.showExportWindow(primaryStage, mapController));
		mainWindowController.addButton(exportFile);
		
		mode = new ImageButton(50, 50, "Změna Barevného Režimu", ImageLoader.MODE_LIGHT);
		mode.setOnAction(e -> {
			mainWindowController.changeModeAppearanceDuringRun(primaryStage, mapController);
			tileMenu.setDarkMode(mapController.getDarkMode());
		});
		mainWindowController.addButton(mode);
		
		options = new ImageButton(50, 50, "Nastavení", ImageLoader.SETTINGS_LIGHT);
		options.setOnAction(e -> {
			mainWindowController.showSettingsWindow(primaryStage, mapController);
			tileMenu.changePlanet(mapController.getActivePlanet());
		});
		mainWindowController.addButton(options);
		
		help = new ImageButton(50, 50, "Nápověda", ImageLoader.HELP_LIGHT);
		help.setOnAction(e -> new HelpWindow(primaryStage, mainWindowController.getDarkMode()));
		mainWindowController.addButton(help);
		
		about = new ImageButton(50, 50, "O Aplikaci", ImageLoader.ABOUT_LIGHT);
		about.setOnAction(e -> new AboutWindow(primaryStage, mainWindowController.getDarkMode()));
		mainWindowController.addButton(about);
		
		buttonsPane.getChildren().addAll(newFile, saveFile, back, loadFile, exportFile, mode, options, help, about);
		
		return buttonsPane;
	}
	
	private Node createRightContent() {
		VBox rightContent = new VBox(5);
		rightContent.setAlignment(Pos.CENTER);
		
		upBtn = new ImageButton(30, 30, "Nahoru", ImageLoader.TRIANGLE_UP);
		upBtn.setOnAction(e -> tileMenu.moveUp());
		
		downBtn = new ImageButton(30, 30, "Dolů", ImageLoader.TRIANGLE_DOWN);
		downBtn.setOnAction(e -> tileMenu.moveDown());
		
		tileMenu = new TileMenu(mainWindowController, upBtn, downBtn, mapController.getActivePlanet());
		
		rightContent.getChildren().add(upBtn);
		rightContent.getChildren().add(tileMenu);
		rightContent.getChildren().add(downBtn);
		rightContent.getChildren().add(createBottomButtons());
		
		return rightContent;
		
	}

	private Node createBottomButtons() {
		VBox buttons = new VBox(5);
		buttons.setPadding(new Insets(10, 0, 0, 0));
		
		buttons.getChildren().addAll(createFirstRowButtons(), createSecondRowButtons());
		
		return buttons;
	}

	private Node createFirstRowButtons() {
		HBox buttonsPane = new HBox(5);
		
		pick = new ImageButton(50, 50, "Vybrat", ImageLoader.PICK_LIGHT);
		pick.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(null);
			mainWindowController.changeToNoTile(e);
			tileMenu.updateChoice();
		});
		mainWindowController.addButton(pick);
		
		erase = new ImageButton(50, 50, "Vymazat", ImageLoader.ERASE_LIGHT);
		erase.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(null);
			mainWindowController.changeToEmptyTile(mapController.getActivePlanet());
			mapController.fillArea(mainWindowController.getCurrentTile());
			mapController.setDragCoordinates(null);
			mapController.changeChosenTiles();
			mapController.changeChosenBorders();
			mapController.repaint();
			tileMenu.updateChoice();
		});
		mainWindowController.addButton(erase);
		
		eyeDropper = new ImageButton(50, 50, "Kapátko", ImageLoader.EYEDROPPER_LIGHT);
		eyeDropper.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(SpecialToolType.EYEDROPPER);
		});
		mainWindowController.addButton(eyeDropper);
		
		buttonsPane.getChildren().addAll(pick, erase, eyeDropper);
		
		return buttonsPane;
	}
	
	private Node createSecondRowButtons() {
		HBox buttonsPane = new HBox(5);
		
		chooseArea = new ImageButton(50, 50, "Vybrat Oblast", ImageLoader.CHOOSEAREA_LIGHT);
		chooseArea.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(SpecialToolType.CHOOSEAREA);
		});
		mainWindowController.addButton(chooseArea);
		
		fillArea = new ImageButton(50, 50, "Výplň Oblasti", ImageLoader.FILLAREA_LIGHT);
		fillArea.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(null);
			mapController.fillArea(mainWindowController.getCurrentTile());
			mapController.setDragCoordinates(null);
			mapController.changeChosenTiles();
			mapController.changeChosenBorders();
			mapController.repaint();
		});
		mainWindowController.addButton(fillArea);
		
		fillBorders = new ImageButton(50, 50, "Výplň Hranic", ImageLoader.FILLBORDERS_LIGHT);
		fillBorders.setOnAction(e -> {
			mainWindowController.changeSpecialToolActive(null);
			mapController.fillBorders(mainWindowController.getCurrentTile());
			mapController.setDragCoordinates(null);
			mapController.changeChosenTiles();
			mapController.changeChosenBorders();
			mapController.repaint();
		});
		mainWindowController.addButton(fillBorders);
		
		buttonsPane.getChildren().addAll(chooseArea, fillArea, fillBorders);
		
		return buttonsPane;
	}
	
}
