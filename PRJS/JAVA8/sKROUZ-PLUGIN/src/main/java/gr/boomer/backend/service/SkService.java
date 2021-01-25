package gr.boomer.backend.service;

import gr.boomer.backend.model.Boomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class SkService {

    static Logger logger = LoggerFactory.getLogger(SkService.class);


//    public static Map<String, List<Boomer>> results(String serachId, StringBuffer sb) {
//        List<Boomer> boomerList = BoomerDao.selectBySerachId(serachId);
//        Map<String, List<Boomer>> results = createResults(boomerList, true);
//        ///
////        Map<String, Boomer> cheapestParts =  new LinkedHashMap<>();
////        calculatecheapestParts (  results , cheapestParts );
////        CheapestPartsPrinter.printCheapestParts( cheapestParts, sb );
//
//        List<String> groupIdList = createGroupIdList(boomerList);
//        CheapestPartsPrinter.printPerShopResults(results, groupIdList, sb, true);
//
//        return results;
//    }
//
//
//    public static Map<String, List<Boomer>> results2(String serachId, StringBuffer sb) {
//
//        List<Boomer> boomerList = BoomerDao.selectBySerachId(serachId);
//        Map<String, List<Boomer>> results = createResults(boomerList, false);
//
////        Map<String, Boomer> cheapestParts =  new LinkedHashMap<>();
////        calculatecheapestParts (  results , cheapestParts );
////        CheapestPartsPrinter.printCheapestParts( cheapestParts, sb );
//
//        List<String> groupIdList = createGroupIdList(boomerList);
//        CheapestPartsPrinter.printPerShopResults(results, groupIdList, sb, false);
//
//        return results;
//    }


    /////////////
    ///////////
    //////////
    //////PRIVATE
    //////////
    /////////////
    ////////////

    private static Map<String, List<Boomer>> createResults(List<Boomer> boomerList, boolean cheapestOnly) {
        Map<String, List<Boomer>> results = new LinkedHashMap<>();
        for (
                Boomer item : boomerList) {
            addToResults(item, results, cheapestOnly);
        }
        return results;
    }


    private static List<String> createGroupIdList(List<Boomer> boomerList) {
        List<String> groupIdList = new ArrayList<>();
        for (Boomer item : boomerList) {
            // only add if not added yet
            if (false == groupIdList.contains(item.getProductId())) {
                groupIdList.add(item.getProductId());
            }
        }
        return groupIdList;
    }


    private static void addToResults(Boomer item, Map<String, List<Boomer>> results, boolean cheapestOnly) {
        if (!results.containsKey(item.getShopName())) {
            List<Boomer> shopItemsList = new LinkedList<>();
            shopItemsList.add(item);
            results.put(item.getShopName(), shopItemsList);
        } else {
            List<Boomer> shopItemsList = results.remove(item.getShopName());
            Boomer found = null;
            for (Boomer boomer : shopItemsList) {
                if (boomer.getSearchId().equalsIgnoreCase(item.getSearchId()) &&
                        boomer.getProductId().equalsIgnoreCase(item.getProductId())) {
                    found = boomer;
                }
            }

            if (found != null && cheapestOnly) {
                if (found.getFinalPrice().compareTo(item.getFinalPrice()) > 0) {
                    shopItemsList.remove(found);
                    shopItemsList.add(item);
                }
            } else {
                shopItemsList.add(item);
            }
            results.put(item.getShopName(), shopItemsList);
        }
    }


    private static void calculatecheapestParts(Map<String, List<Boomer>> results, Map<String, Boomer> cheapestParts) {

        for (Map.Entry<String, List<Boomer>> entry : results.entrySet()) {
            String key = entry.getKey(); // product
            List<Boomer> shopItemsList = entry.getValue();
            //        System.out.println("\n " + key);
            for (Boomer currItem : shopItemsList) {
                //System.out.println(currItem.getItemName() + "    |||    " + currItem.getShopName() + " -->  " + currItem.getPrice() + "    <---   " + currItem.getUrlLink());
                cheapestParts.putIfAbsent(currItem.getProductId(), currItem);
                Boomer old = cheapestParts.get(currItem.getProductId());
                BigDecimal oldValue;
                BigDecimal currValue;
                try {
                    oldValue = old.getFinalPrice();
                    currValue = currItem.getFinalPrice();
                    if (currValue.compareTo(oldValue) < 0) {
                        cheapestParts.remove(old.getProductId());
                        cheapestParts.put(currItem.getProductId(), currItem);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(old.getFinalPrice());
                    System.out.println(currItem.getFinalPrice());
                }
            }
        }
    }

}
