package app.frontend.ui.staff;


import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class StaffSearchDialog extends Dialog {

    public TextField selectedStaffTextField;
    public TextField selectedStaffId;
    public TextField colorLabel ;

    public StaffSearchDialog(TextField selectedStaffTextField, TextField selectedStaffId, TextField colorLabel ) {
        this.selectedStaffTextField = selectedStaffTextField;
        this.selectedStaffId = selectedStaffId;
        this.colorLabel = colorLabel;

        init();
    }

    public void init() {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        setWidth("500px");

        VerticalLayout staff = StaffComponent.create()
                .setSearchDialog(this)
                .build();

        add(staff);

    }


}
