package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;
import javafx.scene.control.TextField;

public class ClientController {
    private ClientMain main ;
    @FXML
    private TextField clientName ;

    @FXML
    private Button connectButton;

    String serverAddress="127.0.0.1";
    int serverPort=33333;
    @FXML
    public void ConnectButton(ActionEvent event){
        try {
            NetworkUtil nc = new NetworkUtil(serverAddress,serverPort);
            //String s =clientName.getText();
            nc.write(clientName.getText());
            new ReadThread(nc);

            main.startMessegePage(clientName.getText());
        } catch(Exception e) {
            //System.out.println (e);
            e.printStackTrace();
        }
    }

    public void setMain(ClientMain c)
    {
        this.main=c ;
    }
}
