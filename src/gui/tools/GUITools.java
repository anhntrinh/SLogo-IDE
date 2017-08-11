package gui.tools;

import general_data_structures.Tuple;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.turtle.TurtleState;

/**
 * contains static methods for general usage by all GUI components
 * @author TNK
 *
 */
public class GUITools {
	public static Background getBackgroundWithColor(Color color){
		return getBackgroundWithColor(color, 0);
	}
	public static Background getBackgroundWithColor(Color color, double radii){
		BackgroundFill[] fills = {new BackgroundFill(color, new CornerRadii(radii), Insets.EMPTY)};
		Background b = new Background(fills);
		return b;
	}
	
	public static Rectangle addBackgroundWithColor(Pane self, Color color, Frame bounds){
		Rectangle rect = new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
		rect.setFill(color);
		rect.setArcHeight(3);
		rect.setArcWidth(3);
		self.getChildren().add(0, rect);
		return rect;
	}
	public static Label plainLabel(String text, int fontSize, Color color,FontWeight weight) {
		Label label = new Label();
		label.setText(text);
		label.setFont(Font.font("HelveticaNeue", weight, fontSize));
		label.setAlignment(Pos.CENTER);
		label.setTextFill(color);
		return label;
	}
	public static Text plainTextHelvetica(String text, int fontSize, Color color, FontWeight weight){
		Text t = new Text(text);
		t.setFont(Font.font("HelveticaNeue", weight, fontSize));
		t.setTextAlignment(TextAlignment.CENTER);
		t.setFill(color);
		return t;
	}
	
	public static Tuple<Double, Double> turtleCoordinateToPixelCoordinate(TurtleState t, Frame bounds){
		double x=t.getX() + bounds.getWidth()/2.0 - 16;
		double y=-t.getY() + bounds.getHeight()/2.0 - 16;
		return new Tuple<Double, Double>(x,y);
	}
	public static TurtleState guiTurtleToTurtleState(double angle, Tuple<Double,Double> pos,Frame bounds) {
		TurtleState t = new TurtleState();
		t.setHeadAngle(angle - 90);
		t.setX(pos.x - bounds.getWidth()/2.0 + 16);
		t.setY(-(pos.y - bounds.getHeight()/2.0 + 16));
		return t;
	}
	
	public static ImageButton makeButton(Image image, Frame frame, EventHandler<MouseEvent> event){
		ImageButton b = new ImageButton();
		b.updateImages(image, image);
		b.setPrefSize(frame.getWidth(), frame.getHeight());
		b.setLayoutX(frame.getX());
		b.setLayoutY(frame.getY());
		b.setOnMouseClicked(event);
		return b;
	}
}
