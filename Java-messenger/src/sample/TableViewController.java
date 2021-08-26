package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class TableViewController implements Runnable {

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TextArea msgBox;


    private ObservableList<Person> data = FXCollections.observableArrayList();


    private ServerSocket ServSock;
    private Hashtable<String, NetworkUtil> table;
    private Thread t;
    private NetworkUtil nc;

    public TableViewController()
    {
        t=new Thread(this);
        t.start();
    }

    public void run()
    {
        table = new Hashtable<>();
        try {
            ServSock = new ServerSocket(33333);
            while (true) {
                Socket clientSock = ServSock.accept();

                nc=new NetworkUtil(clientSock);
                String clientName=(String) nc.read();
                dataTable(clientName);
            }
        }catch(Exception e) {
            System.out.println("Server starts:"+e);
        }

    }
    public void initializeColumns() {

        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());

        TableColumn<Person, String> actionCol = new TableColumn<>("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));

        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory =
                new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
                    @Override
                    public TableCell call( final TableColumn<Person, String> param ) {
                        final TableCell<Person, String> cell = new TableCell<Person, String>() {
                            Button btn = new Button("Send");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                // action of 'Select' button click
                                btn.setOnAction((ActionEvent event) -> {
                                            if (msgBox.getText() != null) {
                                                NetworkUtil nu = table.get(data.get(getIndex()).getName());
                                                if (nu != null) {
                                                    nu.write("Server: " + msgBox.getText());
                                                }
                                                msgBox.setText(null);

                                                if (getIndex() < data.size()) {
                                                    data.remove(getIndex());
                                                }
                                            }
                                        }

                                );
                                if(!empty)
                                {setGraphic(btn);
                                    setText(null);}

                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);
        tableView.getColumns().addAll(nameCol,actionCol);

    }
    public void dataTable(String username) {
        Person person=new Person(username);
        data.add(person);
        tableView.setItems(data);
        if(data.size()!=0) {
            table.put(data.get(data.size() - 1).getName(), nc);}
    }
}
