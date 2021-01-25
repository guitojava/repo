package app.frontend.ui.calender;


import app.backend.appointment.Appointment;
import app.backend.appointment.AppointmentService;
import app.backend.customer.Customer;
import app.backend.customer.CustomerService;
import app.backend.staff.Staff;
import app.backend.staff.StaffService;
import app.frontend.custom.AudioPlayer;
import app.frontend.custom.confirm.ConfirmNotification;
import app.frontend.custom.datetime.CustomDateTimePicker;
import app.frontend.ui.customer.CustomerDialog;
import app.frontend.ui.customer.CustomerSearchDialog;
import app.frontend.ui.staff.StaffSearchDialog;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.Binder;
import org.vaadin.stefan.fullcalendar.Entry;
import org.vaadin.stefan.fullcalendar.FullCalendar;

import javax.swing.text.html.HTML;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class CalenderDialog extends Dialog {

    String afmInfo =
            "<span>" +
                " 000999999999 ΜΕΤΡΟΠΟΛΙ ΣΠΙΖΖΑ - METROPOLIS LHKLKJLHKLHJ   " +
                "<br>665-89726 Greece   lalalala <b>sjfgdsjf</b>  " +
                "</hr>"+
                "<br> 665-89726 Greece"+
                "<br> 665-89726 Greece"+
                "</hr>"+
            "   <br> 665-89726 Greece" +
            "</span>";

    FullCalendar calendar;
    Entry entry;
    boolean newInstance;

    AppointmentService appointmentService = new AppointmentService();
    StaffService staffService = new StaffService();
    CustomerService customerService = new CustomerService();

    TextField amountTxt = new TextField();
    TextField taxTxt = new TextField();
    TextField totTxt = new TextField(); // amount + tax

    TextField timestamp = new TextField();
    TextField cashRegId = new TextField();

    TextField customerIdTxt = new TextField();
    TextField staffIdTxt = new TextField();
    TextField staffColorTxt = new TextField();

    Appointment appointment;
    CalenderComponent calenderComponent;

    public CalenderDialog(FullCalendar calendar, Entry entry, boolean newInstance, CalenderComponent calenderComponent, Appointment appointment) {
        this.calenderComponent = calenderComponent;
        this.calendar = calendar;
        this.entry = entry;
        this.newInstance = newInstance;
        if (!entry.getTitle().isBlank()) { // todo check
            this.appointment = appointmentService.select(entry.getId());
        }

        if (appointment != null && appointment.getCustomerId() != null) {
            customerIdTxt.setValue(appointment.getCustomerId());
        }

        if (appointment != null && appointment.getStaffId() != null) {
            staffIdTxt.setValue(appointment.getStaffId());
            Staff staff = staffService.select(appointment.getStaffId());
            if (staff != null) {
                staffColorTxt.getStyle().set("background-color", staff.getColor());
            }
        }


        amountTxt.addValueChangeListener(event -> {
            //Notification.show(event.getValue());
            updateTotals();
            //         amountChanged();
        });
        amountTxt.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        taxTxt.addValueChangeListener(event -> {
            // Notification.show(event.getValue());
            //         taxChanged();
            updateTotals();

        });
        taxTxt.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);

        init();
    }

    public void updateTotals() {

        try {

            BigDecimal amount = new BigDecimal(amountTxt.getValue());
            BigDecimal tax = new BigDecimal(taxTxt.getValue());
            BigDecimal total = amount.add(tax);

            totTxt.setValue(total.toString()); // amount + tax

        } catch (Exception ex) {
            //ignore
        }

    }


    public void init() {


        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);
        setWidth("500px");

        VerticalLayout layout = new VerticalLayout();
        layout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);
        layout.setSizeFull();


        TextField staffTxt = new TextField();
        staffTxt.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        if (appointment != null && appointment.getStaffId() != null) {
            Staff st = staffService.select(appointment.getStaffId());
            if (st != null) {
                staffTxt.setValue(st.getFullName());
            }
        }
        staffTxt.setReadOnly(true);
        staffTxt.setWidth("300px");
        staffIdTxt.setVisible(false);//hidden
        Button staffBtn = new Button("Select Staff", e -> new StaffSearchDialog(staffTxt, staffIdTxt, staffColorTxt).open());
        staffBtn.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        staffBtn.getElement().getStyle().set("margin-left", "auto");

        staffColorTxt.setWidth("50px");
        staffColorTxt.setEnabled(false);


        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(


                "dd-MM-yyyy HH:mm:ss", new Locale("el", "GR"));


        timestamp.setValue(LocalDateTime.now().format(formatter));
        VerticalLayout moneyLayout = new VerticalLayout();

        HorizontalLayout horizontalLayoutAmt = new HorizontalLayout();
        HorizontalLayout horizontalLayoutTax = new HorizontalLayout();
        HorizontalLayout horizontalLayoutTot = new HorizontalLayout();


        horizontalLayoutAmt.add(new Label("Price:"), amountTxt);
        horizontalLayoutTax.add(new Label(" Cost:"), taxTxt);
        horizontalLayoutTot.add(new Label("-TOTAL-  "), taxTxt);

        UUID uuid = UUID.randomUUID();


        Html afmInfoLable = new Html( afmInfo );


        moneyLayout.add(
                cashRegId
                , afmInfoLable

                ,timestamp
                , new Label( uuid.toString())
                , new Label("======================")
                , horizontalLayoutAmt
//                ,amountTxt
                , horizontalLayoutTax
//                ,taxTxt
                , new Label("======================")
                , totTxt
        );


        AudioPlayer player = new AudioPlayer();
        player.setSource("https://file-examples.com/wp-content/uploads/2017/11/file_example_WAV_1MG.wav");
        moneyLayout.add(player);


        HorizontalLayout staffLayout = new HorizontalLayout(staffTxt, staffColorTxt);
        layout.add(moneyLayout, staffIdTxt, staffLayout);


        TextField customerTxt = new TextField("Customer");
        if (appointment != null && appointment.getCustomerId() != null && !appointment.getCustomerId().isEmpty()) {
            Customer c = customerService.select(appointment.getCustomerId());
            customerTxt.setValue(c.getTitle());
        }
        customerTxt.setReadOnly(true);
        customerIdTxt.setVisible(false);// hidden

        Button customerBtn = new Button("Select Customer", e -> new CustomerSearchDialog(customerTxt, customerIdTxt).open());
        customerBtn.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        Button customerEditBtn = new Button("View Customer", e -> {
            if (customerIdTxt.getValue().isBlank()) {
                Notification notification = new Notification("Select a Customer first ", 5000,
                        Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
                notification.open();
            } else {
                Customer c = customerService.select(customerIdTxt.getValue());
                new CustomerDialog(c, false, customerTxt).open();
            }
        });
        HorizontalLayout customerBtnLayout = new HorizontalLayout(customerBtn, customerEditBtn, staffBtn);
        layout.add(customerIdTxt, customerTxt, customerBtnLayout);


        CustomDateTimePicker fieldStart = new CustomDateTimePicker("Start");
        CustomDateTimePicker fieldEnd = new CustomDateTimePicker("End");
        HorizontalLayout datesLayout = new HorizontalLayout();
        datesLayout.add(fieldStart, fieldEnd);
        layout.add(datesLayout);


        TextArea desc = new TextArea();
        desc.focus();
        //desc.setMaxHeight( "70px");

        ArrayList<String> workList = new ArrayList<>();
        workList.add("KOY ");
        workList.add("ANT ");
        workList.add("XTE ");
        workList.add("NYX ");
        workList.add("BAF ");
        workList.add("EXT ");
        HorizontalLayout descBtnLayout = new HorizontalLayout();
        for (String str : workList) {
            Button btn = new Button(str, e -> {
                desc.setValue(desc.getValue() + str);
                desc.focus();
            });
            btn.addThemeVariants(ButtonVariant.LUMO_SMALL);
            descBtnLayout.add(btn);
        }
        layout.add(descBtnLayout, desc);


        //binder
        Binder<Entry> binder = new Binder<>(Entry.class);
        binder.forField(customerTxt)
                .asRequired()
                .bind(Entry::getTitle, Entry::setTitle);

        binder.forField(desc)
                .asRequired()
                .bind(Entry::getDescription, Entry::setDescription);

        binder.bind(fieldStart, Entry::getStart, Entry::setStart);
        binder.bind(fieldEnd, Entry::getEnd, Entry::setEnd);
        binder.setBean(entry);


        HorizontalLayout buttons = new HorizontalLayout();
        Button buttonSave;

        this.addDialogCloseActionListener(e -> save(entry));

        if (newInstance) {
            buttonSave = new Button("Create", e -> {
                if (binder.validate().isOk()) {
                    save(entry);
                }
            });
        } else {
            buttonSave = new Button("Save", e -> {
                if (binder.validate().isOk()) {
                    save(entry);
                }
            });
        }


        buttonSave.addClickShortcut(Key.PAGE_DOWN);
        buttonSave.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        buttons.add(buttonSave);

        Button buttonCancel = new Button("Cancel", e -> close());
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_PRIMARY);
        buttons.add(buttonCancel);

        if (!newInstance) {

            Button buttonRemove = new Button("Delete", e -> {
                ConfirmNotification c = new ConfirmNotification("Delete appointment " + entry.getTitle() + "?") {
                    public void yesAction(ConfirmNotification obj) {
                        deleteAndClose(entry);
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

    public void save(Entry entry) {
        if (customerIdTxt.getValue() != null) {
            entry.setDescription(entry.getDescription().trim());
            calenderComponent.save(entry, customerIdTxt.getValue(), staffIdTxt.getValue());

            if (appointmentService.exists(entry.getId())) {
                appointment = appointmentService.select(entry.getId());
            }

            // set staff color on appointment
            if (staffIdTxt.getValue() != null && staffService.exists(staffIdTxt.getValue())) {
                Staff staff = staffService.select(staffIdTxt.getValue());
                appointment.setColor(staff.getColor());
            }


            calenderComponent.selectAll(calendar);
            close();
        }
    }


    public void deleteAndClose(Entry entry) {
        calenderComponent.delete(entry);
        calenderComponent.selectAll(calendar);
        close();
    }


}
