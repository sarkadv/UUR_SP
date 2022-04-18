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
	
	private ImageButton tileBtn1;
	private Tile tile1;
	private ImageButton tileBtn2;
	private Tile tile2;
	private ImageButton tileBtn3;
	private Tile tile3;
	private List<Tile> allTiles;
	private MainWindowController controller;
	private final int BTN_SIZE = 150;
	private boolean darkMode;
	private int currentFirst;
	
	public TileMenu(MainWindowController controller) {
		this.darkMode = false;
		this.currentFirst = 0;
		
		this.controller = controller;
		
		if(this.darkMode == false) {
			this.allTiles = new ArrayList<Tile>(TilePicker.getTileMapDay().values());
			this.allTiles.remove(TilePicker.getTile(00));
		}
		else {
			this.allTiles = new ArrayList<Tile>(TilePicker.getTileMapNight().values());
			this.allTiles.remove(TilePicker.getTile(01));
		}
		
		this.init(currentFirst);
		
	}
	
	public void updateChoice() {
		removeHighlight();
		handleHighlight();
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
		
		this.handleHighlight();
		
		tileBtn1.setOnAction(e -> {
			controller.changeCurrentTile(e, tile1);
			this.handleHighlight();
		});
		
		tileBtn2.setOnAction(e -> {
			controller.changeCurrentTile(e, tile2);
			this.handleHighlight();
		});
		
		tileBtn3.setOnAction(e -> {
			controller.changeCurrentTile(e, tile3);
			this.handleHighlight();
		});
		
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
	
	private void handleHighlight() {
		if(controller.getCurrentTile() != null) {
			if(controller.getCurrentTile().name.equals(this.tileBtn1.getName())) {
				tileBtn1.highlightImage();
				tileBtn2.imageBackToNormal();
				tileBtn3.imageBackToNormal();
			}
			else if(controller.getCurrentTile().name.equals(this.tileBtn2.getName())) {
				tileBtn2.highlightImage();
				tileBtn1.imageBackToNormal();
				tileBtn3.imageBackToNormal();
			}
			else if(controller.getCurrentTile().name.equals(this.tileBtn3.getName())) {
				tileBtn3.highlightImage();
				tileBtn1.imageBackToNormal();
				tileBtn2.imageBackToNormal();
			}
		}

	}
	
	private void removeHighlight() {
		tileBtn1.imageBackToNormal();
		tileBtn2.imageBackToNormal();
		tileBtn3.imageBackToNormal();
	}
	
	public void setDarkMode() {
		this.darkMode = !this.darkMode;
		
		if(this.darkMode == false) {
			this.allTiles = new ArrayList<Tile>(TilePicker.getTileMapDay().values());
			this.allTiles.remove(TilePicker.getTile(00));
		}
		else {
			this.allTiles = new ArrayList<Tile>(TilePicker.getTileMapNight().values());
			this.allTiles.remove(TilePicker.getTile(01));
		}
		
		this.init(currentFirst);
	}

}
