package app.frontend.view;

import app.frontend.ui.calender.CalenderComponent;
import app.frontend.ui.customer.CustomerComponent;
import app.frontend.ui.staff.StaffComponent;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.material.Material;
import org.vaadin.stefan.fullcalendar.CalendarView;
import org.vaadin.stefan.fullcalendar.CalendarViewImpl;

import javax.annotation.PostConstruct;

@Route(ViewsConfiguration.MainView)
@PageTitle("Main")
@PWA(name = "Application", shortName = "App", enableInstallPrompt = true)
@Theme(value = Lumo.class, variant = Material.LIGHT)
@Viewport("width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes, , viewport-fit=cover")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@JsModule("./styles/full-calendar-styles.js")

public class MainView extends AppLayout {

    public VerticalLayout mainContent;
    private VerticalLayout calendarComponent;
    private VerticalLayout customerComponent;
    private VerticalLayout staffComponent;

    public MainView() {
        mainContent = new VerticalLayout();
        calendarComponent = CalenderComponent.create().build(this, CalendarViewImpl.TIME_GRID_WEEK);
    }

    @PostConstruct
    public void init() {

        mainContent.add(calendarComponent);

        Image img = new Image("img/logo.png" , "Image not found");

        img.setHeight("44px");
        addToNavbar(new DrawerToggle(), img);

        Button calenderBtn = new Button("Week", VaadinIcon.CALENDAR.create(), e -> {
            reloadCalender(CalendarViewImpl.TIME_GRID_WEEK);
        });
        calenderBtn.setWidth("100px");
        calenderBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button weekListBtn = new Button("", VaadinIcon.LIST.create());
        weekListBtn.addClickListener(e -> {
            reloadCalender(CalendarViewImpl.LIST_WEEK);
        });
        weekListBtn.setWidth("50px");

        Button dayViewBtn = new Button("Today", VaadinIcon.CALENDAR.create());
        dayViewBtn.addClickListener(e -> {
            reloadCalender(CalendarViewImpl.TIME_GRID_DAY);
        });
        dayViewBtn.setWidth("100px");

        Button monthViewBtn = new Button("Month", VaadinIcon.CALENDAR.create());
        monthViewBtn.addClickListener(e -> {
            reloadCalender(CalendarViewImpl.DAY_GRID_MONTH);
        });
        monthViewBtn.setWidth("100px");



        Button dayListBtn = new Button("", VaadinIcon.LIST.create());
        dayListBtn.addClickListener(e -> {
            reloadCalender(CalendarViewImpl.LIST_DAY);
        });
        dayListBtn.setWidth("50px");
        dayListBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);


        Button monthListBtn = new Button("", VaadinIcon.LIST.create());
        monthListBtn.addClickListener(e -> {
            reloadCalender(CalendarViewImpl.LIST_MONTH);
        });
        monthListBtn.setWidth("50px");

        Button yearListBtn = new Button("Year", VaadinIcon.LIST.create());
        yearListBtn.addClickListener(e -> {
            reloadCalender(CalendarViewImpl.LIST_YEAR);
        });
        yearListBtn.setWidth("100px");



        Button customerBtn = new Button("Customers", VaadinIcon.FILE_ADD.create(), e -> {
            mainContent.removeAll();
            customerComponent = CustomerComponent.create().build();
            mainContent.add(customerComponent);

        });
        customerBtn.setWidth("150px");
        customerBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button staffBtn = new Button("Staff", VaadinIcon.FILE_ADD.create(), e -> {
            mainContent.removeAll();
            staffComponent = StaffComponent.create().build();
            mainContent.add(staffComponent);

        });
        staffBtn.setWidth("150px");
        staffBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button aboutBtn = new Button("About", VaadinIcon.HOME.create(), e -> {
            new Notification(
                    " by George Leon ", 5000,
                    Notification.Position.TOP_CENTER).open();

        });
        aboutBtn.setWidth("100px");
        aboutBtn.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        VerticalLayout drawer = new VerticalLayout();
        drawer.add(

                new HorizontalLayout(calenderBtn,weekListBtn),
                customerBtn,

                new HorizontalLayout(dayViewBtn,dayListBtn),
                new HorizontalLayout(monthViewBtn,monthListBtn),

                yearListBtn,


                staffBtn,
                aboutBtn);   // add buttons
        addToDrawer(drawer);
        setDrawerOpened(true);

        setContent(mainContent);
    }

    public void reloadCalender(CalendarView calendarView) {
        //UI.getCurrent().navigate(ViewsConfiguration.MainView)
        mainContent.removeAll();
        calendarComponent = CalenderComponent.create().build(this, calendarView);
        mainContent.add(calendarComponent);
    }

}
