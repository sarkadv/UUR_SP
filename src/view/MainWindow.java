package view;


import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Resources;

public class MainWindow extends Application {
	
	private Canvas resizableCanvas = new ResizableCanvas(650, 650);
	private DoubleProperty windowHeight = new SimpleDoubleProperty(0);
	private DoubleProperty windowWidth = new SimpleDoubleProperty(0);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Sarka Dvorakova A21B0116P");
		stage.setScene(createScene());
		stage.setMinWidth(700);
		
		stage.setMinHeight(700);
		stage.show();
		
		windowHeight.bind(stage.heightProperty());
		windowWidth.bind(stage.widthProperty());
		
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			int newValInt = newVal.intValue();
			resizableCanvas.resize(newValInt - 250, newValInt - 250);
		});
		
		stage.heightProperty().addListener((obs, oldVal, newVal) -> {
			int newValInt = newVal.intValue();
			resizableCanvas.resize(newValInt - 250, newValInt - 250);
		});
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), 850, 700);
		
		return scene;
	}
	
	private Parent createRootPane() {
		HBox rootPane = new HBox();
		rootPane.setAlignment(Pos.CENTER);
		
		rootPane.getChildren().add(createLeftContent());
		rootPane.getChildren().add(createRightContent());
		
		return rootPane;
	}
	
	private Node createLeftContent() {
		GridPane leftContent = new GridPane();
		leftContent.setAlignment(Pos.CENTER);
		leftContent.setPadding(new Insets(10));
		
		leftContent.add(createTopButtons(), 0, 0);
		leftContent.add(resizableCanvas, 0, 1);
		
		return leftContent;
		
	}
	
	private Node createTopButtons() {
		HBox buttons = new HBox(5);
		buttons.setPadding(new Insets(10, 5, 10, 5));
		
		Button newFile = new ImageButton(50, 50, "Nová Mapa", Resources.NEW_LIGHT);
		Button saveFile = new ImageButton(50, 50, "Uložit Mapu", Resources.SAVE_LIGHT);
		Button back = new ImageButton(50, 50, "Zpět", Resources.BACK_LIGHT);
		Button loadFile = new ImageButton(50, 50, "Načíst Mapu", Resources.LOAD_LIGHT);
		Button exportFile = new ImageButton(50, 50, "Exportovat Jako Obrázek", Resources.EXPORT_LIGHT);
		Button mode = new ImageButton(50, 50, "Tmavý Režim", Resources.MODE_LIGHT);
		Button settings = new ImageButton(50, 50, "Nastavení", Resources.SETTINGS_LIGHT);
		Button help = new ImageButton(50, 50, "Nápověda", Resources.HELP_LIGHT);
		Button about = new ImageButton(50, 50, "O Aplikaci", Resources.ABOUT_LIGHT);
		
		buttons.getChildren().addAll(newFile, saveFile, back, loadFile, exportFile, mode, settings, help, about);
		
		return buttons;
	}
	
	private Node createRightContent() {
		VBox rightContent = new VBox(5);
		rightContent.setAlignment(Pos.CENTER);
		
		Button up = new ImageButton(30, 30, "Nahoru", Resources.TRIANGLE_UP);
		Button down = new ImageButton(30, 30, "Dolů", Resources.TRIANGLE_DOWN);
		
		rightContent.getChildren().add(up);
		rightContent.getChildren().add(createTilePicker());
		rightContent.getChildren().add(down);
		rightContent.getChildren().add(createBottomButtons());
		
		return rightContent;
		
	}
	
	private Node createTilePicker() {
		GridPane tilePickerPane = new GridPane();
		
		Button tile1 = new ImageButton(150, 150, "Dlaždice 1", Resources.HADES_FACTORY_D);
		Button tile2 = new ImageButton(150, 150, "Dlaždice 2", Resources.HADES_PATH_D);
		Button tile3 = new ImageButton(150, 150, "Dlaždice 3", Resources.HADES_PLANT_D);
		
		tilePickerPane.add(tile1, 0, 0);
		tilePickerPane.add(tile2, 0, 1);
		tilePickerPane.add(tile3, 0, 2);
		
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
		
		Button pick = new ImageButton(50, 50, "Vybrat", Resources.PICK_LIGHT);
		Button erase = new ImageButton(50, 50, "Vymazat", Resources.ERASE_LIGHT);
		Button eyeDropper = new ImageButton(50, 50, "Kapátko", Resources.EYEDROPPER_LIGHT);
		
		buttons.getChildren().addAll(pick, erase, eyeDropper);
		return buttons;
	}
	
	private Node createSecondRowButtons() {
		HBox buttons = new HBox(5);
		
		Button chooseArea = new ImageButton(50, 50, "Vybrat Oblast", Resources.CHOOSEAREA_LIGHT);
		Button fillArea = new ImageButton(50, 50, "Výplň Oblasti", Resources.FILLAREA_LIGHT);
		Button fillBorders = new ImageButton(50, 50, "Výplň Hranic", Resources.FILLBORDERS_LIGHT);
		
		buttons.getChildren().addAll(chooseArea, fillArea, fillBorders);
		return buttons;
	}

}
