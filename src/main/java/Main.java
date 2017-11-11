import javafx.application.Application;
import javafx.stage.Stage;
import ui.UserInterface;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new UserInterface(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
