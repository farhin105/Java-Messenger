package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class ServerMain extends Application { //extends Application

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TableView.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TableViewController controller = loader.getController();
        controller.initializeColumns();

        //Parent root = FXMLLoader.load(getClass().getResource("TableView.fxml"));
        primaryStage.setTitle("   Server  ");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
