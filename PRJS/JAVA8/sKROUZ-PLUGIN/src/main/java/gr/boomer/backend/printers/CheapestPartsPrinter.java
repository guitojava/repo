package gr.boomer.backend.printers;

import gr.boomer.backend.model.Boomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class CheapestPartsPrinter {

    static Logger logger = LoggerFactory.getLogger(CheapestPartsPrinter.class);


    public static String printCheapestParts(List<Boomer> cheapestParts)  {

        StringBuffer sb = new StringBuffer();

        if ( cheapestParts== null || cheapestParts.isEmpty())
            return null;

        sb.append(System.lineSeparator());
        sb.append(  "Boomer Results for  SearchId=" +  cheapestParts.get(0).getSearchId() + System.lineSeparator() + "UTC=" +      cheapestParts.get(0).getCreationDate().toString()   );
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("==================================");
        sb.append(System.lineSeparator());
        sb.append("----  LIST of cheapest parts -----");
        sb.append(System.lineSeparator());
        sb.append("==================================");
        sb.append(System.lineSeparator());
        BigDecimal total = BigDecimal.ZERO;

        Boomer old = new Boomer();

        for (Boomer currItem : cheapestParts) {


            if (old.getProductId() == null || false == old.getProductId().equalsIgnoreCase(currItem.getProductId())) {
                total = total.add(currItem.getFinalPrice());
                sb.append(  System.lineSeparator() );
            } else {
                sb.append(System.lineSeparator());
                sb.append("  - Pick One -  " + System.lineSeparator());
                sb.append(System.lineSeparator());
            }

            // sb.append(  currItem.getProductId() + currItem.getPrice()  + "  €  "  + currItem.getShopName() + " " +  currItem.getItemUrl() +  "    "  + currItem.getItemName()   );

            sb.append(currItem.getItemName());
            sb.append(System.lineSeparator());



            sb.append(String.format("%10s", currItem.getFinalPrice())   + " €     --->    " +  String.format("%30s", currItem.getShopName())    +  " \t\t\t\t\t\t-->    "    +  String.format("%30s", currItem.getItemUrl())     );
            sb.append(System.lineSeparator());


            old = currItem;
        }

        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append("=>  Σ =  " + total.toString() + "  € ");
        sb.append(System.lineSeparator());

        return sb.toString();


    }

//    public static void printAll(Map<String, List<Boomer>> results, StringBuffer sb) {
//        // stuff per shop
//        sb.append(System.lineSeparator());
//        sb.append(" ================================== ");
//        sb.append(System.lineSeparator());
//        sb.append(" ----  LIST of ALL RESULTS   ------ ");
//        sb.append(System.lineSeparator());
//        sb.append(" ================================== ");
//        sb.append(System.lineSeparator());
//
//        for (Map.Entry<String, List<Boomer>> entry : results.entrySet()) {
//            String key = entry.getKey();
//
//            sb.append("\n " + key);
//            sb.append(System.lineSeparator());
//
//            List<Boomer> shopItemsList = entry.getValue();
//            for (Boomer currItem : shopItemsList) {
//                sb.append(currItem.toString());
//                sb.append(System.lineSeparator());
//            }
//        }
//    }



//    public static void printPerShopResults(Map<String, List<Boomer>> results, List<String> groupIdList, StringBuffer sb, boolean showTotal) {
//
//        // stuff per shop
//        sb.append(System.lineSeparator());
//        sb.append(" ================================== ");
//        sb.append(System.lineSeparator());
//        sb.append(" ----  LIST of PER SHOP      ------ ");
//        sb.append(System.lineSeparator());
//        sb.append(" ================================== ");
//        sb.append(System.lineSeparator());
//        List<Map.Entry<String, List<Boomer>>> list = new LinkedList<>(results.entrySet());
//        list.sort((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()));
//
//        for (Map.Entry<String, List<Boomer>> entry : list) {
//            BigDecimal totPerShop = BigDecimal.ZERO;
//            String shopNameKey = entry.getKey();
//            List<Boomer> shopItemsList = entry.getValue();
//            List<String> currGroupIds = new ArrayList<>(groupIdList);
//            if (shopItemsList.size() > 0) {
//
//                sb.append("\n " + shopNameKey);
//                sb.append(System.lineSeparator());
//
//                for (Boomer o : shopItemsList) {
//
//                    sb.append("  +++   " + o.getProductId() + "      " + o.getItemName() + "   " + o.getFinalPrice() + "  -->   " + o.getItemUrl());
//                    sb.append(System.lineSeparator());
//
//                    BigDecimal price = o.getFinalPrice();
//                    totPerShop = totPerShop.add(price);
//                    currGroupIds.remove(o.getProductId());
//                }
//
//                for (String grpId : currGroupIds) {
//                    sb.append("  ---   " + grpId);
//                    sb.append(System.lineSeparator());
//                }
//
//                if (showTotal) {
//                    sb.append(" τιμη  Σ =  " + totPerShop.toString() + "  € ");
//                    sb.append(System.lineSeparator());
//                }
//            }
//
//        }
//    }
//


}






