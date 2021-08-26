package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ClientMain extends Application {
    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        showHomePage();
    }

    public void showHomePage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Client.fxml"));
        Parent root = loader.load();

        ClientController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("         Client Log In Page ");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void startMessegePage (String CName) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ClientMessege.fxml"));
        Parent root = loader.load();

        ClientMessegeController controller = loader.getController();
        controller.setClientMain(this);

        stage.setTitle(CName);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
