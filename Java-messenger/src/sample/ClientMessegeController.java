package sample;

import javafx.fxml.FXML;

import java.awt.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ClientMessegeController {
    @FXML
    private TextArea msgClient;

    private ClientMain main;

    public void setMsg(String msg)
    {

        msgClient.setText(msg);

    }

    public void setClientMain(ClientMain c)
    {
        this.main=c;
        ReadThread.setC(this);
    }
}


