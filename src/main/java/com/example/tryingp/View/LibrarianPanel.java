package com.example.tryingp.View;

import javafx.scene.layout.BorderPane;
import com.example.tryingp.Controllers.Controller;
import com.example.tryingp.Models.Person;

public class LibrarianPanel extends BorderPane {

    private Person person;
    public LibrarianPanel() {

        BillView billView = new BillView(Controller.books);
        billView.setUser(person);
        setCenter(billView);

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
