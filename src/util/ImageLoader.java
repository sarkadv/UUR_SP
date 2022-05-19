package util;

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
	
	private ImageLoader() {};
	
	private static Image spritesheetHades = new Image("file:resources/spritesheet_hades.png");
	private static Image spritesheetChronos = new Image("file:resources/spritesheet_chronos.png");
	private static Image spritesheetApollon = new Image("file:resources/spritesheet_apollon.png");
	private static Image spritesheetButtonsUpper = new Image("file:resources/spritesheet_buttons_upper.png");
	private static Image spritesheetButtonsLower = new Image("file:resources/spritesheet_buttons_lower.png");
	private static Image spritesheetHint = new Image("file:resources/spritesheet_hint.png");
	
	public static final Image TRIANGLE_UP = new Image("file:resources/triangle_up.png");
	public static final Image TRIANGLE_UP_LEFT_HALF = new Image("file:resources/triangle_up_left_half.png");
	public static final Image TRIANGLE_UP_RIGHT_HALF = new Image("file:resources/triangle_up_right_half.png");
	public static final Image TRIANGLE_DOWN = new Image("file:resources/triangle_down.png");
	public static final Image TRIANGLE_DOWN_LEFT_HALF = new Image("file:resources/triangle_down_left_half.png");
	public static final Image TRIANGLE_DOWN_RIGHT_HALF = new Image("file:resources/triangle_down_right_half.png");
	public static final Image TRIANGLE_LEFT = new Image("file:resources/triangle_left.png");
	public static final Image TRIANGLE_LEFT_TOP_HALF = new Image("file:resources/triangle_left_top_half.png");
	public static final Image TRIANGLE_LEFT_BOTTOM_HALF = new Image("file:resources/triangle_left_bottom_half.png");
	public static final Image TRIANGLE_RIGHT = new Image("file:resources/triangle_right.png");
	public static final Image TRIANGLE_RIGHT_TOP_HALF = new Image("file:resources/triangle_right_top_half.png");
	public static final Image TRIANGLE_RIGHT_BOTTOM_HALF = new Image("file:resources/triangle_right_bottom_half.png");
	
	public static final Image ALIEN_LOGO = new Image("file:resources/alien.png");
	
	public static final Image ABOUT_LIGHT = crop(spritesheetButtonsUpper, 0, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image ABOUT_DARK = crop(spritesheetButtonsUpper, 3, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image BACK_LIGHT = crop(spritesheetButtonsUpper, 1, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image BACK_DARK = crop(spritesheetButtonsUpper, 4, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image EXPORT_LIGHT = crop(spritesheetButtonsUpper, 2, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image EXPORT_DARK = crop(spritesheetButtonsUpper, 5, 0, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image HELP_LIGHT = crop(spritesheetButtonsUpper, 0, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image HELP_DARK = crop(spritesheetButtonsUpper, 3, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image LOAD_LIGHT = crop(spritesheetButtonsUpper, 1, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image LOAD_DARK = crop(spritesheetButtonsUpper, 4, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image MODE_LIGHT = crop(spritesheetButtonsUpper, 2, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image MODE_DARK = crop(spritesheetButtonsUpper, 5, 1, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image NEW_LIGHT = crop(spritesheetButtonsUpper, 0, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image NEW_DARK = crop(spritesheetButtonsUpper, 3, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image SAVE_LIGHT = crop(spritesheetButtonsUpper, 1, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image SAVE_DARK = crop(spritesheetButtonsUpper, 4, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image SETTINGS_LIGHT = crop(spritesheetButtonsUpper, 2, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	public static final Image SETTINGS_DARK = crop(spritesheetButtonsUpper, 5, 2, BUTTON_UPPER_SIZE, BUTTON_UPPER_SIZE);
	
	public static final Image PICK_LIGHT = crop(spritesheetButtonsLower, 2, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image PICK_DARK = crop(spritesheetButtonsLower, 5, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image ERASE_LIGHT = crop(spritesheetButtonsLower, 1, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image ERASE_DARK = crop(spritesheetButtonsLower, 4, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image FILLBORDERS_LIGHT = crop(spritesheetButtonsLower, 2, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image FILLBORDERS_DARK = crop(spritesheetButtonsLower, 5, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image CHOOSEAREA_LIGHT = crop(spritesheetButtonsLower, 0, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image CHOOSEAREA_DARK = crop(spritesheetButtonsLower, 3, 0, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image EYEDROPPER_LIGHT = crop(spritesheetButtonsLower, 0, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image EYEDROPPER_DARK = crop(spritesheetButtonsLower, 3, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image FILLAREA_LIGHT = crop(spritesheetButtonsLower, 1, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	public static final Image FILLAREA_DARK = crop(spritesheetButtonsLower, 4, 1, BUTTON_LOWER_SIZE, BUTTON_LOWER_SIZE);
	
	public static final Image HADES_PATH_D = crop(spritesheetHades, 0, 0, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_PATH_N = crop(spritesheetHades, 1, 0, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_FACTORY_D = crop(spritesheetHades, 2, 0, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_FACTORY_N = crop(spritesheetHades, 3, 0, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_SHOP_D = crop(spritesheetHades, 0, 1, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_SHOP_N = crop(spritesheetHades, 1, 1, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_EMPTY_D = crop(spritesheetHades, 2, 1, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_EMPTY_N = crop(spritesheetHades, 3, 1, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_HOUSE_D = crop(spritesheetHades, 0, 2, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_HOUSE_N = crop(spritesheetHades, 1, 2, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_PLANT_D = crop(spritesheetHades, 2, 2, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_PLANT_N = crop(spritesheetHades, 3, 2, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_WATER_D = crop(spritesheetHades, 0, 3, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_WATER_N = crop(spritesheetHades, 1, 3, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_WALL_D = crop(spritesheetHades, 2, 3, TILE_SIZE, TILE_SIZE);
	public static final Image HADES_WALL_N = crop(spritesheetHades, 3, 3, TILE_SIZE, TILE_SIZE);
	
	public static final Image CHRONOS_PATH_D = crop(spritesheetChronos, 0, 0, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_PATH_N = crop(spritesheetChronos, 1, 0, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_FACTORY_D = crop(spritesheetChronos, 2, 0, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_FACTORY_N = crop(spritesheetChronos, 3, 0, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_SHOP_D = crop(spritesheetChronos, 0, 3, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_SHOP_N = crop(spritesheetChronos, 1, 3, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_EMPTY_D = crop(spritesheetChronos, 0, 1, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_EMPTY_N = crop(spritesheetChronos, 1, 1, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_HOUSE_D = crop(spritesheetChronos, 2, 3, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_HOUSE_N = crop(spritesheetChronos, 3, 3, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_PLANT_D = crop(spritesheetChronos, 2, 1, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_PLANT_N = crop(spritesheetChronos, 3, 1, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_WATER_D = crop(spritesheetChronos, 0, 2, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_WATER_N = crop(spritesheetChronos, 1, 2, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_WALL_D = crop(spritesheetChronos, 2, 2, TILE_SIZE, TILE_SIZE);
	public static final Image CHRONOS_WALL_N = crop(spritesheetChronos, 3, 2, TILE_SIZE, TILE_SIZE);
	
	public static final Image APOLLON_PATH_D = crop(spritesheetApollon, 0, 0, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_PATH_N = crop(spritesheetApollon, 1, 0, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_FACTORY_D = crop(spritesheetApollon, 2, 0, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_FACTORY_N = crop(spritesheetApollon, 3, 0, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_SHOP_D = crop(spritesheetApollon, 0, 1, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_SHOP_N = crop(spritesheetApollon, 1, 1, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_EMPTY_D = crop(spritesheetApollon, 2, 1, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_EMPTY_N = crop(spritesheetApollon, 3, 1, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_HOUSE_D = crop(spritesheetApollon, 0, 2, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_HOUSE_N = crop(spritesheetApollon, 1, 2, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_PLANT_D = crop(spritesheetApollon, 2, 2, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_PLANT_N = crop(spritesheetApollon, 3, 2, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_WATER_D = crop(spritesheetApollon, 0, 3, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_WATER_N = crop(spritesheetApollon, 1, 3, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_WALL_D = crop(spritesheetApollon, 2, 3, TILE_SIZE, TILE_SIZE);
	public static final Image APOLLON_WALL_N = crop(spritesheetApollon, 3, 3, TILE_SIZE, TILE_SIZE);
	
	public static final Image HINT_DEFAULT = crop(spritesheetHint, 0, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_ABOUT = crop(spritesheetHint, 1, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_BACK = crop(spritesheetHint, 2, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_EXPORT = crop(spritesheetHint, 0, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_HELP = crop(spritesheetHint, 1, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_LOAD = crop(spritesheetHint, 2, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_MODE = crop(spritesheetHint, 3, 0, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_NEW = crop(spritesheetHint, 3, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_SAVE = crop(spritesheetHint, 3, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_SETTINGS = crop(spritesheetHint, 0, 4, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	
	public static final Image HINT_PICK = crop(spritesheetHint, 3, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_ERASE = crop(spritesheetHint, 2, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_EYEDROPPER = crop(spritesheetHint, 1, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_CHOOSEAERA = crop(spritesheetHint, 0, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_FILLAREA = crop(spritesheetHint, 2, 2, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_FILLBORDERS = crop(spritesheetHint, 0, 3, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	
	public static final Image HINT_UP = crop(spritesheetHint, 1, 4, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	public static final Image HINT_DOWN = crop(spritesheetHint, 1, 1, HINT_PICTURE_WIDTH, HINT_PICTURE_HEIGHT);
	
	private static Image crop(Image src, int col, int row, int imageWidth, int imageHeight) {
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
