package app.frontend.ui.customer;

import app.backend.customer.Customer;
import app.backend.customer.CustomerService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
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

public class CustomerComponent {
    private VerticalLayout mainContent = new VerticalLayout();
    private HorizontalLayout toolbar = new HorizontalLayout();
    private Grid<Customer> grid = new Grid<>();
    private CustomerSearchDialog searchDialog;

    public CustomerComponent setSearchDialog(CustomerSearchDialog searchDialog) {
        this.searchDialog = searchDialog;
        return this;
    }

    private CustomerService customerService = new CustomerService();
    TextField findTxt = new TextField();

    public CustomerComponent() {
    }

    public static CustomerComponent create() {
        return new CustomerComponent();
    }

    public VerticalLayout build() {

        createCustomersGrid();
        createToolbar();

        mainContent.add(toolbar, grid);
        return mainContent;
    }

    void createCustomersGrid() {
        List<Customer> customerList = customerService.selectAll();
        ListDataProvider<Customer> dataProvider = new ListDataProvider<>(customerList);
        grid.setDataProvider(dataProvider);
        grid.addColumn(Customer::getTitle);    //.setHeader("Customer ");
        grid.addItemClickListener(event -> {
            Customer customer = event.getItem();
            if (searchDialog != null) {
                searchDialog.selectedCustomerId.setValue(customer.getId());
                searchDialog.selectedCustomerTextField.setValue( customer.getTitle() );
                searchDialog.close();
            } else {
                new CustomerDialog(grid, dataProvider, customer, false).open();
            }

        });

    }

    private void applyFilter() {
        ListDataProvider<Customer> dataProvider = (ListDataProvider<Customer>) grid.getDataProvider();
        dataProvider.clearFilters();
        if (findTxt.getValue() != null)
            dataProvider.addFilter(customer -> {
                boolean result = StringUtils.containsIgnoreCase(customer.getFullName() == null ? "" : customer.getFullName(), findTxt.getValue()) ||
                        StringUtils.containsIgnoreCase(customer.getPhone() == null ? "" : customer.getPhone(), findTxt.getValue()) ||
                        StringUtils.containsIgnoreCase(customer.getEmail() == null ? "" : customer.getEmail(), findTxt.getValue()) ||
                        StringUtils.containsIgnoreCase(customer.getDateOfBirth() == null ? "" : customer.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE), findTxt.getValue());
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
        Button newCustomer = new Button("New", VaadinIcon.FILE_ADD.create(), e -> {
            new CustomerDialog(grid, (ListDataProvider<Customer>) grid.getDataProvider(), null, true).open();
        });
        toolbar.add(newCustomer, findTxt);
    }

}
