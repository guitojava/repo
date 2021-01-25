package gr.boomer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


public class BoomerServer {

    static Logger logger = LoggerFactory.getLogger(BoomerServer.class);


    public static void main(String[] args) {


        Properties dbProps = GlobalParams.getDbProps();


        logger.info("Startup Boomer ok !!!!");


//      http://localhost:4567/save
        RestApi.save();

//      http://localhost:4567/cheapest
        RestApi.cheapest();


//
////     http://localhost:4567/results
//        RestApi.results();
//
////         http://localhost:4567/results2
//        RestApi.results2();



    }

}
