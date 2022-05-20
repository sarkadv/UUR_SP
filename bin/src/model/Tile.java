package model;

import javafx.scene.image.Image;

public class Tile {
	
	public final int id;
	public final Image image;
	public final String name;
	
	public Tile(int id, Image image, String name) {
		this.id = id;
		this.image = image;
		this.name = name;
	}

}
