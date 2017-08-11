package gui.menu;

import java.util.HashMap;
import java.util.Map;

import gui.tableviews.UITableView;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import javafx.scene.text.Text;
import model.turtle.TurtleState;

/**
 * Displays the current attributes of the turtle
 * @author TNK
 *
 */
public class UIAttributesView extends UITableView{
	private Map<Double, Text> _idToText = new HashMap<Double, Text>();
	public UIAttributesView(Frame frame){
		super(frame, "Turtle Attributes");
		setupViews();
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, getBounds());
	}

	public void update(TurtleState s) {
		if(_idToText.containsKey(s.getID())){
			_idToText.get(s.getID()).setText(turtleStateToString(s));
		}else{
			_idToText.put(s.getID(), addText(turtleStateToString(s)));
		}
	}
	
	private String turtleStateToString(TurtleState s){
		String pen = s.getPen()? "Down":"Up";
		double x = Math.round(s.getX());
		double y = Math.round(s.getY());
		return "ID: "+s.getID()+"\tPosition: (" + x + ", " + y + ")\t"
				+ "\nAngle: " + s.getHeadAngle() + "\tPen: " + pen ;
	}
}
