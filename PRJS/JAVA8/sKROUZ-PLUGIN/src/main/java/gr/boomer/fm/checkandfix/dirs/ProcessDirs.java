package gr.boomer.fm.checkandfix.dirs;

import gr.boomer.fm.checkandfix.FMInputParams;
import gr.boomer.fm.checkandfix.FMOutputParams;
import gr.boomer.fm.checkandfix.HelperCheckAndFix;

import java.util.HashMap;
import java.util.Map;

/**
 * Finds inValid Dirs and Missing dirs
 **/
public class ProcessDirs {
    public static void processDirs() {
        try {
            calcRootDirs();
            calcYearFolders();
        } catch (RuntimeException re) {
            System.out.println("\n==============>  ERROR in processDirs " + re.getMessage());
        }
    }

    private static void calcRootDirs() {
        /////////////
        // Step 1 - root dirs
        ////////////
        // deal with root dirs only

        ProcessRootDir.loadValidDirsForRoot();
        FMOutputParams.rootDirsDataSet = HelperCheckAndFix.getHashMapDIR(FMInputParams.systemFolderOutputFile);
        assert FMOutputParams.rootDirsDataSet != null;
        Map<String, String> root = HelperCheckAndFix.filterKeyContainsString(FMOutputParams.rootDirsDataSet, "/root/");
        FMOutputParams.rootDirsDataSet = new HashMap<>(root);
        ProcessRootDir.findInvalidDirsForRootDir();
        ProcessRootDir.findMissingDirsForRootDir();
    }

    private static void calcYearFolders() {
        //////////////
        // step 2 - year folders
        //////////////
        // remove root dirs proceesed in previous step
        ProcessDirWithYearsBranches.loadValidSubDirsForYearForBranch();
        FMOutputParams.dirsDataSet = HelperCheckAndFix.getHashMapDIR(FMInputParams.systemFolderOutputFile);
//            assert FMOutputParams.dirsDataSet != null;
//            Map<String, String> temp = HelperCheckAndFix.filterValueNOTContainsString(FMOutputParams.dirsDataSet, "/root/");
//            FMOutputParams.dirsDataSet = new HashMap<>(temp);
        ProcessDirWithYearsBranches.findInvalidDirs();
        ProcessDirWithYearsBranches.findMissingDirsWithYears();
        HelperCheckAndFix.printOutputForInvalid_MissingDirs();
    }
}
