package view;

import model.Tile;
import controller.MainWindowController;
import controller.MapController;
import controller.ResizableCanvasController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MainWindowModel;
import model.MapModel;
import model.ResizableCanvasModel;
import util.ImageLoader;
import util.TilePicker;


public class MainWindowView extends Application {
	
	//private ResizableCanvasController resizableCanvasController = new ResizableCanvasController();
	private MapModel mapModel = new MapModel();
	private MapView mapView;
	private MapController mapController;
	
	private MainWindowModel mainWindowModel = new MainWindowModel();
	private MainWindowController mainWindowController = new MainWindowController(mainWindowModel);

	public static void main(String[] args) {
		launch(args);
	}

	public void init() {
		TilePicker.init();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Sarka Dvorakova A21B0116P");
		stage.setScene(createScene());
		stage.setMinWidth(820);
		
		stage.setMinHeight(700);
		stage.show();
		
		stage.heightProperty().addListener((obs, oldValue, newValue) -> {
			mapController.changeTileSize(stage.getWidth(), newValue.doubleValue());
		});
		
		stage.widthProperty().addListener((obs, oldValue, newValue) -> {
			mapController.changeTileSize(newValue.doubleValue(), stage.getHeight());
		});
		
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 850, 700);
		
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
		
		mapView.setOnMouseReleased(e -> mapController.putTile(e, mainWindowModel.getCurrentTile().get()));
		
		return leftContent;
		
	}
	
	private Node createTopButtons() {
		HBox buttons = new HBox(5);
		buttons.setPadding(new Insets(10, 5, 10, 5));
		
		Button newFile = new ImageButton(50, 50, "Nová Mapa", ImageLoader.NEW_LIGHT);
		Button saveFile = new ImageButton(50, 50, "Uložit Mapu", ImageLoader.SAVE_LIGHT);
		Button back = new ImageButton(50, 50, "Zpět", ImageLoader.BACK_LIGHT);
		Button loadFile = new ImageButton(50, 50, "Načíst Mapu", ImageLoader.LOAD_LIGHT);
		Button exportFile = new ImageButton(50, 50, "Exportovat Jako Obrázek", ImageLoader.EXPORT_LIGHT);
		Button mode = new ImageButton(50, 50, "Tmavý Režim", ImageLoader.MODE_LIGHT);
		Button settings = new ImageButton(50, 50, "Nastavení", ImageLoader.SETTINGS_LIGHT);
		Button help = new ImageButton(50, 50, "Nápověda", ImageLoader.HELP_LIGHT);
		Button about = new ImageButton(50, 50, "O Aplikaci", ImageLoader.ABOUT_LIGHT);
		
		buttons.getChildren().addAll(newFile, saveFile, back, loadFile, exportFile, mode, settings, help, about);
		
		return buttons;
	}
	
	private Node createRightContent() {
		VBox rightContent = new VBox(5);
		rightContent.setAlignment(Pos.CENTER);
		
		Button up = new ImageButton(30, 30, "Nahoru", ImageLoader.TRIANGLE_UP);
		Button down = new ImageButton(30, 30, "Dolů", ImageLoader.TRIANGLE_DOWN);
		
		rightContent.getChildren().add(up);
		rightContent.getChildren().add(createTilePicker());
		rightContent.getChildren().add(down);
		rightContent.getChildren().add(createBottomButtons());
		
		return rightContent;
		
	}
	
	private Node createTilePicker() {
		GridPane tilePickerPane = new GridPane();
		
		Button tileBtn1 = new ImageButton(150, 150, "Dlaždice 1", ImageLoader.HADES_FACTORY_D);
		Tile tile1 = TilePicker.HADES_FACTORY_D;
		Button tileBtn2 = new ImageButton(150, 150, "Dlaždice 2", ImageLoader.HADES_SHOP_D);
		Tile tile2 = TilePicker.HADES_SHOP_D;
		Button tileBtn3 = new ImageButton(150, 150, "Dlaždice 3", ImageLoader.HADES_PLANT_D);
		Tile tile3 = TilePicker.HADES_PLANT_D;
		
		tilePickerPane.add(tileBtn1, 0, 0);
		tilePickerPane.add(tileBtn2, 0, 1);
		tilePickerPane.add(tileBtn3, 0, 2);
		
		tileBtn1.setOnAction(e -> mainWindowController.changeCurrentTile(e, tile1));
		tileBtn2.setOnAction(e -> mainWindowController.changeCurrentTile(e, tile2));
		tileBtn3.setOnAction(e -> mainWindowController.changeCurrentTile(e, tile3));
		
		return tilePickerPane;
	}

	private Node createBottomButtons() {
		VBox buttons = new VBox(5);
		buttons.setPadding(new Insets(10, 0, 0, 0));
		
		buttons.getChildren().addAll(createFirstRowButtons(), createSecondRowButtons());
		
		return buttons;
	}

	private Node createFirstRowButtons() {
		HBox buttons = new HBox(5);
		
		Button pick = new ImageButton(50, 50, "Vybrat", ImageLoader.PICK_LIGHT);
		Button erase = new ImageButton(50, 50, "Vymazat", ImageLoader.ERASE_LIGHT);
		Button eyeDropper = new ImageButton(50, 50, "Kapátko", ImageLoader.EYEDROPPER_LIGHT);
		
		buttons.getChildren().addAll(pick, erase, eyeDropper);
		return buttons;
	}
	
	private Node createSecondRowButtons() {
		HBox buttons = new HBox(5);
		
		Button chooseArea = new ImageButton(50, 50, "Vybrat Oblast", ImageLoader.CHOOSEAREA_LIGHT);
		Button fillArea = new ImageButton(50, 50, "Výplň Oblasti", ImageLoader.FILLAREA_LIGHT);
		Button fillBorders = new ImageButton(50, 50, "Výplň Hranic", ImageLoader.FILLBORDERS_LIGHT);
		
		buttons.getChildren().addAll(chooseArea, fillArea, fillBorders);
		return buttons;
	}

}
