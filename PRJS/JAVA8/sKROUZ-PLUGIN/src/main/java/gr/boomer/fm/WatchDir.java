package gr.boomer.fm;


import gr.boomer.fm.checkandfix.ConstantsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.*;


public class WatchDir  {
    /**
     C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\bin\java.exe -javaagent:C:\Users\pc\AppData\Local\JetBrains\Toolbox\apps\IDEA-C\ch-0\202.6397.94\lib\idea_rt.jar=60259:C:\Users\pc\AppData\Local\JetBrains\Toolbox\apps\IDEA-C\ch-0\202.6397.94\bin -Dfile.encoding=UTF-8 -classpath C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\charsets.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\access-bridge-64.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\cldrdata.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\dnsns.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\jaccess.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\jfxrt.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\localedata.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\nashorn.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\openjsse.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\sunec.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\sunjce_provider.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\sunmscapi.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\sunpkcs11.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\ext\zipfs.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\jce.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\jfr.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\jfxswt.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\jsse.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\management-agent.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\resources.jar;C:\java\zulu8.44.0.13-ca-fx-jdk8.0.242-win_x64\jre\lib\rt.jar;C:\workspace\primer\erp-server\target\classes;C:\workspace\primer\erp-server-core\target\classes;C:\workspace\primer\common\target\classes;C:\Users\pc\.m2\repository\org\jeasy\easy-rules-core\3.4.0\easy-rules-core-3.4.0.jar;C:\Users\pc\.m2\repository\com\sun\mail\javax.mail\1.5.6\javax.mail-1.5.6.jar;C:\Users\pc\.m2\repository\javax\activation\activation\1.1\activation-1.1.jar;C:\Users\pc\.m2\repository\org\apache\commons\commons-email\1.5\commons-email-1.5.jar;C:\Users\pc\.m2\repository\org\apache\commons\commons-lang3\3.11\commons-lang3-3.11.jar;C:\Users\pc\.m2\repository\commons-io\commons-io\1.3.2\commons-io-1.3.2.jar;C:\Users\pc\.m2\repository\org\jsoup\jsoup\1.12.1\jsoup-1.12.1.jar;C:\Users\pc\.m2\repository\ch\qos\logback\logback-access\1.2.3\logback-access-1.2.3.jar;C:\Users\pc\.m2\repository\org\slf4j\slf4j-api\1.7.7\slf4j-api-1.7.7.jar;C:\Users\pc\.m2\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;C:\Users\pc\.m2\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;C:\Users\pc\.m2\repository\org\codehaus\janino\janino\3.1.2\janino-3.1.2.jar;C:\Users\pc\.m2\repository\org\codehaus\janino\commons-compiler\3.1.2\commons-compiler-3.1.2.jar;C:\Users\pc\.m2\repository\org\projectlombok\lombok\1.18.12\lombok-1.18.12.jar;C:\Users\pc\.m2\repository\org\mapstruct\mapstruct\1.3.1.Final\mapstruct-1.3.1.Final.jar;C:\Users\pc\.m2\repository\xom\xom\1.3.4\xom-1.3.4.jar;C:\Users\pc\.m2\repository\xml-apis\xml-apis\1.3.03\xml-apis-1.3.03.jar;C:\Users\pc\.m2\repository\xerces\xercesImpl\2.8.0\xercesImpl-2.8.0.jar;C:\Users\pc\.m2\repository\xalan\xalan\2.7.2\xalan-2.7.2.jar;C:\Users\pc\.m2\repository\xalan\serializer\2.7.2\serializer-2.7.2.jar;C:\Users\pc\.m2\repository\org\jdbi\jdbi3-core\3.12.0\jdbi3-core-3.12.0.jar;C:\Users\pc\.m2\repository\org\antlr\antlr4-runtime\4.7.2\antlr4-runtime-4.7.2.jar;C:\Users\pc\.m2\repository\io\leangen\geantyref\geantyref\1.3.7\geantyref-1.3.7.jar;C:\Users\pc\.m2\repository\com\github\ben-manes\caffeine\caffeine\2.8.0\caffeine-2.8.0.jar;C:\Users\pc\.m2\repository\org\checkerframework\checker-qual\2.10.0\checker-qual-2.10.0.jar;C:\Users\pc\.m2\repository\org\jdbi\jdbi3-sqlite\3.12.0\jdbi3-sqlite-3.12.0.jar;C:\Users\pc\.m2\repository\org\jdbi\jdbi3-sqlobject\3.12.0\jdbi3-sqlobject-3.12.0.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.11.1\jackson-databind-2.11.1.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.11.1\jackson-annotations-2.11.1.jar;C:\Users\pc\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.11.1\jackson-core-2.11.1.jar;C:\Users\pc\.m2\repository\io\dropwizard\metrics\metrics-core\4.1.10.1\metrics-core-4.1.10.1.jar;C:\Users\pc\.m2\repository\org\hibernate\validator\hibernate-validator\6.1.5.Final\hibernate-validator-6.1.5.Final.jar;C:\Users\pc\.m2\repository\jakarta\validation\jakarta.validation-api\2.0.2\jakarta.validation-api-2.0.2.jar;C:\Users\pc\.m2\repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;C:\Users\pc\.m2\repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;C:\Users\pc\.m2\repository\org\mariuszgromada\math\MathParser.org-mXparser\4.3.3\MathParser.org-mXparser-4.3.3.jar;C:\Users\pc\.m2\repository\com\dorkbox\SystemTray\3.15\SystemTray-3.15.jar;C:\Users\pc\.m2\repository\com\dorkbox\ShellExecutor\1.1\ShellExecutor-1.1.jar;C:\Users\pc\.m2\repository\org\javassist\javassist\3.23.0-GA\javassist-3.23.0-GA.jar;C:\Users\pc\.m2\repository\net\java\dev\jna\jna\4.5.2\jna-4.5.2.jar;C:\Users\pc\.m2\repository\net\java\dev\jna\jna-platform\4.5.2\jna-platform-4.5.2.jar;C:\Users\pc\.m2\repository\net\lingala\zip4j\zip4j\1.3.2\zip4j-1.3.2.jar;C:\Users\pc\.m2\repository\com\prowidesoftware\pw-swift-core\SRU2018-7.10.4\pw-swift-core-SRU2018-7.10.4.jar;C:\Users\pc\.m2\repository\org\apache\commons\commons-text\1.6\commons-text-1.6.jar;C:\Users\pc\.m2\repository\mysql\mysql-connector-java\5.1.47\mysql-connector-java-5.1.47.jar;C:\Users\pc\.m2\repository\org\apache\commons\commons-dbcp2\2.5.0\commons-dbcp2-2.5.0.jar;C:\Users\pc\.m2\repository\org\apache\commons\commons-pool2\2.6.0\commons-pool2-2.6.0.jar;C:\Users\pc\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Users\pc\.m2\repository\com\microsoft\sqlserver\mssql-jdbc\7.0.0.jre8\mssql-jdbc-7.0.0.jre8.jar;C:\Users\pc\.m2\repository\org\xhtmlrenderer\flying-saucer-pdf-openpdf\9.1.18\flying-saucer-pdf-openpdf-9.1.18.jar;C:\Users\pc\.m2\repository\com\github\librepdf\openpdf\1.2.5\openpdf-1.2.5.jar;C:\Users\pc\.m2\repository\com\googlecode\juniversalchardet\juniversalchardet\1.0.3\juniversalchardet-1.0.3.jar;C:\Users\pc\.m2\repository\com\twelvemonkeys\imageio\imageio-tiff\3.4.1\imageio-tiff-3.4.1.jar;C:\Users\pc\.m2\repository\com\twelvemonkeys\imageio\imageio-core\3.4.1\imageio-core-3.4.1.jar;C:\Users\pc\.m2\repository\com\twelvemonkeys\imageio\imageio-metadata\3.4.1\imageio-metadata-3.4.1.jar;C:\Users\pc\.m2\repository\com\twelvemonkeys\common\common-lang\3.4.1\common-lang-3.4.1.jar;C:\Users\pc\.m2\repository\com\twelvemonkeys\common\common-io\3.4.1\common-io-3.4.1.jar;C:\Users\pc\.m2\repository\com\twelvemonkeys\common\common-image\3.4.1\common-image-3.4.1.jar;C:\Users\pc\.m2\repository\org\xhtmlrenderer\flying-saucer-core\9.1.18\flying-saucer-core-9.1.18.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-server\9.4.14.v20181114\jetty-server-9.4.14.v20181114.jar;C:\Users\pc\.m2\repository\javax\servlet\javax.servlet-api\3.1.0\javax.servlet-api-3.1.0.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-http\9.4.14.v20181114\jetty-http-9.4.14.v20181114.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-io\9.4.14.v20181114\jetty-io-9.4.14.v20181114.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-util\9.4.14.v20181114\jetty-util-9.4.14.v20181114.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-servlet\9.4.14.v20181114\jetty-servlet-9.4.14.v20181114.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-security\9.4.14.v20181114\jetty-security-9.4.14.v20181114.jar;C:\Users\pc\.m2\repository\org\glassfish\jersey\containers\jersey-container-jetty-http\2.25\jersey-container-jetty-http-2.25.jar;C:\Users\pc\.m2\repository\org\glassfish\hk2\external\javax.inject\2.5.0-b30\javax.inject-2.5.0-b30.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-continuation\9.2.14.v20151106\jetty-continuation-9.2.14.v20151106.jar;C:\Users\pc\.m2\repository\org\glassfish\jersey\core\jersey-common\2.25\jersey-common-2.25.jar;C:\Users\pc\.m2\repository\org\glassfish\jersey\bundles\repackaged\jersey-guava\2.25\jersey-guava-2.25.jar;C:\Users\pc\.m2\repository\org\glassfish\hk2\osgi-resource-locator\1.0.1\osgi-resource-locator-1.0.1.jar;C:\Users\pc\.m2\repository\javax\ws\rs\javax.ws.rs-api\2.0.1\javax.ws.rs-api-2.0.1.jar;C:\Users\pc\.m2\repository\org\glassfish\jersey\core\jersey-server\2.25\jersey-server-2.25.jar;C:\Users\pc\.m2\repository\org\glassfish\jersey\core\jersey-client\2.25\jersey-client-2.25.jar;C:\Users\pc\.m2\repository\org\glassfish\jersey\media\jersey-media-jaxb\2.25\jersey-media-jaxb-2.25.jar;C:\Users\pc\.m2\repository\javax\annotation\javax.annotation-api\1.2\javax.annotation-api-1.2.jar;C:\Users\pc\.m2\repository\org\glassfish\hk2\hk2-api\2.5.0-b30\hk2-api-2.5.0-b30.jar;C:\Users\pc\.m2\repository\org\glassfish\hk2\hk2-utils\2.5.0-b30\hk2-utils-2.5.0-b30.jar;C:\Users\pc\.m2\repository\org\glassfish\hk2\external\aopalliance-repackaged\2.5.0-b30\aopalliance-repackaged-2.5.0-b30.jar;C:\Users\pc\.m2\repository\org\glassfish\hk2\hk2-locator\2.5.0-b30\hk2-locator-2.5.0-b30.jar;C:\Users\pc\.m2\repository\javax\validation\validation-api\1.1.0.Final\validation-api-1.1.0.Final.jar;C:\Users\pc\.m2\repository\org\glassfish\jersey\containers\jersey-container-servlet-core\2.25\jersey-container-servlet-core-2.25.jar;C:\Users\pc\.m2\repository\com\google\code\gson\gson\2.8.5\gson-2.8.5.jar;C:\Users\pc\.m2\repository\com\google\guava\guava\26.0-jre\guava-26.0-jre.jar;C:\Users\pc\.m2\repository\com\google\code\findbugs\jsr305\3.0.2\jsr305-3.0.2.jar;C:\Users\pc\.m2\repository\org\checkerframework\checker-qual\2.5.2\checker-qual-2.5.2.jar;C:\Users\pc\.m2\repository\com\google\errorprone\error_prone_annotations\2.1.3\error_prone_annotations-2.1.3.jar;C:\Users\pc\.m2\repository\com\google\j2objc\j2objc-annotations\1.1\j2objc-annotations-1.1.jar;C:\Users\pc\.m2\repository\org\codehaus\mojo\animal-sniffer-annotations\1.14\animal-sniffer-annotations-1.14.jar;C:\Users\pc\.m2\repository\org\reflections\reflections\0.9.11\reflections-0.9.11.jar;C:\Users\pc\.m2\repository\org\bouncycastle\bcpkix-jdk15on\1.61\bcpkix-jdk15on-1.61.jar;C:\Users\pc\.m2\repository\org\bouncycastle\bcprov-jdk15on\1.61\bcprov-jdk15on-1.61.jar;C:\Users\pc\.m2\repository\org\bouncycastle\bcmail-jdk15on\1.61\bcmail-jdk15on-1.61.jar;C:\Users\pc\.m2\repository\org\bouncycastle\bcpg-jdk15on\1.61\bcpg-jdk15on-1.61.jar;C:\Users\pc\.m2\repository\org\bouncycastle\bcprov-ext-jdk15on\1.61\bcprov-ext-jdk15on-1.61.jar;C:\Users\pc\.m2\repository\org\bouncycastle\bctls-jdk15on\1.61\bctls-jdk15on-1.61.jar;C:\Users\pc\.m2\repository\org\bouncycastle\bctsp-jdk15on\1.46\bctsp-jdk15on-1.46.jar;C:\Users\pc\.m2\repository\xml-apis\xml-apis\1.4.01\xml-apis-1.4.01.jar;C:\Users\pc\.m2\repository\com\squareup\okhttp3\okhttp\3.12.0\okhttp-3.12.0.jar;C:\Users\pc\.m2\repository\com\squareup\okio\okio\1.15.0\okio-1.15.0.jar;C:\Users\pc\.m2\repository\net\sourceforge\htmlunit\htmlunit\2.35.0\htmlunit-2.35.0.jar;C:\Users\pc\.m2\repository\org\apache\commons\commons-lang3\3.9\commons-lang3-3.9.jar;C:\Users\pc\.m2\repository\org\apache\httpcomponents\httpmime\4.5.8\httpmime-4.5.8.jar;C:\Users\pc\.m2\repository\org\apache\httpcomponents\httpclient\4.5.8\httpclient-4.5.8.jar;C:\Users\pc\.m2\repository\org\apache\httpcomponents\httpcore\4.4.11\httpcore-4.4.11.jar;C:\Users\pc\.m2\repository\commons-codec\commons-codec\1.11\commons-codec-1.11.jar;C:\Users\pc\.m2\repository\net\sourceforge\htmlunit\htmlunit-core-js\2.35.0\htmlunit-core-js-2.35.0.jar;C:\Users\pc\.m2\repository\net\sourceforge\htmlunit\neko-htmlunit\2.35.0\neko-htmlunit-2.35.0.jar;C:\Users\pc\.m2\repository\xerces\xercesImpl\2.12.0\xercesImpl-2.12.0.jar;C:\Users\pc\.m2\repository\net\sourceforge\htmlunit\htmlunit-cssparser\1.4.0\htmlunit-cssparser-1.4.0.jar;C:\Users\pc\.m2\repository\commons-io\commons-io\2.6\commons-io-2.6.jar;C:\Users\pc\.m2\repository\commons-net\commons-net\3.6\commons-net-3.6.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\websocket\websocket-client\9.4.16.v20190411\websocket-client-9.4.16.v20190411.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-client\9.4.16.v20190411\jetty-client-9.4.16.v20190411.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\jetty-xml\9.4.16.v20190411\jetty-xml-9.4.16.v20190411.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\websocket\websocket-common\9.4.16.v20190411\websocket-common-9.4.16.v20190411.jar;C:\Users\pc\.m2\repository\org\eclipse\jetty\websocket\websocket-api\9.4.16.v20190411\websocket-api-9.4.16.v20190411.jar;C:\Users\pc\.m2\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar gr.primer.server.fm.WatchDir
     2020-08-13 11:06:31.393 [main] INFO  gr.primer.server.fm.WatchDir.main(181) - WatchDir ==   C:\test_data_emron_full\MAIN_DIR
     2020-08-13 11:06:31.405 [main] INFO  gr.primer.server.fm.WatchDir.<init>(77) - Scanning C:\test_data_emron_full\MAIN_DIR ...
     2020-08-13 11:06:31.415 [main] INFO  gr.primer.server.fm.WatchDir.<init>(79) - Done.

     2020-08-13 11:06:40.675 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_CREATE|||C:\test_data_emron_full\MAIN_DIR\New Text Document.txt|||89691334728900
     2020-08-13 11:07:02.534 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_DELETE|||C:\test_data_emron_full\MAIN_DIR\New Text Document.txt|||89713193378100
     2020-08-13 11:07:02.534 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_CREATE|||C:\test_data_emron_full\MAIN_DIR\readme_file_test.txt|||89713194000500

     2020-08-13 11:07:30.551 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_CREATE|||C:\test_data_emron_full\MAIN_DIR\readme_file_test - Copy.txt|||89741211563600
     2020-08-13 11:07:30.552 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_MODIFY|||C:\test_data_emron_full\MAIN_DIR\readme_file_test - Copy.txt|||89741212149100

     2020-08-13 11:07:37.938 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_CREATE|||C:\test_data_emron_full\MAIN_DIR\New folder|||89748598421700
     2020-08-13 11:07:37.939 [main] INFO  gr.primer.server.fm.WatchDir.register(41) - register: C:\test_data_emron_full\MAIN_DIR\New folder

     2020-08-13 11:07:47.778 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_DELETE|||C:\test_data_emron_full\MAIN_DIR\readme_file_test - Copy.txt|||89758437602500
     2020-08-13 11:07:47.778 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_CREATE|||C:\test_data_emron_full\MAIN_DIR\New folder\readme_file_test - Copy.txt|||89758438129200
     2020-08-13 11:07:47.779 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_MODIFY|||C:\test_data_emron_full\MAIN_DIR\New folder|||89758438495900

     2020-08-13 11:09:02.970 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_DELETE|||C:\test_data_emron_full\MAIN_DIR\New folder\readme_file_test - Copy.txt|||89833629946200
     2020-08-13 11:09:02.971 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_CREATE|||C:\test_data_emron_full\MAIN_DIR\readme_file_test - Copy.txt|||89833630678600
     2020-08-13 11:09:02.974 [main] INFO  gr.primer.server.fm.WatchDir.processEvents(126) - PRIMER_FILE_CHANGE_EVENT|||ENTRY_MODIFY|||C:\test_data_emron_full\MAIN_DIR\New folder|||89833633337000

     */
    public static String baseDir =  "C:/test_data_emron_full/MAIN_DIR";
    private static final Logger logger = LoggerFactory.getLogger(WatchDir.class);
    private final WatchService watcher;
    private final Map<WatchKey,Path> keys;
    private final boolean recursive;
    private boolean trace = false;


    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

