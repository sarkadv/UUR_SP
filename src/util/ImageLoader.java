package util;

import java.io.IOException;
import java.nio.IntBuffer;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

public class ImageLoader {
	
	private static final int TILE_SIZE = 500;
	private static final int BUTTON_UPPER_SIZE = 155;
	private static final int BUTTON_LOWER_SIZE = 95;
	private static final int HINT_PICTURE_WIDTH = 600;
	private static final int HINT_PICTURE_HEIGHT = 490;
	
	public static final Color COLOR_PLANET_ONE_LIGHT = Color.rgb(211, 182, 228);
	public static final Color COLOR_PLANET_ONE_DARK = Color.rgb(45, 31, 51);
	public static final Color COLOR_PLANET_TWO_LIGHT = Color.rgb(160, 237, 229);
	public static final Color COLOR_PLANET_TWO_DARK = Color.rgb(51, 135, 127);
	public static final Color COLOR_PLANET_THREE_LIGHT = Color.rgb(252, 182, 94);
	public static final Color COLOR_PLANET_THREE_DARK = Color.rgb(149, 79, 0);
	
	private static Image spritesheetHades;
	private static Image spritesheetChronos;
	private static Image spritesheetApollon;
	private static Image spritesheetButtonsUpper;
	private static Image spritesheetButtonsLower;
	private static Image spritesheetHint;
	
	public static Image TRIANGLE_UP;
	public static Image TRIANGLE_UP_LEFT_HALF;
	public static Image TRIANGLE_UP_RIGHT_HALF;
	public static Image TRIANGLE_DOWN;
	public static Image TRIANGLE_DOWN_LEFT_HALF;
	public static Image TRIANGLE_DOWN_RIGHT_HALF;
	public static Image TRIANGLE_LEFT;
	public static Image TRIANGLE_LEFT_TOP_HALF;
	public static Image TRIANGLE_LEFT_BOTTOM_HALF;
	public static Image TRIANGLE_RIGHT;
	public static Image TRIANGLE_RIGHT_TOP_HALF;
	public static Image TRIANGLE_RIGHT_BOTTOM_HALF;
	
	public static Image ALIEN_LOGO;
	
	public static Image ABOUT_LIGHT;
	public static Image ABOUT_DARK;
	public static Image BACK_LIGHT;
	public static Image BACK_DARK;
	public static Image EXPORT_LIGHT;
	public static Image EXPORT_DARK;
	public static Image HELP_LIGHT;
	public static Image HELP_DARK;
	public static Image LOAD_LIGHT;
	public static Image LOAD_DARK;
	public static Image MODE_LIGHT;
	public static Image MODE_DARK;
	public static Image NEW_LIGHT;
	public static Image NEW_DARK;
	public static Image SAVE_LIGHT;
	public static Image SAVE_DARK;
	public static Image SETTINGS_LIGHT;
	public static Image SETTINGS_DARK;
	
	public static Image PICK_LIGHT;
	public static Image PICK_DARK;
	public static Image ERASE_LIGHT;
	public static Image ERASE_DARK;
	public static Image FILLBORDERS_LIGHT;
	public static Image FILLBORDERS_DARK;
	public static Image CHOOSEAREA_LIGHT;
	public static Image CHOOSEAREA_DARK;
	public static Image EYEDROPPER_LIGHT;
	public static Image EYEDROPPER_DARK;
	public static Image FILLAREA_LIGHT;
	public static Image FILLAREA_DARK;
	
	public static Image HADES_PATH_D;
	public static Image HADES_PATH_N;
	public static Image HADES_FACTORY_D;
	public static Image HADES_FACTORY_N;
	public static Image HADES_SHOP_D;
	public static Image HADES_SHOP_N;
	public static Image HADES_EMPTY_D;
	public static Image HADES_EMPTY_N;
	public static Image HADES_HOUSE_D;
	public static Image HADES_HOUSE_N;
	public static Image HADES_PLANT_D;
	public static Image HADES_PLANT_N;
	public static Image HADES_WATER_D;
	public static Image HADES_WATER_N;
	public static Image HADES_WALL_D;
	public static Image HADES_WALL_N;
	
	public static Image CHRONOS_PATH_D;
	public static Image CHRONOS_PATH_N;
	public static Image CHRONOS_FACTORY_D;
	public static Image CHRONOS_FACTORY_N;
	public static Image CHRONOS_SHOP_D;
	public static Image CHRONOS_SHOP_N;
	public static Image CHRONOS_EMPTY_D;
	public static Image CHRONOS_EMPTY_N;
	public static Image CHRONOS_HOUSE_D;
	public static Image CHRONOS_HOUSE_N;
	public static Image CHRONOS_PLANT_D;
	public static Image CHRONOS_PLANT_N;
	public static Image CHRONOS_WATER_D;
	public static Image CHRONOS_WATER_N;
	public static Image CHRONOS_WALL_D;
	public static Image CHRONOS_WALL_N;
	
