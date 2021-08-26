package sample;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private final SimpleStringProperty name;

    Person(String pName) {
        name = new SimpleStringProperty(pName);
    }

    public String getName() {
        return name.get();
    }
    public void setFirstName(String fName) {name.set(fName);
    }



    public String toString() {
        return name+"" ;
    }

}



