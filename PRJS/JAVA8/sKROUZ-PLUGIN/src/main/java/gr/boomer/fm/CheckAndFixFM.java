package gr.boomer.fm;

import gr.boomer.fm.checkandfix.ConstantsServer;
import gr.boomer.fm.checkandfix.FMInputParams;
import gr.boomer.fm.checkandfix.FMOutputParams;
import gr.boomer.fm.checkandfix.dirs.ProcessDirs;
import gr.boomer.fm.checkandfix.files.ProcessFiles;
import gr.boomer.fm.fmdao.RootFilesDao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CheckAndFixFM {

    public static boolean run_ProcessFiles = true;
    public static boolean run_ProcessDirs = true;
    private static FileWriter systemFolderOutputFileWriter;

    public static void main(String[] args) {

        ConstantsServer.loadConstants(true);

        // SET your InputParams  <<< =========
        FMInputParams.SYS_DIR_INPUT = ConstantsServer.DROPBOX_PATH;
        FMInputParams.COMPANY_DIR_NAME = "EMRON PRIVATE COMPANY - ΜΠΟΝΕΣ ΘΩΜΑΣ";
        FMInputParams.ALL_YEAR_LIST.add("/root/");
        FMInputParams.ALL_YEAR_LIST.add("/2019/");
        FMInputParams.BRANCH_LIST.add("Κεντρικό");
        FMInputParams.BRANCH_LIST.add("Υποκατάστημα");
        FMInputParams.companyId = 165;

        FMInputParams.setDefaultValuesAndCheckInputsHaveBeenSet();

        int out = run();

        if (out == 0) {
            System.out.println(FMOutputParams.getOutput());
//             FMOutputParams.primerConnectFilesNotFoundOnDiskResults
//             FMOutputParams.primerConnectSysFileNotFoundInDBResults);
//             FMOutputParams.invalidSysDirsResults3);
//             FMOutputParams.missingSysDirsResults4);
        } else {
            System.out.println("ERROR code == " + out);
        }


        System.exit(out);

    }

    public static int run() {
        FMOutputParams.setDefaultValues();//clear old results if any

        if (FMInputParams.SYS_DIR_INPUT.isEmpty()) {
            System.out.println("FMInputParams.SYS_DIR_INPUT   is null or empty fail fast ");
            return -1;
        }

        if (FMInputParams.ALL_YEAR_LIST.isEmpty()) {
            System.out.println("FMInputParams.ALL_YEAR_LIST   is null or empty fail fast ");
            return -2;
        }

        if (FMInputParams.BRANCH_LIST.isEmpty()) {
            System.out.println("FMInputParams.BRANCH_LIST   is null or empty fail fast ");
            return -3;
        }

        if (FMInputParams.companyId == null) {
            System.out.println("FMInputParams.companyId   is null or empty fail fast ");
            return (-4);
        }

        if (FMInputParams.COMPANY_DIR_NAME == null || FMInputParams.COMPANY_DIR_NAME.isEmpty()) {
            System.out.println("FMInputParams.COMPANY_NAME   is null or empty fail fast ");
            return (-5);
        }

        // Run Process
        FMOutputParams.startRecordingOutput();
        println3("====== FILTERS ", "Year list   =" + FMInputParams.ALL_YEAR_LIST, "COMPANY_NAME=" + FMInputParams.COMPANY_DIR_NAME);
        execute();
        FMOutputParams.stopRecordingOutput();

        // END Process
        return 0;// ok
    }

    public static void execute() {
        long start = System.currentTimeMillis();
        // created initial data files
        createRootOutputFile(FMInputParams.rootOutputFile);
        createPrimerConnectOutputFile(FMInputParams.primerConnectOutputFile);
        createSystemFolderOutputFile();// includes FILES and DIRS
        if (run_ProcessFiles) ProcessFiles.processFiles();
        if (run_ProcessDirs) ProcessDirs.processDirs();
        long end = System.currentTimeMillis();
        long milliseconds = end - start;
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        PrintEndReport(milliseconds, minutes, seconds);
        System.out.println("CheckFiles ... THIS IS THE END    ");
    }

    public static void println3(String s, String s2, String s3) {
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s3);
    }

    public static void PrintEndReport(long milliseconds, long minutes, long seconds) {
        println3("===============================", "filesCount: ", " dirsCount: ");
        System.out.println("Time elapsed: " + milliseconds + " mills       " + minutes + ":" + seconds + "   mm:ss");
        System.out.println("\n===============================");
        System.out.println("FINISH/END...");
    }

    private static void createSystemFolderOutputFile() {

        try {
            File myObj = new File(FMInputParams.systemFolderOutputFile);
            myObj.delete();
            myObj.createNewFile();
            systemFolderOutputFileWriter = new FileWriter(FMInputParams.systemFolderOutputFile);
            System.out.println("OPEN resultsFile ::=  " + FMInputParams.systemFolderOutputFile + "     " + LocalDateTime.now().format(FMInputParams.formatter));
        } catch (IOException ioException) {
            try {
                systemFolderOutputFileWriter.close();
                systemFolderOutputFileWriter = null;
            } catch (IOException e) {
                systemFolderOutputFileWriter = null;
            }
            System.out.println("Abort as I  Can't  get a writer to write filesFrom OS DISk " + FMInputParams.ERROR);
            return;
        }
        System.out.println("Scanning  " + FMInputParams.SYS_DIR_INPUT);
        if (FMInputParams.SYS_DIR_INPUT == null || FMInputParams.SYS_DIR_INPUT.isEmpty()) {
            System.out.println("Abort as I  don't have a start folder sysStartFolder  " + FMInputParams.ERROR);
            return;
        }
        File maindir = new File(FMInputParams.SYS_DIR_INPUT);  // Provide full path for directory(change accordingly)
        if (maindir.exists() && maindir.isDirectory()) {
            // array for files and sub-directories
            File[] arr = maindir.listFiles();
            // Calling recursive method
            RecursivePrint(arr, 0);
        }
        closeSystemFolderOutputFile();
    }

    private static void closeSystemFolderOutputFile() {
        try {
            if (systemFolderOutputFileWriter != null) {
                systemFolderOutputFileWriter.close();
                systemFolderOutputFileWriter = null;
            }
        } catch (IOException e) {
            systemFolderOutputFileWriter = null;
            System.out.println("Can't close results file " + FMInputParams.ERROR + " " + e.getMessage());
        }

    }

    private static void RecursivePrint(File[] arr, int level) {
        if (arr != null && arr.length > 0) {
            // for-each loop for main directory files
            for (File f : arr) {
                if (f != null) {
                    if (f.exists()) {
                        try {
                            if (f.isFile()) {
                                FMOutputParams.totFiles++;
                                String msg = f.getPath() + FMInputParams.FILE;
                                writeToSystemFolderOutputFile(msg);
                            } else if (f.isDirectory()) {

                                FMOutputParams.totDirs++;

                                boolean isALastDir = true;
                                for (final File fileEntry : Objects.requireNonNull(f.listFiles())) {
                                    if (fileEntry.isDirectory()) {
                                        isALastDir = false;
                                    }
                                }

                                String msg = f.toPath()
                                        + FMInputParams.DIR + FMInputParams.PATH_SEPARATOR
                                        + level + FMInputParams.PATH_SEPARATOR
                                        + isALastDir;

                                writeToSystemFolderOutputFile(msg);

                                RecursivePrint(f.listFiles(), level + 1);
                            }
                        } catch (Exception x) {
                            System.out.println("Invalid path  " + f.getPath() + " " + FMInputParams.ERROR);
                            x.printStackTrace();
                        }
                    }

                } // f not null
            }// loop
        }//params check
    } // RecursivePrint

    private static void writeToSystemFolderOutputFile(String msg) {
        try {
            msg = normaliseSysFolderLine(msg, FMInputParams.SYS_DIR_INPUT);
            systemFolderOutputFileWriter.write(msg + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Can't write to results file  msg = " + msg + " " + FMInputParams.ERROR + " " + e.getMessage());
        }
    }

    private static String normaliseSysFolderLine(String str, String sysStartFolder) {
        if (str.startsWith(sysStartFolder)) {
            str = str.substring(sysStartFolder.length());
        }
        if (!isLinux()) {
            str = str.replace("\\", "/"); // to linux
        }
        return str;
    }

    private static boolean isLinux() {
        return (File.separator.equals("/"));
    }

    /***
     ROOT FILES
     */
    private static void writeRootOutputFile(String str, FileWriter writer) {
        try {
            str = normaliseRootFile(str, FMInputParams.removePrefixFromRootBBRowData);
            writer.write(str + FMInputParams.FILE + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Can't write to results file  msg = " + str + " " + FMInputParams.ERROR + " " + e.getMessage());
        }
    }

    private static String normaliseRootFile(String str, String removePrefix) {
        if (!removePrefix.isEmpty() && str.startsWith(removePrefix)) {
            str = str.substring(removePrefix.length());
        }
        return str;
    }

    private static void createRootOutputFile(String rootOutputFile) {

        RootFilesDao rootFilesDAO = new RootFilesDao();
        List<String> rootFolderFiles = rootFilesDAO.getFiles(FMInputParams.companyId);


        if (rootFolderFiles != null & !rootFolderFiles.isEmpty()) {
            FileWriter writer = null;
            try {
                File rootFilesListFile = new File(rootOutputFile);
                rootFilesListFile.delete();
                rootFilesListFile.createNewFile();
                writer = new FileWriter(rootOutputFile);
                for (String str : rootFolderFiles) {
                    writeRootOutputFile(str, writer);
                }
                writer.close();
            } catch (IOException ioException) {
                System.out.println("Abort as I  Can't  get a writer to write root folder Files  " + FMInputParams.ERROR);
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    System.out.println("  writer.close();  " + FMInputParams.ERROR);
                }
            }
        }
    }

    private static void createPrimerConnectOutputFile(String primerConnectOutputFile) {

//        PrimerConnectFilesDAO primerConnectFilesDAO = new PrimerConnectFilesDAO();
//        List<String> primerConnectFilesList = new ArrayList<>();
//
//
//        PrimerConnectFilesDAO primerConnectFilesDAO = new PrimerConnectFilesDAO();
//        List<String> primerConnectFilesList = primerConnectFilesDAO.getFiles();
//
//        if (primerConnectFilesList != null & !primerConnectFilesList.isEmpty()) {
//            FileWriter writer = null;
//            try {
//                File primerConnectFile = new File(primerConnectOutputFile);
//                primerConnectFile.delete();
//                primerConnectFile.createNewFile();
//                writer = new FileWriter(primerConnectOutputFile);
//                for (String str : primerConnectFilesList) {
//                    writePrimerConnectOutputFile(str, writer);
//                }
//                writer.close();
//            } catch (IOException ioException) {
//                System.out.println("Abort as I  Can't  get a writer to write primerConnectFiles  " + FMInputParams.ERROR);
//            } finally {
//                try {
//                    if (writer != null) {
//                        writer.close();
//                    }
//                } catch (IOException e) {
//                    System.out.println("  writer.close();  " + FMInputParams.ERROR);
//                }
//            }
    }


    /**
     * PrimerConnectOutputFile
     */
    private static void writePrimerConnectOutputFile(String str, FileWriter writer) {
        try {
            str = normalisePrimerConnectFine(str, FMInputParams.removePrefixFromPrimerConnectDBRowData);
            writer.write(str + FMInputParams.FILE + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Can't write to results file  msg = " + str + " " + FMInputParams.ERROR + " " + e.getMessage());
        }
    }

    private static String normalisePrimerConnectFine(String str, String removePrefix) {
        if (!removePrefix.isEmpty()
                && str.startsWith(removePrefix)) {
            str = str.substring(removePrefix.length());
        }
        return str;
    }
}