	public static Image APOLLON_PATH_D;
	public static Image APOLLON_PATH_N;
	public static Image APOLLON_FACTORY_D;
	public static Image APOLLON_FACTORY_N;
	public static Image APOLLON_SHOP_D;
	public static Image APOLLON_SHOP_N;
	public static Image APOLLON_EMPTY_D;
	public static Image APOLLON_EMPTY_N;
	public static Image APOLLON_HOUSE_D;
	public static Image APOLLON_HOUSE_N;
	public static Image APOLLON_PLANT_D;
	public static Image APOLLON_PLANT_N;
	public static Image APOLLON_WATER_D;
	public static Image APOLLON_WATER_N;
	public static Image APOLLON_WALL_D;
	public static Image APOLLON_WALL_N;
	
	public static Image HINT_DEFAULT;
	public static Image HINT_ABOUT;
	public static Image HINT_BACK;
	public static Image HINT_EXPORT;
	public static Image HINT_HELP;
	public static Image HINT_LOAD;
	public static Image HINT_MODE;
	public static Image HINT_NEW;
	public static Image HINT_SAVE;
	public static Image HINT_SETTINGS;
	
	public static Image HINT_PICK;
	public static Image HINT_ERASE;
	public static Image HINT_EYEDROPPER;
	public static Image HINT_CHOOSEAERA;
	public static Image HINT_FILLAREA;
	public static Image HINT_FILLBORDERS;
	
	public static Image HINT_UP;
	public static Image HINT_DOWN;
	
	private ImageLoader() {};
	
