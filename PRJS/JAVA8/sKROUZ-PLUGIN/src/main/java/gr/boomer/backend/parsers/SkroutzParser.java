package gr.boomer.backend.parsers;

import gr.boomer.backend.db.BoomerDao;
import gr.boomer.backend.model.Boomer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class SkroutzParser {

    public static final String SKROUTZ_SOURCE_ID = "skroutz".toLowerCase();
    public static final String SKROUTZ_BASE_URL = "https://www.skroutz.gr";

    private static String baseurl = SKROUTZ_BASE_URL;

    private static Logger logger = LoggerFactory.getLogger(SkroutzParser.class);

    public static void parseAndSave(Document doc, String searchId, String productUrl) {


//        logger.info( doc.outerHtml()  );


//      String permalink = "";
//		Element permalinkElem = doc.getElementsByClass( "permalink"  ).first();
//		if (permalinkElem!=null) {
//			String hrefElem = permalinkElem.attr("href");
//			if (hrefElem!=null) {
//				String[] hrefElemSplit = hrefElem.split("#");
//				if (hrefElemSplit[0]!=null) {
//					permalink = hrefElemSplit[0];
//				}
//			}
//		}
//
//
//        if (! permalink.startsWith(baseurl )){
//            permalink =  baseurl + permalink;
//        }
//       logger.info(    "   All results    =>   " +   permalink    );


        LocalDateTime timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();

        String categName = "";
        Element categElem = doc.getElementsByClass("js-card-head js-bc-nav ").first();
        if (categElem != null) {
            Elements categElms = categElem.getElementsByTag("a");
            for (Element cEle : categElms) {
                categName = categName + "|" + cEle.text();
            }
        }

        logger.info("       categName=" + categName);

        String specs = "";
        Element specsElem = doc.getElementsByClass("specs").first();
        if (specsElem != null) {
            specs = specsElem.text();
        }
        logger.info("       specs=" + specs);

        String productId = "";
        Element productIdElem = doc.getElementsByClass("js-scroll-top").first();

        if (productIdElem != null) {
            productId = productIdElem.text();

        }


        logger.info("        " + productId + "   --   " + productUrl);


        // DEL OLD VALS
        if (BoomerDao.countBySerachIdProductId(searchId, SKROUTZ_SOURCE_ID, productId) > 0) {
            logger.warn("Delete old values ..... ");
            BoomerDao.deleteBySerachIdProductId(searchId, SKROUTZ_SOURCE_ID, productId);
        }


        Elements productCards = doc.getElementsByClass("cf card js-product-card");
        for (Element pCard : productCards) {


            String shopName = "";
            Element shopNameElem = pCard.getElementsByClass("shop-name").first();
            if (shopNameElem != null) {
                shopName = shopNameElem.text();// should be only one
            }

            System.out.println("" + shopName);


            String availability = "";
            Element availabilityElem = pCard.getElementsByClass("availability").first();
            if (availabilityElem != null) {
                availability = availabilityElem.text();
            }


            String rating = "";
            Element ratingElem = pCard.getElementsByClass("rating stars").first();
            if (ratingElem != null) {
                rating = ratingElem.attr("title");
            }


            String areacode = "";
            Element areacodeElem = pCard.getElementsByClass("shop-expander-tabs").first();
            if (areacodeElem != null) {
                String areaCodeText = areacodeElem.text();
                if (areaCodeText.matches(".*\\d.*")) {// contains numbers
                    //logger.info(" areaCodeText:   " + areaCodeText);
                    String str1 = areaCodeText.replaceAll("[0-9]", "").replaceAll(" ", "");
                    if (str1 != null && !str1.isEmpty() && str1.startsWith(",") && str1.length() > 2) {
                        str1 = str1.substring(1);
                    }
                    // logger.info(" str1:   " + str1 );
                    areacode = str1;
                } else {
                    areacode = areaCodeText;
                }

            }


//            Elements priceElems = pCard.getElementsByClass("price");
//            String price = "";
//            if (priceElems != null) {
//                Element priceElm = priceElems.select("a:eq(0)").first();
//                if (priceElm != null) {
//                    price = priceElm.text().trim();
//                    if (price.startsWith("Επιπλέον χρεώσεις")) {
//                        Element priceElm2 = priceElm.nextElementSibling();
//                        if (priceElm2 != null) {
//                            price = priceElm2.text().trim();
//                        }
//                    }
//                }
//            }
//


            BigDecimal netPrice = BigDecimal.ZERO;
            BigDecimal finalPrice = BigDecimal.ZERO;
            BigDecimal courierCost = BigDecimal.ZERO;
            BigDecimal payOnDeliveryCost = BigDecimal.ZERO;


            Element priceElem = pCard.getElementsByClass("price-content").first();
            if (priceElem != null) {

                Elements links = priceElem.select("a[href]");
                for (Element elem : links) {

                    String dataType = elem.attr("data-type");


                    System.out.println();
                    System.out.print(shopName + "     ");

                    if (dataType.equalsIgnoreCase("final_price")) {
                        String val = elem.text().trim();
                        System.out.print("   final_price   " + val);

                        val = val.replace("€", "")
                                .replace(",", ".")
                                .replaceAll("\\s+", "").trim();
                        try {
                            finalPrice = new BigDecimal(val);
                        } catch (Exception ex) {
                            //ignore
                        }
                    }

                    if (dataType.equalsIgnoreCase("net_price")) {
                        String val = elem.text().trim();

                        System.out.print("   net_price   " + val);

                        val = val.replace("€", "")
                                .replace(",", ".")
                                .replaceAll("\\s+", "").trim();
                        try {
                            netPrice = new BigDecimal(val);
                        } catch (Exception ex) {
                            //ignore
                        }
                    }
                }

                System.out.println();
                Elements extraCostElemes = pCard.getElementsByClass("extra-cost cf");
                if (extraCostElemes != null) {
                    for (Element elem : extraCostElemes) {
                        Element spanElem = elem.tagName("span");
                        //    Element emElem  = elem.tagName("em");
                        //   System.out.println(  "emElem " + emElem.text()    );

                        String val = spanElem.text();
                        System.out.println(val);

                        val = val.replace("€", "")
                                .replace(",", ".")
                                .replaceAll("\\s+", "").trim();

                        if (val.contains("Μεταφορικ")) {
                            if (val.contains("Δεν")) {
                                courierCost = new BigDecimal("-1");
                            } else {
                                val = val.replaceAll("[^\\d.]", "");
                                try {
                                    courierCost = new BigDecimal(val);
                                } catch (Exception ex) {
                                    //ignore
                                }
                            }
                        }

                        if (val.contains("Αντικαταβολ")) {
                            if (val.contains("Δεν")) {
                                payOnDeliveryCost = new BigDecimal("-1");
                            } else {
                                val = val.replaceAll("[^\\d.]", "");
                                try {
                                    payOnDeliveryCost = new BigDecimal(val);
                                } catch (Exception ex) {
                                    //ignore
                                }

                            }
                        }


                    }
                }
            }


            if (finalPrice.equals(BigDecimal.ZERO)) {
                if (netPrice.compareTo(BigDecimal.ZERO) > 0) {
                    finalPrice = finalPrice.add(netPrice);
                }
                if (courierCost.compareTo(BigDecimal.ZERO) > 0) {
                    finalPrice = finalPrice.add(courierCost);
                }
                if (payOnDeliveryCost.compareTo(BigDecimal.ZERO) > 0) {
                    finalPrice = finalPrice.add(payOnDeliveryCost);
                }
            }

//            Element priceElem = pCard.getElementsByClass("price").first();
//           // String price = "";
//
//            BigDecimal finalPrice = BigDecimal.ZERO;
//
//            if (priceElem != null) {
//                String priceHtmlString =   priceElem.html();
//                List<BigDecimal> pricesList = new ArrayList<>();
//                String regex =   "\\d+(,\\d+)?\\s+(\u20AC)";
//                Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
//                Matcher matcher = pattern.matcher(priceHtmlString);
//                while (matcher.find())
//                {
//                  //  System.out.println("Start index: " + matcher.start());
//                 //   System.out.println(" End  index: " + matcher.end() + " ");
//                    logger.info(" : " + matcher.group());
//                    String val = matcher.group();
//                    val = val.trim();
//                    val = val.replace("€", "")
//                            .replace(",", ".")
//                            .replaceAll("\\s+", "").trim();
//                    try {
//                        pricesList.add(  new BigDecimal(val) );
//                    } catch (Exception ex) {
//                        //ignore
//                    }
//                }
//                Optional<BigDecimal> max = pricesList.stream().max(Comparator.naturalOrder());
//                if (max.isPresent()) {
//                    finalPrice = max.get();
//                    System.out.println("FinalPrice: " +finalPrice  );
//                }
//
//
//                System.out.println(   "    " +   pricesList.size()) ;
//
//
//                if (   pricesList.size() != 4   ){
//                    System.out.println(   " @@@@@@@@@@@@@@@    NOT   pricesList.size() == 4        " +   pricesList.size()) ;
//
//
//                }
//
//
//            }


//*[@id="prices"]/li[30]/div[4]/div/div/a[2]

//            Elements priceElems = pCard.getElementsByClass("price");
//            String price = "";
//            if (priceElems != null) {
//                Element priceElm = priceElems.select("a:eq(0)").first();
//                if (priceElm != null) {
//                    price = priceElm.text().trim();
//                    if (price.startsWith("Επιπλέον χρεώσεις")) {
//                        Element priceElm2 = priceElm.nextElementSibling();
//                        if (priceElm2 != null) {
//                            price = priceElm2.text().trim();
//                        }
//                    }
//                } else {
//                    logger.error("Can't get Price for html:    " + pCard.html());
//                }
//            }

            // price clean up
//            price = price.replace("€", "")
//                    .replace(",", ".")
//                    .replaceAll("\\s+", "").trim();
//            try {
//                item.setPrice(new BigDecimal(price));
//            } catch (Exception ex) {
//                //ignore
//            }


            String itemName = "";
            String linkUrl = "";
            Elements descElems = pCard.getElementsByClass("description");
            if (descElems != null) {
                Element descElem = descElems.select("a").first();
                if (descElem != null) {
                    linkUrl = descElem.attr("href");
                    itemName = descElem.attr("title");
                }

            }

            if (!linkUrl.startsWith(baseurl)) {
                linkUrl = baseurl + linkUrl;
            }


            //      logger.info(itemName + "    |||    " + shopName + " -->  " + price + "    <---   " + linkUrl);
            ///////////////////////////////////

            Boomer item = new Boomer();
            item.setSearchId(searchId);
            item.setCreationDate(timestamp);
            item.setSourceId(SKROUTZ_SOURCE_ID);
            item.setProductId(productId);
            item.setProductUrl(productUrl);
            item.setCategory(categName);
            item.setItemName(itemName);
            item.setShopName(shopName);
            item.setNetPrice(netPrice);
            item.setFinalPrice(finalPrice);
            item.setCourierCost(courierCost);
            item.setPayOnDeliveryCost(payOnDeliveryCost);
            item.setItemUrl(linkUrl);
            item.setSpecs(specs);
            item.setAvailability(availability);
            item.setAreacode(areacode);
            item.setRating(rating);


            BoomerDao.save(item);


        }
    }

}
