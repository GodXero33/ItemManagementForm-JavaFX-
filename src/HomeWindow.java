import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeWindow extends Application {
	public static void main (String[] args) {
		Application.launch(args);
	}

	@Override
	public void start (Stage stage) throws IOException {
		final Scene scene = new Scene(FXMLLoader.load(this.getClass().getResource("view/home_view.fxml")));

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
