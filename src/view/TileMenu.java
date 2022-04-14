package view;

import java.util.ArrayList;
import java.util.List;

import controller.MainWindowController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Tile;
import util.ImageLoader;
import util.TilePicker;

public class TileMenu extends GridPane {
	
	private Button tileBtn1;
	private Tile tile1;
	private Button tileBtn2;
	private Tile tile2;
	private Button tileBtn3;
	private Tile tile3;
	private List<Tile> allTiles;
	private MainWindowController controller;
	private final int BTN_SIZE = 150;
	private boolean darkMode = false;
	private int currentFirst = 0;
	
	public TileMenu(MainWindowController controller) {
		
		this.controller = controller;
		
		if(this.darkMode == false) {
			this.allTiles = new ArrayList<Tile>(TilePicker.getTileMapDay().values());
		}
		else {
			this.allTiles = new ArrayList<Tile>(TilePicker.getTileMapNight().values());
		}
		
		this.init(currentFirst);
		
	}
	
	private void init(int currentFirst) {

		tile1 = this.allTiles.get(currentFirst);
		tileBtn1 = new ImageButton(BTN_SIZE, BTN_SIZE, tile1.name, tile1.image);
		this.add(tileBtn1, 0, 0);
		
		tile2 = this.allTiles.get(currentFirst + 1);
		tileBtn2 = new ImageButton(BTN_SIZE, BTN_SIZE, tile2.name, tile2.image);
		this.add(tileBtn2, 0, 1);
		
		tile3 = this.allTiles.get(currentFirst + 2);
		tileBtn3 = new ImageButton(BTN_SIZE, BTN_SIZE, tile3.name, tile3.image);
		this.add(tileBtn3, 0, 2);
		
		tileBtn1.setOnAction(e -> controller.changeCurrentTile(e, tile1));
		tileBtn2.setOnAction(e -> controller.changeCurrentTile(e, tile2));
		tileBtn3.setOnAction(e -> controller.changeCurrentTile(e, tile3));
		
	}

	public void moveUp() {
		if(currentFirst > 0) {
			this.currentFirst--;
			init(currentFirst);
		}
	}
	
	public void moveDown() {
		if(currentFirst < this.allTiles.size() - 3) {
			this.currentFirst++;
			init(currentFirst);
		}
	}

}
