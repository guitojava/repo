package gr.boomer.fm.checkandfix.dirs;

import gr.boomer.fm.checkandfix.FMInputParams;
import gr.boomer.fm.checkandfix.FMOutputParams;
import gr.boomer.fm.checkandfix.HelperCheckAndFix;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ProcessDirWithYearsBranches {

    public static void loadValidSubDirsForYearForBranch() {
        List<String> validDirIgnoreList = HelperCheckAndFix.loadTextFileLinesIntoStringList("validDirIgnoreList.txt");
        validDirIgnoreList.removeIf(String::isEmpty);
        FMOutputParams.validDirIgnoreList = validDirIgnoreList;
        for (String year : FMInputParams.ALL_YEAR_LIST) {
            List<String> validDirsList = HelperCheckAndFix.loadTextFileLinesIntoStringList("validDir.txt");
            validDirsList.removeIf(String::isEmpty);
            String key = year.replaceAll("/", "");
            validDirsList.replaceAll(t -> t.replace(FMInputParams.__YEAR_TAG__, key));
            FMOutputParams.validDirsMapOfStringList.put(key, validDirsList);
        }
        // use previous create results and add to them
        for (Map.Entry<String, List<String>> entry : FMOutputParams.validDirsMapOfStringList.entrySet()) {
            String key = entry.getKey();
//            List<String> validDirsList = entry.getValue();
            for (String branch : FMInputParams.BRANCH_LIST) {
                List<String> validDirWithBranchList = HelperCheckAndFix.loadTextFileLinesIntoStringList("validDirsWthBranch.txt");
                validDirWithBranchList.replaceAll(t -> t.replace(FMInputParams.__BRANCH__, branch));
                validDirWithBranchList.replaceAll(t -> t.replace(FMInputParams.__YEAR_TAG__, key));
                entry.getValue().addAll(validDirWithBranchList);
            }
        }
//        System.out.println("FMOutputParams.validDirsMapOfStringList  size: " + FMOutputParams.validDirsMapOfStringList.size());
    }




    ///////////
    ////////////MissingDirsWithYears
    /////////////
    public static void findMissingDirsWithYears() {
        ArrayList<String> MissingDirsResults = findMissingDirsWithListWithYears(FMOutputParams.sysDirsResults, FMOutputParams.validDirsMapOfStringList);
        HelperCheckAndFix.removeAsWeMustApplyIgnoreList(MissingDirsResults, FMOutputParams.validDirIgnoreList);
        FMOutputParams.missingSysDirsResults4 = MissingDirsResults;
        normaliseMissingDirsResultsWithYears();
    }
    private  static ArrayList<String> findMissingDirsWithListWithYears(Map<String, String> realSysDirs, Map<String, List<String>> validDir) {
        System.out.println("do FindMissingDirsWithList ===== |||  ===== Start ");
        ArrayList<String> retList = new ArrayList<>();
        //loop for each validDir and find it on the disk
        for (Map.Entry<String, List<String>> validDirEntry : validDir.entrySet()) {
            for (String fPath : validDirEntry.getValue()) {
                if (fPath.endsWith("/")) {
                    fPath = StringUtils.chop(fPath);
                }
                fPath = "/" + validDirEntry.getKey() + "/" + FMInputParams.COMPANY_DIR_NAME + fPath;
                if (!realSysDirs.containsKey(fPath)
//                        && !fPath.contains("12. Primer Connect ()")
//                        && !fPath.contains("12. Primer Connect (root)")
//                        && !fPath.startsWith("//")  //  does NOT start with /root/
                        && !fPath.startsWith("/root/")  //  does NOT start with /root/

                ) {
                    retList.add(fPath);
                }
            }
        }
        System.out.println("do FindMissingDirsWithList ===== |||  ===== END ");
        return retList;
    }
    private static void normaliseMissingDirsResultsWithYears() {
        //         remove asxeta results .e.g. year not in ALL_YEAR_LIST
        System.out.println("\n==============>  normaliseMissingDirsResults  ");
        Iterator<String> iterator = FMOutputParams.missingSysDirsResults4.iterator();
        while (iterator.hasNext()) {
            String res = iterator.next();
            boolean valid = false;
            for (String year : FMInputParams.ALL_YEAR_LIST) {
                if (res.startsWith(year)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                iterator.remove();
            }
        }
    }
    ///////////
    ////////////InvalidDirs
    /////////////
    public static void findInvalidDirs() {
        removeFirstLevel();
        FMOutputParams.sysDirsResults = HelperCheckAndFix
                .filterMapForCheckDirForYears(FMOutputParams.dirsDataSet,
                        FMInputParams.ALL_YEAR_LIST,
                        FMInputParams.COMPANY_DIR_NAME);
        ArrayList<String> finalInvalidDirs = filterOutIfNumericDir();
        HelperCheckAndFix.removeAsWeMustApplyIgnoreList(finalInvalidDirs, FMOutputParams.validDirIgnoreList);
        FMOutputParams.invalidSysDirsResults3 = finalInvalidDirs;
        normaliseInvalidDirsResults();// for years
//        normaliseInvalidDirsResultsExtra();// for 99. Unsorted and   7. LoipA ARXEIA
    }

    private static void removeFirstLevel() {
        //remove
        // here we remove the first level from the results as we  know it is mandatory dir
        // e.g     key, value      /2019/EMRON PRIVATE COMPANY - ΜΠΟΝΕΣ ΘΩΜΑΣ  [DIR]
        assert FMOutputParams.dirsDataSet != null;
        Iterator<Map.Entry<String, String>> iter = FMOutputParams.dirsDataSet.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            String val = entry.getValue();
            String key = entry.getKey();
            long cnt = key.chars().filter(ch -> ch == '/').count();
            if (cnt == 2) {
                iter.remove();
            }
        }
    }

    private  static ArrayList<String> findInvalidDirs(Map<String, String> inputMap, HashMap<String, List<String>> validDirsMap) {
        System.out.println("do findInvalidDirs   ===== Start ");
        ArrayList<String> invalidPaths = new ArrayList<>();
        for (Map.Entry<String, String> entry : inputMap.entrySet()) {
            String key = entry.getKey();
            boolean found = isValidSystemPath(key, validDirsMap);
            if (!found) {
                invalidPaths.add(key);  // found one
            }
        }
        System.out.println("do findInvalidDirs   ===== end  ");
        return invalidPaths;
    }

    private static boolean isValidSystemPath(String path, HashMap<String, List<String>> validDirsMap) {
        if (path.charAt(path.length() - 1) == '/') {
            path = StringUtils.chop(path);
        }
        for (Map.Entry<String, List<String>> entry : validDirsMap.entrySet()) {
            for (String validPath : entry.getValue()) {
                if (validPath == null || validPath.isEmpty()) {
                    continue;
                }
                // chop off    last char if a /
                if (validPath.charAt(validPath.length() - 1) == '/') {
                    validPath = StringUtils.chop(validPath);
                }
                String matchToString = "/" + entry.getKey() + "/" + FMInputParams.COMPANY_DIR_NAME + validPath;
                if (path.equals(matchToString)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void normaliseInvalidDirsResults() {
        // remove asxeta results .e.g. year not in ALL_YEAR_LIST
        System.out.println("\n==============>  normaliseINVALIDDirsResults   ");
        Iterator<String> iterator = FMOutputParams.invalidSysDirsResults3.iterator();
        while (iterator.hasNext()) {
            String res = iterator.next();
            boolean valid = false;
            for (String year : FMInputParams.ALL_YEAR_LIST) {
                if (res.startsWith(year)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                iterator.remove();
            }
        }
    }

    private static ArrayList<String> filterOutIfNumericDir() {
        ArrayList<String> invalidDirsList = findInvalidDirs(FMOutputParams.sysDirsResults, FMOutputParams.validDirsMapOfStringList);
        ArrayList<String> finalInvalidDirs = new ArrayList<>();
        for (String t : invalidDirsList) {
            int idx = t.lastIndexOf("/");
            String gv = t.substring(idx, t.length() - 1);
            gv = gv.replace("/", "");
            if (!HelperCheckAndFix.isNumeric(gv)) {
                finalInvalidDirs.add(t);
            }
        }
        return finalInvalidDirs;
    }
}
