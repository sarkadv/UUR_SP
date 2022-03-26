package view;


import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	private ResizableCanvas resizableCanvas = new ResizableCanvas(500, 500);
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
		Scene scene = new Scene(createRootPane(), 700, 700);
		
		return scene;
	}
	
	private Parent createRootPane() {
		HBox rootPane = new HBox();
		
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
		buttons.setPadding(new Insets(10));
		
		Button newFile = new Button("New File");
		newFile.setMinSize(50, 50);
		newFile.setMaxSize(50, 50);
		
		Button saveFile = new Button("Save File");
		saveFile.setMinSize(50, 50);
		saveFile.setMaxSize(50, 50);
		
		Button back = new Button("Back");
		back.setMinSize(50, 50);
		back.setMaxSize(50, 50);
		
		Button loadFile = new Button("LoadFile");
		loadFile.setMinSize(50, 50);
		loadFile.setMaxSize(50, 50);
		
		Button exportFile = new Button("Export File");
		exportFile.setMinSize(50, 50);
		exportFile.setMaxSize(50, 50);
		
		Button mode = new Button("Light Mode / Dark Mode");
		mode.setMinSize(50, 50);
		mode.setMaxSize(50, 50);
		
		Button settings = new Button("Settings");
		settings.setMinSize(50, 50);
		settings.setMaxSize(50, 50);
		
		buttons.getChildren().addAll(newFile, saveFile, back, loadFile, exportFile, mode, settings);
		
		return buttons;
	}
	
	private Node createRightContent() {
		VBox rightContent = new VBox(5);
		rightContent.setAlignment(Pos.CENTER);
		
		Button up = new Button();
		up.setMinSize(30, 30);
		up.setMaxSize(30, 30);
		
		Button down = new Button();
		down.setMinSize(30, 30);
		down.setMaxSize(30, 30);
		
		rightContent.getChildren().add(up);
		rightContent.getChildren().add(createTilePicker());
		rightContent.getChildren().add(down);
		rightContent.getChildren().add(createBottomButtons());
		
		return rightContent;
		
	}
	
	private Node createTilePicker() {
		GridPane tilePickerPane = new GridPane();
		
		Button tile1 = new Button();
		tile1.setPrefSize(150, 150);
		
		Button tile2 = new Button();
		tile2.setPrefSize(150, 150);
		
		Button tile3 = new Button();
		tile3.setPrefSize(150, 150);
		
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
		
		Button pick = new Button("Pick");
		pick.setMinSize(50, 50);
		pick.setMaxSize(50, 50);
		
		Button erase = new Button("Erase");
		erase.setMinSize(50, 50);
		erase.setMaxSize(50, 50);
		
		Button eyeDropper = new Button("Eye Dropper");
		eyeDropper.setMinSize(50, 50);
		eyeDropper.setMaxSize(50, 50);
		
		buttons.getChildren().addAll(pick, erase, eyeDropper);
		return buttons;
	}
	
	private Node createSecondRowButtons() {
		HBox buttons = new HBox(5);
		
		Button chooseArea = new Button("Choose Area");
		chooseArea.setMinSize(50, 50);
		chooseArea.setMaxSize(50, 50);
		
		Button fillArea = new Button("Fill Area");
		fillArea.setMinSize(50, 50);
		fillArea.setMaxSize(50, 50);
		
		Button fillBorders = new Button("Fill Borders");
		fillBorders.setMinSize(50, 50);
		fillBorders.setMaxSize(50, 50);
		
		buttons.getChildren().addAll(chooseArea, fillArea, fillBorders);
		return buttons;
	}

}
