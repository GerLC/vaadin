package com.vaadin.tutorial.crm.views.dashboardhire;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.views.main.MainView;

@CssImport("./views/about/about-view.css")
@Route(value = "dashboard", layout = MainView.class)
@PageTitle("Dashboard | Hirespace")
public class DashboardView extends Div {

    public DashboardView() {

    }

}
