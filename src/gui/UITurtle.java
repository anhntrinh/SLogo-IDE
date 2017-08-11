package gui;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import general_data_structures.Tuple;
import gui.API.TurtleDisplayHandler;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.TurtleAnimationData;
import gui.tools.UITurtleAttributes;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import model.turtle.TurtleState;
/**
 * The turtle that is seen by the user
 * Contains reference to TurtleState from the backend
 * Contains animators for translating and rotating turtle
 * Provides the framework for sequentially executing commands
 * @author TNK
 *
 */
public class UITurtle extends Pane implements Observer {

	private TurtleState _turtleState;
	private UITurtleAttributes _turtleAtt;
	private UITurtleAttributes _priorTurtleAtt;
	private Tuple<TranslateTransition, RotateTransition> _animators;
	private LinkedList<TurtleAnimationData> _queue = new LinkedList<TurtleAnimationData>();
	private Color _lineColor = MyColors.GREEN_900;
	private double _shapeIndex;
	private double _id;
	private double _strokeWidth = 3;
	private ImageView _imageView;
	private TurtleDisplayHandler _handler;
	private Frame _displayBounds;

	private double ANIMATION_SPEED = 400;// in pixels per second
	public final double MAX_SPEED = 1000;

	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators, TurtleDisplayHandler handler,
			Frame displayBounds, TurtleState state) {
		this(animators, new Image("turtle.png"),state, handler, displayBounds);
	}
	
	/**
	 * UNIMPLEMENTED
	 * This event handler should allow the user to right click the turtle
	 * and toggle attributes such as Penup/Pendown and Visibility
	 */
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			if(e.getButton() == MouseButton.SECONDARY){
				
			}
		}
		
	};

	public UITurtle(Tuple<TranslateTransition, RotateTransition> animators, Image image, TurtleState state,
			TurtleDisplayHandler handler, Frame displayBounds) {
		_turtleState = state;
		_handler = handler;
		_displayBounds = displayBounds;
		setImageView(new ImageView(image));
		setupAnimators(animators);
		this.setTurtleState(getTurtleState());
	}
	
	private void setupAnimators(Tuple<TranslateTransition, RotateTransition> animators){
		_animators = animators;
		_animators.x.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				playNextAnimation();
			}
		});
		_animators.y.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				playNextAnimation();
			}
		});
	}

	public void setImageView(ImageView imageView) {
		if (_imageView != null)
			this.getChildren().remove(_imageView);
		_imageView = imageView;
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(32);
		imageView.setFitWidth(32);
		this.getChildren().add(imageView);
	}

	public void addAnimationToQueue(double angle, Tuple<Double, Double> pos) {
		_queue.add(new TurtleAnimationData(angle, pos));
		if (!Animation.Status.RUNNING.equals(_animators.x.getStatus())
				&& !Animation.Status.RUNNING.equals(_animators.y.getStatus())) {
			playNextAnimation();
		}
	}
	/**
	 * Pops a TurtleAnimationData and and plays it
	 */
	private void playNextAnimation() {
		TurtleAnimationData next = _queue.poll();
		if (next != null) {
			play(next.getAngle(), next.getPosition());
		}
	}

	private void play(double angle, Tuple<Double, Double> pos) {
		//first adds line
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(pos.x, pos.y, angle);
		if (getPriorAttributes() != null && getTurtleState().getPen()) {
			addLine();
		}
		// setting the angle was a complicated process but i don't see any
		// easier way
		// to mathematically ensure that the turtles angle on display is in sync
		// with the
		// backend's coordinate system. RotationAnimation also adds a few
		// glitches.
		double deltaX = _turtleAtt.getX() - _priorTurtleAtt.getX();
		double deltaY = _turtleAtt.getY() - _priorTurtleAtt.getY();
		Tuple<Double, Double> angle1 = new Tuple<Double, Double>(_turtleAtt.getAngle() - _priorTurtleAtt.getAngle(),
				Math.abs(_turtleAtt.getAngle() - _priorTurtleAtt.getAngle()));
		Tuple<Double, Double> angle2 = new Tuple<Double, Double>(360 + _priorTurtleAtt.getAngle() - _turtleAtt.getAngle(),
				Math.abs(360 + _priorTurtleAtt.getAngle() - _turtleAtt.getAngle()));
		// This line right here sets deltaAngle to the angle with the closest
		// value
		// to zero while still preserving the angles direction (positive or
		// negative)
		double deltaAngle = angle1.y == Math.min(angle1.y, angle2.y) ? angle1.x : angle2.x;
		//If animation speed is too much, then don't animate and directly move turtle
		if(ANIMATION_SPEED > MAX_SPEED){
			setTurtleState(getTurtleState());
		}
		else{
			animateTurtle(deltaX, deltaY, deltaAngle);
		}
		
	}
	
	public void animateTurtle(double deltaX, double deltaY, double deltaAngle){
		if (Math.abs(deltaY) + Math.abs(deltaX) != 0) {
			_animators.x.setByX(deltaX);
			_animators.x.setByY(deltaY);
			_animators.x.setDuration( // 1 pixels per 10 millisecond
					Duration.millis(1000 / ANIMATION_SPEED * (Math.abs(deltaX) + Math.abs(deltaY)) + 1));
			_animators.x.play();
		}
		if (deltaAngle != 0) {
			_animators.y.setByAngle(deltaAngle); // 1 ms per degree
			_animators.y.setDuration(Duration.millis(Math.abs(deltaAngle)*300/ANIMATION_SPEED));//(400/ANIMATION_SPEED) is just some arbitrary constant
			_animators.y.play();
		}
	}

	private void addLine() {
		UITurtleAttributes old = getPriorAttributes();
		UITurtleAttributes curr = getNewAttributes();
		if (old != null && getTurtleState().getPen()) {
			double ins = getWidth() / 2.;
			Line line = new Line(old.getX() + ins, old.getY() + ins, curr.getX() + ins, curr.getY() + ins);
			line.setStroke(_lineColor);
			line.setStrokeWidth(_strokeWidth);
			line.setOpacity(0.5);
			_handler.addLineToScreen(line);
		}
	}
	
	/**
	 * sets TurtleState and updates the graphical components of the UITurtle to 
	 * reflect the TurtleState
	 * @param s
	 */
	public void setTurtleState(TurtleState s) {
		setTurtleAttributes(GUITools.turtleCoordinateToPixelCoordinate(s, _displayBounds),(-s.getHeadAngle() + 90));
		this._turtleState = s;
		this.setLayoutX(_turtleAtt.getX());
		this.setLayoutY(_turtleAtt.getY());
		this.setWidth(32);
		this.setHeight(32);
		this.setRotate(_turtleAtt.getAngle());
	}
	
	private void setTurtleAttributes(Tuple<Double, Double> pos, double angle){
		_priorTurtleAtt = _turtleAtt;
		_turtleAtt = new UITurtleAttributes(pos.x, pos.y,angle);
	}

	public void reset() {
		stop();
	}

	public TurtleState getTurtleState() {
		return _turtleState;
	}

	public UITurtleAttributes getPriorAttributes() {
		return _priorTurtleAtt;
	}

	public UITurtleAttributes getNewAttributes() {
		return _turtleAtt;
	}

	public void setTurtleImage(Image img) {
		_imageView.setImage(img);
	}

	public void setVisiblityTo(boolean b) {
		this._imageView.setVisible(b);
	}

	public void setLineColor(Color color) {
		this._lineColor = color;
	}

	public void setPenStrokeWidth(double d) {
		this._strokeWidth = d;
	}

	public void setPenVisibility(boolean bool) {
		this.getTurtleState().setPen(bool);
	}

	/**
	 * Updates turtle attributes like position, angle, visibility, etc.
	 * Adds the changes in position and angle to the animation queue
	 */
	@Override
	public void update(Observable o, Object arg) {
		TurtleState newState = (TurtleState) o;
		if( this._lineColor != _handler.getColorPalette(newState.getPenColorIndex())){
			this.setLineColor(_handler.getColorPalette(newState.getPenColorIndex()));
		}else if(_shapeIndex != newState.getShapeIndex()){
			this.setTurtleImage(_handler.getTurtleImage(newState.getShapeIndex()));
			_shapeIndex = newState.getShapeIndex();
		}else if(this._strokeWidth != newState.getPenSize()){
			_strokeWidth = newState.getPenSize();
		}
		else{
			addAnimationToQueue(-newState.getHeadAngle() + 90, GUITools.turtleCoordinateToPixelCoordinate(newState, _displayBounds));
		}
	}
	
	//UNIMPLEMENTED
	//This method should stop the turtle and empty the animation queue.
	//Current problem is that the animation stops but the TurtleState is out
	//of sync with the display state.
	public void stop() {
		pause();
		//emptyAnimationQueue
		//setTurtleStateToDisplayState
	}
	private void setTurtleStateToDisplayState(){
		this.setTurtleState(GUITools.guiTurtleToTurtleState(
				this._turtleAtt.getAngle(),
				new Tuple<Double, Double>(this._turtleAtt.getX(), this._turtleAtt.getY()),
				_displayBounds));
	}

	private void emptyAnimationQueue() {
		_queue.clear();
		System.out.println("QUEUE SIZE = " + _queue.size());
	}

	public void pause() {
		this._animators.x.pause();
		this._animators.y.pause();
	}

	public void play() {
		if(Animation.Status.PAUSED.equals(_animators.x.getStatus())){
			this._animators.x.play();
		}
		if(Animation.Status.PAUSED.equals(_animators.y.getStatus())){
			this._animators.y.play();
		}		
	}

	public void setSpeed(double speed) {
		this.ANIMATION_SPEED = speed;
	}

	public double getTurtleId() {
		return _id;
	}

}
