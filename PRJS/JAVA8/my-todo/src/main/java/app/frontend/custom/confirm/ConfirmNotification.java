package app.frontend.custom.confirm;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

abstract public class ConfirmNotification extends Notification {
    public ConfirmNotification(   String message  ) {
        this.setPosition(Position.TOP_CENTER) ;
        this.addThemeVariants(NotificationVariant.LUMO_ERROR);
        Label label = new Label( message );
        label.setWidth( "20px");
        Button noBtn = new Button(" Cancel", e -> this.close());
        noBtn.setWidth(  "200px");
        noBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button yesBtn = new Button("Yes do it ", e ->  yesAction(  this ) );
        yesBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        yesBtn.setWidth(  "200px");
        yesBtn.getElement().getStyle().set("margin-left", "auto");
        noBtn.getElement().getStyle().set("margin-left", "auto");
        VerticalLayout  vLayout = new VerticalLayout();
        vLayout.add(  yesBtn, noBtn);
        this.add( label, vLayout );
    }
    public abstract void yesAction( ConfirmNotification obj);
}
