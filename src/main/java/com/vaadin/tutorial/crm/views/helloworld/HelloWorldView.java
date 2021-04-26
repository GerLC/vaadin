package com.vaadin.tutorial.crm.views.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.tutorial.crm.views.main.MainView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CssImport("./views/helloworld/hello-world-view.css")
@Route(value = "hello", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Hello World")
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

        Button button = new Button("Navigate to my view");
        button.addClickListener( e-> {
            Map<String, List<String>> parameters = new HashMap();
            parameters.put("param", Arrays.asList("value", name.getValue()));
            QueryParameters queryParameters = new QueryParameters(parameters);
            button.getUI().ifPresent(ui -> ui.navigate("http://localhost:8080/aplicants", queryParameters));
        });

        add(button);
    }

}
