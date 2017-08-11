package gui.menu;

import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;


/**
 * Holds palettes with colors.
 * User can use the index of the palette to change the line color or background color
 * @author TNK
 *
 */
final class Palette extends UIView {

	private Color _color;
	private ColorPicker _picker;
	private Label _label;

	public Palette(Color color, String text, Frame frame) {
		super(frame);
		_color = color;
		_picker = new ColorPicker(color);
		_picker.setLayoutX(0);
		_picker.setLayoutY(0);
		_picker.setPrefHeight(getBounds().getHeight());
		_picker.setPrefWidth(getBounds().getWidth() * 2. / 3.);
		_picker.setBackground(Background.EMPTY);
		_picker.setOnAction(e -> setColor(_picker.getValue()));
		_label = new Label(text);
		_label.setLayoutX(getBounds().getWidth() * 2. / 3.);
		_label.setLayoutY(0);
		_label.setPrefHeight(getBounds().getHeight());
		_label.setPrefWidth(getBounds().getWidth() / 3.0);
		_label.setAlignment(Pos.CENTER);
		this.getChildren().add(_picker);
		this.getChildren().add(_label);
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_200, getBounds());
	}

	public Color getColor() {
		return _color;
	}

	public void setColor(Color color) {
		_color = color;
		_picker.setValue(color);
	}

}
