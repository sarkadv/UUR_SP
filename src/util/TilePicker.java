package util;

import java.util.HashMap;

import model.Tile;

public class TilePicker {
	
	public static final Tile HADES_EMPTY_D = new Tile(00, ImageLoader.HADES_EMPTY_D, "empty_d");
	public static final Tile HADES_EMPTY_N = new Tile(01, ImageLoader.HADES_EMPTY_N, "empty_n");
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
	
	private static final HashMap<Integer, Tile> tileMapDay = new HashMap<Integer, Tile>();
	private static final HashMap<Integer, Tile> tileMapNight = new HashMap<Integer, Tile>();
	
	public static void init() {
		tileMapDay.put(HADES_PATH_D.id, HADES_PATH_D);
		tileMapNight.put(HADES_PATH_N.id, HADES_PATH_N);
		tileMapDay.put(HADES_EMPTY_D.id, HADES_EMPTY_D);
		tileMapNight.put(HADES_EMPTY_N.id, HADES_EMPTY_N);
		tileMapDay.put(HADES_FACTORY_D.id, HADES_FACTORY_D);
		tileMapNight.put(HADES_FACTORY_N.id, HADES_FACTORY_N);
		tileMapDay.put(HADES_PLANT_D.id, HADES_PLANT_D);
		tileMapNight.put(HADES_PLANT_N.id, HADES_PLANT_N);
		tileMapDay.put(HADES_SHOP_D.id, HADES_SHOP_D);
		tileMapNight.put(HADES_SHOP_N.id, HADES_SHOP_N);
		tileMapDay.put(HADES_WATER_D.id, HADES_WATER_D);
		tileMapNight.put(HADES_WATER_N.id, HADES_WATER_N);
		tileMapDay.put(HADES_HOUSE_D.id, HADES_HOUSE_D);
		tileMapNight.put(HADES_HOUSE_N.id, HADES_HOUSE_N);
		tileMapDay.put(HADES_WALL_D.id, HADES_WALL_D);
		tileMapNight.put(HADES_WALL_N.id, HADES_WALL_N);
	}
	
	public static Tile getTile(int tileId) {
		if(tileMapDay.containsKey(tileId)) {
			return tileMapDay.get(tileId);
		}
		else if(tileMapNight.containsKey(tileId)) {
			return tileMapNight.get(tileId);
		}
		else {
			return null;
		}
	}

	public static HashMap<Integer, Tile> getTileMapDay() {
		return tileMapDay;
	}

	public static HashMap<Integer, Tile> getTileMapNight() {
		return tileMapNight;
	}

}
