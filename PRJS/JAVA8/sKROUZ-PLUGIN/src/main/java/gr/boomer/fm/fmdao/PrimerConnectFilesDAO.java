package gr.boomer.fm.fmdao;

import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;

public class PrimerConnectFilesDAO {

    private Jdbi jdbi = Jdbi.create(" todo ");

    public List<String> getFiles() {
        return new ArrayList<>();
    }
//        jdbi.withHandle(handle ->
//                handle.createQuery("select CONCAT(path, name)  from primer_connect_file")
//                        .mapTo(String.class)
//                        .list());
//    }


    public boolean exists(String in) {
//        String sql =
//                "select name from ( select CONCAT(path, name) as name from primer_connect_file) r "+
//                " where r.name like '%"+in+"%'";
//        Optional<String> ret= jdbi.withHandle(handle ->
//                handle.createQuery(sql)
//                        .mapTo(String.class)
//                        .findOne());
//
//        return ret.isPresent();
        return false;
    }


}
