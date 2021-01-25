/*
 * created by leon
 */

package org.kidsjava.stocks;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Tests {
    public static void main(String[] args){
        System.out.println("test");
        test();
    }
    static void test(){
    Map<LocalDate, BigDecimal> dailyPrices   = new HashMap<>();
    ListedStock  listedStock = new ListedStock( "AAAA", dailyPrices );
    LocalDate day1 = LocalDate.of(Integer.parseInt("2018"), Integer.parseInt("01"), Integer.parseInt("01"));
    LocalDate day2 = LocalDate.of(Integer.parseInt("2018"), Integer.parseInt("01"), Integer.parseInt("02"));
    dailyPrices.put( day1,  new BigDecimal("100.5"));
    dailyPrices.put( day2,  new BigDecimal("200.5"));
    Pair<LocalDate, LocalDate> period1  = new Pair<>(day1, day2);
    EquityOption equityOption = new EquityOption( TradeType.BUY, 1L, listedStock, new BigDecimal("50.5"  ), period1 ) ;
    System.out.println(  equityOption.toString() );
  //  LocalDate day3 = LocalDate.of(Integer.parseInt("2018"), Integer.parseInt("01"), Integer.parseInt("10"));
    BigDecimal  res = equityOption.exercise( day2 );
    System.out.println("DIFF: "+  res );
}
}
