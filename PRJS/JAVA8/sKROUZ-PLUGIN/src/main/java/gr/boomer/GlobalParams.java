package gr.boomer;

import java.util.Properties;

public final class GlobalParams {

    private static Properties dbProps;
    private GlobalParams() {
        // initialisation
         dbProps = PropertiesUtils.getPropertiesFromClasspath("db.sqlite.properties");
    }

    public static Properties getDbProps() {
        return dbProps;
    }



}
