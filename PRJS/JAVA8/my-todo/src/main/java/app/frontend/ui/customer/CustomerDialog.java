package app.frontend.ui.customer;


import app.backend.SystemSettings;
import app.backend.appointment.Appointment;
import app.backend.appointment.AppointmentService;
import app.backend.customer.Customer;
import app.backend.customer.CustomerService;
import app.frontend.custom.confirm.ConfirmNotification;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerDialog extends Dialog {

    private final Grid<Customer> grid;
    private ListDataProvider<Customer> dataProvider;
    private final Customer customer;
    boolean newInstance;

    private final CustomerService customerService = new CustomerService();
    private final AppointmentService appointmentService = new AppointmentService();
    private TextField customerTxtField;

    public CustomerDialog(Grid<Customer> grid, ListDataProvider<Customer> dataProvider, Customer customer, boolean newInstance) {
        this.grid = grid;
        this.dataProvider = dataProvider;
        this.customer = customer;
        this.newInstance = newInstance;
        init();
    }

    public CustomerDialog(Customer customer, boolean newInstance, TextField customerTxtField) {
        this.grid = null;
        this.dataProvider = null;
        this.customerTxtField = customerTxtField;
        this.customer = customer;
        this.newInstance = newInstance;
        init();
    }


    TextField fullName;
    TextField phone;
    TextField email;
    DatePicker dateOfBirth;
    TextArea notes;

    public void init() {
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        setWidth("500px");

        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
        layout.setSizeFull();

        fullName = new TextField("FullName");
        fullName.focus();

        phone = new TextField("Phone");
        email = new TextField("Email");
        dateOfBirth = new DatePicker("DateOfBirth");
        dateOfBirth.setLocale(SystemSettings.LOCALE);
        dateOfBirth.setInitialPosition(LocalDate.now().minusYears(20));

        notes = new TextArea("Notes");
        notes.setMinLength(150);


        layout.add(fullName, phone, email, dateOfBirth, notes);

        Binder<Customer> binder = new Binder<>(Customer.class);
        binder.forField(fullName).asRequired().bind(Customer::getFullName, Customer::setFullName);
        binder.bind(phone, Customer::getPhone, Customer::setPhone);
        binder.bind(email, Customer::getEmail, Customer::setEmail);
        binder.bind(dateOfBirth, Customer::getDateOfBirth, Customer::setDateOfBirth);
        binder.bind(notes, Customer::getNotes, Customer::setNotes);

        binder.setBean(customer);

        HorizontalLayout buttons = new HorizontalLayout();
        Button buttonSave;
        this.addDialogCloseActionListener(e -> updateAndClose(customer));


        if (newInstance) {
            buttonSave = new Button("Create", e -> {
                if (binder.validate().isOk()) {
                    Customer customer = new Customer();
                    customer.setId(UUID.randomUUID().toString());
                    customer.setFullName(fullName.getValue().trim());
                    customer.setPhone(phone.getValue().trim());
                    customer.setEmail(email.getValue().trim());
                    customer.setDateOfBirth(dateOfBirth.getValue());
                    customer.setNotes(notes.getValue().trim());
                    create(customer);
                }
            });
        } else {
            buttonSave = new Button("Save", e -> {
                if (binder.validate().isOk()) {
                    update(customer);
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


            fullName.setReadOnly(true);
            phone.setReadOnly(true);
            email.setReadOnly(true);
            dateOfBirth.setReadOnly(true);
            notes.setReadOnly(true);

            Button unlockBtn = new Button("Edit", VaadinIcon.UNLOCK.create(), e  -> {
                fullName.setReadOnly(false);
                phone.setReadOnly(false);
                email.setReadOnly(false);
                dateOfBirth.setReadOnly(false);
                notes.setReadOnly(false);
                fullName.focus();
            });

            unlockBtn.addThemeVariants(ButtonVariant.LUMO_SMALL);
            unlockBtn.getStyle().set("margin-left", "auto");
            buttons.add(unlockBtn);



            Button buttonRemove = new Button("Delete", e -> deleteAndClose(customer));

            buttonRemove.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ERROR);
            buttonRemove.getStyle().set("margin-left", "auto");
            buttons.add(buttonRemove);
        }

        add(buttons, layout);

    }

    private void create(Customer customer) {
        customerService.save(customer);
        reload(customer);
    }

    private void update(Customer customer) {
        customerService.update(customer);
        reload(customer);
    }

    private void reload(Customer customer) {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        dataProvider = new ListDataProvider<>(customerList);

        if (customerTxtField != null) {
            customerTxtField.setReadOnly(false);
            customerTxtField.setValue(customer.getTitle());
            customerTxtField.setReadOnly(true);
        }

        if (grid != null) {
            grid.setDataProvider(dataProvider);
            grid.getDataProvider().refreshAll();
        }
    }

    private void updateAndClose(Customer customer) {
        update(customer);
        close();
    }

    private void deleteAndClose(Customer customer) {
        List<Appointment> customerAppointmentList = appointmentService.selectAllForCustomer(customer.getId());

        boolean hasUpcomingAppointments = false;
        for (Appointment app : customerAppointmentList) {
            if (app.getStartDt().isAfter(LocalDateTime.now())) {
                hasUpcomingAppointments = true;
            }
        }

        if (hasUpcomingAppointments) {
            Notification notification = new Notification("User Error: Not allowed to delete has upcoming appointments", 5000, Notification.Position.TOP_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();
        } else {
            if (customerAppointmentList.isEmpty()) {
                deleteBackendAction(customer);
            } else {

                ConfirmNotification c = new ConfirmNotification("Delete customer " + customer.getFullName() + " and " + customerAppointmentList.size() + " appointments?") {
                    public void yesAction(ConfirmNotification obj) {
                        deleteBackendAction(customer);
                        obj.close();
                    }
                };
                c.open();
            }
        }

    }

    private void deleteBackendAction(Customer customer) {
        appointmentService.deleteByCustomerId(customer.getId());
        customerService.delete(customer);
        close();
        reload(customer);
    }

}
