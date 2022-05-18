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
	
	public static final Tile CHRONOS_EMPTY_D = new Tile(80, ImageLoader.CHRONOS_EMPTY_D, "empty_d");
	public static final Tile CHRONOS_EMPTY_N = new Tile(81, ImageLoader.CHRONOS_EMPTY_N, "empty_n");
	public static final Tile CHRONOS_PATH_D = new Tile(90, ImageLoader.CHRONOS_PATH_D, "path_d");
	public static final Tile CHRONOS_PATH_N = new Tile(91, ImageLoader.CHRONOS_PATH_N, "path_n");
	public static final Tile CHRONOS_FACTORY_D = new Tile(100, ImageLoader.CHRONOS_FACTORY_D, "factory_d");
	public static final Tile CHRONOS_FACTORY_N = new Tile(101, ImageLoader.CHRONOS_FACTORY_N, "factory_n");
	public static final Tile CHRONOS_PLANT_D = new Tile(110, ImageLoader.CHRONOS_PLANT_D, "plant_d");
	public static final Tile CHRONOS_PLANT_N = new Tile(111, ImageLoader.CHRONOS_PLANT_N, "plant_n");
	public static final Tile CHRONOS_SHOP_D = new Tile(120, ImageLoader.CHRONOS_SHOP_D, "shop_d");
	public static final Tile CHRONOS_SHOP_N = new Tile(121, ImageLoader.CHRONOS_SHOP_N, "shop_n");
	public static final Tile CHRONOS_WATER_D = new Tile(130, ImageLoader.CHRONOS_WATER_D, "water_d");
	public static final Tile CHRONOS_WATER_N = new Tile(131, ImageLoader.CHRONOS_WATER_N, "water_n");
	public static final Tile CHRONOS_HOUSE_D = new Tile(140, ImageLoader.CHRONOS_HOUSE_D, "house_d");
	public static final Tile CHRONOS_HOUSE_N = new Tile(141, ImageLoader.CHRONOS_HOUSE_N, "house_n");
	public static final Tile CHRONOS_WALL_D = new Tile(150, ImageLoader.CHRONOS_WALL_D, "wall_d");
	public static final Tile CHRONOS_WALL_N = new Tile(151, ImageLoader.CHRONOS_WALL_N, "wall_n");
	
	private static final HashMap<Integer, Tile> tileMapDayHades = new HashMap<Integer, Tile>();
	private static final HashMap<Integer, Tile> tileMapNightHades = new HashMap<Integer, Tile>();
	
	private static final HashMap<Integer, Tile> tileMapDayChronos = new HashMap<Integer, Tile>();
	private static final HashMap<Integer, Tile> tileMapNightChronos = new HashMap<Integer, Tile>();
	
	public static void init() {
		tileMapDayHades.put(HADES_PATH_D.id, HADES_PATH_D);
		tileMapNightHades.put(HADES_PATH_N.id, HADES_PATH_N);
		tileMapDayHades.put(HADES_EMPTY_D.id, HADES_EMPTY_D);
		tileMapNightHades.put(HADES_EMPTY_N.id, HADES_EMPTY_N);
		tileMapDayHades.put(HADES_FACTORY_D.id, HADES_FACTORY_D);
		tileMapNightHades.put(HADES_FACTORY_N.id, HADES_FACTORY_N);
		tileMapDayHades.put(HADES_PLANT_D.id, HADES_PLANT_D);
		tileMapNightHades.put(HADES_PLANT_N.id, HADES_PLANT_N);
		tileMapDayHades.put(HADES_SHOP_D.id, HADES_SHOP_D);
		tileMapNightHades.put(HADES_SHOP_N.id, HADES_SHOP_N);
		tileMapDayHades.put(HADES_WATER_D.id, HADES_WATER_D);
		tileMapNightHades.put(HADES_WATER_N.id, HADES_WATER_N);
		tileMapDayHades.put(HADES_HOUSE_D.id, HADES_HOUSE_D);
		tileMapNightHades.put(HADES_HOUSE_N.id, HADES_HOUSE_N);
		tileMapDayHades.put(HADES_WALL_D.id, HADES_WALL_D);
		tileMapNightHades.put(HADES_WALL_N.id, HADES_WALL_N);
		
		tileMapDayChronos.put(CHRONOS_PATH_D.id, CHRONOS_PATH_D);
		tileMapNightChronos.put(CHRONOS_PATH_N.id, CHRONOS_PATH_N);
		tileMapDayChronos.put(CHRONOS_EMPTY_D.id, CHRONOS_EMPTY_D);
		tileMapNightChronos.put(CHRONOS_EMPTY_N.id, CHRONOS_EMPTY_N);
		tileMapDayChronos.put(CHRONOS_FACTORY_D.id, CHRONOS_FACTORY_D);
		tileMapNightChronos.put(CHRONOS_FACTORY_N.id, CHRONOS_FACTORY_N);
		tileMapDayChronos.put(CHRONOS_PLANT_D.id, CHRONOS_PLANT_D);
		tileMapNightChronos.put(CHRONOS_PLANT_N.id, CHRONOS_PLANT_N);
		tileMapDayChronos.put(CHRONOS_SHOP_D.id, CHRONOS_SHOP_D);
		tileMapNightChronos.put(CHRONOS_SHOP_N.id, CHRONOS_SHOP_N);
		tileMapDayChronos.put(CHRONOS_WATER_D.id, CHRONOS_WATER_D);
		tileMapNightChronos.put(CHRONOS_WATER_N.id, CHRONOS_WATER_N);
		tileMapDayChronos.put(CHRONOS_HOUSE_D.id, CHRONOS_HOUSE_D);
		tileMapNightChronos.put(CHRONOS_HOUSE_N.id, CHRONOS_HOUSE_N);
		tileMapDayChronos.put(CHRONOS_WALL_D.id, CHRONOS_WALL_D);
		tileMapNightChronos.put(CHRONOS_WALL_N.id, CHRONOS_WALL_N);
	}
	
	public static Tile getTile(int tileId, int activePlanet) {
		if(activePlanet == 0) {
			if(tileMapDayHades.containsKey(tileId)) {
				return tileMapDayHades.get(tileId);
			}
			else if(tileMapNightHades.containsKey(tileId)) {
				return tileMapNightHades.get(tileId);
			}
			else {
				return null;
			}
		}
		else {
			if(tileMapDayChronos.containsKey(tileId)) {
				return tileMapDayChronos.get(tileId);
			}
			else if(tileMapNightChronos.containsKey(tileId)) {
				return tileMapNightChronos.get(tileId);
			}
			else {
				return null;
			}
		}

	}

	public static HashMap<Integer, Tile> getTileMapDay(int activePlanet) {
		if(activePlanet == 0) {
			return tileMapDayHades;
		}
		else {
			return tileMapDayChronos;
		}
	}

	public static HashMap<Integer, Tile> getTileMapNight(int activePlanet) {
		if(activePlanet == 0) {
			return tileMapNightHades;
		}
		else {
			return tileMapNightChronos;
		}
	}
}
