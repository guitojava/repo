package gr.boomer.fm.checkandfix;

import java.io.File;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FMInputParams {

    public final static String thisYear = Year.now().toString();
    // Final params
    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
    public final static String ERROR = "[ERROR]";
    public final static String FILE_SEPERATOR = File.separator;   //   / \
    public final static String PATH_SEPARATOR = File.pathSeparator; // :
    public final static String FILE = PATH_SEPARATOR + "[FILE]";
    public final static String DIR = PATH_SEPARATOR + "[DIR]";
    public final static String systemDirsOutputFile = "logs/dirs.txt";
    public final static String systemFolderOutputFile = "logs/files.txt";
    public final static String removePrefixFromPrimerConnectDBRowData = "/3. ΕΤΑΙΡΕΙΕΣ";                                                 // if found it is removed from the row data from database data
    public final static String primerConnectOutputFile = "logs/primerConnectFiles.txt";
    public final static String rootOutputFile = "logs/rootDirFiles.txt";
    public final static String removePrefixFromRootBBRowData = "";
    public final static String __YEAR_TAG__ = "__YEAR_TAG__";                                                                           //important is used in the validDir.txt
    public final static String __BRANCH__   = "__BRANCH__";                                                                             //important is used in the validDir.txt
    public final static List<String> ALL_YEAR_LIST = new ArrayList<>();
    public final static List<String> BRANCH_LIST = new ArrayList<>();
    public static String SYS_DIR_INPUT = "";// required
    public static String COMPANY_DIR_NAME = "";// and company name
    public static Integer companyId; // sys id for company
    public static void setDefaultValuesAndCheckInputsHaveBeenSet() {
        System.out.println("===========  Defaults For INPUT PARAMS  ");
        System.out.println(" Default Value PATH_SEPARATOR                             :  " + PATH_SEPARATOR);
        System.out.println(" Default Value FILE_SEPERATOR                             :  " + FILE_SEPERATOR);
        System.out.println(" Default Value removePrefixFromPrimerConnectDBRowData     :  " + removePrefixFromPrimerConnectDBRowData);
        System.out.println(" Default Value systemDirsOutputFile                       : " + systemDirsOutputFile);
        System.out.println(" Default Value systemFilesOutputFile                      : " + systemFolderOutputFile);
        System.out.println(" Default Value primerConnectOutputFile                    : " + primerConnectOutputFile);
        System.out.println(" Default Value startFolderParam   " + SYS_DIR_INPUT);
        if (companyId == null) {
            System.out.println("companyId is required ");
        }
        if (SYS_DIR_INPUT.isEmpty()) {
            System.out.println("sysStartFolder is required ");
        }

        if (ALL_YEAR_LIST.isEmpty()) {
            System.out.println("ALL_YEAR_LIST is required ");// note must be in this format  /YYYY/,/yyyy/,/2020/,/2019/,
        }

        if (COMPANY_DIR_NAME.isEmpty()) {
            System.out.println("COMPANY_NAME is required ");
        }

        System.out.println("COMPANY_NAME= " + COMPANY_DIR_NAME);
        System.out.println("companyId=    "+ companyId);
        System.out.println("YEAR_INPUT=   " + ALL_YEAR_LIST);
        System.out.println("SYS_DIR_INPUT="+ SYS_DIR_INPUT);
        System.out.println("===========  Defaults For INPUT  ");
        System.out.println("   ");
        System.out.println("   ");
    }

}
