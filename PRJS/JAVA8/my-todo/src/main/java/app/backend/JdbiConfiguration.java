package app.backend;


import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlite3.SQLitePlugin;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.sqlite.SQLiteDataSource;

import java.util.ArrayList;
import java.util.List;

public class JdbiConfiguration {

    public static Jdbi jdbi() {

        List<JdbiPlugin> jdbiPlugins = new ArrayList<>();

        jdbiPlugins.add(new SQLitePlugin());

        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:database.db");

        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);

        // Register all available plugins
        jdbiPlugins.forEach(jdbi::installPlugin);

        // Register all available rowMappers
        MapperConfiguration.rowMappers().forEach(jdbi::registerRowMapper);

        return jdbi;
    }


}
