package gr.boomer;

import gr.boomer.backend.service.CheapestService;
import gr.boomer.backend.service.SaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.utils.StringUtils;

import static spark.Spark.get;
import static spark.Spark.post;

public class RestApi {

    static Logger logger = LoggerFactory.getLogger(BoomerServer.class);

    static void save() {
        post("/save", (request, response) -> {
            logger.info("save Resource ");
            return SaveService.run(request.body());
        });
    }

     static void cheapest() {
        get("/cheapest", (req, res) -> {
            logger.info("cheapest Resource ");
            //res.type("application/json");
            res.type("text/plain; charset=utf-8");
            String searchId = req.queryParams("searchId");
            if ( searchId==null || StringUtils.isEmpty(searchId)) {
                return "Invalid Input";
            }
            return  CheapestService.run( searchId );
        });
    }

    //return Utils.objectMapper.writeValueAsString(results);

//    public static void results() {
//        get("/results", (req, res) -> {
//            logger.info("results Resource ");
//
//            //res.type("application/json");
//            res.type("text/html; charset=utf-8");
//            String searchId = req.queryParams("searchId");
//            if ( searchId==null || StringUtils.isEmpty(searchId)) {
//                return "Invalid Input";
//            }
//            StringBuffer sb = new StringBuffer("Results for " +   searchId   +    " \n\n");
//            Map<String, List<Boomer>> results = SkService.results( searchId, sb);
//            //return Utils.objectMapper.writeValueAsString(results);
//
//            return sb.toString();
//
//        });
//    }
//
//    public static void results2() {
//        get("/results2", (req, res) -> {
//            logger.info("results Resource ");
//
//            //res.type("application/json");
//            res.type("text/html; charset=utf-8");
//            String searchId = req.queryParams("searchId");
//            if ( searchId==null || StringUtils.isEmpty(searchId)) {
//                return "Invalid Input";
//            }
//            StringBuffer sb = new StringBuffer("Results for " +   searchId   +    " \n\n");
//            Map<String, List<Boomer>> results = SkService.results2( searchId, sb);
//            //return Utils.objectMapper.writeValueAsString(results);
//
//            return sb.toString();
//        });
//    }




}
