package util;

import java.util.HashMap;

import model.Tile;

public class TilePicker {
	
	public static final Tile HADES_PATH_D = new Tile(10, ImageLoader.HADES_PATH_D, "path_d");
	public static final Tile HADES_PATH_N = new Tile(11, ImageLoader.HADES_PATH_N, "path_n");
	public static final Tile HADES_FACTORY_D = new Tile(20, ImageLoader.HADES_FACTORY_D, "factory_d");
	public static final Tile HADES_FACTORY_N = new Tile(21, ImageLoader.HADES_FACTORY_N, "factory_n");
	public static final Tile HADES_PLANT_D = new Tile(30, ImageLoader.HADES_PLANT_D, "plant_d");
	public static final Tile HADES_PLANT_N = new Tile(31, ImageLoader.HADES_PLANT_N, "plant_n");
	public static final Tile HADES_SHOP_D = new Tile(40, ImageLoader.HADES_SHOP_D, "shop_d");
	public static final Tile HADES_SHOP_N = new Tile(41, ImageLoader.HADES_SHOP_N, "shop_n");
	public static final Tile HADES_WATER_D = new Tile(50, ImageLoader.HADES_WATER_D, "water_d");
	public static final Tile HADES_WATER_N = new Tile(51, ImageLoader.HADES_WATER_N, "water_n");
	public static final Tile HADES_HOUSE_D = new Tile(60, ImageLoader.HADES_HOUSE_D, "house_d");
	public static final Tile HADES_HOUSE_N = new Tile(61, ImageLoader.HADES_HOUSE_N, "house_n");
	public static final Tile HADES_WALL_D = new Tile(70, ImageLoader.HADES_WALL_D, "wall_d");
	public static final Tile HADES_WALL_N = new Tile(71, ImageLoader.HADES_WALL_N, "wall_n");
	
	private static final HashMap<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
	
	public static void init() {
		tileMap.put(HADES_PATH_D.id, HADES_PATH_D);
		tileMap.put(HADES_PATH_N.id, HADES_PATH_N);
		tileMap.put(HADES_FACTORY_D.id, HADES_FACTORY_D);
		tileMap.put(HADES_FACTORY_N.id, HADES_FACTORY_N);
		tileMap.put(HADES_PLANT_D.id, HADES_PLANT_D);
		tileMap.put(HADES_PLANT_N.id, HADES_PLANT_N);
		tileMap.put(HADES_SHOP_D.id, HADES_SHOP_D);
		tileMap.put(HADES_SHOP_N.id, HADES_SHOP_N);
		tileMap.put(HADES_WATER_D.id, HADES_WATER_D);
		tileMap.put(HADES_WATER_N.id, HADES_WATER_N);
		tileMap.put(HADES_HOUSE_D.id, HADES_HOUSE_D);
		tileMap.put(HADES_HOUSE_N.id, HADES_HOUSE_N);
		tileMap.put(HADES_WALL_D.id, HADES_WALL_D);
		tileMap.put(HADES_WALL_N.id, HADES_WALL_N);
	}
	
	public static Tile getTile(int tileId) {
		if(tileMap.containsKey(tileId)) {
			return tileMap.get(tileId);
		}
		else {
			return null;
		}
	}
}
