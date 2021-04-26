package com.vaadin.tutorial.crm.views.joboffer;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import com.vaadin.tutorial.crm.views.main.MainView;
import org.springframework.stereotype.Component;

@Component
@Route(value = "aplicants", layout = MainView.class)
@PageTitle("Job Offer Aplicant's")
public class AplicantListView extends VerticalLayout implements AfterNavigationObserver {


    Grid<Contact> grid = new Grid<>(Contact.class);
    ContactService contactService;

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        QueryParameters queryParameters = event.getLocation().getQueryParameters();
    }


    public AplicantListView(ContactService contactService) {
        this.contactService = contactService;

        setSizeFull();
        configureGrid();
        add(grid);
        updateGrid();
    }


    private void configureGrid() {

        grid.setColumns("firstName", "lastName", "email", "status");

        grid.addColumn(Contact::getFirstName)
                .setFooter("Total: " +
                        1 +
                        " people");

        grid.setSizeFull();
        grid.addClassName("contact-grid");

    }


    private void updateGrid() {
        grid.setItems(contactService.findAll());
    }

}
