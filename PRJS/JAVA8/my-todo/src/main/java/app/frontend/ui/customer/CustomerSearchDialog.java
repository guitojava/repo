package app.frontend.ui.customer;


import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CustomerSearchDialog extends Dialog {

    public TextField selectedCustomerTextField;
    public TextField selectedCustomerId;

    public CustomerSearchDialog(TextField selectedCustomerTextField, TextField selectedCustomerId) {
        this.selectedCustomerTextField = selectedCustomerTextField;
        this.selectedCustomerId = selectedCustomerId;

        init();
    }

    public void init() {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        setWidth("500px");

        VerticalLayout customer = CustomerComponent.create()
                .setSearchDialog( this )
                .build();

        add(customer);

    }


}
