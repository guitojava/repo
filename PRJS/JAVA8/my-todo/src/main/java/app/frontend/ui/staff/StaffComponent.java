package app.frontend.ui.staff;

import app.backend.staff.Staff;
import app.backend.staff.StaffService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class StaffComponent {
    private VerticalLayout mainContent = new VerticalLayout();
    private HorizontalLayout toolbar = new HorizontalLayout();
    private StaffSearchDialog searchDialog;
    private Grid<Staff> grid = new Grid<>();
    private Label  htmlColorLbl = new Label();


    public StaffComponent setSearchDialog(StaffSearchDialog searchDialog) {
        this.searchDialog = searchDialog;
        return this;
    }

    private StaffService staffService = new StaffService();
    TextField findTxt = new TextField();

    public StaffComponent() {
    }

    public static StaffComponent create() {
        return new StaffComponent();
    }

    public VerticalLayout build() {

        htmlColorLbl.setWidth("373px");

        createStaffsGrid();
        createToolbar();
        mainContent.add(toolbar, htmlColorLbl,  grid);
        htmlColorLbl.getElement().setProperty("innerHTML", "&nbsp;");
        return mainContent;
    }

    void createStaffsGrid() {
        List<Staff> staffList = staffService.selectAll();
        ListDataProvider<Staff> dataProvider = new ListDataProvider<>(staffList);
        grid.setWidth( "400px");
        grid.setDataProvider(dataProvider);
        grid.addColumn(Staff::getFullName); //  .setHeader("");
        grid.addColumn( Staff::getNotes);  // .setHeader("");

//        grid.getColumns().get( 1).getElement().getStyle().set("background-color",   "Black");
//                //grid.getDataProvider()olumns().get(1).getElement().getOuterHTML() ) ;

//        ListDataProvider<PersonData> s=(ListDataProvider<PersonData>) grid.getDataProvider();
//        ArrayList<PersonData> s1=(ArrayList<PersonData>) s.getItems();
//        System.out.println("Value="+s1.get(0).getId());


       // grid.getColumns().get( 1).setFooter(new Html("<span style=\"background-color: #ffff00;\">Welcome</span>"));


//        Grid.Column<Staff> nameColumn = grid
//                .addColumn(Staff::getColor)
//                .setHeader("Name")
//                .setFlexGrow(0)
//                .setWidth("100px")
//                .setResizable(false);
//
//
//
//        nameColumn.setFooter("Name");
//
//        grid.addColumn((ValueProvider<Staff, ?>) nameColumn);


        grid.addItemClickListener(event -> {
            Staff staff = event.getItem();
            if (searchDialog != null) {
                searchDialog.selectedStaffId.setValue(staff.getId());
                searchDialog.selectedStaffTextField.setValue(staff.getFullName() );
                searchDialog.colorLabel.getStyle().set("background-color", staff.getColor());


                htmlColorLbl.getElement().setProperty("innerHTML", staff.getColorLabel());

                searchDialog.close();

            } else {
                new StaffDialog(grid, dataProvider, staff, false).open();
            }

        });

    }

    private void applyFilter() {
        ListDataProvider<Staff> dataProvider = (ListDataProvider<Staff>) grid.getDataProvider();
        dataProvider.clearFilters();
        if (findTxt.getValue() != null)
            dataProvider.addFilter(staff -> {
                boolean result = StringUtils.containsIgnoreCase(staff.getFullName() == null ? "" : staff.getFullName(), findTxt.getValue()) ||
                        StringUtils.containsIgnoreCase(staff.getPhone() == null ? "" : staff.getPhone(), findTxt.getValue()) ||
                        StringUtils.containsIgnoreCase(staff.getEmail() == null ? "" : staff.getEmail(), findTxt.getValue()) ||
                        StringUtils.containsIgnoreCase(staff.getColor() == null ? "" : staff.getColor(), findTxt.getValue()) ||
                        StringUtils.containsIgnoreCase(staff.getDateOfBirth() == null ? "" : staff.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE), findTxt.getValue());
                return result;
            });
    }

    private void createToolbar() {
        findTxt.setWidth("300px");
        findTxt.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER, TextFieldVariant.MATERIAL_ALWAYS_FLOAT_LABEL);



        findTxt.focus();
        findTxt.setValueChangeMode(ValueChangeMode.ON_CHANGE);
        findTxt.addValueChangeListener(event -> {
            applyFilter();
            grid.getDataProvider().refreshAll();
        });
        Button newStaff = new Button("New", VaadinIcon.FILE_ADD.create(), e -> {
            new StaffDialog(grid, (ListDataProvider<Staff>) grid.getDataProvider(), null, true).open();
        });
        toolbar.add(newStaff, findTxt);
    }

}
