package gr.boomer.backend.parsers;


import gr.boomer.backend.db.BoomerDao;
import gr.boomer.backend.model.Boomer;
import gr.boomer.backend.service.DateTimeService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class BestPriceParser {

    public static final String BESTPRICE_SOURCE_ID = "bestprice".toLowerCase();
    public static final String BESTPRICE_BASE_URL = "https://www.bestprice.gr";

    private static String baseurl = BESTPRICE_BASE_URL;

    private static Logger logger = LoggerFactory.getLogger(BestPriceParser.class);

    public static void parseAndSave(Document doc, String searchId, String productUrl) {

        String categName = "";
        Element categElem = doc.getElementsByClass("breadcrumb").first();
        if (categElem != null) {
            Elements categElms = categElem.getElementsByTag("a");
            for (Element cEle : categElms) {
                categName = categName + "|" + cEle.text();
            }
        }
        logger.info("       categName=" + categName);


        String specs = "";
        Element specsElem = doc.getElementsByClass("item-header__spec-content").first();
        if (specsElem != null) {
            specs = specsElem.text();
        }
        logger.info("       specs=" + specs);


        String productId = "";
        Element productIdElem = doc.getElementsByClass("hgroup").first();
        if (productIdElem != null) {
            productId = productIdElem.text();
        }
        logger.info("        " + productId + "   --   " + productUrl);


        if (BoomerDao.countBySerachIdProductId(searchId, BESTPRICE_SOURCE_ID, productId) > 0) {
            logger.warn("Delete old values ..... ");
            BoomerDao.deleteBySerachIdProductId(searchId, BESTPRICE_SOURCE_ID, productId);
        }


        Elements products = doc.getElementsByClass("prices__group");
        for (Element pCard : products) {


            String shopName = pCard.attr("data-domain");


//            String shopName = "";
//            Element shopNameElem = pCard.getElementsByClass("shop-name").first();
//            if (shopNameElem != null) {
//                shopName = shopNameElem.text();// should be only one
//            }

            System.out.println("" + shopName);

//
//            String availability = "";
//            Element availabilityElem = pCard.getElementsByClass("availability").first();
//            if (availabilityElem != null) {
//                availability = availabilityElem.text();
//            }


//            String rating = "";
//            Element ratingElem = pCard.getElementsByClass("rating stars").first();
//            if (ratingElem != null) {
//                rating = ratingElem.attr("title");
//            }


//            String areacode = "";
//            Element areacodeElem = pCard.getElementsByClass("shop-expander-tabs").first();
//            if (areacodeElem != null) {
//                String areaCodeText = areacodeElem.text();
//                if (areaCodeText.matches(".*\\d.*")) {// contains numbers
//                    //logger.info(" areaCodeText:   " + areaCodeText);
//                    String str1 = areaCodeText.replaceAll("[0-9]", "").replaceAll(" ", "");
//                    if (str1 != null && !str1.isEmpty() && str1.startsWith(",") && str1.length() > 2) {
//                        str1 = str1.substring(1);
//                    }
//                    // logger.info(" str1:   " + str1 );
//                    areacode = str1;
//                } else {
//                    areacode = areaCodeText;
//                }
//
//            }


            //  BigDecimal netPrice = BigDecimal.ZERO;
            //  BigDecimal courierCost = BigDecimal.ZERO;
            //  BigDecimal payOnDeliveryCost = BigDecimal.ZERO;


            BigDecimal finalPrice = BigDecimal.ZERO;
            Element priceAAAElem = null;
            Element priceElem = pCard.getElementsByClass("prices__price").first();
            if (priceElem != null) {

                priceAAAElem = priceElem.select("a").first();
                if (priceAAAElem != null) {
                    String val = priceAAAElem.text();
                    val = val.trim();
                    val = val.replace("€", "")
                            .replace(",", ".")
                            .replaceAll("\\s+", "").trim();
                    try {
                        finalPrice = new BigDecimal(val);
                    } catch (Exception ex) {
                        //ignore
                    }
                }
            }


            String linkUrl = "";
            if (priceAAAElem != null) {
                linkUrl = priceAAAElem.attr("href");


                linkUrl = linkUrl.substring(0, linkUrl.indexOf("?"));


                if (!linkUrl.startsWith(baseurl)) {
                    linkUrl = baseurl + linkUrl;
                }
            }

            String itemName = "";
            Element pricesTitleElem = pCard.getElementsByClass("prices__title").first();
            if (pricesTitleElem != null) {
                itemName = pricesTitleElem.text();
            }


            Boomer item = new Boomer();
            item.setSearchId(searchId);
            item.setCreationDate(DateTimeService.now());
            item.setSourceId(BESTPRICE_SOURCE_ID);
            item.setProductId(productId);
            item.setProductUrl(productUrl);
            item.setCategory(categName);
            item.setItemName(itemName);
            item.setShopName(shopName);
//            item.setNetPrice(netPrice);
            item.setFinalPrice(finalPrice);
//            item.setCourierCost(courierCost);
//            item.setPayOnDeliveryCost(payOnDeliveryCost);

            item.setItemUrl(linkUrl);
            item.setSpecs(specs);


//            item.setAvailability(availability);
//            item.setAreacode(areacode);
//            item.setRating(rating);


            logger.info(item.toString());


            BoomerDao.save(item);


        }
    }

}
