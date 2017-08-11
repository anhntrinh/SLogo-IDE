package gui.menu;

import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShapeEntry extends UIView {
	
	private Label _label;
	private ImageView _imageView;
	public ShapeEntry(Frame frame, Image image, String text){
		super(frame);
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_200, getBounds());
		_label = new Label(text);
		_label.setLayoutX(0);
		_label.setLayoutY(48);
		_label.setPrefWidth(getBounds().getWidth());
		_label.setAlignment(Pos.CENTER);
		_imageView = new ImageView(image);
		_imageView.setLayoutX((getBounds().getWidth() - 32)/2);
		_imageView.setLayoutY(8);
		_imageView.setFitWidth(32);
		_imageView.setFitHeight(32);
		this.getChildren().add(_label);
		this.getChildren().add(_imageView);
	}

	public Image getImage() {
		return _imageView.getImage();
	}
	
	public void setImage(Image image){
		_imageView.setImage(image);
	}
	
}
