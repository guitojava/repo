package app.frontend.ui.staff;


import app.backend.SystemSettings;
import app.backend.appointment.AppointmentService;
import app.backend.staff.Staff;
import app.backend.staff.StaffService;
import app.frontend.custom.confirm.ConfirmNotification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaffDialog extends Dialog {


    private static final String[] COLORS = {
            "LemonChiffon",
            "Bisque",
            "LightCyan",
            "LightSalmon",
            "LightSkyBlue",
            "PaleGreen",
            "OrangeRed",
            "Orange",
            "LightPink",
            "Khaki",
            "Violet"
    };

    TextField colorlable = new TextField();



    private Grid<Staff> grid;
    ListDataProvider<Staff> dataProvider;
    private Staff staff;
    boolean newInstance;

    private StaffService staffService = new StaffService();
    private AppointmentService appointmentService = new AppointmentService();

    public StaffDialog(Grid<Staff> grid, ListDataProvider<Staff> dataProvider, Staff staff, boolean newInstance) {
        this.grid = grid;
        this.dataProvider = dataProvider;
        this.staff = staff;
        this.newInstance = newInstance;
        init();
    }

    public void init() {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        setWidth("500px");

        colorlable.getStyle().set("background-color",   "Bisque");


        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
        layout.setSizeFull();

        TextField fullName = new TextField("FullName");
        fullName.focus();


        ComboBox<String> color = new ComboBox<>("Color", COLORS);
        color.setValue("Bisque");
        color.addValueChangeListener(e->{
            colorlable.getStyle().set("background-color", color.getValue()  );
        });

        color.setRequired(true);

        TextField phone = new TextField("Phone");
        TextField email = new TextField("Email");
        DatePicker dateOfBirth = new DatePicker("DateOfBirth");
        dateOfBirth.setLocale(SystemSettings.LOCALE);
        dateOfBirth.setInitialPosition(LocalDate.now().minusYears(20));

        TextArea notes = new TextArea("Notes");
        notes.setMinLength(150);

        layout.add(fullName, colorlable, color, phone, email, dateOfBirth, notes);

        Binder<Staff> binder = new Binder<>(Staff.class);
        binder.forField(fullName).asRequired().bind(Staff::getFullName, Staff::setFullName);
        binder.bind(phone, Staff::getPhone, Staff::setPhone);
        binder.bind(email, Staff::getEmail, Staff::setEmail);
        binder.bind(color, Staff::getColor, Staff::setColor);
        binder.bind(dateOfBirth, Staff::getDateOfBirth, Staff::setDateOfBirth);
        binder.bind(notes, Staff::getNotes, Staff::setNotes);

        binder.setBean(staff);

        HorizontalLayout buttons = new HorizontalLayout();
        Button buttonSave;
        this.addDialogCloseActionListener(e -> {
            updateAndClose(staff);
        });


        if (newInstance) {
            buttonSave = new Button("Create", e -> {
                if (binder.validate().isOk()) {
                    Staff staff = new Staff();
                    staff.setId(UUID.randomUUID().toString());
                    staff.setFullName(fullName.getValue().trim());
                    staff.setColor(color.getValue().trim());
                    staff.setPhone(phone.getValue().trim());
                    staff.setEmail(email.getValue().trim());
                    staff.setDateOfBirth(dateOfBirth.getValue());
                    staff.setNotes(notes.getValue().trim());
                    create(staff);
                }
            });
        } else {
            buttonSave = new Button("Save", e -> {
                if (binder.validate().isOk()) {
                    update(staff);
                }
            });
        }


//        buttonSave.addClickShortcut(Key.PAGE_DOWN);
        buttonSave.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        buttonSave.addClickListener(e -> close());
        buttons.add(buttonSave);

        Button buttonCancel = new Button("Cancel", e -> close());
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        buttons.add(buttonCancel);

        if (!newInstance) {
            Button buttonRemove = new Button("Delete", e -> {
                ConfirmNotification c = new ConfirmNotification("Delete staff "+ staff.getFullName()  + "?") {

                    public void yesAction(ConfirmNotification obj) {
                        deleteAndClose(staff);
                        obj.close();
                    }
                };
                c.open();
            });
            buttonRemove.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
            buttonRemove.getStyle().set("margin-left", "auto");
            buttons.add(buttonRemove);
        }

        add(buttons, layout);

    }

    private void create(Staff staff) {
        staffService.save(staff);
        reload(staff);
    }

    private void update(Staff staff) {
        staffService.update(staff);
        reload(staff);
    }

    private void reload(Staff staff) {
        List<Staff> staffList = new ArrayList<>();
        staffList.add(staff);
        dataProvider = new ListDataProvider<>(staffList);
        grid.setDataProvider(dataProvider);
        grid.getDataProvider().refreshAll();
    }

    private void updateAndClose(Staff staff) {
        update(staff);
        close();
    }

    private void deleteAndClose(Staff staff) {
        appointmentService.unassignByStaffId(staff.getId());
        staffService.delete(staff);
        close();
        reload(staff);
    }

}
