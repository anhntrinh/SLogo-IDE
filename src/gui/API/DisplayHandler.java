package gui.API;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface DisplayHandler {
	public abstract Color getColorPalette(double index);
	public abstract Image getShape(double index);
}
