package app.frontend.ui.calender;

import app.backend.SystemSettings;
import app.backend.appointment.Appointment;
import app.backend.appointment.AppointmentService;
import app.backend.customer.Customer;
import app.backend.customer.CustomerService;
import app.backend.staff.Staff;
import app.backend.staff.StaffService;
import app.frontend.view.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.stefan.fullcalendar.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CalenderComponent {

    private final VerticalLayout mainContent = new VerticalLayout();
    private FullCalendar calendar;
    private final HorizontalLayout toolbar = new HorizontalLayout();
    private final AppointmentService appointmentService = new AppointmentService();
    private final StaffService staffService = new StaffService();
    private MainView mainView;
    private CalendarView calView;

    public CalenderComponent() {
    }

    public static CalenderComponent create() {

        return new CalenderComponent();
    }

    public VerticalLayout build(MainView mainView, CalendarView calView) {
        this.mainView = mainView;
        this.calView = calView;
        createCalender();

        createToolbar();
        mainContent.add(toolbar, calendar);
        return mainContent;
    }


    private void createCalender() {

        calendar = FullCalendarBuilder.create().build();
        calendar.allowDatesRenderEventOnOptionChange(true);
        calendar.setTimezone(SystemSettings.TIMEZONE);
        calendar.setLocale(SystemSettings.LOCALE);
        calendar.setNowIndicatorShown(false);
        calendar.setHeightAuto();
        calendar.setMinTime(LocalTime.of(9, 30));
        calendar.setMaxTime(LocalTime.of(21, 0));
        calendar.setFirstDay(DayOfWeek.TUESDAY);
        calendar.setWeekNumbersVisible(false);
        calendar.changeView(calView);
        calendar.setTimeslotsSelectable(true);
        calendar.setNumberClickable(true);
        calendar.setOption("allDaySlot", false);
        calendar.setOption("slotDuration", "00:30:00");
        calendar.setOption("hiddenDays", "[1]");
        calendar.setBusinessHours(
//        Δευ.                                             κλειστά
//        Τρι.                               9:30 - 14:00      17:30 - 20:30
//        Τετ.                                             9:30 - 14:00
//        Πεμ.                              9:30 - 14:00      17:30 - 20:30
//        Παρ.  Σαβ.                                    9:30 - 20:30
//        Κυρ.                                             9:30 - 14:00
//
                new BusinessHours(LocalTime.of(9, 30), LocalTime.of(14, 0), DayOfWeek.TUESDAY),
                new BusinessHours(LocalTime.of(17, 30), LocalTime.of(20, 30), DayOfWeek.TUESDAY),
                new BusinessHours(LocalTime.of(9, 30), LocalTime.of(14, 0), DayOfWeek.WEDNESDAY),
                new BusinessHours(LocalTime.of(9, 30), LocalTime.of(14, 0), DayOfWeek.THURSDAY),
                new BusinessHours(LocalTime.of(17, 30), LocalTime.of(20, 30), DayOfWeek.THURSDAY),
                new BusinessHours(LocalTime.of(9, 30), LocalTime.of(20, 30), DayOfWeek.FRIDAY),
                new BusinessHours(LocalTime.of(9, 30), LocalTime.of(20, 30), DayOfWeek.SATURDAY),
                new BusinessHours(LocalTime.of(9, 30), LocalTime.of(14, 0), DayOfWeek.SUNDAY)
        );



        /*
         * When user clicks on an EMPTY  time-slot  to make a new entry
         */
        calendar.addTimeslotsSelectedListener((event) -> {
            LocalDateTime start = event.getStartDateTime();
            LocalDateTime end = event.getStartDateTime().plusMinutes(30);
            Entry entry = new Entry(null, "", start, end,
                    false, true, "Bisque", "");
            new CalenderDialog(calendar, entry, true, this, null).open();
        });
        /*
         * When user clicks on an EXISTING entry
         */
        calendar.addEntryClickedListener(event -> {
            Entry entry = event.getEntry();
            Appointment appointment = appointmentService.select(entry.getId());
            new CalenderDialog(calendar, entry, false, this, appointment).open();
        });


        calendar.addEntryDroppedListener(event -> {
            Entry newEntry = event.applyChangesOnEntry();
            Appointment appointment = appointmentService.select(newEntry.getId());
            save(newEntry, appointment.getCustomerId(), appointment.getStaffId());
        });
        calendar.addEntryResizedListener(event -> {
            Entry newEntry = event.applyChangesOnEntry();
            Appointment appointment = appointmentService.select(newEntry.getId());
            save(newEntry, appointment.getCustomerId(), appointment.getStaffId());
        });

        // fill with data
        selectAll(calendar);

    }




    private void createToolbar() {
        Button buttonToday = new Button("", VaadinIcon.HOME.create(), e -> calendar.today());
        Button buttonPrevious = new Button("", VaadinIcon.ANGLE_LEFT.create(), e -> calendar.previous());
        Button buttonNext = new Button("", VaadinIcon.ANGLE_RIGHT.create(), e -> calendar.next());
        buttonNext.setIconAfterText(true);


        // simulate the date picker light that we can use in polymer
        DatePicker gotoDate = new DatePicker();
        gotoDate.addValueChangeListener(event1 -> calendar.gotoDate(event1.getValue()));
        gotoDate.getElement().getStyle().set("visibility", "hidden");
        gotoDate.getElement().getStyle().set("position", "fixed");
        gotoDate.setWidth("0px");
        gotoDate.setHeight("0px");
        gotoDate.setWeekNumbersVisible(true);

        Button buttonDatePicker = new Button(VaadinIcon.CALENDAR.create());
        buttonDatePicker.getElement().appendChild(gotoDate.getElement());
        buttonDatePicker.addClickListener(event -> gotoDate.open());

        toolbar.add( buttonToday, buttonPrevious, buttonNext, buttonDatePicker, gotoDate);

    }


    public void save(Entry entry, String customerId, String staffId) {


        Appointment app = new Appointment();
        app = app.toAppointment(entry);
        app.setCustomerId(customerId);
        app.setStaffId(staffId);

        // set staff color on appointment
        if (staffId != null && staffService.exists(staffId)) {
            Staff staff = staffService.select(staffId);
            app.setColor(staff.getColor());
        }

        if (appointmentService.save(app)) {
            new Notification("Save done " + app.getTitle(), 3000,
                    Notification.Position.TOP_CENTER).open();
        } else {
            Notification notification = new Notification("System Error:  " + app.getError(), 3000,
                    Notification.Position.TOP_CENTER);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.open();

            mainView.reloadCalender(calView);

        }
    }


    public void delete(Entry entry) {
        Appointment app = new Appointment();
        app = app.toAppointment(entry);
        appointmentService.delete(app);
        new Notification("Delete done " + app.getTitle(), 3000, Notification.Position.TOP_CENTER).open();
    }

    public void selectAll(FullCalendar calendar) {
        this.calendar = calendar;

        calendar.removeAllEntries();

        List<Appointment> appointmentList = appointmentService.selectAll();
        CustomerService customerService = new CustomerService();
        StaffService staffService = new StaffService();

        // update the title with customer info
        for (Appointment app : appointmentList) {

            String title = "";

            if (app.getStaffId() != null) {
                Staff st = staffService.select(app.getStaffId());
                if (st != null) {
                    title = title + "[" + st.getFullName() + "]";
                }
            }

            if (app.getDescription() != null && app.getDescription().length() >= 3) {
                title = title + "  [" + app.getDescription().substring(0, 3) + "]";
            }


            if (app.getCustomerId() != null) {
                Customer c = customerService.select(app.getCustomerId());
                title = title + " " + c.getTitle();
            }


            app.setTitle(title);
        }


        for (Appointment app : appointmentList) {
            calendar.addEntry(app.toCalenderEntry());
        }

        calendar.render();
    }


}
