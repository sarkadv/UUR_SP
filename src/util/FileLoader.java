package util;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MapModel;

public class FileLoader {
	
	private static int mapWidth;
	private static int mapHeight;
	private static int tilesVisibleLine;
	private static int[][] tiles;
	private static boolean darkMode;
	private static boolean successful = true;
	
	public static void loadFile(File file) {
		try (Scanner sc = new Scanner(file)){
			try {
				mapWidth = Integer.parseInt(sc.nextLine());
				mapHeight = Integer.parseInt(sc.nextLine());
				tiles = new int[mapWidth][mapHeight];
				
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
	
	public static void setMapModel(MapModel model) {
		model.setAllTiles(tiles);
		model.setAllTilesHeight(mapHeight);
		model.setAllTilesWidth(mapWidth);
		model.setTilesVisibleLine(tilesVisibleLine);
		model.setDarkMode(darkMode);
	}

	public static boolean isSuccessful() {
		return successful;
	}

}
