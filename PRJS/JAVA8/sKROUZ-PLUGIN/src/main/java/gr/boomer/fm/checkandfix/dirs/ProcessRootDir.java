package gr.boomer.fm.checkandfix.dirs;

import gr.boomer.fm.checkandfix.FMInputParams;
import gr.boomer.fm.checkandfix.FMOutputParams;
import gr.boomer.fm.checkandfix.HelperCheckAndFix;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ProcessRootDir {

    public static void loadValidDirsForRoot() {
        List<String> validDirIgnoreList = HelperCheckAndFix.loadTextFileLinesIntoStringList("validDirIgnoreList_root.txt");
        validDirIgnoreList.removeIf(String::isEmpty);
        FMOutputParams.validDirIgnoreListRootDir = validDirIgnoreList;
        List<String> validDirsList = HelperCheckAndFix.loadTextFileLinesIntoStringList("validDir_root.txt");
        validDirsList.removeIf(String::isEmpty);
        for (int i = 0; i < validDirsList.size(); i++) {
            validDirsList.set(i,  validDirsList.get(i)  );
        }
        FMOutputParams.validDirsMapOfStringListForRootDir.put("root" , validDirsList);
    }
    ////////////////////////////////
    ////////  MissingDirsForRootDir
    // //////////////////////////////
    public static void findMissingDirsForRootDir() {
        ArrayList<String> MissingDirsResults = findMissingDirsForRootDir(FMOutputParams.sysDirsResultsForRoot, FMOutputParams.validDirsMapOfStringListForRootDir);
        HelperCheckAndFix.removeAsWeMustApplyIgnoreList(MissingDirsResults, FMOutputParams.validDirIgnoreListRootDir);
        FMOutputParams.missingRootDirsResults = MissingDirsResults;
        FMOutputParams.missingRootDirsResults.removeIf(res -> !res.startsWith("/root/"));
    }
    private  static ArrayList<String> findMissingDirsForRootDir(Map<String, String> realSysDirs, Map<String, List<String>> validDir) {
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
                ) {
                    retList.add(fPath);
                }
            }
        }
        System.out.println("do FindMissingDirsWithList ===== |||  ===== END ");
        return retList;
    }




    ////////////////////////////////
    // invalid dirs for root folder
    ///////////////////////////////
    public static void findInvalidDirsForRootDir() {
        FMOutputParams.sysDirsResultsForRoot = HelperCheckAndFix.filterMapForCheckDirForRoots(FMOutputParams.rootDirsDataSet,FMInputParams.COMPANY_DIR_NAME);
        removeFirstLevel();
        ArrayList<String> finalInvalidDirs = filterOutIfNumericDir();
        HelperCheckAndFix.removeAsWeMustApplyIgnoreList(finalInvalidDirs, FMOutputParams.validDirIgnoreListRootDir);
        FMOutputParams. invalidRootDirsResults = finalInvalidDirs;
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
    private static ArrayList<String> filterOutIfNumericDir() {
        ArrayList<String> invalidDirsList = findInvalidDirs(FMOutputParams.sysDirsResultsForRoot, FMOutputParams.validDirsMapOfStringListForRootDir);
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
    private static void removeFirstLevel() {
        //remove
        // here we remove the first level from the results as we  know it is mandatory dir
        // e.g     key, value      /root/EMRON PRIVATE COMPANY - ΜΠΟΝΕΣ ΘΩΜΑΣ  [DIR]
        assert FMOutputParams.sysDirsResultsForRoot != null;
        Iterator<Map.Entry<String, String>> iter = FMOutputParams.sysDirsResultsForRoot.entrySet().iterator();
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
}
