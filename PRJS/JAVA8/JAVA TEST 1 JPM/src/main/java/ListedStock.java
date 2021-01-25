import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ListedStock extends  Stock {

    private String venueIdentifier;
    private Map<LocalDate, BigDecimal> dailyPrices = new HashMap<>() ;

    public ListedStock() {
    }

    public ListedStock(final String venueIdentifier, final Map<LocalDate, BigDecimal> dailyPrices) {
        this.venueIdentifier = venueIdentifier;
        this.dailyPrices = dailyPrices;
    }

    public String getVenueIdentifier() {
        return venueIdentifier;
    }

    public void setVenueIdentifier(final String venueIdentifier) {
        this.venueIdentifier = venueIdentifier;
    }

    public Map<LocalDate, BigDecimal> getDailyPrices() {
        return dailyPrices;
    }

    public void setDailyPrices(final Map<LocalDate, BigDecimal> dailyPrices) {
        this.dailyPrices = dailyPrices;
    }
}
