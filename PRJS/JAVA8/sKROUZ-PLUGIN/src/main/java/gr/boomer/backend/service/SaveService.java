package gr.boomer.backend.service;

import gr.boomer.backend.parsers.BestPriceParser;
import gr.boomer.backend.parsers.SkroutzParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class SaveService {

    private static Logger logger = LoggerFactory.getLogger(SaveService.class);

    public static String run(String rBody) {

        String delimiter = "<span id='delimiter' class='Delimiter'></span>";
        String[] splitOutput = rBody.split(delimiter);

        String tags = splitOutput[0];//searchId
        if (tags == null || tags.isEmpty() || tags.equalsIgnoreCase("undefined")) {
            UUID uuid = UUID.randomUUID();
            tags = uuid.toString();
        }

        String searchId = tags;
        String pageTitle = splitOutput[1];
        String pageUrl = splitOutput[2];
        String html = splitOutput[3];
        Document doc = Jsoup.parse(html.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("<br>", ""));

        //       logger.info("html:>> " + doc.html());
        logger.info("tags:>> " + tags);
        logger.info("title:>> " + pageTitle);
        logger.info("url:>> " + pageUrl);


        if (pageUrl.toLowerCase().startsWith(SkroutzParser.SKROUTZ_BASE_URL)) {
            SkroutzParser.parseAndSave(doc, searchId, pageUrl);
        } else if (pageUrl.toLowerCase().startsWith(BestPriceParser.BESTPRICE_BASE_URL)) {
            BestPriceParser.parseAndSave(  doc, searchId , pageUrl  );
            logger.info("html:>> " + doc.html());
        }

        // always return searchId
        return searchId;
    }
}
