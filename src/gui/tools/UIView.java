package gui.tools;

import javafx.scene.layout.Pane;

/**
 * 
 * GUI objects inside a UIView can access frame and bounds to determine
 * where to locate themselves
 * @author TNK
 *
 */
public class UIView extends Pane{
	
	private Frame _frame;
	private Frame _bounds;
	
	public UIView(){
		super();
		_frame = new Frame(0,0,0,0);
		_bounds = new Frame(0,0,0,0);
	}
	
	public UIView(Frame frame){
		super();
		_bounds = frame.toLocalBounds();
		setFrame(frame);
	}
	
	public void setFrame(Frame frame){
		this.setLayoutX(frame.getX());
		this.setLayoutY(frame.getY());
		this.setPrefWidth(frame.getWidth());
		this.setPrefHeight(frame.getHeight());
	}

	public Frame getFrame() {
		return _frame;
	}
	public Frame getBounds(){
		return _bounds;
	}
	
	
}
