package com.vaadin.tutorial.crm.views.joboffer;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.views.main.MainView;

@Route(value = "Aplicants", layout = MainView.class)
@PageTitle("Aplicants")
public class ListAplicantView extends VerticalLayout {


    Grid<Contact> grid = new Grid<>(Contact.class);

    public ListAplicantView() {

        configureGrid();
        add(grid);
    }


    private void configureGrid() {
        grid.setColumns("firstName", "lastName", "email", "status");

        grid.setSizeFull();
        grid.addClassName("contact-grid");

        grid.asSingleSelect().addValueChangeListener(event -> event.getValue());

    }

}
