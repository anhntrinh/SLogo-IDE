package gui.API;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface UIMainHandler {
	public abstract void setLineColor(Color color);
	public abstract void setTurtleImage(Image image);
	public abstract void addFunctionToTerminal(String s);
	public abstract void setBackgroundColor(Color value);
	public abstract void setLanguage(String language);
}
