package gr.boomer.fm.fmdao;


import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;

public class RootFilesDao {
    private Jdbi jdbi = Jdbi.create("");
    public static String __root__tag = "/root/";
    public List<String> getFiles(int companyId) {
        String sb = "select" +
                "  CONCAT(ctype.company_file_type_path , cf.company_file_name)   " +
                " from" +
                " crm_company_file cf," +
                " crm_company_file_record rec," +
                " crm_company_file_record_category cat," +
                " crm_company_file_record_subcategory subcat," +
                " crm_company_file_type ctype" +
                " where" +
                " cf.company_id = " + companyId+" and" +
                " rec.company_id = "+ companyId +" and" +
//                " rec.state = 1 and" +
                " cf.company_file_record_id = rec.company_file_record_id and" +
                " cf.company_file_type_id = ctype.company_file_type_id and" +
                " rec.company_file_record_category_id = cat.company_file_record_category_id and" +
                " rec.company_file_record_subcategory_id = subcat.company_file_record_subcategory_id";
//        List<String> results = jdbi.withHandle(handle ->
//                handle.createQuery(sb)
//                        .mapTo(String.class)
//                        .list());
//
//        List<String> ret = new ArrayList<>();
//        // postProcessing
//        // from this  {"name":"ΦΑΚΕΛΟΣ ΣΥΣΤΑΣΗΣ","path":"1. ΧΑΡΤΙΑ ΙΔΡΥΣΗΣ/ΦΑΚΕΛΟΣ ΣΥΣΤΑΣΗΣ","children":null}ΥΠΕΥΘΥΝΗ ΔΗΛΩΣΗ ΓΙΑ ΒΕΒΑΙΩΣΗ ΑΠΟΔ.ΑΦΜ.jpg
//        // to this =>   1. ΧΑΡΤΙΑ ΙΔΡΥΣΗΣ/ΦΑΚΕΛΟΣ ΣΥΣΤΑΣΗΣ/ΥΠΕΥΘΥΝΗ ΔΗΛΩΣΗ ΓΙΑ ΒΕΒΑΙΩΣΗ ΑΠΟΔ.ΑΦΜ.jpg
//
//        if (results != null && !results.isEmpty()) {
//            for (String strLineText : results) {
//
//                try {
//                    int begin = strLineText.indexOf("{");
//                    int end = strLineText.indexOf("}", begin);
//                    String json = strLineText.substring(begin, end + 1);
//                    String fileText = strLineText.substring(end + 1);
//                    Gson gson = new GsonBuilder().create();
//                    RootFolderPathObject obj = gson.fromJson(json, RootFolderPathObject.class);
//                    ret.add("/root/" + FMInputParams.COMPANY_DIR_NAME + "/" + obj.getPath() + "/" + fileText);
//                } catch (Exception ex){
//                    continue;
//                }
//            }
//        }

        return new ArrayList<>();
    }

}
