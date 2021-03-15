package com.vaadin.tutorial.crm.views.login;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | Vaadin CRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private LoginForm login = new LoginForm();

    public LoginView(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        Button updateI18nButton = new Button("Switch to Spanish",
                event -> login.setI18n(createSpanish()));


        login.setAction("login");
        H1 title = new H1("Vaadin CRM");
        Icon icon = VaadinIcon.VAADIN_H.create();
        icon.setSize("30px");
        icon.getStyle().set("margin-left", "5px");
        icon.getStyle().set("top", "-4px");

        title.add(icon);

        add(title, login, updateI18nButton);
    }


    private LoginI18n createSpanish() {
        final LoginI18n i18n = LoginI18n.createDefault();

        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setDescription("Introduzca usuario valido");
        i18n.getForm().setUsername("Usuario");
        i18n.getForm().setTitle("Iniciar Sesion");
        i18n.getForm().setSubmit("Entrar");
        i18n.getForm().setPassword("Contrasena");
        i18n.getForm().setForgotPassword("Olvido contrasena");
        i18n.getErrorMessage().setTitle("Usuario/Contrasena invalidos");
        i18n.getErrorMessage()
                .setMessage("Confirme los datos suministrados nuevamente.");
        i18n.setAdditionalInformation(
                "Bienvenido a Vaadin");
        return i18n;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
