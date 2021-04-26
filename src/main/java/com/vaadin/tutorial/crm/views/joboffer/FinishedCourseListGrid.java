package com.vaadin.tutorial.crm.views.joboffer;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Component
@Route(value = "skills")
public class FinishedCourseListGrid<Course> extends VerticalLayout {

    //Components
    Grid<Course> grid = new Grid<>();


    public FinishedCourseListGrid() {

        configureGrid();
        setSizeFull();
        add(grid);

        updateList();

    }

    private void configureGrid() {
        grid.setSizeFull();

        grid.addColumn(new ComponentRenderer<>(item -> new Div())).setHeader("Course Code");
        grid.addColumn(new ComponentRenderer<>(item -> new Div())).setHeader("Course Name");
        grid.addColumn(new ComponentRenderer<>(item -> new Div())).setHeader("Description");
        grid.addColumn(new ComponentRenderer<>(item -> new Div()));
        grid.addColumn(new ComponentRenderer<>(item -> new Div())).setHeader("Associated Skill");

        grid.asSingleSelect().addValueChangeListener(event -> event.notifyAll());

    }

    private void updateList() {
        grid.setItems();
    }





}
