import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		Controller controller = new Controller();
		s.setScene(controller.getViewController().getScene());
		s.setTitle("SLOGO");
		s.setResizable(false);
		s.show();
	}
}