	public static void init() throws Exception {
		spritesheetHades = new Image("file:resources/spritesheet_hades.png");
		spritesheetChronos = new Image("file:resources/spritesheet_chronos.png");
		spritesheetApollon = new Image("file:resources/spritesheet_apollon.png");
		spritesheetButtonsUpper = new Image("file:resources/spritesheet_buttons_upper.png");
		spritesheetButtonsLower = new Image("file:resources/spritesheet_buttons_lower.png");
		spritesheetHint = new Image("file:resources/spritesheet_hint.png");
		
		TRIANGLE_UP = new Image("file:resources/triangle_up.png");
		TRIANGLE_UP_LEFT_HALF = new Image("file:resources/triangle_up_left_half.png");
		TRIANGLE_UP_RIGHT_HALF = new Image("file:resources/triangle_up_right_half.png");
		TRIANGLE_DOWN = new Image("file:resources/triangle_down.png");
		TRIANGLE_DOWN_LEFT_HALF = new Image("file:resources/triangle_down_left_half.png");
		TRIANGLE_DOWN_RIGHT_HALF = new Image("file:resources/triangle_down_right_half.png");
		TRIANGLE_LEFT = new Image("file:resources/triangle_left.png");
		TRIANGLE_LEFT_TOP_HALF = new Image("file:resources/triangle_left_top_half.png");
		TRIANGLE_LEFT_BOTTOM_HALF = new Image("file:resources/triangle_left_bottom_half.png");
		TRIANGLE_RIGHT = new Image("file:resources/triangle_right.png");
		TRIANGLE_RIGHT_TOP_HALF = new Image("file:resources/triangle_right_top_half.png");
		TRIANGLE_RIGHT_BOTTOM_HALF = new Image("file:resources/triangle_right_bottom_half.png");
		
		ALIEN_LOGO = new Image("file:resources/alien.png");
		
		ABOUT_LIGHT = crop(spritesheetButtonsUpper, 0, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		ABOUT_DARK = crop(spritesheetButtonsUpper, 3, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		BACK_LIGHT = crop(spritesheetButtonsUpper, 1, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		BACK_DARK = crop(spritesheetButtonsUpper, 4, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		EXPORT_LIGHT = crop(spritesheetButtonsUpper, 2, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		EXPORT_DARK = crop(spritesheetButtonsUpper, 5, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		HELP_LIGHT = crop(spritesheetButtonsUpper, 0, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		HELP_DARK = crop(spritesheetButtonsUpper, 3, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		LOAD_LIGHT = crop(spritesheetButtonsUpper, 1, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		LOAD_DARK = crop(spritesheetButtonsUpper, 4, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		MODE_LIGHT = crop(spritesheetButtonsUpper, 2, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		MODE_DARK = crop(spritesheetButtonsUpper, 5, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		NEW_LIGHT = crop(spritesheetButtonsUpper, 0, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		NEW_DARK = crop(spritesheetButtonsUpper, 3, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		SAVE_LIGHT = crop(spritesheetButtonsUpper, 1, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		SAVE_DARK = crop(spritesheetButtonsUpper, 4, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		SETTINGS_LIGHT = crop(spritesheetButtonsUpper, 2, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		SETTINGS_DARK = crop(spritesheetButtonsUpper, 5, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
		
		PICK_LIGHT = crop(spritesheetButtonsLower, 2, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		PICK_DARK = crop(spritesheetButtonsLower, 5, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		ERASE_LIGHT = crop(spritesheetButtonsLower, 1, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		ERASE_DARK = crop(spritesheetButtonsLower, 4, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		FILLBORDERS_LIGHT = crop(spritesheetButtonsLower, 2, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		FILLBORDERS_DARK = crop(spritesheetButtonsLower, 5, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		CHOOSEAREA_LIGHT = crop(spritesheetButtonsLower, 0, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		CHOOSEAREA_DARK = crop(spritesheetButtonsLower, 3, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		EYEDROPPER_LIGHT = crop(spritesheetButtonsLower, 0, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		EYEDROPPER_DARK = crop(spritesheetButtonsLower, 3, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		FILLAREA_LIGHT = crop(spritesheetButtonsLower, 1, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		FILLAREA_DARK = crop(spritesheetButtonsLower, 4, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
		
		HADES_PATH_D = crop(spritesheetHades, 0, 0, TILE_SIZE, TILE_SIZE);
		HADES_PATH_N = crop(spritesheetHades, 1, 0, TILE_SIZE, TILE_SIZE);
		HADES_FACTORY_D = crop(spritesheetHades, 2, 0, TILE_SIZE, TILE_SIZE);
		HADES_FACTORY_N = crop(spritesheetHades, 3, 0, TILE_SIZE, TILE_SIZE);
		HADES_SHOP_D = crop(spritesheetHades, 0, 1, TILE_SIZE, TILE_SIZE);
		HADES_SHOP_N = crop(spritesheetHades, 1, 1, TILE_SIZE, TILE_SIZE);
		HADES_EMPTY_D = crop(spritesheetHades, 2, 1, TILE_SIZE, TILE_SIZE);
		HADES_EMPTY_N = crop(spritesheetHades, 3, 1, TILE_SIZE, TILE_SIZE);
		HADES_HOUSE_D = crop(spritesheetHades, 0, 2, TILE_SIZE, TILE_SIZE);
		HADES_HOUSE_N = crop(spritesheetHades, 1, 2, TILE_SIZE, TILE_SIZE);
		HADES_PLANT_D = crop(spritesheetHades, 2, 2, TILE_SIZE, TILE_SIZE);
		HADES_PLANT_N = crop(spritesheetHades, 3, 2, TILE_SIZE, TILE_SIZE);
		HADES_WATER_D = crop(spritesheetHades, 0, 3, TILE_SIZE, TILE_SIZE);
		HADES_WATER_N = crop(spritesheetHades, 1, 3, TILE_SIZE, TILE_SIZE);
		HADES_WALL_D = crop(spritesheetHades, 2, 3, TILE_SIZE, TILE_SIZE);
		HADES_WALL_N = crop(spritesheetHades, 3, 3, TILE_SIZE, TILE_SIZE);
		
		CHRONOS_PATH_D = crop(spritesheetChronos, 0, 0, TILE_SIZE, TILE_SIZE);
		CHRONOS_PATH_N = crop(spritesheetChronos, 1, 0, TILE_SIZE, TILE_SIZE);
		CHRONOS_FACTORY_D = crop(spritesheetChronos, 2, 0, TILE_SIZE, TILE_SIZE);
		CHRONOS_FACTORY_N = crop(spritesheetChronos, 3, 0, TILE_SIZE, TILE_SIZE);
		CHRONOS_SHOP_D = crop(spritesheetChronos, 0, 3, TILE_SIZE, TILE_SIZE);
		CHRONOS_SHOP_N = crop(spritesheetChronos, 1, 3, TILE_SIZE, TILE_SIZE);
		CHRONOS_EMPTY_D = crop(spritesheetChronos, 0, 1, TILE_SIZE, TILE_SIZE);
		CHRONOS_EMPTY_N = crop(spritesheetChronos, 1, 1, TILE_SIZE, TILE_SIZE);
		CHRONOS_HOUSE_D = crop(spritesheetChronos, 2, 3, TILE_SIZE, TILE_SIZE);
		CHRONOS_HOUSE_N = crop(spritesheetChronos, 3, 3, TILE_SIZE, TILE_SIZE);
		CHRONOS_PLANT_D = crop(spritesheetChronos, 2, 1, TILE_SIZE, TILE_SIZE);
		CHRONOS_PLANT_N = crop(spritesheetChronos, 3, 1, TILE_SIZE, TILE_SIZE);
		CHRONOS_WATER_D = crop(spritesheetChronos, 0, 2, TILE_SIZE, TILE_SIZE);
		CHRONOS_WATER_N = crop(spritesheetChronos, 1, 2, TILE_SIZE, TILE_SIZE);
		CHRONOS_WALL_D = crop(spritesheetChronos, 2, 2, TILE_SIZE, TILE_SIZE);
		CHRONOS_WALL_N = crop(spritesheetChronos, 3, 2, TILE_SIZE, TILE_SIZE);
		
		APOLLON_PATH_D = crop(spritesheetApollon, 0, 0, TILE_SIZE, TILE_SIZE);
		APOLLON_PATH_N = crop(spritesheetApollon, 1, 0, TILE_SIZE, TILE_SIZE);
		APOLLON_FACTORY_D = crop(spritesheetApollon, 2, 0, TILE_SIZE, TILE_SIZE);
		APOLLON_FACTORY_N = crop(spritesheetApollon, 3, 0, TILE_SIZE, TILE_SIZE);
		APOLLON_SHOP_D = crop(spritesheetApollon, 0, 1, TILE_SIZE, TILE_SIZE);
		APOLLON_SHOP_N = crop(spritesheetApollon, 1, 1, TILE_SIZE, TILE_SIZE);
		APOLLON_EMPTY_D = crop(spritesheetApollon, 2, 1, TILE_SIZE, TILE_SIZE);
		APOLLON_EMPTY_N = crop(spritesheetApollon, 3, 1, TILE_SIZE, TILE_SIZE);
		APOLLON_HOUSE_D = crop(spritesheetApollon, 0, 2, TILE_SIZE, TILE_SIZE);
		APOLLON_HOUSE_N = crop(spritesheetApollon, 1, 2, TILE_SIZE, TILE_SIZE);
		APOLLON_PLANT_D = crop(spritesheetApollon, 2, 2, TILE_SIZE, TILE_SIZE);
		APOLLON_PLANT_N = crop(spritesheetApollon, 3, 2, TILE_SIZE, TILE_SIZE);
		APOLLON_WATER_D = crop(spritesheetApollon, 0, 3, TILE_SIZE, TILE_SIZE);
		APOLLON_WATER_N = crop(spritesheetApollon, 1, 3, TILE_SIZE, TILE_SIZE);
		APOLLON_WALL_D = crop(spritesheetApollon, 2, 3, TILE_SIZE, TILE_SIZE);
		APOLLON_WALL_N = crop(spritesheetApollon, 3, 3, TILE_SIZE, TILE_SIZE);
		
		HINT_DEFAULT = crop(spritesheetHint, 0, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_ABOUT = crop(spritesheetHint, 1, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_BACK = crop(spritesheetHint, 2, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_EXPORT = crop(spritesheetHint, 0, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_HELP = crop(spritesheetHint, 1, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_LOAD = crop(spritesheetHint, 2, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_MODE = crop(spritesheetHint, 3, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_NEW = crop(spritesheetHint, 3, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_SAVE = crop(spritesheetHint, 3, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_SETTINGS = crop(spritesheetHint, 0, 4, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		
		HINT_PICK = crop(spritesheetHint, 3, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_ERASE = crop(spritesheetHint, 2, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_EYEDROPPER = crop(spritesheetHint, 1, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_CHOOSEAERA = crop(spritesheetHint, 0, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_FILLAREA = crop(spritesheetHint, 2, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_FILLBORDERS = crop(spritesheetHint, 0, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		
		HINT_UP = crop(spritesheetHint, 1, 4, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
		HINT_DOWN = crop(spritesheetHint, 1, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	}
	
	private static Image crop(Image src, int col, int row, int imageWidth, int imageHeight) throws Exception {
	    if(src == null) {
	    	throw new Exception();
	    }
		PixelReader r = src.getPixelReader();
	    
	    PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();
	    
	    int[] pixels = new int[imageWidth * imageHeight];
	    r.getPixels(col * imageWidth, row * imageHeight, imageWidth, imageHeight, (WritablePixelFormat<IntBuffer>) pixelFormat,
	        pixels, 0, imageWidth);
	    
	    WritableImage out = new WritableImage(imageWidth, imageHeight);
	    PixelWriter w = out.getPixelWriter();
	    w.setPixels(0, 0, imageWidth, imageHeight, pixelFormat,
	        pixels, 0, imageWidth);

	    return (Image)out;
	}

}
