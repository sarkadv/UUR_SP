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
	
	public static final Color COLOR_PLANET_ONE_LIGHT = Color.rgb(211, 182, 228);
	public static final Color COLOR_PLANET_ONE_DARK = Color.rgb(45, 31, 51);
	
	private ImageLoader() {};
	
	private static Image spritesheetHades = new Image("file:resources/spritesheet_hades.png");
	private static Image spritesheetButtonsUpper = new Image("file:resources/spritesheet_buttons_upper.png");
	private static Image spritesheetButtonsLower = new Image("file:resources/spritesheet_buttons_lower.png");
	
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
	
	public static final Image ABOUT_LIGHT = crop(spritesheetButtonsUpper, 0, 0, BUTTON_UPPER_SIZE);
	public static final Image ABOUT_DARK = crop(spritesheetButtonsUpper, 3, 0, BUTTON_UPPER_SIZE);
	public static final Image BACK_LIGHT = crop(spritesheetButtonsUpper, 1, 0, BUTTON_UPPER_SIZE);
	public static final Image BACK_DARK = crop(spritesheetButtonsUpper, 4, 0, BUTTON_UPPER_SIZE);
	public static final Image EXPORT_LIGHT = crop(spritesheetButtonsUpper, 2, 0, BUTTON_UPPER_SIZE);
	public static final Image EXPORT_DARK = crop(spritesheetButtonsUpper, 5, 0, BUTTON_UPPER_SIZE);
	public static final Image HELP_LIGHT = crop(spritesheetButtonsUpper, 0, 1, BUTTON_UPPER_SIZE);
	public static final Image HELP_DARK = crop(spritesheetButtonsUpper, 3, 1, BUTTON_UPPER_SIZE);
	public static final Image LOAD_LIGHT = crop(spritesheetButtonsUpper, 1, 1, BUTTON_UPPER_SIZE);
	public static final Image LOAD_DARK = crop(spritesheetButtonsUpper, 4, 1, BUTTON_UPPER_SIZE);
	public static final Image MODE_LIGHT = crop(spritesheetButtonsUpper, 2, 1, BUTTON_UPPER_SIZE);
	public static final Image MODE_DARK = crop(spritesheetButtonsUpper, 5, 1, BUTTON_UPPER_SIZE);
	public static final Image NEW_LIGHT = crop(spritesheetButtonsUpper, 0, 2, BUTTON_UPPER_SIZE);
	public static final Image NEW_DARK = crop(spritesheetButtonsUpper, 3, 2, BUTTON_UPPER_SIZE);
	public static final Image SAVE_LIGHT = crop(spritesheetButtonsUpper, 1, 2, BUTTON_UPPER_SIZE);
	public static final Image SAVE_DARK = crop(spritesheetButtonsUpper, 4, 2, BUTTON_UPPER_SIZE);
	public static final Image SETTINGS_LIGHT = crop(spritesheetButtonsUpper, 2, 2, BUTTON_UPPER_SIZE);
	public static final Image SETTINGS_DARK = crop(spritesheetButtonsUpper, 5, 2, BUTTON_UPPER_SIZE);
	
	public static final Image PICK_LIGHT = crop(spritesheetButtonsLower, 2, 1, BUTTON_LOWER_SIZE);
	public static final Image PICK_DARK = crop(spritesheetButtonsLower, 5, 1, BUTTON_LOWER_SIZE);
	public static final Image ERASE_LIGHT = crop(spritesheetButtonsLower, 1, 0, BUTTON_LOWER_SIZE);
	public static final Image ERASE_DARK = crop(spritesheetButtonsLower, 4, 0, BUTTON_LOWER_SIZE);
	public static final Image FILLBORDERS_LIGHT = crop(spritesheetButtonsLower, 2, 0, BUTTON_LOWER_SIZE);
	public static final Image FILLBORDERS_DARK = crop(spritesheetButtonsLower, 5, 0, BUTTON_LOWER_SIZE);
	public static final Image CHOOSEAREA_LIGHT = crop(spritesheetButtonsLower, 0, 0, BUTTON_LOWER_SIZE);
	public static final Image CHOOSEAREA_DARK = crop(spritesheetButtonsLower, 3, 0, BUTTON_LOWER_SIZE);
	public static final Image EYEDROPPER_LIGHT = crop(spritesheetButtonsLower, 0, 1, BUTTON_LOWER_SIZE);
	public static final Image EYEDROPPER_DARK = crop(spritesheetButtonsLower, 3, 1, BUTTON_LOWER_SIZE);
	public static final Image FILLAREA_LIGHT = crop(spritesheetButtonsLower, 1, 1, BUTTON_LOWER_SIZE);
	public static final Image FILLAREA_DARK = crop(spritesheetButtonsLower, 4, 1, BUTTON_LOWER_SIZE);
	
	public static final Image HADES_PATH_D = crop(spritesheetHades, 0, 0, TILE_SIZE);
	public static final Image HADES_PATH_N = crop(spritesheetHades, 1, 0, TILE_SIZE);
	public static final Image HADES_FACTORY_D = crop(spritesheetHades, 2, 0, TILE_SIZE);
	public static final Image HADES_FACTORY_N = crop(spritesheetHades, 3, 0, TILE_SIZE);
	public static final Image HADES_SHOP_D = crop(spritesheetHades, 0, 1, TILE_SIZE);
	public static final Image HADES_SHOP_N = crop(spritesheetHades, 1, 1, TILE_SIZE);
	public static final Image HADES_EMPTY_D = crop(spritesheetHades, 2, 1, TILE_SIZE);
	public static final Image HADES_EMPTY_N = crop(spritesheetHades, 3, 1, TILE_SIZE);
	public static final Image HADES_HOUSE_D = crop(spritesheetHades, 0, 2, TILE_SIZE);
	public static final Image HADES_HOUSE_N = crop(spritesheetHades, 1, 2, TILE_SIZE);
	public static final Image HADES_PLANT_D = crop(spritesheetHades, 2, 2, TILE_SIZE);
	public static final Image HADES_PLANT_N = crop(spritesheetHades, 3, 2, TILE_SIZE);
	public static final Image HADES_WATER_D = crop(spritesheetHades, 0, 3, TILE_SIZE);
	public static final Image HADES_WATER_N = crop(spritesheetHades, 1, 3, TILE_SIZE);
	public static final Image HADES_WALL_D = crop(spritesheetHades, 2, 3, TILE_SIZE);
	public static final Image HADES_WALL_N = crop(spritesheetHades, 3, 3, TILE_SIZE);
	
	private static Image crop(Image src, int col, int row, int imageSize) {
	    PixelReader r = src.getPixelReader();
	    
	    PixelFormat<IntBuffer> pixelFormat = PixelFormat.getIntArgbInstance();
	    
	    int[] pixels = new int[imageSize * imageSize];
	    r.getPixels(col * imageSize, row * imageSize, imageSize, imageSize, (WritablePixelFormat<IntBuffer>) pixelFormat,
	        pixels, 0, imageSize);
	    
	    WritableImage out = new WritableImage(imageSize, imageSize);
	    PixelWriter w = out.getPixelWriter();
	    w.setPixels(0, 0, imageSize, imageSize, pixelFormat,
	        pixels, 0, imageSize);

	    return (Image)out;
	}

}
