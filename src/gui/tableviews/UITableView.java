package gui.tableviews;

import java.util.ArrayList;
import java.util.List;
import gui.tools.Frame;
import gui.tools.GUITools;
import gui.tools.MyColors;
import gui.tools.UIView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * provides the basic layout and functionality
 * for UIVariablesView, UIVocabView, and UIAttributesView
 * @author TNK
 *
 */
public abstract class UITableView extends UIView {
	private FlowPane _flowPane;
	private List<Text> _texts = new ArrayList<Text>();
	
	public UITableView(Frame frame, String title) {
		super(frame);
		setupViews(title);

	}
	

	private void setupViews(String title) {
		GUITools.addBackgroundWithColor(this, MyColors.GREEN_100, getBounds());
		setupTitle(title);

		_flowPane = new FlowPane(Orientation.VERTICAL);
		_flowPane.setPadding(new Insets(48, 12, 10, 12));
		_flowPane.setVgap(20);

		
		_flowPane.setBackground(Background.EMPTY);
		_flowPane.setAlignment(Pos.TOP_CENTER);
		_flowPane.setColumnHalignment(HPos.CENTER);

		ScrollPane scroll = new ScrollPane(_flowPane);
		scroll.setBackground(Background.EMPTY);
		scroll.setBorder(Border.EMPTY);
		scroll.setBlendMode(BlendMode.DARKEN);
		scroll.setPrefWidth(getBounds().getWidth());
		scroll.setPrefHeight(getBounds().getHeight());
		
		this.getChildren().add(scroll);
	}
	private void setupTitle(String t){
		Label title = GUITools.plainLabel(t, 20, MyColors.INDIGO, FontWeight.NORMAL);
		title.setPrefWidth(getBounds().getWidth());
		title.setPrefHeight(40);
		title.setAlignment(Pos.CENTER);
		this.getChildren().add(title);
	}
	protected Text addText(String s){
		StackPane stack = new StackPane();
		stack.setBackground(
				new Background(
						new BackgroundFill[] {
								new BackgroundFill(
										MyColors.GREEN_200, new CornerRadii(4), new Insets(-8,-12,-8,-8))}));
		Text l = new Text(s);
		l.setWrappingWidth(getBounds().getWidth() - 48);
		l.setTextAlignment(TextAlignment.LEFT);
		_texts.add(l);
		stack.getChildren().add(l);
		_flowPane.getChildren().add(stack);
		return l;
	}
	
	public void clear(){
		_flowPane.getChildren().clear();
	}
	
	public List<Text> getTexts(){
		return _texts;
	}


}
