/*
 * created by leon
 */

package org.kidsjava.stocks;

/*
Equity options are the most common type of equity derivative.
They provide the right, but not the obligation, to buy (call)
or sell (put) a quantity of stock (e.g. 1 contract = 100 shares of stock),
at a set price (strike price),
within a certain period of time (prior to the expiration date)
 */

import javafx.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDate;


public class EquityOption {

private TradeType tradeType;
private Long qty;
private ListedStock stock;
private BigDecimal strikePrice;
private Pair<LocalDate, LocalDate> period;

    public EquityOption(final TradeType tradeType, final Long qty, final ListedStock stock, final BigDecimal strikePrice, final Pair<LocalDate, LocalDate> period) {
        //todo add somemore validations add
        this.tradeType = tradeType;
        this.qty = qty;
        this.stock = stock;
        this.strikePrice = strikePrice;
        if ( period.getKey().isBefore( period.getValue()  ) )  {
            this.period = period;
        }
    }
    /*
      Please define a function “exercise”
      that assumes an investor uses its right to buy (for call option) or sell (for put option)
      the share at a specific date and returns profit or loss, i.e. the difference between the strike price and the stock price on that date.
     */
    public BigDecimal exercise (LocalDate day ){
        // find day in
        BigDecimal stockPriceonDay = stock.getDailyPrices().get(day);

        if (stockPriceonDay!=null)
        return  stockPriceonDay.subtract(strikePrice).multiply(  new BigDecimal(qty) );
        else
            throw new RuntimeException("no data");

    }

    @Override
    public String toString() {
        return "EquityOption{" +
                "tradeType=" + tradeType +
                ", qty=" + qty +
                ", stock=" + stock +
                ", strikePrice=" + strikePrice +
                ", period=" + period +
                '}';
    }
}
