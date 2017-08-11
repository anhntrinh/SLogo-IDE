package gui.tableviews;

import java.util.List;
import java.util.ResourceBundle;

import gui.API.UIMainHandler;
import gui.tools.Frame;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class UIVocabTable extends UITableView {
	
	private UIMainHandler _handler;
	ResourceBundle _resources;
	String[] _initialFunctions = {
		"set :distance 10\n\nrepeat 18 \n[ \n\tforward * 5 :distance\n\tright 150\n\tforward * 6 :distance\n\tright 100\n\tforward * 3 :distance\n\tright 90\n]",
		"dotimes [ :dist 200 ] \n[\n\tfd :dist\n\trt 89\n]"
	};
	
	public UIVocabTable(Frame bounds, UIMainHandler handler,ResourceBundle resources) {
		super(bounds, resources.getString("FunctionsTitle"));
		_handler = handler;
		for(int i =0; i<_initialFunctions.length; i++){
			addText(_initialFunctions[i]);
		}
	}
	
	@Override
	protected Text addText(String s){
		super.addText(s);
		Text t = getTexts().get(getTexts().size() - 1); //were assuming that super added text to list
		t.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				_handler.addFunctionToTerminal(t.getText());
			}
		});
		return t;
	}
	
	public void setFunctionBox(List<String> functions){
		clear();
		for(String s: this._initialFunctions){
			addText(s);
		}
		for(String s: functions){
			this.addText(s);
		}
	}

}
