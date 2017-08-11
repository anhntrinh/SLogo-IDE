package gui.menu;

import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Holds palettes with colors.
 * User can use the index of the palette to change the line color or background color
 * @author TNK
 *
 */
public class PalletteView extends UIView {

	private Map<Double, Palette> _palettes = new HashMap<Double, Palette>();
	private ResourceBundle _resources;

	public PalletteView(Frame frame,ResourceBundle resources) {
		super(frame);
		_resources = resources;
		setupViews();
	}

	private void addNewPalette(double index, Color color, Frame frame) {
		_palettes.put(index, new Palette(color, ""+ (int) index,frame));
		this.getChildren().add(_palettes.get(index));
	}

	public void setPalleteColorAtIndex(Double index, Color color) {
		_palettes.get(index).setColor(color);
	}

	private void setupViews() {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, getBounds());
		setupTitle();
		setupDefaultPalettes();
		
	}
	
	private void setupDefaultPalettes() {
		double width = getBounds().getWidth() - 12;
		double height = (getBounds().getHeight() - 40)/4 - 6;
		double y = 40;
		addNewPalette(0.0, MyColors.GREEN_900, new Frame(6,y, width, height));
		addNewPalette(1.0, MyColors.GREEN_100,new Frame(6,y + 1*(height + 6), width, height));
		addNewPalette(2.0, MyColors.AMBER,new Frame(6,y + 2*(height + 6), width, height));
		addNewPalette(3.0, MyColors.INDIGO,new Frame(6,y + 3*(height + 6), width, height));
		//addNewPalette(5.0, MyColors.GREEN_200,new Frame(6,y + 4*(height + 6), width, height));
		
	}

	private void setupTitle(){
		Label l = GUITools.plainLabel(_resources.getString("PaletteTitle"), 18, MyColors.INDIGO, FontWeight.LIGHT);
		l.setPrefHeight(40);
		l.setPrefWidth(getBounds().getWidth());
		l.setAlignment(Pos.CENTER);
		this.getChildren().add(l);
	}

	public Color getPalette(double index) {
		return this._palettes.get(index).getColor();
	}
	
}


