package gr.boomer.fm.checkandfix.files;

import gr.boomer.fm.checkandfix.FMInputParams;
import gr.boomer.fm.checkandfix.FMOutputParams;
import gr.boomer.fm.checkandfix.HelperCheckAndFix;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProcessFiles {
    public static void processFiles() {
        try {
            // datasets
            FMOutputParams.filesDataSet = HelperCheckAndFix.getHashMapForFILE(FMInputParams.systemFolderOutputFile);
            FMOutputParams.pcFilesDataSet = HelperCheckAndFix.getHashMapForFILE(FMInputParams.primerConnectOutputFile);
            FMOutputParams.rootFilesDataSet = HelperCheckAndFix.getHashMapForFILE(FMInputParams.rootOutputFile);
            List<String> validDirIgnoreList = HelperCheckAndFix.loadTextFileLinesIntoStringList("validDirIgnoreList.txt");
            validDirIgnoreList.removeIf(String::isEmpty);
            FMOutputParams.validDirIgnoreList = validDirIgnoreList;// They will be ignored in Algorithm
            // FILES
            // filter data sets e.g. per YEAR and company
            Map<String, String> sysFilesResult = HelperCheckAndFix.filterMapForCheckFiles(FMOutputParams.filesDataSet, FMInputParams.ALL_YEAR_LIST, FMInputParams.COMPANY_DIR_NAME);
            Map<String, String> pcFilesResult = HelperCheckAndFix.filterMapForCheckFiles(FMOutputParams.pcFilesDataSet, FMInputParams.ALL_YEAR_LIST, FMInputParams.COMPANY_DIR_NAME);
            Map<String, String> rootFilesResult = HelperCheckAndFix.filterMapForCheckFiles(FMOutputParams.rootFilesDataSet, FMInputParams.ALL_YEAR_LIST, FMInputParams.COMPANY_DIR_NAME);
            // run crosschecks
            ArrayList<String> pcFileNotFoundOnDiskCrossCheckResults = crossCheck_2(sysFilesResult, pcFilesResult);
            ArrayList<String> sysFileNotFoundInDBCrossCheckResults = crossCheck_1(sysFilesResult, pcFilesResult);
            ArrayList<String> rootFilesNotFoundOnDiskResults = crossCheck_2(sysFilesResult, rootFilesResult);
            ArrayList<String> rootFilesNotFoundInDBResults = crossCheck_1(sysFilesResult, rootFilesResult);
            HelperCheckAndFix.removeAsWeMustApplyIgnoreList(pcFileNotFoundOnDiskCrossCheckResults, FMOutputParams.validDirIgnoreList);
            HelperCheckAndFix.removeAsWeMustApplyIgnoreList(sysFileNotFoundInDBCrossCheckResults, FMOutputParams.validDirIgnoreList);
            HelperCheckAndFix.removeAsWeMustApplyIgnoreList(rootFilesNotFoundOnDiskResults, FMOutputParams.validDirIgnoreListRootDir);
            HelperCheckAndFix.removeAsWeMustApplyIgnoreList(rootFilesNotFoundInDBResults, FMOutputParams.validDirIgnoreListRootDir);
            removeUnneededForYear(pcFileNotFoundOnDiskCrossCheckResults);
            removeUnneededForYear(sysFileNotFoundInDBCrossCheckResults);
            removeUnneededForRoot(rootFilesNotFoundOnDiskResults);
            removeUnneededForRoot(rootFilesNotFoundInDBResults);
            FMOutputParams.primerConnectFilesNotFoundOnDiskResults = pcFileNotFoundOnDiskCrossCheckResults;
            FMOutputParams.primerConnectSysFileNotFoundInDBResults = sysFileNotFoundInDBCrossCheckResults;
            FMOutputParams.rootFilesNotFoundOnDiskResults = rootFilesNotFoundOnDiskResults;
            FMOutputParams.rootSysFilesNotFoundInDBResults = rootFilesNotFoundInDBResults;
            HelperCheckAndFix.printOutputForFiles();
        } catch (RuntimeException re) {
            System.out.println("\n==============>  ERROR in processFiles ");
            re.printStackTrace();
        }
    }

    private static void removeUnneededForYear(ArrayList<String> inList) {
        // remove asxeta results .e.g. year not in ALL_YEAR_LIST
        System.out.println("\n==============>  remove asxeta results  sysFileNotFoundInDBResults2  ");
        Iterator<String> iterator = inList.iterator();
        while (iterator.hasNext()) {
            String res = iterator.next();
            boolean valid = false;
            for (String year : FMInputParams.ALL_YEAR_LIST) {
                if (!res.startsWith("/root/") && res.startsWith(year)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                iterator.remove();
            }
        }
    }

    private static void removeUnneededForRoot(ArrayList<String> inList) {
        // remove asxeta results .e.g. year not in ALL_YEAR_LIST
        System.out.println("\n==============>  remove asxeta results  sysFileNotFoundInDBResults2  ");
        inList.removeIf(res -> !res.startsWith("/root/"));

    }


    /**
     * @param sysMap data from disk
     * @param dbMap  data from database
     * @return results
     */
    public static ArrayList<String> crossCheck_1
    (Map<String, String> sysMap, Map<String, String> dbMap) {
        ArrayList<String> ret = new ArrayList<>();
        for (String sysKey : sysMap.keySet()) {
            if (!dbMap.containsKey(sysKey)) {
                ret.add(sysKey);
            }
        }
        return ret;
    }

    /**
     * @param sysMap data from disk
     * @param dbMap  data from database
     * @return results
     */
    public static ArrayList<String> crossCheck_2
    (Map<String, String> sysMap, Map<String, String> dbMap) {
        ArrayList<String> ret = new ArrayList<>();
        for (String pcKey : dbMap.keySet()) {
            if (!sysMap.containsKey(pcKey)) {
                ret.add(pcKey);
            }
        }
        return ret;
    }
}
