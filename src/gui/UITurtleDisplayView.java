package gui;

import java.util.ArrayList;
import java.util.List;

import general_data_structures.Tuple;
import gui.API.DisplayHandler;
import gui.API.TurtleDisplayHandler;
import gui.API.UIDisplayAPI;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.turtle.TurtleState;

/**
 * holds the turtles on display
 * provides the functionality to move turtles graphically
 * @author TNK
 *
 */
public class UITurtleDisplayView extends UIView implements UIDisplayAPI {

	class Handler implements TurtleDisplayHandler {
		@Override
		public void addLineToScreen(Line l) {
			addLine(l);
		}

		@Override
		public Color getColorPalette(double index) {
			return _handler.getColorPalette(index);
		}

		@Override
		public Image getTurtleImage(double index) {
			// TODO Auto-generated method stub
			return _handler.getShape(index);
		}
		
	}

	private List<UITurtle> _turtles = new ArrayList<UITurtle>();
	private List<Line> _lines = new ArrayList<Line>();
	double _strokeWidth = 2.5;
	private Rectangle _background;
	private DisplayHandler _handler;

	public UITurtleDisplayView(Frame frame, DisplayHandler handler) {
		super(frame);
		_handler = handler;
		setupViews();
		setupMouseControl();

	}

	private void setupMouseControl() {
		setOnMouseDragged(mouseHandler);
		setOnMousePressed(mouseHandler);
		setOnMouseReleased(mouseHandler);

	}

	public void resetDisplay() {
		clearLines();
	}
	/**
	 * adds a new turtle to display with the given TurtleState
	 * 
	 * @param state
	 * @return returns UITurtle so that the Controller class can add the turtle as an
	 * observer to TurtleState
	 */
	public UITurtle addTurtle(TurtleState state) {
		TranslateTransition tran = new TranslateTransition();
		RotateTransition rot = new RotateTransition();
		Tuple<TranslateTransition, RotateTransition> animators = new Tuple<TranslateTransition, RotateTransition>(tran,
				rot);

		UITurtle t = new UITurtle(animators, new Handler(), getBounds(), state);

		tran.setNode(t);
		tran.setDuration(Duration.millis(200));

		rot.setNode(t);
		rot.setDuration(Duration.millis(200));
		
		_turtles.add(t);
		this.getChildren().add(t);
		
		return t;
	}

	public void setTurtleImage(Image image) {
		for (UITurtle t : _turtles) {
			t.setImageView(new ImageView(image));
		}
	}

	public UITurtle getTurtle(Double id) {
		return _turtles.stream().filter(turtle -> turtle.getTurtleId() == id).findFirst().orElse(null);
	}

	public void setPenWidth(double width) {
		for (UITurtle t : _turtles) {
			t.setPenStrokeWidth(width);
		}

	}

	public void setBackgroundColor(Color c) {
		_background.setFill(c);
	}

	public void setPenColor(Color c) {
		for (UITurtle t : _turtles) {
			t.setLineColor(c);
		}
	}

	public void stop() {
		for (UITurtle t : _turtles) {
			t.stop();
		}
	}

	public void pause() {
		for (UITurtle t : _turtles) {
			t.pause();
		}
	}

	public void play() {
		for (UITurtle t : _turtles) {
			t.play();
		}
	}


	private void addLine(Line l) {
		_lines.add(l);
		this.getChildren().add(1, l);
	}
	
	private void removeLine(Line l){
		this.getChildren().remove(l);
	}

	private void clearLines() {
		this.getChildren().removeAll(_lines);
		_lines.clear();
	}

	private void setupViews() {
		_background = GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, getBounds());
		this.setClip(new Rectangle(getBounds().getWidth(), getBounds().getHeight()));
		for (UITurtle t : _turtles) {
			this.getChildren().add(t);
		}
	}

	// MOUSE CONTROLS
	/**
	 * Purpose is to allow user to drag and drop the turtles on screen
	 */
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
		private UITurtle _selectedTurtle;
		private Line _line;

		@Override
		public void handle(MouseEvent mouseEvent) {
			if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED){
				System.out.println("Mouse clicked");
				UITurtle t = detectTurtle(mouseEvent.getX(), mouseEvent.getY());
				t.getTurtleState().setVisibility(!t.getTurtleState().getVisibility());
			}
			if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
				UITurtle t = detectTurtle(mouseEvent.getX(), mouseEvent.getY());
				_selectedTurtle = t;
				_line = new Line(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getX(), mouseEvent.getY());
				_line.setFill(MyColors.INDIGO);
				_line.setStrokeWidth(1);
				addLine(_line);
			} else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				if (_line != null) {
					_line.setEndX(mouseEvent.getX());
					_line.setEndY(mouseEvent.getY());
				}
			} else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
				if (_selectedTurtle != null) {
					System.out.println("moving turtle");
					Tuple<Double, Double> newPos = new Tuple<Double, Double>(mouseEvent.getX() - 16, mouseEvent.getY() - 16);
					
					TurtleState newState = GUITools.guiTurtleToTurtleState(90.0,newPos, getBounds());
					updateTurtleStatePrimitives(_selectedTurtle.getTurtleState(), newState);
				}
				removeLine(_line);
				_selectedTurtle = null;
				_line = null;
			}
		}
	};
	/**
	 * 
	 * @param x
	 * @param y
	 * @return true if there is a turtle at (x,y) 
	 */
	private UITurtle detectTurtle(double x, double y) {
		for(UITurtle t: _turtles){
			if(t.getBoundsInParent().contains(x, y))
				return t;
		}
		return null;
	}

	public void setSpeed(double speed) {
		for(UITurtle t: _turtles){
			t.setSpeed(speed);
		}
	}
	/**
	 * Purpose is to change the pos and angle of the current TurtleState to the new TurtleState
	 * @param turtleState
	 * @param newState
	 * 
	 */
	private void updateTurtleStatePrimitives(TurtleState turtleState, TurtleState newState) {
		turtleState.setAll((newState.getX()),(newState.getY()), newState.getHeadAngle());
	}

	
	
}
