package gr.boomer;


import java.io.FileInputStream;
        import java.io.InputStream;
        import java.util.Properties;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

public class PropertiesUtils extends Properties {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    private PropertiesUtils() {}

    public static Properties getPropertiesFromClasspath(String fileName)
            throws RuntimeException {

        Properties props = new Properties();
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream(fileName);
            if (is == null) { // try this instead
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                logger.debug("loaded properties file with Thread.currentThread()");
            }
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("ERROR LOADING PROPERTIES FROM CLASSPATH >" + fileName + "< !!!", e);
        }
        return props;
    }

    public static Properties getPropertiesFromPath(String fileName)
            throws RuntimeException {

        Properties props = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            props.load(fis);
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException("ERROR LOADING PROPERTIES FROM PATH >" + fileName + "< !!!", e);
        }
        return props;
    }
}