    /**
     * Register the given directory with the WatchService
     */
    private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                logger.info("register: {}\n", dir);
            } else {
                if (!dir.equals(prev)) {
                    logger.info("update: {} -> {} \n", prev, dir);
                }
            }
        }
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    public  WatchDir(Path dir, boolean recursive) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;

        if (recursive) {
            logger.info("Scanning {} ...\n", dir);
            registerAll(dir);
            logger.info("Done.");
        } else {
            register(dir);
        }

        // enable trace after initial registration
        this.trace = true;
    }


    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                logger.error("WatchKey not recognized!!   dir == null ");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    logger.warn("OVERFLOW kind  TODO  ***  ");
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);



                // print out event   write to file
                logger.info( ConstantsServer.PRIMER_FILE_CHANGE_EVENT+ "|||{}|||{}|||{}\n", event.kind().name(), child,  System.nanoTime() );


                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                        logger.error(" Error ::   ",  x );

                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    static void usage() {
        logger.error("usage: java WatchDir [-r] dir   exit true ");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
//        // parse arguments
//        if (args.length == 0 || args.length > 2)
//            usage();
//        boolean recursive = false;
//        int dirArg = 0;
//        if (args[0].equals("-r")) {
//            if (args.length < 2)
//                usage();
//            recursive = true;
//            dirArg++;
//        }
//
//        // register directory and process its events
//        Path dir = Paths.get(args[dirArg]);
//
//

        Path dir = Paths.get(baseDir);
        logger.info( "WatchDir ==   "+  dir.toAbsolutePath() );
        new WatchDir(dir, true).processEvents();

//        new WatchDir(dir, recursive).processEvents();

    }



}
