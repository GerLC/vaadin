package com.vaadin.tutorial.crm.views.list;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Company;
import com.vaadin.tutorial.crm.backend.entity.Contact;
import com.vaadin.tutorial.crm.backend.service.CompanyService;
import com.vaadin.tutorial.crm.backend.service.ContactService;
import com.vaadin.tutorial.crm.views.main.MainView;

@Route(value = "contacts", layout = MainView.class)
@PageTitle("Contacts")
public class ListView extends VerticalLayout {

    //Components
    private Grid<Contact> grid = new Grid<>(Contact.class);
    TextField filter = new TextField("Filter by name");

    // Services
    private ContactService contactService;
    private CompanyService companyService;

    ContactForm contactForm;

    public ListView(ContactService contactService, CompanyService companyService) {
        this.contactService = contactService;
        configureGrid();
        configureFilter();
        contactForm = new ContactForm(companyService.findAll());

        addClassName("list-view");
        setSizeFull();

        Div content = new Div();
        content.add(grid, contactForm);
        content.addClassName("content");
        content.setSizeFull();

        add(filter, content );

        updateList();
    }

    private void configureGrid() {
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            Company company = contact.getCompany();
            return  company == null ? "---" : company.getName();
        }).setHeader("Company");
        
        grid.setSizeFull();
        grid.addClassName("contact-grid");
        grid.getColumns().forEach(contactColumn -> contactColumn.setAutoWidth(true));

    }

    private void configureFilter() {
        filter.setClearButtonVisible(true);
        filter.addThemeVariants(TextFieldVariant.LUMO_HELPER_ABOVE_FIELD);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());
    }

    private void updateList() {
        grid.setItems(contactService.findAll(filter.getValue()));
    }

    private void editContact(Contact contact) {
        if (contact == null) {
            closeEditor();
        } else {
            contactForm.setContact(contact);
            contactForm.setVisible(true);
            contactForm.addClassName("editing");
        }
    }

    private void closeEditor() {
        contactForm.setContact(null);
        contactForm.setVisible(false);
        removeClassName("editing");
    }

}
