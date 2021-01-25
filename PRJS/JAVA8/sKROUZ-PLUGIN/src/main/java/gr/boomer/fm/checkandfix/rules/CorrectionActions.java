package gr.boomer.fm.checkandfix.rules;

import java.util.ArrayList;

public class CorrectionActions {

    private static void RunCorrectionActionsForSysFolder(ArrayList<String> invalidSysDirs) {
        boolean doSysDIR = true; // need to be  global param
        if (doSysDIR) {
            for (String dir : invalidSysDirs) {
                // todo implement method  fireCorrectAnInvalidSysDir(  dir );
                //todo  fireCorrectAnInvalidSysDir(  dir );  // this takes an invalid dir and does a coeectionAction e.g. delete it ???
                System.out.println("fireCorrectAnInvalidSysDir for  e.g. DELETE dir =  " + dir);
            }
        }
    }


    private static void RunCorrectionActionsForSysFiles() {
        boolean doRuleSysFiles = true; // need to be  global param
        if (doRuleSysFiles) { // todo implement method
            System.out.println("RunCorrectionActionsForSysFiles   ");
        }
    }


    private static void RunCorrectionActionsForPrimerConnectDatabaseTable() {
        boolean doRulePCF = true; // need to be  global param
        if (doRulePCF) { //todo implement method
            System.out.println("RunCorrectionActionsForPrimerConnectDatabaseTable   ");
        }

    }

//  System.out.println("debug ");
//        if (!invalidDirsList.isEmpty()) {
//        // found incorrect paths / dirs in sysFolder  //printStringArrayList("INVALID folders found in SysDIR ", "  ", sysMapFoldersWithInvalidDirStructureRulesResults);
//        //todo    doAction  CorrectionAction_3   //  RunCorrectionActionsForSysFolder(sysMapFoldersWithInvalidDirStructureRulesResults);
//    } else {
//        // success  nothing to report all ok
//        System.out.println("SUCCESS  nothing to report all ok NO-INVALID folders in SysDIR  " + FMInputParams.sysStartFolder);
//    }
//




}
