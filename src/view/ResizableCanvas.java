package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ResizableCanvas extends Canvas {
	
	public ResizableCanvas(int width, int height) {
		super(width, height);
	}

    @Override
    public double minHeight(double width)
    {
        return 500;
    }

    @Override
    public double minWidth(double height)
    {
        return 500;
    }

    @Override
    public boolean isResizable()
    {
        return true;
    }

    @Override
    public void resize(double width, double height)
    {
    	if(width >= this.minWidth(height) || width <= this.maxWidth(height)) {
    		if(height >= this.minHeight(width) || height <= this.maxHeight(width)) {
    			super.setWidth(height);
    	        super.setHeight(height);
    	        paint(); 
    		}
    	}
    }
    
    private void paint() {
    	GraphicsContext gc = this.getGraphicsContext2D();
    	gc.setFill(Color.MEDIUMPURPLE);
    	gc.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
