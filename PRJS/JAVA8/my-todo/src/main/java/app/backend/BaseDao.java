package app.backend;

import org.jdbi.v3.core.Jdbi;

public class BaseDao {

    public final static Jdbi jdbi = JdbiConfiguration.jdbi();

}