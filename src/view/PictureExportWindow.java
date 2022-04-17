package view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Tile;
import util.FileSaver;
import util.ImageLoader;
import util.TilePicker;

public class PictureExportWindow extends Stage {
	
	private int[][] tiles;
	private int mapWidth;
	private int mapHeight;
	private final int windowWidth;
	private final int windowHeight;
	private GridPane picturePane;
	private int tileSize;
	
	public PictureExportWindow(int[][] tiles, Stage primaryStage) {
		this.tiles = tiles;
		this.mapWidth = tiles.length;
		this.mapHeight = tiles[0].length;
		this.windowWidth = 700;
		this.windowHeight = 800;
		this.tileSize = Math.min(windowWidth/mapWidth, (windowHeight - 100)/mapHeight);
		
		this.setScene(createScene());
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("Exportovat jako obrázek");
		this.initOwner(primaryStage);
		this.setMinHeight(windowHeight);
		this.setMaxHeight(windowHeight);
		this.setMinWidth(windowWidth);
		this.setMaxWidth(windowWidth);
		
	}
	
	private Scene createScene() {
		Scene scene = new Scene(createRootPane(), windowWidth, windowHeight);
		
		return scene;
	}
	
	private Parent createRootPane() {
		VBox rootPane = new VBox(5);
		rootPane.setPadding(new Insets(10));
		rootPane.setAlignment(Pos.CENTER);
		
		Button exportBtn = new Button("Exportovat");
		exportBtn.setOnAction(e -> saveSnapShot());
		
		rootPane.getChildren().addAll(createPicturePane(), new Separator(), exportBtn);
		
		return rootPane;
	}
	
	private Parent createPicturePane() {
		picturePane = new GridPane();
		
    	for(int x = 0; x < mapWidth; x++) {
    		for(int y = 0; y < mapHeight; y++) {
    			int id = tiles[x][y];
    			
    			Tile tile = TilePicker.getTile(id);
    			
    			ImageView tileImageView = new ImageView(tile.image);
    			
        		tileImageView.setFitHeight(tileSize);
        		tileImageView.setFitWidth(tileSize);
        		tileImageView.setSmooth(true);
        			
        		picturePane.add(tileImageView, x, y);
    			
    		}
    	}
    	
    	return picturePane;
	}
	
	private void saveSnapShot() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Uložení souboru");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png"));
		File file = fileChooser.showSaveDialog(this);
		
		WritableImage writableImage = new WritableImage((int)Math.rint(3.0*(tileSize*mapWidth)), (int)Math.rint(3.0*(tileSize*mapHeight)));
	    SnapshotParameters spa = new SnapshotParameters();
	    spa.setTransform(Transform.scale(3.0, 3.0));
	    
	    spa.setFill(ImageLoader.COLOR_PLANET_ONE);
	    WritableImage img = picturePane.snapshot(spa, writableImage); 
		
		if(file != null) {
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);
			} 
			catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Exportovat jako obrázek");
				alert.setHeaderText("Nepodařilo se exportovat mapu");
				alert.setContentText("Při exportu došlo k neočekávané chybě.");
				alert.showAndWait();
			}
		}
		
		this.close();
		
	}

}