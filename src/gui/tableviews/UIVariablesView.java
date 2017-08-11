package gui.tableviews;

import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import gui.tools.Frame;

import parser.storage.VariableStorage;

public class UIVariablesView extends UITableView implements Observer {
	ResourceBundle _resources;
	public UIVariablesView(Frame bounds, ResourceBundle resources) {
		super(bounds, resources.getString("VariablesTitle"));
		_resources = resources;
	}
	

	@Override
	public void update(Observable o, Object arg) {
		VariableStorage map = (VariableStorage) o;
		clear();
		for(Entry<String, Double> e: map.getMap().entrySet()){
			addText(e.getKey().replaceFirst(":", "") + " : " + e.getValue());
		}
	}

}
