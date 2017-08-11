package gui.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gui.API.UIMainHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.FontWeight;


/**
 * holds the default images with indexes
 * User can change the image of the turtle
 * programmatically by specifying the index of 
 * a ShapeEntry
 * @author TNK
 *
 */
public class ShapesView extends UIView {

	private ResourceBundle _resources;
	private UIMainHandler _handler;
	private Map<Double, ShapeEntry> _shapes = new HashMap<Double, ShapeEntry>();

	public ShapesView(Frame frame, UIMainHandler handler,ResourceBundle resources) {
		super(frame);
		_handler = handler;
		_resources = resources;
		setupViews();
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, getBounds());
		setupTitle();
		setupDefaultShapes();
	}
	
	private void setupTitle() {
		Label l = GUITools.plainLabel(_resources.getString("ShapeTitle"), 20, MyColors.INDIGO, FontWeight.NORMAL);
		l.setPrefHeight(40);
		l.setPrefWidth(getBounds().getWidth());
		l.setAlignment(Pos.CENTER);
		this.getChildren().add(l);
	}

	public void addShape(double index, Image image, Frame frame){
		ShapeEntry shape = new ShapeEntry(frame, image,""+ (int) index);
		_shapes.put(index, shape);
		shape.setOnMouseClicked(e -> _handler.setTurtleImage(shape.getImage()));
		this.getChildren().add(shape);
	}

	private void setupDefaultShapes() {
		double inset = 8;
		double y = 40;
		double width = (getBounds().getWidth() - 4*inset)/3;
		double height = getBounds().getHeight() - 48;
		addShape(0, new Image("turtle.png"), new Frame(inset, y, width, height));
		addShape(1, new Image("pacman.png"), new Frame(2*inset + width, y, width, height));
		addShape(2, new Image("devil.png"), new Frame(3*inset + 2*width, y, width, height));
		
	}

	public Image getImage(double index) {
		return this._shapes.get(index).getImage();
	}
}
