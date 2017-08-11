package gui;

import gui.API.ButtonControlHandler;
import gui.tools.Frame;
import gui.tools.ImageButton;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

/**
 * Purpose of this class is to provide animation control
 * Contains the Play, Pause, and Stop button,and Animation Speed Slider 
 * @author TNK
 *
 */
public class UIButtonControlView extends UIView{
	private ImageButton _pauseButton;
	private ImageButton _playButton;
	private ImageButton _stopButton;
	private Slider _speedSlider;
	private ButtonControlHandler _handler;
	public UIButtonControlView(Frame frame, ButtonControlHandler handler){
		super(frame);
		_handler = handler;
		setupViews();
	}
	private void setupViews() {
		//GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, _bounds);
		this.setBorder(new Border(new BorderStroke[]{ 
				new BorderStroke(MyColors.GREEN_100, BorderStrokeStyle.SOLID,
						new CornerRadii(3), new BorderWidths(1.5))
				}));
		setupButton(_playButton, 16, "play.png",new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				play();
			}
		});
		setupButton(_pauseButton, 60, "pause.png",new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				pause();
			}
		});
		setupButton(_stopButton, 104, "stop.png",new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				stop();
			}
		});
		setupSlider();
	}
	private void setupSlider() {
		_speedSlider = new Slider();
		_speedSlider.setMin(1);
		_speedSlider.setMax(11);
		_speedSlider.setValue(4);
		_speedSlider.setShowTickLabels(true);
		_speedSlider.setShowTickMarks(true);
		_speedSlider.setMajorTickUnit(2);
		_speedSlider.setMinorTickCount(1);
		_speedSlider.setPrefWidth(getBounds().getWidth()/2 - 8);
		_speedSlider.setPrefHeight(getBounds().getHeight() - 16);
		_speedSlider.setLayoutY(8);
		_speedSlider.setLayoutX(getBounds().getWidth()/2);
		_speedSlider.setBackground(Background.EMPTY);
		_speedSlider.setBorder(Border.EMPTY);
		_speedSlider.setOnMouseExited(e -> _handler.handleNewSpeed(_speedSlider.getValue() * 100));
		this.getChildren().add(_speedSlider);
	}
	private void setupButton(ImageButton b, double insetX, 
			String imgName, EventHandler<? super MouseEvent> event){
		b = new ImageButton();
		b.updateImages(new Image(imgName), new Image(imgName));
		b.setLayoutX(insetX);
		b.setLayoutY(2);
		b.setPrefWidth(32);
		b.setPrefHeight(32);
		b.setOnMouseClicked(event);
		this.getChildren().add(b);
	}
	private void play(){
		_handler.handlePlay();
	}
	private void pause(){
		_handler.handlePause();
	}
	private void stop(){
		_handler.handleStop();
	}
	
}
