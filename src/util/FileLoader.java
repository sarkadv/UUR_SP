package util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import controller.MapController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FileLoader {
	
	private static int mapWidth;
	private static int mapHeight;
	private static int tilesVisibleLine;
	private static Integer[][] tiles;
	private static boolean darkMode;
	private static boolean successful = true;
	
	public static void loadFile(File file) {
		try (Scanner sc = new Scanner(file)){
			try {
				mapWidth = Integer.parseInt(sc.nextLine());
				mapHeight = Integer.parseInt(sc.nextLine());
				tiles = new Integer[mapWidth][mapHeight];
				
				tilesVisibleLine = Integer.parseInt(sc.nextLine());
				
				String darkModeLine = sc.nextLine();
				if(darkModeLine.equals("D")) {
					darkMode = false;
				}
				else if(darkModeLine.equals("N")) {
					darkMode = true;
				}
				
				for(int y = 0; y < mapHeight; y++) {
					String line = sc.nextLine();
					String[] ids = line.split(" ");
					
					for(int x = 0; x < mapWidth; x++) {
						tiles[x][y] = Integer.parseInt(ids[x]);
					}
				}
			}
			catch(Exception e) {
				successful = false;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Načítání Souboru");
				alert.setHeaderText("Chyba při načítání souboru");
				alert.setContentText("Soubor se nepodařilo načíst z důvodu špatného formátu.");
				alert.showAndWait();
			}

			
		} catch (IOException e) {
			successful = false;
			
			System.out.println(e.getMessage());
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Načítání Souboru");
			alert.setHeaderText("Chyba při načítání souboru");
			alert.setContentText("Při čtení souboru došlo k neočekávané chybě.");
			alert.showAndWait();
		}
	}
	
	public static void setMapModel(MapController controller) {
		controller.setDarkMode(darkMode);
		controller.setAllTiles(tiles);
		controller.setAllTilesHeight(mapHeight);
		controller.setAllTilesWidth(mapWidth);
		controller.setTilesVisibleLine(tilesVisibleLine);
		controller.setFirstTileVisibleX(0);
		controller.setFirstTileVisibleY(0);
		controller.repaint();
	}

	public static boolean isSuccessful() {
		return successful;
	}

}
