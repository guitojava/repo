package gr.boomer.backend.db;

import gr.boomer.backend.model.Boomer;
import gr.boomer.backend.service.DateTimeService;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoomerDao {

    static Logger logger = LoggerFactory.getLogger(BoomerDao.class);

    private static Jdbi jdbi = Jdbi.create("jdbc:sqlite:C:/sqlite/boomer.db", "boomer", "boomer"); // (H2 in-memory database)

    private static Map<String, String> colMaps = new HashMap<>();

    static {


        colMaps.put("search_id", "searchId");
        colMaps.put("creation_date", "creationDate");
        colMaps.put("source_id", "sourceId");
        colMaps.put("product_id", "productId");
        colMaps.put("product_url", "productUrl");
        colMaps.put("category", "category");
        colMaps.put("net_price", "netPrice");
        colMaps.put("final_price", "finalPrice");
        colMaps.put("courier_cost", "courierCost");
        colMaps.put("pay_on_delivery_cost", "payOnDeliveryCost");
        colMaps.put("shop_name", "shopName");
        colMaps.put("item_name", "itemName");
        colMaps.put("specs", "specs");
        colMaps.put("item_url", "itemUrl");
        colMaps.put("availability", "availability");
        colMaps.put("areacode", "areacode");
        colMaps.put("rating", "rating");

        logger.info(colMaps.toString());

    }


    public static boolean save(Boomer boomer) {
        try {
            String sql =
                    " INSERT INTO boomer " +
                            " ( search_id, creation_date, source_id,product_id, product_url, category, net_price, final_price," +
                            " courier_cost, pay_on_delivery_cost, shop_name, item_name, specs, item_url, availability, areacode, rating ) " +
                            " VALUES (:searchId,:creationDate,:sourceId,:productId,:productUrl,:category, :netPrice, :finalPrice,:courierCost," +
                            " :payOnDeliveryCost, :shopName, :itemName,:specs, :itemUrl, :availability, :areacode, :rating) ";

            Map<String, Object> item = new HashMap<>();
            item.put("searchId", boomer.getSearchId());
            item.put("creationDate", DateTimeService.toText(boomer.getCreationDate()));
            item.put("sourceId", boomer.getSourceId());
            item.put("productId", boomer.getProductId());
            item.put("productUrl", boomer.getProductUrl());
            item.put("category", boomer.getCategory());
            item.put("netPrice", boomer.getNetPrice());
            item.put("finalPrice", boomer.getFinalPrice());
            item.put("courierCost", boomer.getCourierCost());
            item.put("payOnDeliveryCost", boomer.getPayOnDeliveryCost());
            item.put("shopName", boomer.getShopName());
            item.put("itemName", boomer.getItemName());
            item.put("specs", boomer.getSpecs());
            item.put("itemUrl", boomer.getItemUrl());
            item.put("availability", boomer.getAvailability());
            item.put("areacode", boomer.getAreacode());
            item.put("rating", boomer.getRating());


            try (Handle handle = jdbi.open()) {
                handle.createUpdate(sql)
                        .bindMap(item)
                        .execute();
            }


        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return true;
    }




    public static void deleteBySerachIdProductId(String searchId, String sourceId, String productId) {
        jdbi.useHandle(handle -> {
            handle.execute("DELETE FROM boomer WHERE search_id = ? AND source_id = ? AND product_id = ?", searchId, sourceId, productId);
        });
    }


    public static Long countBySerachIdProductId(String searchId, String sourceId, String productId) {

        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM boomer  WHERE search_id = ? AND source_id = ? AND product_id = ?")
                        .bind(0, searchId)
                        .bind(1, sourceId)
                        .bind(2, productId)
                        .mapTo(Long.class)
                        .one());
    }


    public static List<Boomer> queryCheapestItems(String searchId) {
        String sql =
                "SELECT b.* " +
                        "FROM boomer b " +
                        "JOIN ( " +
                        "        SELECT product_id, MIN(final_price) AS final_price FROM boomer " +
                        "        WHERE search_id = :searchId GROUP BY product_id " +
                        ") mb " +
                        "ON b.product_id = mb.product_id AND b.final_price = mb.final_price " +
                        "WHERE search_id = :searchId " +
                        "ORDER BY b.product_id ";


        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("searchId", searchId)
                        .map(new BoomerMapper())
                        .list()
        );

    }


    public static List<Boomer> query(String searchId) {
        String sql = "SELECT * FROM boomer WHERE search_id = :searchId order by product_id ";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("searchId", searchId)
                        .map(new BoomerMapper())
                        .list()
        );

    }
        public static   List<Boomer>  query(String searchId, String productId) {
        String sql = "SELECT * FROM boomer WHERE search_id = :searchId AND product_id = :productId order by product_id ";
            return jdbi.withHandle(handle ->
                    handle.createQuery(sql)
                            .bind("searchId", searchId)
                            .bind("productId", productId)
                            .map(new BoomerMapper())
                            .list()
            );
    }


}