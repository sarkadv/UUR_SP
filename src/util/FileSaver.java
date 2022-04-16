package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MapModel;

public class FileSaver {
	private static int mapWidth;
	private static int mapHeight;
	private static int tilesVisibleLine;
	private static int[][] tiles;
	private static boolean darkMode;
	private static boolean successful = true;
	
	private static void setMapModel(MapModel model) {
		mapWidth = model.getAllTilesWidth().get();
		mapHeight = model.getAllTilesHeight().get();
		tilesVisibleLine = model.getTilesVisibleLine().get();
		tiles = model.getAllTiles();
		darkMode = model.getDarkMode().get();
	}
	
	public static void saveFile(File file, MapModel model) {
		setMapModel(model);
		
		try (FileWriter fw = new FileWriter(file)){
			fw.write(mapWidth + "\n");
			fw.write(mapHeight + "\n");
			fw.write(tilesVisibleLine + "\n");
				
			if(darkMode) {
				fw.write("N");
			}
			else {
				fw.write("D");
			}
			fw.write("\n");
				
			for(int y = 0; y < mapHeight; y++) {
				for(int x = 0; x < mapWidth; x++) {
					fw.write(tiles[x][y] + " ");
				}
				fw.write("\n");
			}
		}
		catch (IOException e) {
			successful = false;
			
			System.out.println(e.getMessage());
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Ukládání Souboru");
			alert.setHeaderText("Chyba při ukládání souboru");
			alert.setContentText("Při ukládání souboru došlo k neočekávané chybě.");
			alert.showAndWait();
		}
	}

	public static boolean isSuccessful() {
		return successful;
	}
}
